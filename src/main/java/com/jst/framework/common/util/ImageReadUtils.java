package com.jst.framework.common.util;

import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.ImageIcon;

import org.apache.log4j.Logger;

/**
 * 图片读取工具
 * @author lixiangjing
 */
public class ImageReadUtils {
	
	static Logger log = Logger.getLogger(ImageReadUtils.class);
	
	public static BufferedImage readImage(byte[] oriByte) throws IOException {
		return fixCmyk(oriByte);
		/*if(isCmyk(oriByte)){
			return fixCmyk(oriByte);
		}else{
			return fixRed(oriByte);
		}*/
	}
	
	/**
	 * 判断图片cmyk格式
	 * @param oriByte
	 * @return
	 */
	public static boolean isCmyk(byte[] oriByte){
		try {
			ByteArrayInputStream input = null;
			try {
				input = new ByteArrayInputStream(oriByte);
				ImageIO.read(input);
			} finally {
				if(input!=null){
					input.close();
				}
			}
		} catch (IOException e) {
			return true;
		}
		return false;
	}
	
	/**
	 * 修复cmyk格式问题
	 * @See http://blog.csdn.net/u011677147/article/details/48785559
	 */
	private static BufferedImage fixCmyk(byte[] oriByte) throws IOException {
		ByteArrayInputStream file = null;
		ImageInputStream input = null;
		BufferedImage image;
		try {
			file = new ByteArrayInputStream(oriByte);
			input = ImageIO.createImageInputStream(file);
		
			Iterator readers = ImageIO.getImageReaders(input);
			if (readers == null || !readers.hasNext()) {
				throw new RuntimeException("1 No ImageReaders found");
			}
			ImageReader reader = (ImageReader) readers.next();
		
			reader.setInput(input);
	
			try {
				// 尝试读取图片 (包括颜色的转换).
				image = reader.read(0); // RGB
			} catch (IIOException e) {
				System.out.println("错误："+e);
				// 读取Raster (没有颜色的转换).
				Raster raster = reader.readRaster(0, null);// CMYK
				image = createJPEG4(raster);
			}
		} finally {
			
			if(input!=null){
				input.close();
			}
			
			if(file!=null){
				file.close();
			}
		}
		return image;
	}
	

	/**
	 * 修复图片变红问题
	 * @See http://www.oschina.net/question/935351_241212?fromerr=RXdFiEd1
	 * @param imagedata
	 * @return
	 */
	private static BufferedImage fixRed(byte[] imagedata) {
        Image image = Toolkit.getDefaultToolkit().createImage(imagedata);
        if (image instanceof BufferedImage) {
            return (BufferedImage) image;
        }
        image = new ImageIcon(image).getImage();
        BufferedImage bimage = null;
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Graphics g = null;
        try {
            int transparency = Transparency.OPAQUE;
            GraphicsDevice gs = ge.getDefaultScreenDevice();
            GraphicsConfiguration gc = gs.getDefaultConfiguration();
            bimage = gc.createCompatibleImage(image.getWidth(null), image.getHeight(null), transparency);
            if (bimage == null) {
                int type = BufferedImage.TYPE_INT_RGB;
                bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), type);
            }
            g = bimage.createGraphics();
            g.drawImage(image, 0, 0, null);
            
        } catch (HeadlessException e) {
        	log.error("图片错误：" + e);
        } finally {
        	if(g != null){
        		g.dispose();
        	}
        	
        }

        return bimage;
    }

	/**
	 * CMYK模式转换RGB
	 * @param raster
	 * @return
	 */
	private static BufferedImage createJPEG4(Raster raster) {
		int w = raster.getWidth();
		int h = raster.getHeight();
		byte[] rgb = new byte[w * h * 3];
		// 彩色空间转换
		float[] Y = raster.getSamples(0, 0, w, h, 0, (float[]) null);
		float[] Cb = raster.getSamples(0, 0, w, h, 1, (float[]) null);
		float[] Cr = raster.getSamples(0, 0, w, h, 2, (float[]) null);
		float[] K = raster.getSamples(0, 0, w, h, 3, (float[]) null);
		for (int i = 0, imax = Y.length, base = 0; i < imax; i++, base += 3) {
			float k = 220 - K[i], y = 255 - Y[i], cb = 255 - Cb[i], cr = 255 - Cr[i];

			double val = y + 1.402 * (cr - 128) - k;
			val = (val - 128) * .65f + 128;
			rgb[base] = val < 0.0 ? (byte) 0 : val > 255.0 ? (byte) 0xff
					: (byte) (val + 0.5);

			val = y - 0.34414 * (cb - 128) - 0.71414 * (cr - 128) - k;
			val = (val - 128) * .65f + 128;
			rgb[base + 1] = val < 0.0 ? (byte) 0 : val > 255.0 ? (byte) 0xff
					: (byte) (val + 0.5);

			val = y + 1.772 * (cb - 128) - k;
			val = (val - 128) * .65f + 128;
			rgb[base + 2] = val < 0.0 ? (byte) 0 : val > 255.0 ? (byte) 0xff
					: (byte) (val + 0.5);
		}
		raster = Raster.createInterleavedRaster(new DataBufferByte(rgb,
				rgb.length), w, h, w * 3, 3, new int[] { 0, 1, 2 }, null);
		ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_sRGB);
		ColorModel cm = new ComponentColorModel(cs, false, true,
				Transparency.OPAQUE, DataBuffer.TYPE_BYTE);
		return new BufferedImage(cm, (WritableRaster) raster, true, null);
	}

}
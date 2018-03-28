package com.jst.framework.common.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;


/**
 * 压缩图片处理工具类
 * 
 * @author lixiangjing
 */
public class ThumbnailUtils {
	
	public static enum Shape {
		oblong(1), square(0);
		private int value;

		private Shape(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}
	
	/**
	 * chicuu图片裁切
	 * 
	 * @param oriByte
	 * @param width
	 * @param height
	 * @return
	 * @throws IOException
	 */
	public static byte[] clip4chicuu(byte[] oriByte, Shape shape)
			throws IOException {
		ByteArrayOutputStream out = null;
		ByteArrayInputStream input = null;
		try {
			BufferedImage bimg = ImageReadUtils.readImage(oriByte);

			int w = bimg.getWidth();
			int h = bimg.getHeight();
			// 找到最大值然后补白
			int max = Math.max(w, h);

			byte[] white = getWhiteImage(max, max);

			out = new ByteArrayOutputStream();
			// 最终宽度
			int finalWidth;
			if (Shape.oblong == shape) {
				// 最终宽度
				finalWidth = (int) (0.728 * max);
			} else {
				finalWidth = max;
			}
			
			input = new ByteArrayInputStream(white);

			Thumbnails.of(input).watermark(Positions.CENTER, bimg, 1f).forceSize(finalWidth, max).toOutputStream(out);
		} finally {
			if(input!=null){
				input.close();
			}
			if(out!=null){
				out.close();
			}
		}
		return out.toByteArray();
	}

	/**
	 * 压缩图片
	 * 
	 * @param oriByte
	 * @param width
	 * @param height
	 * @param imagetype 图片类型jpg..
	 * @return
	 * @throws IOException
	 */
	public static byte[] resize(byte[] oriByte, int width, int height, String imagetype)
			throws IOException {
		ByteArrayOutputStream out = null;
		try {
			out = new ByteArrayOutputStream();
			if (width == 2000) {
				Thumbnails.of(ImageReadUtils.readImage(oriByte)).outputFormat(imagetype).outputQuality(0.9).scale(1).toOutputStream(out);
				return out.toByteArray();
			}
			//Thumbnails.of(ImageReadUtils.readImage(oriByte)).scale(1f).outputFormat(imagetype).toOutputStream(out);//按比例缩小  
			Thumbnails.of(ImageReadUtils.readImage(oriByte)).outputQuality(1.0).size(width, height).outputFormat(imagetype).toOutputStream(out);
		} finally {
			if(out!=null){
				out.close();
			}
		}
		return out.toByteArray();
	}

	/**
	 * 生成一张w x h规格的白底图片
	 * 
	 * @param w
	 * @param h
	 * @return
	 * @throws IOException
	 */
	private static byte[] getWhiteImage(int w, int h) throws IOException {

		BufferedImage b_img = new BufferedImage(w, h,BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics = b_img.createGraphics();

		graphics.setPaint(new Color(255, 255, 255));
		graphics.fillRect(0, 0, b_img.getWidth(), b_img.getHeight());

		ByteArrayOutputStream byteOut = null;
		try {
			byteOut = new ByteArrayOutputStream();
			ImageIO.write(b_img, "jpg", byteOut);
		} finally {
			if(byteOut!=null){
				byteOut.close();
			}
		}
		byte[] bytes = byteOut.toByteArray();
		return bytes;
	}
	
}

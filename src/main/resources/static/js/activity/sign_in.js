jQuery(function($) {
	totalUser();
	receiveUser();
	$("#result_info").hide();
	$("#successTip").hide();
	$("#failedTip").hide();
	$("#greenBtn").hide();
	$("#greyBtn").hide();
	$("#noFound").hide();
	$("#reget").hide();
	$("#status_cell").hide();

	$('#handGiftCode').keydown(function(e){
		if(e.keyCode==13){
			setTimeout(receive, 200);
		}
		});




	function totalUser(){
		$.ajax({
			type:"POST",
			contentType: "application/json",
			data:JSON.stringify({
			}),
			url:'/demo/sign/queryUserNum',
			datatype:"JSON",
			success:function (result){
				$('#totalUser').html(result.data);
			},
			error:function(result){}
	});
	}
	
	
	function receiveUser(){
		$.ajax({
			type:"POST",
			contentType: "application/json",
			data:JSON.stringify({
			}),
			url:'/demo/sign/queryReceiveNum',
			datatype:"JSON",
			success:function (result){
				$('#receiveUser').html('<span class="first-cell">'+result.data+'</span>');
			},
			error:function(result){}
	});
	}
	




	

//领取操作	
	$("#receive").on("click",function(){
		var giftCode = $("#handGiftCode").val();
		$.ajax({
			async: false,
			type:"POST",
			contentType: "application/json",
			data:JSON.stringify({
				'giftCode':giftCode
			}),
			url:'/demo/sign/updateUserData',
			datatype:"JSON",
			success:function (result){
				$('#status').html('<span class="first-cell">'+result.data.osResult+'</span>');
				if(result.data.osResult=="未领取"||result.data.osResult=="领取失败，请核实礼品码"){
					$("#re_btn").show();
				}
				else {
					$("#re_btn").hide();
			}
			},
			error:function(result){}
	});
	});
	
	$("#handGiftCode").bind("input", function(){
		if($.trim(document.getElementById("handGiftCode").value).length==4){
			submit();
		}else if($.trim(document.getElementById("handGiftCode").value).length==5){
			var b = document.getElementById("handGiftCode").value;
			document.getElementById("handGiftCode").value = b.substring(1);
			submit();
		}
	});
	
function receive(){
		var giftCode = document.getElementById("handGiftCode").value;
		$.ajax({
			async: false,
			type:"POST",
			contentType: "application/json",
			data:JSON.stringify({
				'giftCode':giftCode
			}),
			url:'/demo/sign/updateUserData',
			datatype:"JSON",
			success:function (result){
				receiveUser();
				$("#result_info").show();
				$('#jobNum').html(result.data.jobNum);
				$('#name').html(result.data.name);
				$('#status').html(result.data.osResult);
				
				if(result.data.osResult=="未领取"||result.data.osResult=="领取失败，请核实礼品码"){
					$("#result_info").show();
					$("#greenBtn").show();
					$("#greyBtn").hide();
					$("#successTip").hide();
					$("#failedTip").show();
					$("#noFound").hide();
					$("#reget").hide();
					$("#status_cell").hide();
				}else if(result.data.osResult=="未查到此礼品码，请核实"){
					$("#result_info").hide();
					$("#noFound").show();
					$("#reget").hide();
					$("#greyBtn").show();
					$("#greenBtn").hide();
					$("#status_cell").hide();
					$("#successTip").hide();
					$("#failedTip").hide();
				}else if(result.data.osResult=="请勿重复领取"){
					$("#result_info").show();
					$("#noFound").hide();
					$("#reget").show();
					$("#greyBtn").show();
					$("#greenBtn").hide();
					$('#status').html("已领取");
					$("#successTip").hide();
					$("#failedTip").hide();
					$("#status_cell").show();
				}else if(result.data.osResult=="领取成功") {
					$('#status').html("已领取");
					$("#result_info").show();
					$("#greyBtn").show();
					$("#greenBtn").hide();
					$("#successTip").show();
					$("#failedTip").hide();
					$("#noFound").hide();
					$("#reget").hide();
					$("#status_cell").show();
			}
			},
			error:function(result){}
	});
	}
	

	

	
function submit(){
		var giftCode = document.getElementById("handGiftCode").value;
		$.ajax({
			async: false,
			type:"POST",
			contentType: "application/json",
			data:JSON.stringify({
				'giftCode':giftCode
			}),
			url:'/demo/sign/queryUserData',
			datatype:"JSON",
			success:function (result){
				$("#result_info").show();
				$("#successTip").hide();
				$("#failedTip").hide();
				$('#jobNum').html(result.data.jobNum);
				$('#name').html(result.data.name);
				$('#status').html(result.data.osResult);
				if(result.data.osResult=="未领取"){
					$("#result_info").show();
					$("#greenBtn").show();
					$("#greyBtn").hide();
					$("#noFound").hide();
					$("#reget").hide();
					$("#status_cell").show();
				}else if(result.data.osResult=="未查到此礼品码，请核实"){
					$("#result_info").hide();
					$("#noFound").show();
					$("#greyBtn").show();
					$("#greenBtn").hide();
					$("#reget").hide();
					$("#status_cell").hide();
				}else if(result.data.osResult=="请勿重复领取"){
					$("#result_info").show();
					$("#noFound").hide();
					$("#reget").show();
					$("#greyBtn").show();
					$("#greenBtn").hide();
					$('#status').html("已领取");
					$("#status_cell").show();
				}else {
					$("#greyBtn").show();
					$("#greenBtn").hide();
					$("#noFound").hide();
					$("#reget").hide();
					$("#status_cell").hide();
			}
			},
			error:function(result){}
	
	});
}
//确认
$("#greenBtn").on("click",function(){
	receive();
});
	
	$("#handGiftCode").focus();
	
	//导出
	$("#download1").on("click",function(){
	   
		$.ajax({
			async: false,
			type:"POST",
			contentType: "application/json",
			data:JSON.stringify({
				//'typeId':"01"
			}),
			url:'/wxdc-mgr/wxdc/downloadFood',
			datatype:"JSON",
			success:function (result){
				
			},
			error:function(result){}
	});

});
	//导出
	$("#download").on("click",function(){
	        window.location.href = "/wxdc-mgr/wxdc/downloadFood";
	});
		

});

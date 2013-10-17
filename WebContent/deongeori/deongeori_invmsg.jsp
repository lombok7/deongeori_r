<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>덩어리 초대하기</title>
		<link rel="stylesheet" href="./css/deongeori_invmsg.css" />
		<script src="./js/jquery-1.10.1.js" type="text/javascript"></script>
		<script type="text/javascript">
			function dng_sendInvmsg() {
				var dng_id = '${dng_id }';
				var dng_inv_to = $('#dng_inv_to').val();
				
				//아이디 중복확인
			    $.ajax({//$는  jQuery. 즉 jQuery로 아작스를 실행
	        		type: "POST",//자료를 보내는 법
	        		url: "./deongeori/deongeori_invmsg_check.jsp", //서버 파일 주소 와 경로   
	        		data: {"dng_id":dng_id, "dng_inv_to":dng_inv_to}, 
	        		datatype: "post",//서버 타입을 post 타입으로 받아옴.
	        		
	        		// case 0  : case 1 ~ 3의 예외 상황에 해당하지 않을때, 초대 메세지 테이블 저장 성공.
	        		// case -1 : 초대 메세지 테이블 저장 실패.
	        		// case 1  : 해당 닉네임이 덩어리 서비스에 가입되어 있지 않은 경우.
	        		// case 2  : 어느 누군가가 이미 해당 덩어리에 초대 메세지를 보낸 경우.
	        		// case 3  : 이미 해당 덩어리에 가입 되어있는 회원인 경우.
	        
	        		success: function (data) {//서버 결과값은 data변수에 저장
	        			
	        			// switch ~ case 문이 안되는 이유? data는 object type?
	               		if (data == 0) {	// 초대 가능
	        				var newtext='<font color="blue">짝짝짝~! 초대에 성공 하였습니다.</font>';
	      					
	  						$("#dng_invmsg_check").text('');
	      					$("#dng_invmsg_check").show();
	   				   		$("#dng_invmsg_check").append(newtext);
	   				   	
	               		} else if (data == -1) {	// 초대 실패.
	               			
	               			var newtext = '<font color="red">초대에 실패하였습니다. 시스템을 확인해주세요!</font>';
		     				
	      	  				$("#dng_invmsg_check").text('');
	     			   		$("#dng_invmsg_check").show();
	        				$("#dng_invmsg_check").append(newtext);	               			
	               	   		
	   					} else if (data == 1) { // 덩어리 서비스에 가입되지 않은 경우. 
	        				var newtext = '<font color="red">덩어리 서비스에 회원이 아닙니다.</font>';
	     				
	      	  				$("#dng_invmsg_check").text('');
	     			   		$("#dng_invmsg_check").show();
	        				$("#dng_invmsg_check").append(newtext);
	        				
	          			} else if (data == 2) {	// 이미 초대 메세지를 보낸 경우.
	  						var newtext = '<font color="red">초대를 받은 회원입니다.</font>';
	     				
      	  					$("#dng_invmsg_check").text('');
     			   			$("#dng_invmsg_check").show();
        					$("#dng_invmsg_check").append(newtext);
          					
        				} else if (data == 3) {	// 이미 덩어리에 가입한 경우.
	  						var newtext = '<font color="red">이미 덩어리에 가입한 회원입니다.</font>';
		     				
      	  					$("#dng_invmsg_check").text('');
     			   			$("#dng_invmsg_check").show();
        					$("#dng_invmsg_check").append(newtext);
        					
        				}
          			},
	      			error:function() {
						alert("data error");
					}
				});//$.ajax	
			}
		</script>
	</head>

	<body>
		<div id="invmsgwrap">
			<form method="post" onsubmit=""> 
				<table id="t_invmsg" border="1">
					<tr>
						<td colspan="2">초대할 회원의 이메일 주소를 입력하세요.</td>					
					<tr>
						<td><input type="email" name="dng_inv_to" id="dng_inv_to" size="40" required="required" /></td>
						<td><input type="button" name="invsubmit" id="invsubmit" value="초대" onclick="javascript:dng_sendInvmsg();"></td>
					</tr>			
					<tr>
						<td colspan="2" class="odd"><span id="dng_invmsg_check">&nbsp;</span></td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="button" value="닫기" onClick="javascript:window.close();" />
						</td>
					</tr>
				</table>					
			</form>
		</div>
	</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> 로그인 폼 </title>
<link rel="stylesheet" href="./css/member2.css" />

<script src="./js/jquery-1.10.1.js"></script>
<script src="./js/member.js"></script>
<!-- 제이쿼리를 이용하여 아이디와 비번을 체크한다. -->
<script>
	/* 아이디와 비번 체크 */
	
	function check(){
		if($.trim($("#id").val())==""){
			alert("로그인 아이디를 입력하세요!");
			$("#id").val("").focus();
			return false;
		}
		
		if($.trim($("#pass").val())==""){
			alert("로그인 비번를 입력하세요!");
			$("#pass").val("").focus();
			return false;
		}
	}
	function join_btn(){
		$("#join-box").css("opacity","1").css("top","350px");
		$("#Login_t").css("opacity","0.2");
		$("#Login_menu").css("opacity","0.2");
		$("#img").css("opacity","0.2");
	}
	function join_btn2(){
		$("#join-box").css("opacity","0").css("top","400px");
		$("#Login_t").css("opacity","1");
		$("#Login_menu").css("opacity","1");
		$("#img").css("opacity","1");
	}
	function background(){
		$("#img1").css("width",(screen.width)).css("height",(screen.height));
	}
	
</script>

</head>
<body style="overflow:hidden" onload="background()"><!-- style="background-image:url(./images/11.jpg)" -->
	<div id="img">
	<img src="./images/22.jpg" id="img1" />
	</div>
	
		<div id="Login_wrap">
		<div id="rbox">	</div>
     	<div id="bbox"> </div>
        <div id="gbox"> </div> 
        <form method="post" action="./member_login_ok.do" onsubmit="return check()">
		<table id="Login_t">
			<tr> <th colspan="2">로그인</th></tr>
			<tr><td colspan="2"></td></tr>
			<tr>
				<th> 아이디 : </th>
				<td> <input name="logid" id="id" size="16" />
				</td>
			</tr>
			
			<tr>
				<th> 비밀번호 : </th>
				<td> <input type="password" name="pass" id="pass" size="16" />
				</td>
			</tr>
		</table>
		<div id="Login_menu">
			<input type="submit" class="bnt" value="로그인" />
			<input type="reset" class="bnt" value="취소" onclick="$('#logid').focus()" />
			<input type="button" class="bnt" value="회원가입" onclick="join_btn()" />
		</div>
		</form>
		</div>
				
		<div id="join-box">
			<form method="post" action="./member_join_ok.do" onsubmit="return join_check()">
				<table id="join-form">
				<th colspan="2"> 회원가입 </th>
				<tr><td align="right"> 이메일 : </td>
					<td><input type="email" name="email" id="email" size="16" required="required" />
					<input type="button" value="중복체크" onclick="id_check()">
					</td>
				<tr style="line-height:1.2; text-align:center">
					<td colspan="2">
					<span style="font-size:11px" id="idcheck">&nbsp;</span>
					</td>
				</tr>
				
				<tr><td align="right"> 닉네임 : </td>
					<td><input type="text" name="nick" id="nick" size="16" required="required" />
					<input type="button" value="중복체크" onclick="nick_check()">
					</td>
				</tr>
				<tr style="line-height:1.2; text-align:center">
					<td colspan="2">
					<span style="font-size:11px" id="nickcheck">&nbsp;</span>
					</td>
				</tr>
				<tr><td align="right"> 
				이&nbsp;&nbsp;&nbsp;름 : </td>
					<td><input type="text" name="name" id="name" size="16" required="required" /></td>
				</tr>
				<tr><td align="right"> 비밀번호 : </td>
					<td><input type="password" name="join-pass" id="join-pass" size="16" required="required" /></td>
				</tr>
				<tr><td align="right"> 비밀번호확인 : </td>
					<td><input type="password" name="join-pass2" id="join-pass2" size="16" required="required" /></td>
				</tr>
				
				<tr align="center">
				<td colspan="2">
				<input type="hidden" name="confirm-email" id="cfe" value="n">
				<input type="hidden" name="confirm-nick" id="cfn" value="n">
				<input type="submit" style="background-color:grey;" class="bnt" value="가입" />
				<input type="button" style="background-color:grey;" class="bnt" value="닫기" onclick="join_btn2()" />
				</td>
				</tr>
				</table>
			</form>
			
		</div>
		<div id="copyright">
		Copyrightⓒ. 2013. Deongeori. All Rights Reserved.
		</div>

</body>
</html>
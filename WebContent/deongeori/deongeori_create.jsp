<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>덩어리 생성</title>
		<script src="./js/jquery-1.10.1.js" type="text/javascript"></script>
		<script src="./js/deongeori.js" type="text/javascript"></script>
		<link rel="stylesheet" type="text/css" href="./css/d_main.css" />
		<link rel="stylesheet" type="text/css" href="./css/main.css" />
		<link rel="stylesheet" type="text/css" href="./css/deongeori.css" />
	</head>

	<body>
	<div id="main_wrap">
		<table>	
			<tr>
				<td colspan="2">
					<div id="header">
						<%@ include file = "../include/main_header.jsp" %>
			 		</div>
		 		</td>
		 	</tr>
		 		<tr>
		 		<td>
		 
		<div id="deongeori_create_wrap">
			<h2 class="deongeori_create_title">덩어리 생성</h2>
			<form name="deongeori_create_f" method="post" action="./deongeori_create_ok.do" onsubmit="return check();" enctype="multipart/form-data">
				<table id="deongeori_create_t">
					<tr>
						<td><img src="./images/noimage.png" id="dngimg" width="140" height="170" /></td>
		           		<td><input type="file" name="dng_img" id="dng_img" size="14" class="input_box" accept="image/*" onchange="javascript:imgPreview(this);" /></td>
					</tr>
		            <tr>
	                	<th>덩어리 이름</th>
	                    <td>
	                    	<input type="text" name="dng_name" id="dng_name" size="30" class="input_box" required="required" />
	   					</td>
	                </tr>
	                <tr>
						<th>비밀번호</th>
	                   	<td><input type="password" name="dng_pwd" id="dng_pwd" size="14" class="input_box" required="required" /></td>
	                </tr>
	                <tr>
	                	<td colspan="2" style="text-align:right;">
	                		<input type="submit" value="생성" class="input_button" />
							<input type="reset" value="취소" class="input_button" onclick="$('#deongeori_name').focus();" />
						</td>
				</table>
			</form>		
		</div>
		</td>
		</tr>
		</table>
		</div>
	</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 정보 수정</title>
<link rel="stylesheet" type="text/css" href="./css/member.css">
<!-- 외부 스타일 시트 링크 -->
<script src="./js/jquery-1.10.1.js"></script>
<script src="./js/member.js"></script>
<!-- 외부 제이쿼리 링크 --> 
</head>
<body>
	<div id="edit_check">
	<form>
	<table id="edit_check_t">
	<tr>
		<td>비밀번호확인 : </td>
		<td><input type="password" name="edit_pwd" id="edit_pwd" size="14"></td>
	</tr>
	<tr>
	<td>
	
	<input type="button" value="확인" onclick="edit_pwd_check('<%=session.getAttribute("id")%>')">
	<input type="reset" value="취소" onclick="$('#edit_pwd').val('').focus();">
	</td>
	</tr>
	</table>
	</form>
	</div>
	
  	<div id="Edit_wrap"> <!--스트일 시트를 지정하기 위한 id -->
    <h2 class="Edit_title"> 회원정보 수정</h2>
    <form name="f" method="post" action="member_edit_ok.do" enctype="multipart/form-data"
     onsubmit="return edit_check()">
     <!-- [수정] 버튼 클릭 시  edit_check() 함수를 호출하여 체크 한 후
          member_edit_ok.do에 의해서 회원정보 수정을 처리한다.-->
     <table id="Edit_t"> 
     
       <tr>
          <th> 이메일 </th>
          <td><input name="dng_mem_email" id="dng_mem_email" size="14" /> 
          </td> 
       </tr>
       
       <tr>
          <th> 닉네임 </th>
          <td><input name="dng_mem_nickname" id="dng_mem_nickname" size="14" />
          <input type="button" value="중복체크" onclick="nick_check()">
					</td>
		</tr>
			<tr style="line-height:1.2; text-align:center">
			<td colspan="2">
			<span style="font-size:11px" id="nickcheck">&nbsp;</span>
          	</td> 
       		</tr>
       
       <tr>
			<th> 프로필 사진 업로드 </th>
			<td>  
			<input type="file" name="dng_mem_profileimgs" accept="image/*" onchange="javascript:imgPreview(this);" />
			</td>
		</tr>
		<tr>
			<td>
			<img src="../images/noimage.png" name="dngimg" id="dngimg" width="140" height="170" />
			</td>
		</tr>
     </table>
     
     <div id="Edit_menu">
        <input type="submit" value="수정" />
        <input type="reset" value="취소" 
          onclick="$('#dng_mem_nickname').focus();" />     
     </div>
     <input type="hidden" name="orgFilename" value="${dng_mem_profileimg}"> 
   </form>
  </div>
</body>
</html>




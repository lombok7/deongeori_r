<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>메인화면</title>
<link rel="stylesheet" type="text/css" href="./css/log_st.css" />

<script src="./js/member.js"></script>
<script>
function edit_btn(){
	$("#ed").css("display","block");
	$("#ed").css("opacity","1");
}
function edit_btn2(){
	$("#ed").css("opacity","0");
	$("#ed").css("display","none");
}
</script>
</head>
<body>
<div id ="logInfo">
<table id="logInfo_t" width="240" align="center">
<tr>
<td>
<table width="230" align="center">
<tr>
  <td align="center">
  <!-- <a href="./member_edit.do" style="font-size:12px" onfocus="this.blur()">정보수정</a> -->
  <input type="button" value="정보수정" onclick="edit_btn()">
  </td>
  <td align="center"><input type="button" value="로그아웃" onclick="location.href='./member_logout.do'"></td>
</tr>
</table>
<table border="1" id="logInfo_st" width="230" height="60" align="center">
<tr>
<td rowspan="3" width="70px" >

<c:choose>
<c:when test="${profileimg==null}" >
<img src="./upload/mbimages/noimage.jpg" style="margin-left:8px" width="50" height="60" border="1"/>
</c:when>
<c:otherwise>
<img src="./upload/mbimages/${profileimg}" style="margin-left:8px" width="50" height="60" border="1"/>
</c:otherwise>
</c:choose>

</td>

<td colspan="2">
${email}
</td>

</tr>

<tr>
<td colspan="2">
${nickname}
</td>
</tr>

<tr>
<td colspan="2">
${name}
</td>
</tr>
</table>
</table>

</div>
<div id ="ed">
 	<div id="ed_pwd_check">
	<form>
	<table id="edit_check_t" >
	<tr><td colspan="2" align="center" >회원정보 수정</td>
	<td>
	<input type="button" style="align:right" value="X" onclick="edit_btn2()">
	</td></tr>
	<tr>
		<td>비밀번호확인 : </td>
		<td><input type="password" name="edit_pwd" id="edit_pwd" size="14"></td>
		<td><input type="button" value="확인" onclick="edit_pwd_check('<%=session.getAttribute("id")%>')"></td>
	</tr>
	</table>
	</form>
	</div>
	
  	<div id="Edit_wrap"> <!--스트일 시트를 지정하기 위한 id -->
    
    <form name="f" method="post" action="member_edit_ok.do" enctype="multipart/form-data"
     onsubmit="return edit_check()">
     <!-- [수정] 버튼 클릭 시  edit_check() 함수를 호출하여 체크 한 후
          member_edit_ok.do에 의해서 회원정보 수정을 처리한다.-->
     <table id="Edit_t" > 
     
       <tr>
          <td width="120px" align="right"> 이메일 : </td>
          <td><input name="dng_mem_email" id="dng_mem_email" size="14" /> 
          </td> 
       </tr>
       <tr>
          <td align="right"> 닉네임 : </td>
          <td><input name="dng_mem_nickname" id="dng_mem_nickname" size="14" />
          </td> 
      	</tr>
       
      	<tr>
     	<td align="right">프로필 &nbsp;<br>이미지 &nbsp;</td>
       	<td>  
			<input type="file" name="dng_mem_profileimgs" accept="image/*" onchange="javascript:imgPreview2(this);" />
		</td>
		</tr>
		<tr>
			<td align="center">이미지<br>미리보기</td>
			<td>
			<img src="./images/noimage.png" id="pfimg" name="pfimg" width="120" height="150" />
			</td>
		</tr>
     </table>
     
    
     <div id="Edit_menu">
     <table>
     <tr>
     <td><br>
     	<input type="hidden" name="confirm-pwd" id="cfp" value="n">
        <input type="submit" value="수정" />
        
     </td>
     </tr>
     </table>
     </div>
     <input type="hidden" name="orgFilename" value="${dng_mem_profileimg}"> 
   </form>
  </div>
</div>
</body>
</html>
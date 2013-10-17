<%@page import="org.apache.catalina.startup.SetAllPropertiesRule"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.gallery.dao.*" %>
<link rel="stylesheet" type="text/css" href="./css/gal.css" />
<script type="text/javascript">

// 덩어리 이미지를 호출 했을 때 호출되는 함수.
// 한 번 클릭하면, 게시판, 갤러리 영역의 글과 이미지가 표시 되고,
// 이 상태에서 다시 클릭하면, 해당 덩어리의 아이디 값을 갖고 메인 페이지로 이동한다.
// function selectDngimg(dng_id)
$.selectgalcont = function(dng_gal_id) {
	var selectgalid = $('#selectgalid').val();
	
	if (selectgalid == dng_gal_id) {
		location.href='./deongeori_list2.do?dng_gal_id='+ dng_gal_id;
		
	} else {	
		$('#selectgalid').val(dng_gal_id);
		/* alert(dng_gal_id); */
	}	
}

function view_cont(){
	$("#gal_cont").css("display","block");
	$("#gal_cont").css("opacity","1");
}
function view_cont2(){
	$("#gal_cont").css("opacity","0");
	$("#gal_cont").css("display","none");
}
</script>
<%
	List<GalleryBean> galleryList=(List<GalleryBean>)request.getAttribute("gd");
    //setAttribute()에 의해서 Object형으로 업캐스팅 되면서
    //키값에 저장된다. 반환되는 값도 다운캐스팅을 해서 저장한다.
    //다운캐스팅은 업캐스팅이 된것에 한해서 다운캐스팅이 된다.
    //레퍼런스간의 캐스팅 즉 다운(업)캐스팅은 상속이 된것에 한에서만 허용된다.
   
%>

<div class="clear" ></div>
 <div id="article">

  
  <div id="article_c">

<!-- 게시판 리스트 -->
<table align="center" width="100%" border="1" cellspacing="0" >
	<tr align="center" valign="middle">
		<td colspan="4"><font color="blue"><b>Gallery</b></font></td>
	</tr>
	
	<!-- <tr align="center" valign="middle" bordercolor="#333333">
		
	</tr> -->
	
	<tr align="center" valign="middle">
		<td>
		<div id="gal_cont">
		<%@ include file = "./gal_cont.jsp" %>
		</div>
		</td>
	</tr>
	<tr>
		<td id="gal_list">
		<%
		for(int i=0;i<galleryList.size();i++){
			GalleryBean gl=galleryList.get(i);
			
		%>	
		<div class="gal_img" align="center" ><img src="./upload/galimages/<%=gl.getDng_gal_img() %>" width="100px" height="100px" onclick="$.selectgalcont('<%=gl.getDng_gal_id()%>')"></div>
		<%} %>
		</td>
	</tr>
		
	<tr align="center">
		<td colspan="5">
	   		<a href="gal_write.do">[글쓰기]</a>
	   		<!-- <a href="bbs_write.do">[글쓰기]</a>  -->
		</td>
	</tr>
<input type="hidden" id="selectgalid" value="" />
</table>
</div>
</div>
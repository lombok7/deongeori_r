<%@page import="org.apache.catalina.startup.SetAllPropertiesRule"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.gallery.dao.*" %>
<link rel="stylesheet" type="text/css" href="./css/gal2.css" />
<script type="text/javascript">

// 덩어리 이미지를 호출 했을 때 호출되는 함수.
// 한 번 클릭하면, 게시판, 갤러리 영역의 글과 이미지가 표시 되고,
// 이 상태에서 다시 클릭하면, 해당 덩어리의 아이디 값을 갖고 메인 페이지로 이동한다.
// function selectDngimg(dng_id)
$.selectgalcont = function(dng_gal_id) {
	/* var selectgalid = $('#selectgalid').val(); */
	var show = $('#show').val();
	/* if (selectgalid == dng_gal_id) { */
		location.href='./deongeori_list3.do?dng_gal_id='+ dng_gal_id + '&show=' + show;
		
	/* } else {	
		$('#selectgalid').val(dng_gal_id);
		alert(dng_gal_id);
	}*/
}

/* function view_cont(){
	$("#gal_cont").css("display","block");
	$("#gal_cont").css("opacity","1");
}
function view_cont2(){
	$("#gal_cont").css("opacity","0");
	$("#gal_cont").css("display","none");
} */
</script>
<%
	List<GalleryBean> galleryList=(List<GalleryBean>)request.getAttribute("gd");
	GalleryBean gm=(GalleryBean)request.getAttribute("gm");
	//setAttribute()에 의해서 Object형으로 업캐스팅 되면서
    //키값에 저장된다. 반환되는 값도 다운캐스팅을 해서 저장한다.
    //다운캐스팅은 업캐스팅이 된것에 한해서 다운캐스팅이 된다.
    //레퍼런스간의 캐스팅 즉 다운(업)캐스팅은 상속이 된것에 한에서만 허용된다.
   
%>

<div class="clear" ></div>
 <div id="article">

  
  <div id="article_c">

<!-- 게시판 리스트 -->
<table align="center" width="100%" cellspacing="0" >
	<tr align="center" valign="middle">
		<td id="gal_name" colspan="4">Gallery</td>
	</tr>
		
	<tr align="center" valign="middle">
		<td>
		<div id="gal_cont">
		<%@ include file = "./gal_cont2.jsp" %>
		</div>
		</td>
	</tr>
	<tr>
		<td>
		<div id="gal_list">
		<ul class="hoverbox">
		<%if(gm!=null){ %>
		<li><div id="gal_img_Most" align="center" style="background-image:url(./upload/galimages/<%=gm.getDng_gal_img()%>)"></div>
		<img src="./upload/galimages/<%=gm.getDng_gal_img() %>" onclick="$.selectgalcont('<%=gm.getDng_gal_id()%>')" class="preview">
		</li>
		<%}%>
		
		<%
		if((galleryList!=null) && (galleryList.size()>0)){
		for(int i=0;i<galleryList.size();i++){
			GalleryBean gl=galleryList.get(i);
			
		%>	
		<li>
		<div class="gal_img" align="center" style="background-image:url(./upload/galimages/<%=gl.getDng_gal_img()%>)"></div>
		<img src="./upload/galimages/<%=gl.getDng_gal_img() %>" onclick="$.selectgalcont('<%=gl.getDng_gal_id()%>')" class="preview">
		
		<%-- <div align="center" style="background-image:url(./upload/galimages/<%=gl.getDng_gal_img()%>)" onclick="$.selectgalcont('<%=gl.getDng_gal_id()%>')"></div>
		<div align="center" style="background-image:url(./upload/galimages/<%=gl.getDng_gal_img()%>)" onclick="$.selectgalcont('<%=gl.getDng_gal_id()%>')" class="preview"> --%>
		<%-- <img src="./upload/galimages/<%=gl.getDng_gal_img() %>" onclick="$.selectgalcont('<%=gl.getDng_gal_id()%>')"> --%>
		<%-- <div class="gal_img" align="center" style="background-image:url(./upload/galimages/<%=gl.getDng_gal_img()%>)" onclick="$.selectgalcont('<%=gl.getDng_gal_id()%>')"></div> --%>
		</li>
		<%}} %>
		</ul>
		</div>
		</td>
	</tr>
		
	<tr align="center">
		<td colspan="5">
	   		<a href="gal_write.do">[글쓰기]</a>
	   		
		</td>
	</tr>
<input type="hidden" id="selectgalid" value="" />
<input type="hidden" id="show" value="1" />
</table>
</div>
</div>
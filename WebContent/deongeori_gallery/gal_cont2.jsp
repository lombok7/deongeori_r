<%@page import="com.gal_rep.action.Gal_repDAO"%>
<%@page import="com.gal_rep.action.Gal_repBean"%>
<%@page import="dng.common.CommonUtil"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.gallery.dao.*" %>
<!-- com.naver.dao 패키지의 모든 클래스를 임포트한다. -->
<%@ page import="java.util.*" %>
<link rel="stylesheet" type="text/css" href="./css/gal2.css" />
<!-- List 클래스를 사용하기 위해서 java.util 패키지의 모든 클래스를 임포트한다. -->
<%
	GalleryBean gc=(GalleryBean)request.getAttribute("gc");
    //setAttribute()에 의해서 Object형으로 업캐스팅 되면서
    //키값에 저장된다. 반환되는 값도 다운캐스팅을 해서 저장한다.
    //다운캐스팅은 업캐스팅이 된것에 한해서 다운캐스팅이 된다.
    //레퍼런스간의 캐스팅 즉 다운(업)캐스팅은 상속이 된것에 한에서만 허용된다.
   	String show = request.getParameter("show");
    System.out.println(show);
%>

<script type="text/javascript">

function show_pop(show){
	if(show==1){
	$("#gal_cont_pop").css("display","block");
	$("#gal_cont_pop").css("opacity","1");
	/* alert('aa'); */
	}else{
	/* alert('bb'); */
	}
}
function show_pop2(){
	$("#gal_cont_pop").css("opacity","0");
	$("#gal_cont_pop").css("display","none");
}

</script>

<body onload="show_pop('<%=show%>')">
<div class="clear" ></div>
  
  <div id="article">
  
  	<div id="gal_cont_pop">
	
		<table id="gal_cont_pop_t">
		<%if(gc==null){ %>
  		<tr>
  		<td>
  		사진이 없습니다.
  		</td>
  		<td>
  		<input type="button" onclick="show_pop2()" value="x">
  		</td>
  		</tr> 		
  		<% }else{ %>
		
		<tr>
		<td rowspan="7">
			<div class="gal_img_pop" align="center" style="background-image:url(./upload/galimages/<%=gc.getDng_gal_img()%>)"></div>
		</td>
		<tr>
		
		<td align="right">
		<input type="button" onclick="show_pop2()" value="x">
		</td>
		</tr>
		
		<tr>
			<td height="80px"><div id="gal_content"><%=gc.getDng_gal_content()%></div></td>
		</tr>
		
		<tr style="font-family:Tahoma;font-size:8pt;">
		<td align="right">
		<%=CommonUtil.idToNickname(gc.getDng_gal_writer())%>&nbsp; &nbsp;<%=gc.getDng_gal_regdate() %>
		</td>
		</tr>
		
		<tr>
		<td align="right">
   	 	<a href="./GalLikeAction.do?num=<%=gc.getDng_gal_id() %>">
   	 	<input type="image" src="./images/ilikeit.png" width="30" height="30" id="best" >
   	 	</a>
   	 	<%=gc.getDng_gal_likecount() %> 
   	  	</td>
   	  	</tr>
   	  	
		<tr>
		<td>
		
		<form name="gal_f" method="post" action="./gal_rep_ok.do?num=<%=gc.getDng_gal_id() %>">
		 
		<%=session.getAttribute("nickname")%>&nbsp;&nbsp;
		<textarea name="DNG_GAL_RE_CONTENT" id="DNG_GAL_RE_CONTENT" rows="2" cols="40"></textarea> 
   		<input type="hidden" name="dng_gal_id" id="dng_gal_id" value="<%= gc.getDng_gal_id() %>" />	
   		<input type="submit" id="b" value="입력" class="input_b"/>
                
        </form>
        </td>
        </tr>
        <% } %>
       
   	  	<tr>
   	  	<td height="150">
   	  	<div id="gal_rep_list">
   	  	<table id="repp" >
        <% if(gc!=null){
        	Gal_repDAO grd=new Gal_repDAO();
        	List<Gal_repBean> gal_repList = grd.getGal_repList(gc.getDng_gal_id());
        	
  			if((gal_repList != null) && (gal_repList.size()>0)){
   			for(int j=0; j<gal_repList.size(); j++){
   			Gal_repBean grb = gal_repList.get(j);	%>
   			<tr>
   	  		<td>   			 			
   			<%=CommonUtil.idToNickname(grb.getDng_gal_re_writer()) %>&nbsp;
   			</td>
   			<td class="gal_rep_cont" style="background-color:#D9E5FF;">
   			<%  grb.getDng_gal_re_content().replaceAll("`","'");
   				grb.getDng_gal_re_content().replaceAll("\r\n","<br>");
   				grb.getDng_gal_re_content().replaceAll("\u0020","&nbsp;"); %>
   			<%=grb.getDng_gal_re_content() %>&nbsp; &nbsp;
   			</td>
   			<td style="font-family:Tahoma;font-size:9pt;">
   			<%=grb.getDng_gal_re_regdate().substring(0,10) %>
   	  		</td>
   	  		</tr>
   	  	<%}}} %>
  		</table>
  		</div>
        </td>
        </tr>
          	  	
   	  	
  		
  	</table>
  	</div>
 </div>
 </body>
  	

<!-- 이하 footer 내용 -->
<div class="clear"></div>

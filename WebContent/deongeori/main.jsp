<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>덩어리 메인화면</title>
<link rel="stylesheet" type="text/css" href="./css/main.css" />
<script src="./js/jquery-1.10.1.js"></script>
<script src="./js/jcarousellite_1.0.1.js"></script>
<script type="text/javascript">
$(function() {
	// 기존 코드는 이미지 한 개 일 때 보여주지 못함. 해결 방법 중 하나. 
	// 5개 미만 일 때는 visible 수 를 항목 수와 동일하게,
	// 5개 이상 일 때는 이상이면 4로 함. 이 때 버튼도 나타나게 함.
	var lilength = $("#dngimglist li").length;	
	
	if (lilength < 6) {
		$(".next").hide();
		$(".prev").hide();
	} else {
		lilength = 5;
	}
	
	$(".deong").jCarouselLite({
		btnNext: ".next",
        btnPrev: ".prev",
        /*circular: false*/
        speed: 200,
        visible: lilength
     
    });
});

// 덩어리 이미지를 호출 했을 때 호출되는 함수.
// 한 번 클릭하면, 게시판, 갤러리 영역의 글과 이미지가 표시 되고,
// 이 상태에서 다시 클릭하면, 해당 덩어리의 아이디 값을 갖고 메인 페이지로 이동한다.
// function selectDngimg(dng_id)
$.selectDngimg = function(dng_id) {
	var selectdngid = $('#selectdngid').val();
	
	if (selectdngid == dng_id) {
		location.href='./deongeori_main.do?dng_id='+ dng_id;
	} else {	
		$('#main_board').text(dng_id);
		$('#main_gallery').text(dng_id);
		
		$.ajax({//$는  jQuery. 즉 jQuery로 아작스를 실행
      		type: "POST",//자료를 보내는 법
      		url: "./deongeori/deongeori_recent_board.jsp", //서버 파일 주소 와 경로   
      		data: {"dng_id":dng_id}, 
      		datatype: "post",//서버 타입을 post 타입으로 받아옴.
      		
      		success: function (xml) {//서버 결과값은 data변수에 저장
      			// xml 노드 null 확인
      			var rlist = "";
      			
      			xmlDoc = $.parseXML(xml);
      			
      			$xml = $(xmlDoc);
      		
      			if ($xml.find("board").find("item").length > 0) {

      				      				
      				// item 노드 loop
      				$xml.find("board").find("item").each(function() {				
      					
      					var id = $(this).find("id").text();
      					var writer = $(this).find("writer").text();
      					var writerimg = $(this).find("writerimg").text();
      					var content = $(this).find("content").text();
      					var pwd = $(this).find("pwd").text();
      					var likecount = $(this).find("likecount").text();
      					var regdate = $(this).find("regdate").text();
      					/* rlist += "<div class='rcnt_img' align='center' style='background-image:url(./upload/mbimages/" + witerimg + "')></div>" */
      					rlist += "<table><tr><td><div class='rcnt_img' align='center' style='background-image:url(./upload/mbimages/" + writerimg + ")'></div></td>" 
      					/* rlist += "<table border='1'><tr><td><img src='./upload/mbimages/" + writerimg + "' width='80px' height='60'px/></td>" */ 
      					rlist += "<td><div class='rcnt'>" + content + "</div></td></tr>"
      					rlist += "<tr style='font-family:Tahoma;font-size:11pt;'><td align='center'>" + writer + "</td>"
      					rlist += "<td align='right'>" + regdate + "</td></tr></table>"
      					/* rlist += "<span><img src='./upload/mbimages/" + writerimg + "' /></span>";
      					rlist += "<span>" + writer + "</span><br />";
      					rlist += "<span>" + content + "</span><br />";
      					rlist += "<span>" + regdate + "</span><br />";
      					rlist += "</div>"; */
      					rlist += "<hr />";
      					
      				});
      			} else {
      				rlist = "<div><span>글이 없습니다.</span></div>"; 
      			}
      			
      			$('#main_board').html(rlist);
    		},
    		error:function() {
				alert("data error");
				}
			});//$.ajax	
			
			$.ajax({//$는  jQuery. 즉 jQuery로 아작스를 실행
	      		type: "POST",//자료를 보내는 법
	      		url: "./deongeori/deongeori_recent_gallery.jsp", //서버 파일 주소 와 경로   
	      		data: {"dng_id":dng_id}, 
	      		datatype: "post",//서버 타입을 post 타입으로 받아옴.
	      		
	      		success: function (xml) {//서버 결과값은 data변수에 저장
	      			// xml 노드 null 확인
	      			var rlist = "";
	      			
	      			xmlDoc = $.parseXML(xml);
	      			
	      			$xml = $(xmlDoc);
	      		
	      			if ($xml.find("gallery").find("item").length > 0) {

	      				      				
	      				// item 노드 loop
	      				$xml.find("gallery").find("item").each(function() {				
	      					
	      					var id = $(this).find("id").text();
	      					var writer = $(this).find("writer").text();
	      					var content = $(this).find("content").text();
	      					var pwd = $(this).find("pwd").text();
	      					var likecount = $(this).find("likecount").text();
	      					var img = $(this).find("img").text();
	      					var regdate = $(this).find("regdate").text();
	      					
	      					rlist += "<span><img src='./upload/galimages/" + img + "'class='gal_img' /></span>";
	      					
	      					
	      				});
	      			} else {
	      				rlist = "<div><span>글이 없습니다.</span></div>"; 
	      			}
	      			
	      			$('#main_gallery').html(rlist);
	    		},
	    		error:function() {
					alert("data error");
					}
				});//$.ajax	
		
		$('#selectdngid').val(dng_id);
		
		
	}	
}

</script>

</head>
<body>

	<div id="main_wrap">
	 <table>	
		<tr>
		 <td colspan="2">
		 <div id="header">
		 <%-- 	<ul>
		 	<li><a href="./deongeori_create.do" onfocus="this.blur()"> 새로운 덩어리 생성 </a></li>
		 	<li><a href="#" onfocus="this.blur()"> QnA </a></li>
			</ul>
		  	<div id="info">
		  	<%@include file= "../log_st.jsp" %>
		    </div> --%>
		     <%@ include file = "../include/main_header.jsp" %>
		 </div>
		 </td>
		</tr>		
		<tr>
			<td colspan="2">
			<div id="moimes">
				<table id="deonglist" >
				<tr>
				<td>
			 	<input type="button" value="&lt;&lt;" class="prev" />
				</td>
				<td>   
				<div class="deong">
				<ul id="dngimglist">
				<c:forEach var="list" items="${dnglist}" varStatus="listCnt"> 
				<li>
				<%-- <div>
				<img src="./upload/dgimages/${list.dng_img}" class="bottom" alt="내가 가입한 덩어리의 로고 이미지" id="dngimg" onclick="$.selectDngimg('${list.dng_id}');"/> --%>
				<div style="background-image:url(./upload/dgimages/${list.dng_img})" class="bottom" id="dngimg" onclick="$.selectDngimg('${list.dng_id}');">
				<div class="dngname_bg">
				</div>
				<div class="dngname">
				<table width="100px">
				<tr><td align="center">${list.dng_name}</td></tr>
				</table>
				</div>
				</div>
				</li>
				</c:forEach>
		        </ul>
				</div>
				</td>
				<td>
			 	<input type="button" value="&gt;&gt;" class="next" />
				</td>
				</tr>
				</table>
			</div>
			</td>
		</tr>		
		<tr>
		 <td>
		  <div id="main_board">게시판 최신글</div>
		 </td>
		 <td>
		  <div id="main_gallery">갤러리 최신글</div>
		 </td>
		</tr>
	 </table>
	</div>
	<input type="hidden" id="selectdngid" value="" />
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>덩어리 초대 메세지 목록</title>
		<link rel="stylesheet" href="./css/deongeori_invmsg.css" />
		<script type="text/javascript" src="./js/jquery-1.10.1.js"></script>
		<script type="text/javascript">
			function joinDeongeori(dng_inv_id, dng_name) {
				
				var isjoin = window.confirm(dng_name + ' 덩어리에 가입하시겠습니까?');				
				
				if (isjoin == true) {
					$('#dng_inv_id').val(dng_inv_id);
					$('#dng_name').val(dng_name);
					
					$('#dngJoinForm').submit();
				}				
			}
			
			function closePopup() {
				window.opener.location.href = "./make_main.do?dng_mem_id=" + '${ id}';
				window.close();
			}
		</script>
	</head>
	<body>
		<div id="#invmsglistwrap">
			<form id="dngJoinForm" method="post" action="./deongeori_join_ok.do" >
				<table id="t_invmsglist">
					<thead>
						<tr>
							<th>From</th>
							<th>Deongeori</th>
							<th>Date & Time</th>
							<th>Agree</th>
						</tr>
					<thead>
					<tbody>
						<c:forEach var="list" items="${dnginvmsglist }"  varStatus="listCnt">
							<tr ${listCnt.count % 2 == 1 ? "class='odd'" : ""}>
								<td>${list.dng_inv_from }</td>
								<td>${list.dng_id }</td>
								<td>${list.dng_inv_regdate }</td>
								<td><input type="button" value="수락" onclick="javascript:joinDeongeori('${list.dng_inv_id}', '${list.dng_id}')" />
							</tr>
						</c:forEach>
					</tbody>
					<tfoot>
						<tr><td colspan="4"><input type="button" value="닫기" onClick="javascript:closePopup();" /></td></tr>
					</tfoot>
				</table>
				<input type="hidden" name="dng_inv_id" id="dng_inv_id" value="" />
				<input type="hidden" name="dng_name" id="dng_name" value="" />
				<input type="hidden" name="dng_mem_id" id="dng_mem_id" value="${id }" />
			</form>
		</div>
	</body>
</html>
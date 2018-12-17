<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
	$(function() {
		var msg = '${msg}';
		if(msg != ''){
			alert(msg);
		}
		
		$("#del").click(function() {
			$("#frm").submit();
		});
	});
</script>
</head>
<body>
	<h1>${board} Select</h1>
	
	<h3>TITLE : ${dto.title}</h3>
	<h3>WRITER : ${dto.writer}</h3>
	<h3>CONTENTS : ${dto.contents}</h3>
	<div>
		<c:forEach items="${dto.files}" var="file" varStatus="i">
			<div id="f${i.index}">
				<a href="../file/fileDown?fname=${file.fname}&oname=${file.oname}">${file.oname}</a>
			</div>
		
		</c:forEach>
	
	</div>
	
	
	<a href="./${board}List">List</a>
	<a href="./${board}Update?num=${dto.num}">update</a>
	<span id="del">delete</span>
	<form id="frm" action="./${board}Delete" method="post">
		<input type="hidden" name="num" value="${dto.num}">
	</form>
	<c:if test="${board ne 'notice'}">
		<a href="./${board}Reply?num=${dto.num}">Reply</a>
	</c:if>
</body>
</html>
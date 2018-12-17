<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">

$(function(){
	
	$("#frm").click(function(){
		var f = $("#f")[0];
		var formData = new FormData(f);
		$.ajax({
			url:"./notice/noticeWrite",
			type:"post",
			processData:false,
			contentType:false,
			enctype:"multipart/form-data",
			data:formData,
			success:function(data){
				alert(data);
			},
			error:function(xhr){
				alert(xhr.status);
			}
		});	
	});
});

</script>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>
<div>
	<form id="f" action="./notice/noticeWrite" method="post">
		<input type="text" id="writer" name="writer">
		<input type="text" id="title" name="title">
		<textarea id="contents" rows="" cols="" name="contents"></textarea>
		<input type="file" name="f1">
		<input type="button" id="frm" value="Write">
	</form>
</div>

<P>  The time on the server is ${serverTime}. </P>
<a href="./notice/noticeList">Notice List</a>
<a href="./qna/qnaList">Qna List</a>
<a href="./memo/memoList">Memo</a>

<c:choose>
	<c:when test="${not empty member}">
		<p>
			<a href="./member/logOut">LogOut</a>
			<a href="./member/myPage">myPage</a>	
		</p>
	</c:when>
	<c:otherwise>
		<p>
			<a href="./member/join">Join</a>
			<a href="./member/login">Login</a>
		</p>
	</c:otherwise>
</c:choose>
</body>
</html>

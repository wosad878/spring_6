<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
$(function(){
	$("#btn1").click(function(){
		$.get("./jtest1", function(data){
			alert(data.f2.num)
		});
	});
	
	$.ajax({
		url:"./list",
		type:"GET",
		data:{
			num:1
		},
		success:function(data){
			$.each(data,function(index, obj){
				alert(obj.writer);
			});
		}
	});
});
</script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>Memo List</h1>
<div id="list"></div>
<button id="btn1">JSON1</button>
</body>
</html>
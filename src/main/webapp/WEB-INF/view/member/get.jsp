<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tf" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script type="text/javascript">
$(function(){
	
    var contextPath = "<%= request.getContextPath()%>";
    var id = ${param.id};
    $.get(contextPath+"/api/members/"+id,
        function(json) {
                var sCont = "";
                    sCont += "<tr>";
                    sCont += "<td id='id'>" + json.id + "</td>";
                    sCont += "<td>" + json.name + "</td>";
                    sCont += "<td>" + json.email + "</td>";
                    sCont += "<td>" + json.registerDateTime + "</td>";
                    sCont += "</tr>";
                
                $("#load:last-child").append(sCont);
        });
    
    $('#list').on("click", function(){
		window.location.href = contextPath+"/memberlist";
	});
    
    $('#modify').on("click", function() {
        window.location.href = contextPath+"/update?id="+$('#id').text();
    });

    $('#remove').on("click", function(){
		var data = {no :  $('#id').text()};
		alert("data > " + data.no + " : " + $('#id').text());
        
        $.ajax({
            url: contextPath +"/api/members/"+data.no,
            type: 'DELETE',
            success: function(res) {
                alert(res);
                window.location.href = contextPath+ "/memberlist";
            },
            eerror:function(request,status,error){
                alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
                window.location.href = contextPath+"/memberlist";
            }
        }); 
    });
});
</script>

</head>

<body>
${param.id}
    <table border="1">
        <thead><th>아이디</th><th>이메일</th><th>이름</th><th>가입일</th></thead>
        <tbody id="load"></tbody>
    </table>
    <button id="remove">삭제</button>
    <button id="modify">수정</button>
    <button id="list">목록</button>
</body>
</html>

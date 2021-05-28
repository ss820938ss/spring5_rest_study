<%@ page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tf" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 추가</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script>
$(function(){
    var contextPath = "<%= request.getContextPath()%>";

    $('#cancel').on("click", function(e){
        window.location.href = contextPath + "/memberlist";
    });

    $('#new').on("click", function(e){
        var newMember = {  email: $('#email').val(), name: $('#name').val(), password: $('#password').val() };
        alert("data > " + newMember.email );
        $.ajax({
            url         : contextPath + "/api/members/",
            type        : "POST",
            contentType : "application/json; charset=utf-8",
            datatype    : "json",
            cache       : false,
            data        : JSON.stringify(newMember),
            success     : function(res) {
                alert(res);
                window.location.href = contextPath + "/memberlist";
            },
            error       : function(request, status, error){
                alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
                window.location.href = contextPath + "/memberlist";
            }
        }); 
    });
});

</script>
</head>
<body>
<form:form>
    <p><label> 이메일:<input type="email" id="email" /> </label></p>
    <p><label> 이름:<input type="text" id="name"/> </label></p>
    <p><label> 비밀번호:<input type="password" id="password"/> </label></p>
    <p><label> 비밀번호 확인:<input type="password" id="confirmPassword"/> </label></p>
    <c:set var="now" value = "<%= LocalDateTime.now() %>" />
    <p><label> 가입일:<input id="registerDateTime" type="date" value='<tf:formatDateTime value="${now }" pattern="yyyy-MM-dd" />' readonly="readonly"/> </label></p>
</form:form>
<button id="new">추가</button><button id="cancel">취소</button>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<table>
    <tr>
        <th>번호</th><th>이름</th><th>email</th>
    </tr>
    <c:forEach var="s" items="${studentList}">
    <tr>
        <td>${s.id}</td>
        <td>${s.name}</td>
        <td>${s.email}</td>
    </tr>
    </c:forEach>

    <a href="/student?action=create">새로운 학생 정보 입력</a>
</body>
</html>
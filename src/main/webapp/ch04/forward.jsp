<%@ page import="ch04.Student" %><%--
  Created by IntelliJ IDEA.
  User: KOSTA
  Date: 2026-05-18
  Time: 오후 3:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>forward 방식으로 이동한 페이지</title>
</head>
<body>
    forward 방식으로 이동한 페이지
    <% Student s = (Student)request.getAttribute("info"); %>
    이름은 <%= s.getName() %> 이고,
    점수는 <%= s.getScore() %> 입니다.
    (EL) 이름 : ${info.name}, 점수 : ${info.score}
</body>
</html>

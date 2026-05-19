<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<form action="/pcontrol?action=insert" method="post">
    id <input type="text" name="id">
    name <input type="text" name="name">
    maker <input type="text" name="maker">
    price <input type="text" name="price">
    date <input type="text" name="date">
  <input type="submit" value="등록">
</form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<form action="/student?action=create" method="post">
    <p>id <input type="text" name="id"></p>
    <p>name <input type="text" name="name"></p>
    <p>univ <input type="text" name="univ"></p>
    <p>birth <input type="text" name="birth"></p>
    <p>email <input type="email" name="email"></p>
  <input type="submit" value="등록">
</form>
</body>
</html>
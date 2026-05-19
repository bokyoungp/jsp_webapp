<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>상품 정보</title>
</head>
<body>
    <h2>상품 정보</h2>
    <hr>
    <ul>
        <li>id , ${product.id}</li>
        <li>name, ${product.name}</li>
        <li>maker, ${product.maker}</li>
        <li>price, ${product.price}</li>
        <li>date, ${product.date}</li>
    </ul>
</body>
</html>
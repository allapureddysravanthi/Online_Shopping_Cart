<%@page import="com.jsp.shoppingCart.Dto.Product"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% Product p=(Product)request.getAttribute("prodobj"); %>

<form action="additemtocart">
Id:<input type="hidden" name="id" value=<%=p.getId() %>> <br>
Brand:<input type="text" name="brand" value=<%=p.getBrand() %>> <br>
Model:<input type="text" name="model" value=<%= p.getModel() %>> <br>
Category<input type="text" name="category" value=<%=p.getCategory() %>> <br>
Price:<input type="price" name="price" value=<%=p.getPrice() %>> <br>
Quantity:<input type="number" name="quantity" > <br>
<input type="submit" value="Add to Cart">
</form>
</body>
</html>
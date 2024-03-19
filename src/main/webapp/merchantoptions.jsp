<%@page import="com.jsp.shoppingCart.Dto.Merchant"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% Merchant m=(Merchant)session.getAttribute("merchantinfo"); %>
<%if(m!=null){ %>

<h1 style=color:green>${message}</h1>
<h1>
<a href="addproduct" >add products</a>
</h1>
<h1>
<a href="viewallproducts.jsp"> view all products</a>
</h1>
<%}
else{ %>
<h1> <a href="MerchantLoginForm.jsp">Please login first</a></h1>
<%} %>
</body>
</html>
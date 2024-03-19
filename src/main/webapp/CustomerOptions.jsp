<%@page import="com.jsp.shoppingCart.Dto.Customer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%Customer c=(Customer)session.getAttribute("customerinfo");
if(c!=null){
%>
<h1  style=color:green>${msg}</h1>
<h1><a href="displayproducts">Display all products</a></h1> <br>
<h1><a href="readbrandfromcustomer.jsp">Display products by brand</a></h1>
<h1><a href="readfromcategory.jsp">Display products by category</a></h1>
<h1><a href="readbetweenrange.jsp">Display between range</a></h1>

<%} else{ %>
<h1><a href="CustomerLogin.jsp">please login first</a></h1>
<%} %>
</body>
</html>
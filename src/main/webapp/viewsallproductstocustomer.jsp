<%@page import="com.jsp.shoppingCart.Dto.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%List<Product> products=(List<Product>)request.getAttribute("productlist");
%>
 <a href="fetchitemsfromcart" > view cart</a>
<table cellpadding="20px" border="1">
<th>id</th>
<th>brand</th>
<th>category</th>
<th>model</th>
<th>price</th>
<th>Add to cart</th>
<%for(Product p: products) {%>
<tr>

<td><%=p.getId() %> </td>
<td><%= p.getBrand() %></td>
<td><%= p.getCategory() %></td>
<td><%=p.getModel() %></td>
<td><%=p.getPrice() %></td>
<td> <a href="additem?id=<%=p.getId() %>">add</a></td>
</tr>
<% } %>
</table>
</body>
</html>
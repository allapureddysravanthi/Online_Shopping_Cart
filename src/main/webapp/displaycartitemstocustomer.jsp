<%@page import="com.jsp.shoppingCart.Dto.Item"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style >
        #buy
		{
			background-color:yellow;
			color:black;
		    display:inline-block ;
		}
		</style>
</head>
<body>
<% 
List<Item>items =(List<Item>)request.getAttribute("itemslist");
double totalprice=(double)request.getAttribute("totalprice");
%>
<table cellPadding="20px" border="1">
<th>Brand</th>
<th>Model</th>
<th>Category</th>
<th>Price</th>
<th>Quantity</th>
<%
for(Item i:items)
{
%>
<tr>
      <td><%=i.getBrand()%></td>
      <td><%=i.getModel()%></td>
      <td><%=i.getCategory()%></td>
      <td><%=i.getPrice()%></td>
      <td><%=i.getQuantity()%></td>    
</tr>
<%} %>

</table>
Total Price:<%=totalprice %><br>
<div id="buy">
<a href="addorder">Buy Now</a>
</div>
</body>
</html>
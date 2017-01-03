<%--

  OsgiCalling component.

  

--%><%
%><%@include file="/libs/foundation/global.jsp"%><%
%><%@page session="false" %><%
%>
<%@ page import="com.mycompany.myrestservice.MyOsgiClass" %>  
<%= properties.get("title", currentPage.getTitle()) %>
</br>
<% MyOsgiClass mosgc = new MyOsgiClass(); %>  
<%= mosgc.displayMessage("OSGI Class Executed") %>
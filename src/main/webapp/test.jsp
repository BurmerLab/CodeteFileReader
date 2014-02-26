<%-- 
    Document   : test
    Created on : 2014-02-26, 21:05:43
    Author     : Michał
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        XSS Injection... try it with and without protection </br>
        <%String xss="<script type=\"text/javascript\">alert(\"aaa!\");</script>";%>
        
        <%System.out.println("<script type=\"text/javascript\">alert(\"aaa!\");</script>");%>
        <c:out value="<script type=\"text/javascript\"\>alert(\"aaa!\");</script>"></c:out>
        <form action="XSSprotection.jsp" method="post">
            <input type="text" name="name" size="50" value="<c:out value="<script type=\"text/javascript\"\>alert(\"aaa!\");</script>"></c:out>"/>
            <input type="submit" value="with XSS protected (c:out)"/>
        </form>
        <form action="XSSInjection.jsp" method="post">
            <input type="text" size="50" name="name" value="<c:out value="<script type=\"text/javascript\"\>alert(\"aaa!\");</script>"></c:out>"/>
            <input type="submit" value="XSS injection"/>
        </form>
    </body>
</html>

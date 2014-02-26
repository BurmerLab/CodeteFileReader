<%-- 
    Document   : table
    Created on : 2014-02-26, 18:58:24
    Author     : Michał
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="pl.codete.database.dao.RowsDAO"%>
<%@page import="pl.codete.pojo.Raport"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.Connection"%>
<%@page import="pl.codete.database.Database"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
  </head>
  <body>
    <c:out value="BLALBALBLLALBLALA"></c:out>
      <%
       Database database = new Database();
       Connection connection = database.GetConnection();
       ArrayList<String> tablesNames = RowsDAO.findNamesAllTablesInDB(connection);
       List<Raport> list = RowsDAO.obtainAllRaportObjects(connection, RowsDAO.findNamesAllTablesInDB(connection));
       %>
       <table border="1">
           <td>Tabela</td>
          <%
             for(String name : tablesNames){
               out.println("<td>" + name + "</td>");
             }
          %>
         </tr>   
         <tr>
           <td>Srednia</td>
          <%
            for(Raport raport : list){
              out.println("<td>" + raport.getAvgValue() + "</td>");
            }
          %>
         </tr>
         <tr>
           <td>Min</td>
            <%
              for(Raport raport : list){
                out.println("<td>" + raport.getMinValue() + "</td>");
              }
              %>
          </tr>
          <tr>
            <td>Max</td>
           <%
              for(Raport raport : list){
                out.println("<td>" + raport.getMaxValue() + "</td>");
              }
          %>
         </tr>
       </table>
    
                
  </body>
</html>

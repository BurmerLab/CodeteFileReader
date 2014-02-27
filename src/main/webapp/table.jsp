<%-- 
    Document   : table
    Created on : 2014-02-26, 18:58:24
    Author     : Michał
--%>

<%@page import="java.text.NumberFormat"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.text.DecimalFormat"%>
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
    <title>Tabela</title>
  </head>
  <body>
    <%
    String a = "sdad";
    %>
    <c:out value="BLALBALBLLALBLALA"></c:out>
    <%request.setAttribute(a, "lololo");%>
    <c:out value="${a}"/>
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
            NumberFormat formatter = new DecimalFormat("#0.00");
            for(Raport raport : list){
              out.println("<td>" + formatter.format(raport.getAvgValue()) + "</td>");
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
         
         <div id="table-from-db">
    Dodac jeszcze zeby od poczatku ladowaly sie wartosci
    ok ten text sie laduje i nastepny trzeba zrobic w stronie tableWithContent
    <script type="text/javascript">// <![CDATA[
      $(document).ready(function() {
      $.ajaxSetup({ cache: false }); // This part addresses an IE bug. without it, IE will only load the first number and will never refresh
      setInterval(function() {
      $('#table-from-db').load('tableWithContent.jsp');
      }, 3000);
      });
    // ]]></script>
         
  </body>
</html>

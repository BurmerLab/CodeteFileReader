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
    <!--<meta http-equiv="refresh" content="60" >-->
  </head>
  <body>
     <div id="table-from-db"></div>
      <%Database database = new Database();
       Connection connection = database.GetConnection();
       ArrayList<String> tablesNames = RowsDAO.findNamesAllTablesInDB(connection);
       List<Raport> list = RowsDAO.obtainAllRaportObjects(connection, RowsDAO.findNamesAllTablesInDB(connection));
       %>
       <table border="1">
           <td>Tabela</td>
            <%for(String name : tablesNames){
               out.println("<td><b>" + name + "</b></td>");
             }%>
         </tr>   
         <tr>
           <td>Srednia</td>
            <%NumberFormat formatter = new DecimalFormat("#0.00");
            for(Raport raport : list){
              out.println("<td>" + formatter.format(raport.getAvgValue()) + "</td>");
            }%>
         </tr>
         <tr>
           <td>Min</td>
           <%for(Raport raport : list){
                out.println("<td>" + raport.getMinValue() + "</td>");
              }%>
          </tr>
          <tr>
            <td>Max</td>
            <%for(Raport raport : list){
                out.println("<td>" + raport.getMaxValue() + "</td>");
              }%>
         </tr>
       </table>
  </body>
</html>

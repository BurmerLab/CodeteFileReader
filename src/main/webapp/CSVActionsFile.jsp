<%-- 
    Document   : CSVActionsFile
    Created on : 2014-02-25, 21:43:09
    Author     : Michał
--%>

<%@page import="pl.codete.reader.CSVReaderTwo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Preparing and saving</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
  </head>
  
  <%
    int fileNumber = Integer.parseInt(request.getParameter("count"));
    out.println("test w : " + fileNumber);
    
   CSVReaderTwo.parseAndAddToDataBaseFile(fileNumber);
    
  %>
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
    
  </div>
  </body>
</html>

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
    out.println("Zapisano do bazy rekordy z liczby plikow: " + fileNumber);
    for(int x=0; x<=fileNumber; x++){
      CSVReaderTwo.parseAndAddToDataBaseFile(fileNumber);
    }
    
  %>
  
    
  </div>
  </body>
</html>

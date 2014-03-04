<%@page import="pl.codete.reader.CSVReader"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Preparing and saving</title>
    
  </head>
  <%
    int fileNumber = Integer.parseInt(request.getParameter("count"));
    String systemType = request.getParameter("system");
    
    out.println("Saved to Data Base records from file number: " + fileNumber);
    for(int x=0; x<=fileNumber; x++){
      CSVReader.runApplication(fileNumber, systemType);
    }
  %>
  
    
  </div>
  </body>
</html>

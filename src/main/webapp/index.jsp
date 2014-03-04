<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CSV Actions</title>
    </head>
    <body>
      <form action="CSVActionsFile.jsp" method="get">
        <p>Wpisz liczbe plikow ktorych chcesz zaladowac:</p>
        <input type="text" name="count"/>
        <p>Insert type of system you use:</p>
        <select name="system">
          <option>windows</option>
          <option>linux</option>
        </select>
        <input type="submit" value="Do Dziela"/>
      </form>
      <a href="table.jsp" target="_blank">Tabela z danymi</a>
    </body>
</html>

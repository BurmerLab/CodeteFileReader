<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.0/jquery.min.js"></script>
        <script>
        $(document).ready(function () {
        var seconds = 60000; // time in milliseconds
        var reload = function() {
           $.ajax({
              url: 'tableContent.jsp',
              cache: false,
              success: function(data) {
                  $('#refreshDIV').html(data);
                  setTimeout(function() {
                     reload();
                  }, seconds);
              }
           });
         };
         reload();
        });
        </script>
    </head>
    <body>
        <div id="refreshDIV"></div>
    </body>
</html>





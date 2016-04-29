
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Detail artist</h1>



        <p>
            ${artist.name} 
        </p>
        <p>
            ${artist.surname}  
        </p>                                
        <p>
            <a href="${pageContext.request.contextPath}/index">home</a>
        </p>
    </body>
</html>

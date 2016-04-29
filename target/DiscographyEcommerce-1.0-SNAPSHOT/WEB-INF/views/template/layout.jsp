<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  
    "http://www.w3.org/TR/html4/loose.dtd">  
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
       
  
<html lang="en">  
    <head>  
        <meta name="viewport" content="width=device-width, initial-scale=1">
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>

        <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css"/>"/>
        
        
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
              
        <!-- per login page -------------------------->

        <!-- Favicon -->
        <link rel="shortcut icon" type="image/png" href="http://www.deep-purple.it/wp-content/uploads/1999/11/deep-purple-machine-head.jpg"/>
        
        <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
        <link href="${pageContext.request.contextPath}/resources/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="${pageContext.request.contextPath}/resources/css/signin.css" rel="stylesheet">

        <!-- ------------------------------------------------------------>                 

        <link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet"/>
        
        

        
    </head>  
    <body>  
        <div>
            <tiles:insertAttribute name="header" />
        </div>  
        <!--div style="float:left;padding:10px;width:15%;">
            <!--tiles:insertAttribute name="menu" /-->
        <!--/div-->  
        <div >  
            <tiles:insertAttribute name="body" />
        </div>  
        <div style="clear:both">
            <tiles:insertAttribute name="footer" />
        </div>  

    </body>  
</html>  
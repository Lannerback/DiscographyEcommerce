<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  
    "http://www.w3.org/TR/html4/loose.dtd">  
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet" />
        <script src="<c:url value="/resources/js/jquery-1.11.1/jquery.min.js"/>"></script>
        <!-- Favicon -->
        <link rel="shortcut icon" type="image/png" href="http://www.deep-purple.it/wp-content/uploads/1999/11/deep-purple-machine-head.jpg"/>
        
        <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
        <link href="${pageContext.request.contextPath}/resources/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="${pageContext.request.contextPath}/resources/css/signin.css" rel="stylesheet">

        <link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet"/>

        <title><tiles:insertAttribute name="title" ignore="true" /></title>  
    </head>
    <body>
        <div>
            <tiles:insertAttribute name="header" />
        </div>  
        <div>
            <tiles:insertAttribute name="menu" />
        </div>  
        <div>  
            <tiles:insertAttribute name="body" />
        </div>  
        <div>
            <tiles:insertAttribute name="footer" />
        </div>  
    </body>
</html>

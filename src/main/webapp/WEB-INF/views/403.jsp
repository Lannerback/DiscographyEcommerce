<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>		
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">   
        <title>Spring Security Tutorial - Form</title>    
	</head>
	<body>
		<H1>Oops! You are not authorized!</H1>
		<p><a href="<c:url value='/login'/>">Back to Login</a></p>
	</body>
</html>

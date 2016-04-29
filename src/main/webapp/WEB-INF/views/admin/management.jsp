<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h1>Admin</h1>

<a href="${pageContext.request.contextPath}/admin/album/list">Gestione CD</a>
<a href="${pageContext.request.contextPath}/admin/artist/list">Gestione artisti</a>   
<a href="${pageContext.request.contextPath}/logout"> logout </a>


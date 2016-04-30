<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>  
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>                        
            </button>
            <!--div class="icontitle">  
            <img src="http://www.deep-purple.it/wp-content/uploads/1999/11/deep-purple-machine-head.jpg">
            </div-->
            <a class="navbar-brand" href="${pageContext.request.contextPath}">Discommerce</a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">                                    
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                            Dropdown Menu
                            <span class="caret"></span>
                        </a>
                            <ul class="dropdown-menu">
                                <li><a href="${pageContext.request.contextPath}/initalbumartist"><b>INIZIALIZZA ALBUM E ARTISTA</b></a></li>
                                <li><a href="${pageContext.request.contextPath}/initrole">crea role</a></li>
                                <li><a href="${pageContext.request.contextPath}/initadmin">crea user</a></li>
                                <li><a href="${pageContext.request.contextPath}/addorder/1">crea ordine</a></li> 
                                <li><a href="${pageContext.request.contextPath}/addrole/1">crea collegamento user_role</a></li>
                                <li><a href="${pageContext.request.contextPath}/initusererole">creauserconrole</a></li>
                                <li><a href="${pageContext.request.contextPath}/initall">INITALL</a></li>
                            </ul>
                       
                    </li>
                <sec:authorize access="hasAnyRole('ROLE_USER','ROLE_ADMIN')">
                    <li><a href="${pageContext.request.contextPath}/logout"> Logout</a></li>
                </sec:authorize>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <sec:authorize access="isAnonymous()">
                    <li><a href="${pageContext.request.contextPath}/user"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
                    <li><a href="${pageContext.request.contextPath}/registration"><span class="glyphicon glyphicon-log-in"></span> Register</a></li>
                </sec:authorize>
                
            </ul>
        </div>
    </div>
</nav>

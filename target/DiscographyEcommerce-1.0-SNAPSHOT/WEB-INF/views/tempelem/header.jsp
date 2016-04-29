<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>  
<div class="header">
    <div class="container">
        <div class="row">
            
            <div class="col-md-5"> 
                <div class="icontitle">                           
                    <img src="http://www.deep-purple.it/wp-content/uploads/1999/11/deep-purple-machine-head.jpg">                                               
                    <a href="${pageContext.request.contextPath}/"><h1>Discommerce</h1></a>
                </div>
            </div>               


           
            
            <div class="col-md-7">

                <ul class="list-inline menu">
                    <sec:authorize access="isAnonymous()">
                    <li><a href="${pageContext.request.contextPath}/user">Login</a></li>
                    <li><a href="${pageContext.request.contextPath}/registration">Register</a></li>
                    </sec:authorize>
                    <li><a href="#">Search Artist</a></li>
                    <li><a href="#">Search CD</a></li>
                    <sec:authorize access="hasAnyRole('ROLE_USER','ROLE_ADMIN')">
                    <li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
                    </sec:authorize>
                </ul>                  
            </div>                
        </div>
    </div> 
</div>

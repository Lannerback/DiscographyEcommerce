<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        
        <title>Login</title>
        <c:if test="${not empty param.err}">
            <div class="msg-container error">              
                <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>
            </div>
        </c:if>
        <c:if test="${not empty param.out}">
            <div class="msg-container logout">
                You've logged out successfully.
            </div>
        </c:if>
        <c:if test="${not empty param.time}">
            <div class="msg-container time">
                You've been logged out due to inactivity.
            </div>
        </c:if>

        <div id="loginForm" class="container">
            <form class="form-signin" action="${pageContext.request.contextPath}/j_spring_security_check" method="post">
                <h2 class="form-signin-heading">Login</h2>
                <label for="username" class="sr-only">Username</label>
                <input type="text" id="username" name="username" class="form-control" placeholder="username" required autofocus>
                <label for="password" class="sr-only">Password</label>
                <input type="password" id="password" name="password" class="form-control" placeholder="password" required>
                <!--div class="checkbox">
                  <label>
                    <input type="checkbox" value="remember-me"> Remember me
                  </label>
                </div-->
                <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
                <input type="hidden" name="action" value="login"/>
            </form>

        </div> <!-- /container -->

        <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
        <script src="${pageContext.request.contextPath}/resources/js/ie10-viewport-bug-workaround.js"></script>
    
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/homestyle.css">
        <title>AEGiS Content Management System</title>
    </head>
    <body>
        <div>
            <h2>Sign in to AEGiS Content Manager System</h2>
            <c:if test="${param.login_error == 1}">
                <h3>Wrong id or password!</h3>
            </c:if>
            <form method="post" class="signin"
                  action="j_spring_security_check">
                <fieldset>
                    <table cellspacing="0">
                        <tr>
                            <th>
                                <label for="username">Username
                                </label>
                            </th>
                            <td><input id="username_or_email"
                                       name="j_username"
                                       type="text" />
                            </td>
                        </tr>
                        <tr>
                            <th><label for="password">Password</label></th>
                            <td><input id="password"
                                       name="j_password"
                                       type="password" />
                            </td>
                        </tr>
                        <tr>
                            <th></th>
                            <td><input name="commit" type="submit"
                                       value="Sign In" /></td>
                        </tr>
                    </table>
                </fieldset>
            </form>
            <footer class="bot">
                <p>copyright&copy 2015 AEGiS All Rights Reserved  <a href="${pageContext.request.contextPath}/home">Return Home</a></p>
            </footer>
        </div>
    </body>
</html>

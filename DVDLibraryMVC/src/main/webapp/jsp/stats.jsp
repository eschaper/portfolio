<%-- 
    Document   : stats
    Created on : Oct 16, 2015, 11:10:58 AM
    Author     : apprentice
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>DVD Library</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
              rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/extras.css"
              rel="stylesheet">
        <!-- SWC Icon -->
        <link rel="shortcut icon"
              href="${pageContext.request.contextPath}/img/icon.png">
    </head>
    <body>
        <div class="container">
            <h1>DVD Library</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-pills">
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/home">Home</a></li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/search">Search</a></li>
                    <li role="presentation" class="active">
                        <a href="${pageContext.request.contextPath}/stats">Stats</a></li>
                </ul>
            </div>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>

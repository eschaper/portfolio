<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Company Contacts</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">

    </head>
    <body>
        <div class="container">
            <h1>Company Contacts</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/home">Home</a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/mainAjaxPage">Contact List
                            (Ajax)</a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/search">Search</a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/stats">Stats</a>
                    </li>
                    <li role="presentation" class="active">
                        <a href="${pageContext.request.contextPath}/displayContactListNoAjax">
                            Contact List (No Ajax)</a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/j_spring_security_logout">Log Out</a>
                    </li>
                </ul>   
            </div>              
        </div>

        <div class="container">
            <h1>Company Contacts</h1>
            <!-- #1 - Link to addContactForm -->
            Hello <sec:authentication property="principal.username" />!<br/>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
            <a href="displayNewContactFormNoAjax">Add a Contact</a><br/>
            </sec:authorize>
            <hr/>

            <!-- #2 - Iterate over contactList: forEach contact in contactList, do something -->
            <c:forEach var="contact" items="${contactList}">
                <!-- #3 - Build custom delete url for each contact.  Use the id of the contact -->
                <!--      to specify the contact to delete or update                           -->
                <s:url value="deleteContactNoAjax"
                       var="deleteContact_url">
                    <s:param name="contactId" value="${contact.contactId}" />
                </s:url>
                <!-- Build custom edit url for each contact -->
                <s:url value="displayEditContactFormNoAjax"
                       var="editContact_url">
                    <s:param name="contactId" value="${contact.contactId}" />
                </s:url>
                <!-- #4 - A pointless demonstration of the if tag -->
                <c:if test="${contact.lastName == 'Doe'}">
                    CEO<br/>
                </c:if>
                Name: ${contact.firstName} ${contact.lastName} |
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                <a href="${deleteContact_url}">Delete</a> |
                </sec:authorize>
                <a href="${editContact_url}">Edit</a><br/>
                Phone: ${contact.phone}<br/>
                Email: ${contact.email}<br/>
                <hr>
            </c:forEach>
        </div>

        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

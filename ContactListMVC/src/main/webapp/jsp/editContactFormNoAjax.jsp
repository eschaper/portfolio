<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- #1 - Directive for Spring Form tag libraries -->
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
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
        </div>

        <div class="container">
            <h1>Edit Contact</h1>
            <a href="displayContactListNoAjax">Contact List (No Ajax)</a><br/>
            <hr/>
            <sf:form class="form-horizontal" role="form" modelAttribute="contact" action="editContactNoAjax" method="POST">
                <div class="form-group">
                    <label for="add-first-name" class="col-md-4 control-label">First Name:</label>
                    <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-first-name" path="firstName" placeholder="First Name"/>
                        <!-- #1 - error tag -->
                        <sf:errors path="firstName" cssclass="error"></sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-last-name" class="col-md-4 control-label">Last Name:</label>
                        <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-last-name" path="lastName" placeholder="Last Name"/>
                        <sf:errors path="lastName" cssclass="error"></sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-company" class="col-md-4 control-label">Company:</label>
                        <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-company" path="company" placeholder="Company"/>
                        <sf:errors path="company" cssclass="error"></sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-email" class="col-md-4 control-label">Email:</label>
                        <div class="col-md-8">
                        <sf:input type="email" class="form-control" id="add-email" path="email" placeholder="Email"/>
                        <sf:errors path="email" cssclass="error"></sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-phone" class="col-md-4 control-label">Phone:</label>
                        <div class="col-md-8">
                        <sf:input type="tel" class="form-control" id="add-phone" path="phone" placeholder="Phone"/>
                        <sf:errors path="phone" cssclass="error"></sf:errors>
                        <sf:hidden path="contactId"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-4 col-md-8">
                        <button type="submit" id="add-button" class="btn btn-default">Save Changes</button>
                    </div>
                </div>
            </sf:form>
        </div>

        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

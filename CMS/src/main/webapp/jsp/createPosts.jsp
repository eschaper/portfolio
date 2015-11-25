<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@taglib prefix = "s" uri="http://www.springframework.org/tags"  %>
<%@taglib prefix = "fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix = "sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>AEGiS Content Management System</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/js/jquery-ui.min.css" rel="stylesheet">
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.gif">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/homestyle.css">
    </head>
    <body>
        <div class="container">
            <h1>Create a Post</h1>
            <br>
        </div> <!--end of head container -->
        <div class="container">
            <div class="nav bar"> 
                <ul class="nav nav-pills">
                    <li role="presentation" class="active">
                        <a href="${pageContext.request.contextPath}/createPosts">Create Posts</a>
                    </li>
                    <sec:authorize access ="hasRole('ROLE_ADMIN')"> 
                        <li role="presentation">
                            <a href="${pageContext.request.contextPath}/postMan">Manage Posts</a>
                        </li>
                        <li role="presentation">
                            <a href="${pageContext.request.contextPath}/userMan">Manage Users</a>
                        </li>
                        <li role="presentation">
                            <a href="${pageContext.request.contextPath}/staticMan">Manage Static Area</a>
                        </li>
                    </sec:authorize> 
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/home">View Blog</a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/j_spring_security_logout">Logout</a>
                    </li>
                </ul>
            </div>
        </div> <!--end of nav container -->

        <div class="row">
            <div class="container">
                <br>
                <p><strong>Add New Post</strong></p>
                <div class="container-fluid col-md-10" style="background: whitesmoke; border-radius: 5px; border: 5px azure; padding: 15px;">
                    <form class="form" role="form">
                        <div class="form-group">
                            <label for="add-title">
                                Title:
                            </label>
                            <input type="text" class="form-control"
                                   id="add-title" placeholder="Title"/>
                        </div>
                        <div class="form-group">
                            <label for="add-postDate">
                                Post Date:
                            </label>
                            <input type="text" class="form-control"
                                   id="add-postDate" />
                        </div>
                        <div class="form-group">
                            <label for="add-expiration">
                                Expiration Date:
                            </label>
                            <input type="text" class="form-control"
                                   id="add-expiration" />
                        </div>
                        <div class="form-group">
                            <label for="bodytextarea">
                                Body:
                            </label>
                            <input type="textarea"class="form-control"
                                   id="bodytextarea" placeholder="Body"/>
                        </div>
                        <div class="form-group">
                            <label for="add-tags">
                                Tags:
                            </label>
                            <input type="text"class="form-control"
                                   id="add-tags" placeholder="Tags"/>
                        </div>
                        <div class="form-group">
                            <div>
                                <button type="submit" id="add-post-button" class="btn btn-default">
                                    Add Post
                                </button>
                            </div>
                        </div>
                    </form>
                    <div id="validationErrors" style="color: tomato"></div>
                </div> <!-- end of right container -->
            </div> <!-- end of body container -->
        </div> <!--end of row column -->
        <footer class="bot">
            <p>copyright&copy 2015 AEGiS All Rights Reserved</p>
        </footer> <!-- end of footer -->
    </div>

    <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery-ui.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tinymce/tinymce.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/createposts.js"></script>
</body>
</html>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>AEGiS Content Management System</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.gif">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/homestyle.css">
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/tinymce/tinymce.min.js"></script>
    </head>
    <body>
        <div class="container">
            <h1>Manage Static Content</h1>
            <br>
        </div> <!--end of head container -->
        <div class="container">
            <div class="nav bar"> 
                <ul class="nav nav-pills">
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/createPosts">Create Posts</a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/postMan">Manage Posts</a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/userMan">Manage Users</a>
                    </li>
                    <li role="presentation" class="active">
                        <a href="${pageContext.request.contextPath}/staticMan">Manage Static Area</a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/home">View Blog</a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/j_spring_security_logout">Logout</a>
                    </li>
                </ul>
            </div>
        </div><!--end of nav container -->

        <div class="container">
            <br>
            <p><strong>Enter Static Content</strong></p>
            <div class="container-fluid col-md-10" style="background: whitesmoke; border-radius: 5px; border: 5px azure; padding: 15px;">
                <div class="form-group">

                    <form>
                        <div class="form-group">                        
                            <input type="textarea"class="form-control"
                                   id="staticContent" placeholder="Body"/>
                        </div>
                    </form>
                    <div class="form-group">
                        <button type="submit" id="add-content-button" class="btn btn-default">
                            Add
                        </button>
                        <div id="completion" class="text-success"></div>
                    </div>
                </div>
                <footer class="bot">
                    <p>copyright&copy 2015 AEGiS All Rights Reserved</p>
                </footer>
            </div>
        </div> <!--end of body container -->
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/staticContentManager.js"></script>
    </body>
</html>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>AEGiS Content Management System</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.gif">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/homestyle.css">
    </head>
    <body>
        <div class="container">
            <h1>Manage Users</h1>
            <br>
        </div><!--end of title container -->
        <div class="container">
            <div class="nav bar"> 
                <ul class="nav nav-pills">
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/createPosts">Create Posts</a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/postMan">Manage Posts</a>
                    </li>
                    <li role="presentation" class="active">
                        <a href="${pageContext.request.contextPath}/userMan">Manage Users</a>
                    </li>
                    <li role="presentation">
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
            <div class="row">
                <br>
                <div class="col-md-8">
                    <p><strong>Existing Users</strong></p>
                    <div class="container-fluid" style="background: whitesmoke; border-radius: 5px; border: 5px azure; padding: 15px;">
                        <table class="table table-hover">
                            <tr>
                                <th width="25%">Public Name</th>
                                <th width="25%">User Name</th>
                                <th width="20%">Authority</th>
                                <th width="15%"></th>
                                <th width="15%"></th>
                            </tr>
                            <tbody id="userTable"></tbody>
                        </table>
                    </div> <!-- end of right container  -->
                </div> <!-- end of right column -->

                <div class="col-md-4">
                    <label>Add a New User</label>
                    <div class="container-fluid" style="background: whitesmoke; border-radius: 5px; border: azure; padding: 15px;">

                        <form class="form" role="form">
                            <div class="form-group">    
                                <label for="add-publicname">
                                    Public Name:
                                </label>
                                <input type="text" class="form-control"
                                       id="add-publicname" placeholder="Name"/>
                            </div>
                            <div class="form-group">
                                <label for="add-username">
                                    User Name:
                                </label>
                                <input type="text" class="form-control"
                                       id="add-username" placeholder="Display Name"/>
                            </div>
                            <div class="form-group">
                                <label for="add-password">
                                    Password:
                                </label>
                                <input type="password" class="form-control"
                                       id="add-password" placeholder="Password"/>
                            </div>
                            <div class="form-group">
                                <label for="add-authority">
                                    Authority:
                                </label>
                                <select class="form-control" id="add-authority">
                                    <option>ROLE_ADMIN</option>
                                    <option>ROLE_USER</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <button type="submit" id="add-button" class="btn btn-default">
                                    Create User
                                </button>
                            </div>
                        </form>
                        <div id="validationErrors" style="color: tomato"></div>
                    </div> <!-- end of left container -->
                </div> <!-- end of left column -->
            </div> <!-- end of row -->        
        </div><!--end of body container -->
        <!--Edit modal-->
        <div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="detailsModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">
                            <span aria-hidden="true">&times;</span>
                            <span class="sr-only">Close</span></button>
                        <h4 class="modal-title" id="detailsModalLabel">Edit User</h4>
                    </div>
                    <div class="modal-body">
                        <form class="form" role="form">
                            <div class="form-group">
                                <label for="edit-publicname">
                                    Name:
                                </label>
                                <input type="text" class="form-control" id="edit-publicname" placeholder="Name">
                            </div> 
                            <div class="form-group">
                                <label for="edit-username">
                                    User Name:
                                </label>
                                <input type="text" class="form-control" id="edit-username" placeholder="User Name">
                            </div>
                            <div class="form-group">
                                <label for="edit-authority">
                                    Authority:
                                </label>
                                <select class="form-control" id="edit-authority">
                                    <option>ROLE_ADMIN</option>
                                    <option>ROLE_USER</option>
                                </select>
                            </div> 
                            <div class="form-group">
                                <label for="edit-password">
                                    Password:
                                </label>
                                <input type="password" class="form-control" id="edit-password" placeholder="Password">
                            </div>
                            <div class="form-group">
                                <button type="submit" id="edit-button" class="btn btn-default">
                                    Edit User
                                </button>
                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">
                                    Cancel
                                </button>
                                <input type="hidden" id="edit-user-id">
                            </div>
                        </form> 
                        <div id="validationEditErrors" style="color: tomato"></div>
                    </div>
                </div> 
            </div>
        </div>
        <footer class="bot">
            <p>copyright&copy 2015 AEGiS All Rights Reserved</p>
        </footer>

        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/userManager.js"></script>
    </body>
</html>

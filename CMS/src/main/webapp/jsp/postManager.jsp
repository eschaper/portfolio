<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>AEGiS Content Management System</title>
        <link href="${pageContext.request.contextPath}/js/jquery-ui.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.gif">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/homestyle.css">
        <style>
            .clsDatePicker {
                z-index: 100000;
            }
        </style>

    </head>
    <body>
        <!--nav bar-->
        <div class="container">
            <h1>Manage Posts</h1>
            <br>
        </div> <!--end of title container -->
        <div class="container">
            <div class="nav bar"> 
                <ul class="nav nav-pills">
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/createPosts">Create Posts</a>
                    </li>
                    <li role="presentation" class="active">
                        <a href="${pageContext.request.contextPath}/postMan">Manage Posts</a>
                    </li>
                    <li role="presentation">
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
                    <p><strong>Existing Posts</strong></p>
                    <div class="container-fluid" style="background: whitesmoke; border-radius: 5px; border: 5px azure; padding: 15px;">
                        <table class="table table-hover">
                            <tr>
                                <th width="15%">Post Date</th>
                                <th width="25%">Title</th>
                                <th width="15%">Author</th>
                                <th width="15%">Expiration Date</th>
                                <th width="10%">Published?</th>
                                <th width="10%"></th>
                                <th width="10%"></th>
                            </tr>
                            <tbody id="contentRows"></tbody>
                        </table>
                    </div> <!-- end left column -->
                </div> <!-- end right column -->
                <div class="col-md-3 radio">
                    <p><strong>Filter Posts</strong></p>
                    <div class="container-fluid" style="background: whitesmoke; border-radius: 5px; border: 5px azure; padding: 15px;">
                        <div class="radio" data-toggle="buttons">
                            <label><input type="radio" id="radio-all" checked>All</label>
                        </div>
                        <div class="radio">
                            <label><input type="radio" id="radio-exp">Expired</label>
                        </div>
                        <div class="radio">
                            <label><input type="radio" id="radio-unpub">Not Published</label>
                        </div>
                    </div> 
                </div> <!-- end left column -->  
            </div><!-- End row  -->  
        </div><!--end of body container -->

        <!--Details Modal-->
        <div class="modal fade" id="detailsModal" tabindex="-1" role="dialog"
             aria-labelledby="detailsModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">
                            <span aria-hidden="true">&times;</span>
                            <span class="sr-only">Close</span>
                        </button>
                        <h4 class="modal-title" id="detailsModalLabel">Post Details</h4>
                    </div>
                    <div class="modal-body">
                        <h3 id="details-postId"></h3>
                        <table class="table">
                            <tr><td><strong>Title:</strong></td></tr>
                            <tr><td id="title"></td></tr>
                            <tr><td><strong>Author:</strong></td></tr>
                            <tr><td id="author"></td></tr>
                            <tr><td><strong>Body:</strong></td></tr>
                            <tr><td><div id="body"></div></td></tr>
                            <tr><td><strong>Post Date:</strong></td></tr>
                            <tr><td id="postDate"></td></tr>
                            <tr><td><strong>Expiration:</strong></td></tr>
                            <tr><td id="expiration"></td></tr>
                            <tr><td><strong>Published:</strong></td></tr>
                            <tr><td id="isPublished"></td></tr>
                            <tr><td><strong>Tags:</strong></td></tr>
                            <tr><td id="tags"></td></tr>
                        </table>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">
                            Close
                        </button>
                    </div>
                </div>
            </div>
        </div>

        <!-- Edit Modal -->
        <div class="modal fade" id="editModal" tabindex="-1" role="dialog"
             aria-labelledby="detailsModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">
                            <span aria-hidden="true">&times;</span>
                            <span class="sr-only">Close</span></button>
                        <h4 class="modal-title" id="detailsModalLabel">Edit Post</h4>
                    </div>
                    <div class="modal-body">
                        <h3 id="edit-postId"></h3>
                        <form class="form" role="form">
                            <div class="form-group">
                                <label for="edit-title">
                                    Title:
                                </label>
                                <input type="text" class="form-control" id="edit-title">
                            </div>
                            <div class="form-group">
                                <label for="edit-body">
                                    Body:
                                </label>
                                <div id="edit-body" class=""></div>
                            </div>
                            <div class="form-group">
                                <label for="edit-postDate">
                                    Post Date:
                                </label>
                                <input type="text" class="clsdatepicker form-control" id="edit-postDate">
                            </div>
                            <div class="form-group">
                                <label for="edit-expiration">
                                    Expiration Date:
                                </label>
                                <input type="text" class="clsdatepicker form-control" id="edit-expiration">
                            </div>
                            <div class="form-group">
                                <label for="edit-tags">
                                    Tags:
                                </label>
                                <input type="text" class="form-control" id="edit-tags">
                            </div>
                            <div class="form-group">
                                <div>
                                    <button  id="edit-button" class="btn btn-default">
                                        Edit Post
                                    </button>
                                    <button id="edit-modal-cancel" type="button" class="btn btn-default" data-dismiss="modal">
                                        Cancel
                                    </button>
                                    <input type="hidden" id="edit-address-id">
                                </div>
                            </div>
                        </form>
                        <div id="validationErrorsModal" style="color: red"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <footer class="bot">
        <p>copyright&copy 2015 AEGiS All Rights Reserved</p>
    </footer>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tinymce/tinymce.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery-ui.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/postManager.js"></script>
</body>
</html>
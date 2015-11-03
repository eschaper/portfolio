<%-- 
    Document   : home
    Created on : Oct 16, 2015, 10:19:56 AM
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
                    <li role="presentation" class="active">
                        <a href="${pageContext.request.contextPath}/home">Home</a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/search">Search</a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/stats">Stats</a>
                    </li
                </ul>
            </div>

            <div class="col-md-8">
                <h2>DVD Library</h2>
                <table id="dvdTable" class="table table-hover">
                    <tr>
                        <th width="20%">Title</th>
                        <th width="20%">Release Date</th>
                        <th width="20%">MPAA Rating</th>
                        <th width="20%">Note</th>
                        <th width="10%"></th>
                        <th width="10%"></th>
                    </tr>

                    <tbody id="contentRows">

                    </tbody>
                </table>
            </div> <!-- End left column -->

            <div class="col-md-4">
                <h2>Add New DVD</h2>
                <form class="form-horizontal" role="form">

                    <div class="form-group">
                        <label for="add-title" class="col-md-4 control-label">
                            Title:
                        </label>
                        <div class="col-md-8">
                            <input type="text"
                                   class="form-control"
                                   id="add-title"
                                   placeholder="Movie Name" />                                   
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="add-releaseDate" class="col-md-4 control-label">
                            Release Date:
                        </label>
                        <div class="col-md-8">
                            <input type="text"
                                   class="form-control"
                                   id="add-releaseDate"
                                   placeholder="eg: 1997" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="add-mpaaRating" class="col-md-4 control-label">
                            MPAA Rating:
                        </label>
                        <div class="col-md-8">
                            <input type="text"
                                   class="form-control"
                                   id="add-mpaaRating"
                                   placeholder="R, PG-13, G etc..." />
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="add-director" class="col-md-4 control-label">
                            Director:
                        </label>
                        <div class="col-md-8">
                            <input type="text"
                                   class="form-control"
                                   id="add-director"
                                   placeholder="Spielberg" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="add-studio" class="col-md-4 control-label">
                            Studio:
                        </label>
                        <div class="col-md-8">
                            <input type="text"
                                   class="form-control"
                                   id="add-studio"
                                   placeholder="Ghibli" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="add-note" class="col-md-4 control-label">
                            Note:
                        </label>
                        <div class="col-md-8">
                            <input type="text"
                                   class="form-control"
                                   id="add-note"
                                   placeholder="but how are the feelz?" />
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-md-offset-4 col-md-8">
                            <button type="submit"
                                    id="add-button"
                                    class="btn btn-default">
                                Add DVD
                            </button>
                        </div>
                    </div>
                </form>
                <div id="validationErrors" style="color: red"></div>
            </div> <!-- End right column -->

        </div> <!-- End container element -->

        <!-- TODO: finish updating js to auto populate -->

        <div class="modal fade" id="detailsModal" tabindex="-1" role="dialog"
             aria-labelledby="detailsModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">
                            <span aria-hidden="true">&times;</span>
                            <span class="sr-only">Close</span>
                        </button>
                        <h4 class="modal-title" id="detailsModalLabel">DVD Details</h4>
                    </div>
                    <div class="modal-body">
                        <h3 id="dvd-id"></h3>
                        <table class="table table-bordered">
                            <tr>
                                <th>Title:</th>
                                <td id="dvd-title"></td>
                            </tr>
                            <tr>
                                <th>Release Date:</th>
                                <td id="dvd-releaseDate"></td>
                            </tr>
                            <tr>
                                <th>MPAA Rating:</th>
                                <td id="dvd-mpaaRating"></td>
                            </tr>
                            <tr>
                                <th>Director:</th>
                                <td id="dvd-director"></td>
                            </tr>
                            <tr>
                                <th>Studio:</th>
                                <td id="dvd-studio"></td>
                            </tr>
                            <tr>
                                <th>Note:</th>
                                <td id="dvd-note"></td>
                            </tr>
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
                        <h4 class="modal-title" id="detailsModalLabel">Edit DVD</h4>
                    </div>
                    <div class="modal-body">
                        <h3 id="dvd-id"></h3>
                        <form class="form-horizontal" role="form">
                            <div class="form-group">
                                <label for="edit-title" class="col-md-4 control-label">
                                    Title:
                                </label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="edit-title"
                                           placeholder="Movie Title">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit-releaseDate" class="col-md-4 control-label">
                                    Release Date:
                                </label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="edit-releaseDate"
                                           placeholder="1997">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit-mpaaRating" class="col-md-4 control-label">
                                    MPAA Rating: 
                                </label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="edit-mpaaRating"
                                           placeholder="G, PG, etc..">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit-director" class="col-md-4 control-label">
                                    Director:
                                </label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="edit-director"
                                           placeholder="Director">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit-studio" class="col-md-4 control-label">
                                    Studio: 
                                </label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="edit-studio"
                                           placeholder="Studio">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit-note" class="col-md-4 control-label">
                                    Note: 
                                </label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="edit-note"
                                           placeholder="How is it?">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-offset-4 col-md-8">
                                    <button id="edit-button" 
                                            class="btn btn-default">
                                        Edit DVD
                                    </button>
                                    <button id="cancel-edit-modal"
                                            type="button" class="btn btn-default"
                                            data-dismiss="modal">
                                        Cancel
                                    </button>
                                    <input type="hidden" id="edit-dvd-id">
                                </div>
                            </div>
                        </form>
                        <div id="validationErrorsModal" style="color: red"></div>
                    </div>
                </div>
            </div>
        </div>                    



        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/dvdlibrary.js"></script>
    </body>
</html>
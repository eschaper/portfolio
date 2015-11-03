<%-- 
    Document   : search
    Created on : Oct 16, 2015, 10:48:55 AM
    Author     : apprentice
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Company Contacts</title>
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
            <h1 class="colorchange">Company Contacts</h1>
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
                    <li role="presentation" class="active">
                        <a href="${pageContext.request.contextPath}/search">Search</a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/stats">Stats</a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/displayContactListNoAjax">
                            Contact List (No Ajax)</a>
                    </li>
                </ul>
            </div>
            <!--View contacts column-->
            <div class="col-md-6">
                <h2>Searched Contacts</h2>
                <table id="contactTable" class="table table-hover">
                    <tr>
                        <th width="40%">Contact Name</th>
                        <th width="30%">Company</th>
                        <th width="15%"></th>
                        <th width="15%"></th>
                    </tr>

                    <tbody id="contentRows">

                    </tbody>
                </table>
            </div> <!-- End left column -->

            <!-- Add new contact column-->
            <div class="col-md-6">
                <h2>Search Contacts</h2>
                <form class="form-horizontal" role="form">

                    <div class="form-group">
                        <label for="search-first-name" class="col-md-4 control-label">
                            First Name:
                        </label>
                        <div class="col-md-8">
                            <input type="text"
                                   class="form-control"
                                   id="search-first-name"
                                   placeholder="First Name" />                                   
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="search-last-name" class="col-md-4 control-label">
                            Last Name:
                        </label>
                        <div class="col-md-8">
                            <input type="text"
                                   class="form-control"
                                   id="search-last-name"
                                   placeholder="Last Name" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="search-company" class="col-md-4 control-label">
                            Company:
                        </label>
                        <div class="col-md-8">
                            <input type="text"
                                   class="form-control"
                                   id="search-company"
                                   placeholder="Company" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="search-email" class="col-md-4 control-label">
                            Email:
                        </label>
                        <div class="col-md-8">
                            <input type="text"
                                   class="form-control"
                                   id="search-email"
                                   placeholder="Email" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="search-phone" class="col-md-4 control-label">
                            Phone:
                        </label>
                        <div class="col-md-8">
                            <input type="text"
                                   class="form-control"
                                   id="search-phone"
                                   placeholder="Phone" />
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-md-offset-4 col-md-8">
                            <button type="submit"
                                    id="search-button"
                                    class="btn btn-default">
                                Find Contact
                            </button>
                        </div>
                    </div>
                </form>
            </div> <!-- End right column -->

        </div> <!-- End container element -->

        <!-- Details Modal -->
        <!-- The aria- attributes are for Accessible Rich Internet Applications
        standards - their purpose is to make web applications more accessible to 
        people with disabilities. -->

        <div class="modal fade" 
             id="detailsModal" 
             tabindex="-1" 
             role="dialog"
             aria-labelledby="detailsModalLabel" 
             aria-hidden="true">

            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" 
                                class="close" 
                                data-dismiss="modal">

                            <span aria-hidden="true">&times;</span>
                            <span class="sr-only">Close</span>

                        </button>
                        <h4 class="modal-title" 
                            id="detailsModalLabel">
                            Contact Details
                        </h4>

                    </div>

                    <div class="modal-body">
                        <h3 id="contact-id"></h3>
                        <table class="table table-bordered">
                            <tr>
                                <th>First Name:</th>
                                <td id="contact-firstName"></td>
                            </tr>
                            <tr>
                                <th>Last Name:</th>
                                <td id="contact-lastName"></td>
                            </tr>
                            <tr>
                                <th>Company:</th>
                                <td id="contact-company"></td>
                            </tr>
                            <tr>
                                <th>Phone:</th>
                                <td id="contact-phone"></td>
                            </tr>
                            <tr>
                                <th>Email:</th>
                                <td id="contact-email"></td>
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
                        <h4 class="modal-title" id="detailsModalLabel">
                            Edit Contact
                        </h4>
                    </div>
                    <div class="modal-body">
                        <h3 id="contact-id"></h3>
                        <form class="form-horizontal" role="form">
                            <div class="form-group">
                                <label for="edit-first-name" 
                                       class="col-md-4 control-label">
                                    First Name:
                                </label>
                                <div class="col-md-8">
                                    <input type="text" 
                                           class="form-control" 
                                           id="edit-first-name"
                                           placeholder="First Name">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit-last-name" 
                                       class="col-md-4 control-label">
                                    Last Name:
                                </label>
                                <div class="col-md-8">
                                    <input type="text" 
                                           class="form-control" 
                                           id="edit-last-name"
                                           placeholder="Last Name">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit-company" 
                                       class="col-md-4 control-label">
                                    Company:
                                </label>
                                <div class="col-md-8">
                                    <input type="text" 
                                           class="form-control" 
                                           id="edit-company"
                                           placeholder="Company">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit-email" 
                                       class="col-md-4 control-label">
                                    Email:
                                </label>
                                <div class="col-md-8">
                                    <input type="email" 
                                           class="form-control" 
                                           id="edit-email"
                                           placeholder="Email">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit-phone" 
                                       class="col-md-4 control-label">
                                    Phone:
                                </label>
                                <div class="col-md-8">
                                    <input type="tel" 
                                           class="form-control" 
                                           id="edit-phone"
                                           placeholder="Phone">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-offset-4 col-md-8">
                                    <button 
                                        id="edit-button" 
                                        class="btn btn-default">
                                        Edit Contact
                                    </button>
                                    <button type="button" 
                                            class="btn btn-default"
                                            data-dismiss="modal">
                                        Cancel
                                    </button>
                                    <input type="hidden" id="edit-contact-id">
                                </div>
                            </div>
                        </form>
                        <div id="validationErrorsModal" style="color: red"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/contactList.js"></script>
</body>
</html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%--   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> --%>
        <title>Vending Machine</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
              rel="stylesheet">
        <link rel="shortcut icon"
              href="${pageContext.request.contextPath}/img/icon.png">
    </head>
    <body>
        <div class="container">

            <h1 class="text-center">The Lost Vending Machine of Globe University</h1>
            <br>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/index">Home</a>
                    </li>
                    <li role="presentation" class="active">
                        <a href="${pageContext.request.contextPath}/admin">Admin</a>
                    </li>
                </ul>
            </div>

            <div class="row">
                <div class="col-xs-5 col-sm-5">
                    <h2 class="text-left">Items</h2>
                    <table id="itemTable" class="table table-responsive table-striped">
                        <tr>
                            <th width="20">Slot</th>
                            <th width="20%">Item</th>
                            <th width="20%">Cost</th>
                            <th width="20%">Amount</th>
                        </tr>
                        <tbody id="machineRows">
                        </tbody>
                    </table>
                </div> <!--end of right column -->
                <div class="col-xs-1"></div>
                <div class="col-xs-5 col-sm-5">
                    <h2 class="text-left">Stock Item</h2>
                    <form class="form-horizontal" role="form">
                        <div class="form-group">
                            <label for="add-slot" class="col-sm-3 control-label">
                                Item Slot:
                            </label>
                            <div class="col-sm-8">
                                <input type="text"
                                       class="form-control"
                                       id="add-slot"
                                       placeholder="eg A3"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-name" class="col-sm-3 control-label">
                                Item Name:
                            </label>

                            <div class="col-sm-8">
                                <input type="text"
                                       class="form-control"
                                       id="add-name"
                                       placeholder="Item Name"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-cost" class="col-sm-3 control-label">
                                Cost:
                            </label>
                            <div class="col-sm-8">
                                <input type="text"
                                       class="form-control"
                                       id="add-cost"
                                       placeholder="$1.00"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-amount" class="col-sm-3 control-label">
                                Amount:
                            </label>
                            <div class="col-sm-8">
                                <input type="text"
                                       class="form-control"
                                       id="add-amount"
                                       placeholder="23"/>
                            </div>
                        </div>
                        <div class="col-sm-offset-4 col-sm-8">
                            <button type="submit"
                                    id="stockItem"
                                    class="btn btn-default">
                                Stock 
                            </button>
                        </div>
                    </form>
                    <div id="validationErrors" style="color: red"/></div>
            </div> <!-- end left column -->

            <div class="col-sm-2"></div>
        </div> <!-- end row -->
    </div>  <!-- end container element-->
    <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/vendingmachine.js"></script>
</body>
</html>

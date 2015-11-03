<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
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
                    <li role="presentation" class="active">
                        <a href="${pageContext.request.contextPath}/index">Home</a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/admin">Admin</a>
                    </li>
                </ul>
            </div>

            <div class="col-xs-5 col-sm-5">
                <h2 class="text-left">Items</h2>
                <table id="itemTable" class="table table-responsive table-striped">
                    <tr>
                        <th width="20">Buy?</th>
                        <th width="20%">Item</th>
                        <th width="20%">Cost</th>
                        <th width="20%">Amount</th>
                    </tr>
                    <tbody id="machineRows">
                    </tbody>
                </table>
            </div> <!-- End left column -->
            <div class="col-xs-1"></div>
            <div class="col-xs-5 col-sm-5">
                <h2 class="text-left">Input Money</h2>
                <h4 class="col-sm-offset-3">Amount Entered</h4>
                <input class="col-sm-offset-3 col-sm-9" id="amountEntered">
                <br>
                <h4 class="col-sm-offset-3">Add Money</h4>
                <button class="moneyButton col-sm-offset-3 col-sm-9 btn-success" value="1">1 &cent;</button>
                <br>
                <button class="moneyButton col-sm-offset-3 col-sm-9 btn-success" value="5">5 &cent;</button>
                <br>
                <button class="moneyButton col-sm-offset-3 col-sm-9 btn-success" value="10">10 &cent;</button>
                <br>
                <button class="moneyButton col-sm-offset-3 col-sm-9 btn-success" value="25">25 &cent;</button>
                <br>
                <button class="moneyButton col-sm-offset-3 col-sm-9 btn-success" value="100">$ 1</button>
                <br>
                <br>
                <button id='changeReturnButton' class="col-sm-offset-3 col-sm-9 btn-danger">Change Return</button>
                <br><br>
                <div id='changeReturn'></div>
            </div> <!-- End right column -->
        </div> <!-- End container element -->
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/vendingmachine.js"></script>
    </body>
</html>

<!--<tr>
    <td width="30%" id="itemOne">
        <img src="${pageContext.request.contextPath}/img/MilkyWay.png" 
             class="img-responsive" 
             style="height: 150px; margin-left: auto; margin-right: auto;"/>
    </td>
    <td width="30%" id="itemTwo">
        <img src="${pageContext.request.contextPath}/img/PayDay.png" 
             class="img-responsive" 
             style="height: 150px; margin-left: auto; margin-right: auto">
    </td>
    <td width="30%" id="itemThree">
        <img src="${pageContext.request.contextPath}/img/Snickers.png" 
             class="img-responsive" 
             style="height: 150px; margin-left: auto; margin-right: auto"/>
    </td>
</tr>
<tr>
    <td width="30%" id="itemFour">
        <img src="${pageContext.request.contextPath}/img/redbull.png" 
             class="img-responsive" 
             style="height: 150px; margin-left: auto; margin-right: auto"/>
    </td>
    <td width="30%" id="itemFive">
        <img src="${pageContext.request.contextPath}/img/DrPepper.png" 
             class="img-responsive" 
             style="height: 150px; margin-left: auto; margin-right: auto"/>
    </td>
    <td width="30%" id="itemSix">
        <img src="${pageContext.request.contextPath}/img/Monster.png" 
             class="img-responsive" 
             style="height: 150px; margin-left: auto; margin-right: auto"/>
    </td>
</tr>
<tr>
    <td width="30%" id="itemSeven">
        <img src="${pageContext.request.contextPath}/img/ChiliCheeseFritos.png" 
             class="img-responsive" 
             style="height: 150px; margin-left: auto; margin-right: auto"/>
    </td>
    <td width="30%" id="itemEight">
        <img src="${pageContext.request.contextPath}/img/lays-classic.png" 
             class="img-responsive" 
             style="height: 150px; margin-left: auto; margin-right: auto"/>
    </td>
    <td width="30%" id="itemNine">
        <img src="${pageContext.request.contextPath}/img/Gardettos.png" 
             class="img-responsive" 
             style="height: 150px; margin-left: auto; margin-right: auto"/>
    </td>
</tr>-->
$(document).ready(function () {

    loadMachine();

    $('.moneyButton').click(function () {
        var money = Number($(this).val());
        putMoney(money);
        $('#changeReturn').text('');

    });

    $('#stockItem').click(function (event) {

        event.preventDefault();

        $.ajax({
            type: 'POST',
            url: 'item',
            data: JSON.stringify({
                slot: $('#add-slot').val(),
                name: $('#add-name').val(),
                cost: $('#add-cost').val(),
                amount: $('#add-amount').val()
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            dataType: 'json'
            
        }).success(function (data, status) {
            $('#add-slot').val('');
            $('#add-name').val('');
            $('#add-cost').val('');
            $('#add-amount').val('');
            loadMachine();
            $('#validationErrors').empty();
            
        }).error(function (data, status) {
            $('#validationErrors').empty();
            $.each(data.responseJSON.fieldErrors, function (index, validationError) {
                $('#validationErrors').append(validationError.message).append($('<br>'));
            });
        });
        
    });

    $('#changeReturnButton').click(function () {
        returnMoney();
    });

});

//Functions

function loadMachine() {
    $.ajax({
        type: 'GET',
        url: 'machine'
    }).success(function (itemlist, status) {
        $('#machineRows').empty();
        var mTable = $('#machineRows');
        $.each(itemlist, function (index, item) {

            if (item.amount > 0) {
                mTable
                        .append($('<tr>')
                                .append($('<td>')
                                        .append($('<a>')
                                                .attr({
                                                    'data-slot': item.slot,
                                                    'onClick': 'vendItem(\"' + item.slot + '\")'
                                                })
                                                .text(item.slot)
                                                ) //</a>
                                        ) //</td>
                                .append($('<td>')
                                        .text(item.name))
                                .append($('<td>')
                                        .text(item.cost))
                                .append($('<td>')
                                        .text(item.amount))
                                ); //</tr>
            }
        });
    });
}

function vendItem(slot) {

    $.ajax({
        type: 'POST',
        url: 'vend',
        data: JSON.stringify({
            money: 1,
            slot: slot
        }),
        contentType: 'application/json',
        dataType: 'json',
        success: function (vendResponse, status) {

            var status = vendResponse.status;
            var message = vendResponse.outcome;

            // item not found
            if (status === 1) {
                alert(message);
            }

            // insufficient funds
            if (status === 2) {
                alert(message);
            }

            // vend item
            if (status === 3) {
                var answer = confirm(message + '\n Return your change?');
                if (answer === true) {
                    returnMoney();
                }
                loadMachine();
                getMoney();
            }
        },
        error: function (err, status) {
            alert(err.responseText);
        }
    });
}

function putMoney(money) {

    $.ajax({
        type: 'PUT',
        url: 'money/' + money
    }).success(function (data, status) {
        getMoney();
    });

}

function getMoney() {

    $.ajax({
        type: 'GET',
        url: 'money'
    }).success(function (data, status) {
        $('#amountEntered').val('$' + (data / 100).toFixed(2));

    });
}

function returnMoney() {
    $.ajax({
        type: 'PUT',
        url: 'changeReturn'
    }).success(function (data, status) {
        $('#changeReturnButton').val('');
        $('#amountEntered').val('');
        $('#changeReturn').text(data);
    });
}

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// Document ready function
$(document).ready(function () {
    loadDvds();

    $('#add-button').click(function (event) {

        event.preventDefault();
        $.ajax({
            type: 'POST',
            url: 'dvd',
            data: JSON.stringify({
                title: $('#add-title').val(),
                releaseDate: $('#add-releaseDate').val(),
                mpaaRating: $('#add-mpaaRating').val(),
                director: $('#add-director').val(),
                studio: $('#add-studio').val(),
                note: $('#add-note').val()
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json'
        }).success(function (data, status) {

            //if call succeeds clear form, reload summary table
            $('#add-title').val('');
            $('#add-releaseDate').val('');
            $('#add-mpaaRating').val('');
            $('#add-director').val('');
            $('#add-studio').val('');
            $('#add-note').val('');
            $('#validationErrors').empty();
            loadDvds();
        }).error(function (data, status) {
            $('#validationErrors').empty();
            $.each(data.responseJSON.fieldErrors, function (index, validationError) {
                $('#validationErrors').append(validationError.message).append($('<br>'));
            });
        });
    });

    $('#edit-button').click(function (event) {

        event.preventDefault();
        $('#validationErrorsModal').empty();

        $.ajax({
            type: 'PUT',
            url: 'dvd/' + $('#edit-dvd-id').val(),
            data: JSON.stringify({
                dvdId: $('#edit-dvd-id').val(),
                title: $('#edit-title').val(),
                releaseDate: $('#edit-releaseDate').val(),
                mpaaRating: $('#edit-mpaaRating').val(),
                director: $('#edit-director').val(),
                studio: $('#edit-studio').val(),
                note: $('#edit-note').val()
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json'
        }).success(function () {
            $('#validationErrorsModal').empty();
            loadDvds();
            $('#editModal').hide(function (data, status) {
                $('#validationErrorsModal').empty();
            });
        }).error(function (data, status) {
            $('#validationErrorsModal').empty();
            $.each(data.responseJSON.fieldErrors, function (index, validationError) {
                $('#validationErrorsModal').append(validationError.message).append($('<br>'));
            });
        });
    });

    $('#search-button').click(function (event) {
        event.preventDefault();
        $.ajax({
            type: 'POST',
            url: 'search/dvds',
            data: JSON.stringify({
                title: $('#search-title').val(),
                releaseDate: $('#search-releaseDate').val(),
                mpaaRating: $('#search-mpaaRating').val(),
                director: $('#search-director').val(),
                studio: $('#search-studio').val(),
                note: $('#search-note').val()
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json'
        }).success(function (data, status) {
            $('#search-title').val(),
                    $('#search-releaseDate').val(),
                    $('#search-mpaaRating').val(),
                    $('#search-director').val(),
                    $('#search-studio').val(),
                    $('#search-note').val(),
                    fillDvdTable(data, status);
        });
    });
    
    $('#cancel-edit-modal').click(function (event) {
        event.preventDefault();
        $('#validationErrorsModal').empty();
    });
    
});

//==========
// FUNCTIONS
//==========

// Load all dvds into the summary table
function loadDvds() {

    $.ajax({
        url: "dvds"
    }).success(function (data, status) {
        fillDvdTable(data, status);
    });

}

function fillDvdTable(dvdTable, status) {

    // clear the previous list
    clearDvdTable();
    // grab the tbody element that will hold the new list of dvds
    var dTable = $('#contentRows');

    $.each(dvdTable, function (index, dvd) {
        dTable.append($('<tr>')
                .append($('<td>')
                        .append($('<a>')
                                .attr({
                                    'data-dvd-id': dvd.dvdId,
                                    'data-toggle': 'modal',
                                    'data-target': '#detailsModal'
                                })
                                .text(dvd.title)
                                ) //</a>
                        ) //</td>
                .append($('<td>').text(dvd.releaseDate))
                .append($('<td>').text(dvd.mpaaRating))
                .append($('<td>').text(dvd.note))
                .append($('<td>')
                        .append($('<a>')
                                .attr({
                                    'data-dvd-id': dvd.dvdId,
                                    'data-toggle': 'modal',
                                    'data-target': '#editModal'
                                })
                                .text('Edit')
                                ) //</a>
                        ) //</td>
                .append($('<td>')
                        .append($('<a>')
                                .attr({
                                    'onClick': 'deleteDvd(' +
                                            dvd.dvdId + ')'
                                })
                                .text('Delete')
                                ) //</a>
                        ) //</td>
                ); //</tr>
    });
}

// Clear all content rows from the summary table
function clearDvdTable() {
    $('#contentRows').empty();
}

//delete a contact
function deleteDvd(id) {
    var answer = confirm("Do you really want to delete this DVD?");
    if (answer === true) {
        $.ajax({
            type: 'DELETE',
            url: 'dvd/' + id
        }).success(function () {
            loadDvds();
        });
    }
}


// MODALS
// This code runs in response to the show.bs.modal event - it gets the correct
// dvd data and renders it to the dialog
$('#detailsModal').on('show.bs.modal', function (event) {

    var element = $(event.relatedTarget);
    var dvdId = element.data('dvd-id');
    var modal = $(this);

    $.ajax({
        type: 'GET',
        url: 'dvd/' + dvdId
    }).success(function (dvd) {
        modal.find('#dvd-id').text(dvd.dvdId);
        modal.find('#dvd-title').text(dvd.title);
        modal.find('#dvd-releaseDate').text(dvd.releaseDate);
        modal.find('#dvd-mpaaRating').text(dvd.mpaaRating);
        modal.find('#dvd-director').text(dvd.director);
        modal.find('#dvd-studio').text(dvd.studio);
        modal.find('#dvd-note').text(dvd.note);
    });
});

// This code runs in response to the show.bs.modal event - it gets the correct
// dvd data and renders it to the dialog
$('#editModal').on('show.bs.modal', function (event) {

    var element = $(event.relatedTarget);
    var dvdId = element.data('dvd-id');
    var modal = $(this);

    $.ajax({
        type: 'GET',
        url: 'dvd/' + dvdId
    }).success(function (dvd) {
        modal.find('#dvd-id').text(dvd.dvdId);
        modal.find('#edit-dvd-id').val(dvd.dvdId);
        modal.find('#edit-title').val(dvd.title);
        modal.find('#edit-releaseDate').val(dvd.releaseDate);
        modal.find('#edit-mpaaRating').val(dvd.mpaaRating);
        modal.find('#edit-director').val(dvd.director);
        modal.find('#edit-studio').val(dvd.studio);
        modal.find('#edit-note').val(dvd.note);
    });
});
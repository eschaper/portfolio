
// CONTACT LIST!!!!!!!
// Document ready function
$(document).ready(function () {
    loadContacts();

    // on click for our add button
    $('#add-button').click(function (event) {

        // we don’t want the button to actually submit
        // we'll handle data submission via ajax
        event.preventDefault();

        // Make an Ajax call to the server. HTTP verb = POST, URL = contact
        $.ajax({
            type: 'POST',
            url: 'contact',
            // Build a JSON object from the data in the form
            data: JSON.stringify({
                firstName: $('#add-first-name').val(),
                lastName: $('#add-last-name').val(),
                company: $('#add-company').val(),
                phone: $('#add-phone').val(),
                email: $('#add-email').val()
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json'
        }).success(function (data, status) {

            // If the call succeeds, clear the form and reload the summary table
            $('#add-first-name').val('');
            $('#add-last-name').val('');
            $('#add-company').val('');
            $('#add-phone').val('');
            $('#add-email').val('');
            $('#validationErrors').empty();
            loadContacts();
            //return false;
        }).error(function (data, status) {
            $('#validationErrors').empty();
            $.each(data.responseJSON.fieldErrors, function (index, validationError) {
                var errorDiv = $('#validationErrors');
                errorDiv.append(validationError.message).append($('<br>'));
            });
        });
    });

    // onclick handler for edit button
    $('#edit-button').click(function (event) {

        // prevent the button press from submitting the whole page
        event.preventDefault();

        // Ajax call -
        // Method - PUT
        // URL - contact/{id}
        // Just reload all of the Contacts upon success
        $.ajax({
            type: 'PUT',
            url: 'contact/' + $('#edit-contact-id').val(),
            data: JSON.stringify({
                contactId: $('#edit-contact-id').val(),
                firstName: $('#edit-first-name').val(),
                lastName: $('#edit-last-name').val(),
                company: $('#edit-company').val(),
                phone: $('#edit-phone').val(),
                email: $('#edit-email').val()
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json'
        }).success(function (data, status) {
            loadContacts();
            $('#editModal').modal('hide');
        }).error(function (data, status) {
            $('#validationErrorsModal').empty();
            $.each(data.responseJSON.fieldErrors, function (index, validationError) {
                var errorDiv = $('#validationErrorsModal');
                errorDiv.append(validationError.message).append($('<br>'));
            });
        });
    });

    // on click for our search button
    $('#search-button').click(function (event) {
        
    // we don’t want the button to actually submit
    // we'll handle data submission via ajax
        event.preventDefault();
        $.ajax({
            type: 'POST',
            url: 'search/contacts',
            data: JSON.stringify({
                firstName: $('#search-first-name').val(),
                lastName: $('#search-last-name').val(),
                company: $('#search-company').val(),
                phone: $('#search-phone').val(),
                email: $('#search-email').val()
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json'
        }).success(function (data, status) {
            $('#search-first-name').val('');
            $('#search-last-name').val('');
            $('#search-company').val('');
            $('#search-phone').val('');
            $('#search-email').val('');
            fillContactTable(data, status);
        });
    });

});

//==========
// FUNCTIONS
//==========

// Load contacts into the summary table
function loadContacts() {
    
    // Make an Ajax GET call to the 'contacts' endpoint. Iterate through
    // each of the JSON objects that are returned and render them to the
    // summary table
    $.ajax({
        url: "contacts"
    }).success(function (data, status) {
        fillContactTable(data, status);
    });
}

// fills the contact table with results from an Ajax call - used in conjunction
// with the Search button and loadContact function
function fillContactTable(contactList, status) {
    
    // clear the previous list
    clearContactTable();
    
    // grab the tbody element that will hold the new list of contacts
    var cTable = $('#contentRows');
    
    // render the new contact data to the table
    $.each(contactList, function (index, contact) {
        cTable.append($('<tr>')
                .append($('<td>')
                        .append($('<a>')
                                .attr({
                                    'data-contact-id': contact.contactId,
                                    'data-toggle': 'modal',
                                    'data-target': '#detailsModal'
                                })
                                .text(contact.firstName + ' ' +
                                        contact.lastName)
                                ) // ends the <a> tag
                        ) // ends the <td> tag for the contact name
                .append($('<td>').text(contact.company))
                .append($('<td>')
                        .append($('<a>')
                                .attr({
                                    'data-contact-id': contact.contactId,
                                    'data-toggle': 'modal',
                                    'data-target': '#editModal'
                                })
                                .text('Edit')
                                ) // ends the <a> tag
                        ) // ends the <td> tag for Edit
                .append($('<td>')
                        .append($('<a>')
                                .attr({
                                    'onClick': 'deleteContact(' +
                                            contact.contactId + ')'
                                })
                                .text('Delete')
                                ) // ends the <a> tag
                        ) // ends the <td> tag for Delete
                ); // ends the <tr> for this Contact
    }); // ends the 'each' function
}


function deleteContact(id) {
    var answer = confirm("Do you really want to delete this contact?");
    if (answer === true) {
        $.ajax({
            type: 'DELETE',
            url: 'contact/' + id
        }).success(function () {
            loadContacts();
        });
    }
}

// Clear all content rows from the summary table
function clearContactTable() {
    $('#contentRows').empty();
}

// MODALS
// This code runs in response to the show.bs.modal event - it gets the correct
// contact data and renders it to the dialog
// This code runs in response to show.bs.modal event for the details Modal
$('#detailsModal').on('show.bs.modal', function (event) {

    // get the element that triggered the event
    var element = $(event.relatedTarget);
    var contactId = element.data('contact-id');
    var modal = $(this);

    // make an ajax call to get contact information for given contact id
    // this is a GET request to contact/{id}
    // upon success, put the returned JSON data into the modal dialog
    $.ajax({
        type: 'GET',
        url: 'contact/' + contactId
    }).success(function (contact) {
        modal.find('#contact-id').text(contact.contactId);
        modal.find('#contact-firstName').text(contact.firstName);
        modal.find('#contact-lastName').text(contact.lastName);
        modal.find('#contact-company').text(contact.company);
        modal.find('#contact-phone').text(contact.phone);
        modal.find('#contact-email').text(contact.email);
    });
});

// This code runs in response to the show.hs.modal event for the edit Modal
$('#editModal').on('show.bs.modal', function (event) {
    var element = $(event.relatedTarget);
    var contactId = element.data('contact-id');
    var modal = $(this);
    $.ajax({
        type: 'GET',
        url: 'contact/' + contactId
    }).success(function (contact) {
        modal.find('#contact-id').text(contact.contactId);
        modal.find('#edit-contact-id').val(contact.contactId);
        modal.find('#edit-first-name').val(contact.firstName);
        modal.find('#edit-last-name').val(contact.lastName);
        modal.find('#edit-company').val(contact.company);
        modal.find('#edit-email').val(contact.email);
        modal.find('#edit-phone').val(contact.phone);
    });
});

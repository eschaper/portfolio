<!-- Details Modal -->
<!-- 
    The aria- attributes are for Accessible Rich Internet Applications standards - their purpose is 
    to make web applications more accessible to people with disabilities.
-->
<div class="modal fade" id="detailsModal" tabindex="-1" role="dialog" aria-labelledby="detailsModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span>
                    <span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title" id="detailsModalLabel">Contact Details</h4>
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
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>       

<!-- Edit Modal -->
<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="detailsModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span>
                    <span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title" id="detailsModalLabel">Edit Contact</h4>
            </div>
            <div class="modal-body">
                <h3 id="contact-id"></h3>
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="edit-first-name" class="col-md-4 control-label">
                            First Name:
                        </label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" id="edit-first-name" 
                                   placeholder="First Name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="edit-last-name" class="col-md-4 control-label">Last Name:</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" id="edit-last-name" 
                                   placeholder="Last Name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="edit-company" class="col-md-4 control-label">Company:</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" id="edit-company" 
                                   placeholder="Company">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="edit-email" class="col-md-4 control-label">Email:</label>
                        <div class="col-md-8">
                            <input type="email" class="form-control" id="edit-email" 
                                   placeholder="Email">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="edit-phone" class="col-md-4 control-label">Phone:</label>
                        <div class="col-md-8">
                            <input type="tel" class="form-control" id="edit-phone" 
                                   placeholder="Phone">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-offset-4 col-md-8">
                            <button type="submit" id="edit-button" class="btn btn-default" 
                                    data-dismiss="modal">
                                Edit Contact
                            </button>
                            <button type="button" class="btn btn-default" 
                                    data-dismiss="modal">
                                Cancel
                            </button>
                            <input type="hidden" id="edit-contact-id">
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

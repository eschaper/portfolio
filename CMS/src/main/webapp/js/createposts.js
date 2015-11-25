$(document).ready(function () {

    tinymce.init({
        selector: "#bodytextarea",
        plugins: [
            "advlist autolink lists link image charmap print preview anchor",
            "searchreplace visualblocks code fullscreen",
            "insertdatetime table paste image preview"
        ],
        entity_encoding: "raw"
    });


    $("#add-post-button").click(function (event) {
        var tagstring;
        event.preventDefault();

        $.ajax({
            type: 'POST',
            url: 'makePost',
            data: JSON.stringify({
                title: $('#add-title').val(),
                body: tinymce.activeEditor.getContent({format: 'raw'}),
                tags: $('#add-tags').val(),
                postDate: $('#add-postDate').datepicker('getDate'),
                expiration: $('#add-expiration').datepicker('getDate')
            }),
            headers: {'Accept': 'application/json',
                'Content-Type': 'application/json'},
            dataType: 'json'
        }).success(function (data, status) {
            $('#add-title').val("");
            $('#add-tags').val("");
            $('#add-postDate').val("");
            $('#add-expiration').val("");
            tinymce.activeEditor.setContent("");
            console.log("Success!");
        }).error(function (data, status) {
            $('#validationErrors').empty();
            $.each(data.responseJSON.fieldErrors, function (index, validationError) {
                var errorDiv = $("#validationErrors");
                errorDiv.append(validationError.message).append($("<br>"));
            });
        });
    });

    $(function () {
        $("#add-postDate").datepicker({
            dateFormat: "yy-mm-dd"
        });
    });

    $(function () {
        $("#add-expiration").datepicker({
            dateFormat: "yy-mm-dd"
        });
    });
});



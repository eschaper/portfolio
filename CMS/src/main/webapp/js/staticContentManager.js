var content;

$(document).ready(function () {

    loadStaticContent();

    initalizeTinyMce();


    $("#add-content-button").click(function (event) {
        $('#completion').empty();
        event.preventDefault();
        $.ajax({
            type: 'PUT',
            url: 'header',
            data: JSON.stringify({
                contentId: 1,
                content: tinymce.activeEditor.getContent({format: 'raw'})
            }),
            headers: {'Accept': 'application/json',
                'Content-Type': 'application/json'},
            dataType: 'json',
            complete: function () {
                $('#completion').append("Content Updated");
            }
        });

        loadStaticContent();

        initalizeTinyMce();
    });

});

function loadStaticContent() {

    $.ajax({
        url: 'header'
    }).success(function (staticContent) {
        content = staticContent.content;
    });

}

function initalizeTinyMce() {
    tinymce.init({
        selector: "#staticContent",
        inline: false,
        entity_encoding: "raw",
        plugins: [
            "advlist autolink lists link image charmap print preview anchor",
            "searchreplace visualblocks code fullscreen",
            "insertdatetime table paste image preview"
        ],
        toolbar: "insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image",
        init_instance_callback: function () {
            tinymce.activeEditor.setContent(content);
        }

    });
}


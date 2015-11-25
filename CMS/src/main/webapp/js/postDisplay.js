$(document).ready(function () {
    $('#blogContent').hide();
    loadAllPosts();
    loadStaticContent();
    loadTags();

});

function loadAllPosts() {
    $.ajax({
        url: 'posts'
    }).success(function (allposts, status) {
        loadPosts(allposts, status);
    });
}

function loadPosts(data, status) {
    clearPosts();
    var blogRoll = $('#blogContent');
    var tagList = $('#tagDisplay');

    $.each(data, function (index, post) {
        blogRoll
                .append($('<div class="postContain word_wrap">')
                        .text(post.title)
                        .append($('<span class = postDetails>')
                                .text("  on " + post.postDate + " by " + post.author)
                                ) // </span>
                        .append($('<div class="postBody">')
                                .append(post.body)
                                ) // </div class="postBody">
                        ); // </div class="postContain word_wrap">
        $.each(post.tags, function (index, tag) {
            if (tag.tagName !== "") {
                blogRoll
                        .append($('<p class="tagToPost">')
                                .text("#" + tag.tagName + " ")
                                ); //</p>
            }
        });

    });
    blogRoll.fadeIn(1000);
}

function loadTags() {
    var tagList = $('#tagDisplay');
    $.ajax({
        url: 'tags'
    }).success(function (alltags, status) {
        $.each(alltags, function (index, tag) {
            if (tag.tagName !== "") {
                tagList.append($('<li class="tagList">')
                        ) //</li>
                        .append($('<a class="tags word_wrap">')
                                .attr({"onClick": "showByTag(" + tag.tagId + ")"})
                                .text("#" + tag.tagName + "")
                                ); //</a>
            }
        });
    });
}

function clearPosts() {
    $('#blogContent').empty();
    $('#tagDisplay').empty();
}

function showByTag(id) {
    var blogRoll = $('#blogContent');
    clearPosts();
    $.ajax({
        url: 'postsByTag/' + id
    }).success(function (allposts, status) {
        $.each(allposts, function (index, post) {

            blogRoll
                    .append($('<div class="postContain">')
                            .text(post.title + " " + post.postDate + " by " + post.author)
                            .append(post.body)
                            ); //</div>
            $.each(post.tags, function (index, tag) {
                blogRoll
                        .append($('<p class="tagToPost">')
                                .text("#" + tag.tagName + " ")
                                ); // </p>
                ;
            });
        });
    });
}

function clearPosts() {
    $('#blogContent').empty();
}

function doRotate(elementId) {
    var g = $(elementId);
    g.delay(300).animate({rotate: '360deg'}, 1500);

}

function loadStaticContent() {
    var content = "";

    $.ajax({
        url: 'header'
    }).success(function (staticContent) {
        $('#static').append(staticContent.content);
    });

}


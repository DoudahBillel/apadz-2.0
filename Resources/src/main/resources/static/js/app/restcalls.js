function find_all_categories_articles(callback, onFinishedCallback) {
    find_all_designated_entities("/restapi/categorieArticles", "categorieArticles", callback, onFinishedCallback);
}

function find_all_articles(callback, onFinishedCallback){
    find_all_designated_entities("/restapi/articles", "articles", callback, onFinishedCallback);
}

function find_article_by_id(id, callback, onFinishedCallback){
    $.get("/restapi/articles/" + id, function (element) {
        callback(element);

        if (typeof onFinishedCallback == "function") {
            onFinishedCallback(elements);
        }
    });
}

function find_all_designated_entities(url, collectionName, callback, onFinishedCallback) {
    $.get(url, function (elements) {
        $.each(elements["_embedded"][collectionName], function (index, designatedEntity) {
            callback(designatedEntity);
        });

        if (typeof onFinishedCallback == "function") {
            onFinishedCallback(elements);
        }
    });
}

// --------------------------------------------------------------------------

function fetch_logged_in_user_details(callback) {
    $.get("/compteServices/fetchLoggedInUserDetails", function (element) {
        callback(element);
    });
}
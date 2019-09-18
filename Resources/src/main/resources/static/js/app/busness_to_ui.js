function add_categorie_article_to_index(parentSelector, categorie) {
    var li =
        "<li id=categorieArticle" + categorie.id + " class='nav-item'>" +
            "<h5>" + categorie.designation + "</h5>" +
            "<div aria-placeholder='Aucun article !' id='articlesHolder" + categorie.id + "' class='d-flex align-items-center justify-content-start'>" +

            "</div>"
        "</li>>";

    $(parentSelector).append(li);
}

function add_article_to_index(parentSelector, article) {
    var cardHolder = parentSelector + " #articlesHolder" + article.idCategorieArticle;

    var title = article.title;
    var description = article.description;
    var addedDate = article.addedDate;
    var imageUrl = "/articleBackground/" + article.id;
    var articleUrl = "/article_description/" + article.id;

    var card =
        '<div class="card m-4" style="width: 20rem;">' +
            '<img class="card-img-top" src="' + imageUrl + '" alt="Card image cap">' +
            '<div class="card-body">' +
                '<h4 class="card-title">' + title + '</h4>' +
                '<p class="card-text">' + description + '</p>' +
                '<a href="' + articleUrl + '" class="btn btn-primary">En savoir plus</a>'+
            '</div>'+
            '<div class="card-footer"> ' +
                '<small class="text-muted">' + addedDate + '</small>' +
            '</div>' +
        '</div>';

    $(cardHolder).append(card);
}

function add_article_to_descriptor(article) {
    var title = article.title;
    var description = article.description;
    var addedDate = article.addedDate;
    var content = article.content;

    $("#title").html(title);
    $("#description").html(description);
    //$("#addedDate").html("Publié le / à: " + addedDate);
    $("#content").html(content);
}

function add_categorie_as_option(parentSelector, categorie){
    add_designated_entity_as_option("/restapi/categorieArticles/", ".categories-articles-options-holder", categorie);
}

function add_designated_entity_as_option(serviceUrl, parentSelector, designatedEntity) {
    add_entity_as_option(parentSelector, designatedEntity, serviceUrl, "designation");
}

// ---------------------------------------------------------------------------------------

function update_profil_indicators(compte) {
    $(".username_holder").text(compte.username);
    $(".username_value_holder").val(compte.username);

    $(".email_holder").text(compte.email);
    $(".email_value_holder").val(compte.email);

    $(".compte_type_holder").text(compte.typeCompte);
    $(".nom_prenom_holder").parent().remove();

    update_signin_progression(100);
}

function update_signin_progression(progression) {
    $("#user-signin-progression").attr("aria-valuenow", progression);
    $("#user-signin-progression").css("width", progression + "%");
    $("#user-signin-progression-indicator").text(progression + "%");
}

function update_number_of_new_notitifcations(numberOfNewNotifications) {
    $(".number_of_notifications_holder").text(numberOfNewNotifications);
}

// ---------------------------------------------------------------------

function add_simple_notifications_to_container(notifications) {
    $.each(notifications, function (index, notification) {
        add_simple_notification_to_container(notification);
    });
}

function add_simple_notification_to_container(notification) {
    var icon = notification.icon;
    var title = notification.title;
    var content = notification.content;

    $(".simple_notifications_holder").prepend(
        '<a class="dropdown-item" href="#">' +
        '<div class="notification__icon-wrapper">' +
        '<div class="notification__icon">' +
        '<i class="material-icons">' + icon + '</i>' +
        '</div>' +
        '</div>' +

        '<div class="notification__content">' +
        '<span class="notification__category">' + title + '</span>' +
        '<p>' + content + '</p>' +
        '</div>' +
        '</a>'
    );
}
SERVICE_URL = "/restapi/articles" ;
DATA_SRC = '_embedded.articles';

IS_EDITABLE = 'true';
IS_DELETABLE = 'true';

ITEM_COLOMNS = [
    { data: 'id' },
    { data: 'title' },
    { data: 'nomCategorieArticle' },
    { data: 'addedDate' },
    { data: 'description' }
];

(function() {
    $(document).ready(function(){
        ITEM_COLOMNS.push(
            {
                orderable : 'false',
                render: function(data, type, row) {
                    return createImageButton(type, row);
                }
            }
        );
    });
})();

function createImageButton(type, row){
    if( type != 'display' )
        return "";

    else {
        return '<td class="align-middle text-center" >' +
                    '<button type="button" class="btn btn-primary " onclick="uploadImage(' + row.id + ');">' +
                        '<i class="fa fa-camera" ></i>' +
                    '</button>' +
                '</td>';
    }
}

function uploadImage(idArticle) {
    $("#idArticle").val(idArticle);

    $('#upload-picture-modal').modal('show');
}
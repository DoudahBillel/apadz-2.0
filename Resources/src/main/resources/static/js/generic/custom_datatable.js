(function() {
	$(document).ready(function() {
		ITEM_COLOMNS.push({
			orderable : 'false',
			render: function(data, type, row) {
				return createEditButton(IS_EDITABLE, type, row);
			}
		});

		ITEM_COLOMNS.push({
			orderable : 'false',
			render: function(data, type, row) {
				return createDeleteButton(IS_DELETABLE, type, row);
			}
		});

		$('#datatable').DataTable( {
			ajax: {
				url: SERVICE_URL,
				dataSrc: DATA_SRC
			},

		    columns:  ITEM_COLOMNS
		});
	});
})();

function createEditButton(data, type, row) {
	if( type != 'display' )
		return "";

	else {
		return '<td class="align-middle text-center" >' +
					'<a class="btn btn-warning" href="/dashboard/update_article/' + row.id + '">' +
						'<i class="fa fa-pencil-alt" ></i>' +
					'</a>' +
				'</td>';
	}
}

function createDeleteButton(data, type, row){
	if( type != 'display' )
		return "";

    else {
    	return '<td class="align-middle text-center" >' +
					'<button type="button" class="btn btn-danger " onclick="deleteItem(' + row.id + ');">' +
							'<i class="fa fa-trash" ></i>' +
					'</button>' +
				'</td>';
	}
}

function deleteItem(id) {
	var r = confirm("Voulez vous vraiment procéder à la suppression ?");

	if (r == true) {
		$.ajax({
			url: SERVICE_URL + "/" + id,
			type: 'DELETE',
			success: function(result) {
				$('#datatable').DataTable().ajax.reload();
			}
		});
	} else {
	}
};



/* Reload page */
 
$(document).ready(function() {
	$('#userFormModal').on('hidden.bs.modal', function () {
		location.reload();
	});
});	
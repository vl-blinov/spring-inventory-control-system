/* Reload page to refresh modal input fields */
 
$(document).ready(function() {
	$('#userFormModal').on('hidden.bs.modal', function () {
		location.reload();
	});
});	
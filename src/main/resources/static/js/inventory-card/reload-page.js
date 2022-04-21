/* Reload page to refresh modal input fields */
 
$(document).ready(function() {
	$('#addCardModal').on('hidden.bs.modal', function () {
		location.reload();
	});
});	
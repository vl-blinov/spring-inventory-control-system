/* Reload page */
 
$(document).ready(function() {
	$('#addCardModal').on('hidden.bs.modal', function () {
		location.reload();
	});
});	
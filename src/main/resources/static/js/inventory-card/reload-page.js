/* Reload page to refresh modal input fields */
 
$(document).ready(function() {
	$('#inventoryCardModal').on('hidden.bs.modal', function () {
		location.reload();
	});
});	
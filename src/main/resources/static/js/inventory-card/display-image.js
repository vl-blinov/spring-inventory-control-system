/* Display image in an inventory card modal  */

$(document).ready(function() {
	$('.modal-body #fileImage').change(function() {
		showImageThumbnail(this)
	});
});	

function showImageThumbnail(fileInput) {
	
	file = fileInput.files[0];
	reader = new FileReader();
	
	reader.onload = function(e) {
		$('.modal-body #productImage').prop('src', e.target.result);
	};
	
	reader.readAsDataURL(file);
}
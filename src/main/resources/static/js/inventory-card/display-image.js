/* Display image in an inventory card modal and check the image size */

$(document).ready(function() {
	
	$('.modal-body #fileImage').change(function() {
		
		fileSize = this.files[0].size;
		
		if(fileSize > 1048576) {
			this.setCustomValidity("The uploaded image is too large. The max image file size is 1MB");
			this.reportValidity();
			this.value = null;
		}
	});
	
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
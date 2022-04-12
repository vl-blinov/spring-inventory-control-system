/* View inventory card */
 
 $(document).ready(function() {
	$('.view').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		var myModal = new bootstrap.Modal(document.getElementById('addCardModal'), {});
		$.get(href, function(inventoryCard) {
			$('.modal-header #formIdentifier').val(inventoryCard.identifier);
			$('.modal-header #formClass').val(inventoryCard.className);
			$('.modal-body #productImage').prop('src', inventoryCard.productImagePath);
			$('.modal-body #imageSrc').val(inventoryCard.productImagePath);
			$('.modal-body #formProductName').val(inventoryCard.productName);
			$('.modal-body #formProductType').val(inventoryCard.productType);
			$('.modal-body #formProductId').val(inventoryCard.productId);
			$('.modal-body #formProductManufacturer').val(inventoryCard.productManufacturer);
			$('.modal-body #formProductCountry').val(inventoryCard.productCountry);
			$('.modal-body #formProductLength').val(inventoryCard.productLength);
			$('.modal-body #formProductWidth').val(inventoryCard.productWidth);
			$('.modal-body #formProductHeight').val(inventoryCard.productHeight);
			$('.modal-body #formProductWeight').val(inventoryCard.productWeight);
			$('.modal-body #productDescription').val(inventoryCard.productDescription);
			$('.modal-footer #formFullName').val(inventoryCard.user.fullName);
			$('.modal-footer #formPosition').val(inventoryCard.user.position);
			$('.modal-footer #formPhone').val(inventoryCard.user.phone);
			$('.modal-footer #formEmail').val(inventoryCard.user.email);
			$('.modal-footer #formCreatedAt').val(inventoryCard.createdAt);
			
			identifier = $('.modal-header #formIdentifier').val();
			var checkedValue = $('.form-check-input:checked').val();

			if(identifier==checkedValue) {
				$('#addCardModal :input').prop('disabled', false);
			}
			else {
				$('#addCardModal :input').prop('disabled', true);
				$('.modal-header #closeModal').prop('disabled', false);
				$('.modal-footer #closeButton').prop('disabled', false);
			}
		});
		myModal.show();
	});
});
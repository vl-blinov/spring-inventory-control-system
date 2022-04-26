/* View a modal with an existing user for updating */
 
 $(document).ready(function() {
	
	$('.update').on('click', function(event) {
		event.preventDefault();
		var href = $(this).prop('href');
		
		$.get(href, function(user) {
			$('.modal-body #formUserId').val(user.id);
			$('.modal-body #formUsername').val(user.username);
			$('.modal-body #formPassword').val('123');
			$('.modal-body #formFirstName').val(user.firstName);
			$('.modal-body #formLastName').val(user.lastName);
			$('.modal-body #formDepartment').val(user.department);
			$('.modal-body #formPosition').val(user.position);
			$('.modal-body #formPhone').val(user.phone);
			$('.modal-body #formEmail').val(user.email);
			$('.modal-body #formAuthority').val(user.authority);
			$('.modal-body #formEnabled').val(user.enabled);
		});
		
		$('#userFormModal').modal('show');
	});
});
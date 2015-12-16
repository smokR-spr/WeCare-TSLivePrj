$(function() {
	/* to check password match 
	$('#re-pass').keyup(function() {
		var newPass = $('#new-pass').val();
		$('#matchResult').html(
				newPass == $(this).val()
				? "Passwords match."
				: "Passwords donot match."
		);
		
		var result = $('#matchResult');
		
		if(newPass == $(this).val() && (newPass != null || newPass != "")) {
			result.html("Passwords match.");
			result.removeClass('red');
			result.addClass('green');
		} else {
			result.html("Passwords donot match!");
			result.removeClass('green');
			result.addClass('red');
		}
		
	});*/
	
	var newPass = $('#new-pass');
	var rePass = $('#re-pass');
	var oldPass = $('#old-pass');
	var allPass = $('#old-pass, #new-pass, #re-pass');
	var button = $('#reset-pass button');
	var result = $('#matchResult');
	
	button.attr('disabled','disabled');
	
	allPass.each(function () {
        $(this).keyup(function () {
            if (!oldPass.val() || !newPass.val() || !rePass.val()) { // all r empty
                button.attr('disabled', 'disabled');
                result.html('');
            }

            if (rePass.val()) {
                if ((newPass.val() == rePass.val()) && (oldPass.val() != newPass.val())) {
                    result.html('Passwords match.');
                    result.addClass('green');
                    result.removeClass('red');
                    button.removeAttr('disabled');
                } else {
                    result.html('Passwords donot match.');
                    result.addClass('red');
                    result.removeClass('green');
                    button.attr('disabled', 'disabled');
                }
            }

            if (newPass.val()) {
                if (oldPass.val() == newPass.val()) {
                    result.html('Old and new password cannot be same.');
                    result.addClass('red');
                    result.removeClass('green');
                    button.attr('disabled', 'disabled');
                } else if(!rePass.val()) {
                    result.html('');
                    result.addClass('green');
                    result.removeClass('red');
                    //button.removeAttr('disabled');
                }
            }
        });
    });
	
	/* document click */
	$(document).mouseup(function (e) {
		/* to close all settings */
		var container = $('.settings');
		
		if(!container.is(e.target)
				&& container.has(e.target).length === 0) {
			//$('.panel').slideUp('slow');
			//$('.flip').removeClass('active');
			$('#matchResult').html("");
		}
		/* --------------------- */
	});
	
	/* reset password form submission */
	$('#reset-pass button').click(function(event) {
		event.preventDefault();
		if(validate()) {
			alert($('#reset-pass').serialize());
			$.ajax({
				type: "post",
				url: "ChangePassword",
				data: $('#reset-pass').serialize(),
				success: function() {
					$('#content').html('');
					$('#content').addClass('green');
					$('#content').addClass('center');
					$('#content').html('Password updated successfully. Redirecting to login page...');
					
					/*$('#reset-pass input').next().html("");
					$('#reset-pass input').val("");
					$('#matchResult').html("Password updated successfully. Redirecting to login page...");
					$('#matchResult').addClass('green');*/
					
					
					//to redirect after 5secs
					setTimeout(function() { window.location = "login.jsp"},5000);
					
				},
				error: function(jqXHR, textStatus, errorThrown) {
					//$(document).attr("msgerr", jqXHR.statusText);
					alert(textStatus + ": " + errorThrown);
					$('#matchResult').html(jqXHR.responseText);
					$('#matchResult').removeClass('green');
					$('#matchResult').addClass('red');
				}
			}); //ajax close
		}
		
		return false;
	});
	
});


var validate = function() {
	if(!$('#old-pass').val() 
			|| !$('#new-pass').val() 
			|| !$('#re-pass').val()) {//empty string evaluates to false
		
		if(!$('#old-pass').val()) 
			$('#old-pass').next().html("<span style='color: red;'> *</span>");
		else
			$('#old-pass').next().html("");
		if(!$('#new-pass').val()) 
			$('#new-pass').next().html("<span style='color: red;'> *</span>");
		else
			$('#new-pass').next().html("");
		if(!$('#re-pass').val()) 
			$('#re-pass').next().html("<span style='color: red;'> *</span>");
		/*else if($('#old-pass').val() === $('#new-pass').val()) {
			$('#re-pass').next().html("Both the passwords cannot be same.");
			$('#old-pass').val('')
			$('#new-pass').val('');
			$('#re-pass').val('');
		}*/
		else
			$('#re-pass').next().html("");
		
		alert("Please enter all the fields!");
		$('.panel').preventDefault();
		
		/*$('#change-pass').prop("disabled",true);*/
		return false;
	} else {
		/*$('#change-pass').prop("disabled",false);*/
		return true;
	}
}
/**
 * settings.js
 */

$(function() {
	var newPass = $('#new-pass');
	var rePass = $('#re-pass');
	var oldPass = $('#old-pass');
	var allPass = $('#old-pass, #new-pass, #re-pass');
	var button = $('#change-pass button');
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
	
	/* to check password match 
	newPass.keyup(function() {
		
		$('#matchResult').html(
				newPass == $(this).val()
				? "Passwords match."
				: "Passwords donot match."
		);

		
		//result.html('keyup - repass');
		if(!(oldPass.val())) {
			if((newPass.val() == oldPass.val()) && !newPass.is(':empty')) {
				result.html("Passwords cannot be same.");
				result.removeClass('green');
				result.addClass('red');
				button.attr('disabled','disabled');
			} else if((newPass.val() != oldPass.val()) && !newPass.is(':empty')) {
				result.html("");
				result.addClass('green');
				button.attr('disabled','disabled');
			} else {
			result.html('');
		}
			
		}
		
		if(!newPass.val() || !oldPass.val() || !rePass.val()) {
			button.attr('disabled','disabled');
		} else {
			button.removeAttr('disabled');
		}
	});

	$('#re-pass').keyup(function() {
		var newPass = $('#new-pass');
		var rePass = $('#re-pass');

		var result = $('#matchResult');

		alert(newPass.val() + " - " + rePass.val());
		if(!(rePass.val())) {
			if((newPass.val() == rePass.val())) {
				result.html("Passwords match.");
				result.removeClass('red');
				result.addClass('green');
				button.removeAttr('disabled');
			} else {
				result.html("Passwords donot match!");
				result.removeClass('green');
				result.addClass('red');
				button.attr('disabled','disabled');
			}
		}
		
		if(newPass.val() || oldPass.val() || rePass.val()) {
			button.attr('disabled','disabled');
		} else {
			button.removeAttr('disabled');
		}
	});*/

	/* slide effect of settings */
	$(".flip").click(function () {
		var panel = $('.panel');

		panel.slideUp('slow');
		if ($(this).next().is(':visible')) {
			$(this).next().slideUp("slow");
			$(this).removeClass('active');
		} else {
			$(this).next().slideDown("slow");
			$(this).addClass('active');
		}
	});

	/* form validation 
	$('#change-pass input').change(function() {

	});*/

	/* change password form submission */
	$('#change-pass button').click(function(event) {
		event.preventDefault();
		if(validate()) {
			//alert($('#change-pass').serialize());
			$.ajax({
				type: "post",
				url: "ChangePassword",
				data: $('#change-pass').serialize(),
				success: function() {
					$('#change-pass input').next().html("");
					$('#change-pass input').val("");
					$('#matchResult').html("Password updated successfully.");
					$('#matchResult').addClass('green');
				},
				error: function(jqXHR, textStatus, errorThrown) {
					//$(document).attr("msgerr", jqXHR.statusText);
					/*alert(textStatus + ": " + errorThrown);*/
					$('#matchResult').html(jqXHR.responseText);
					$('#matchResult').removeClass('green');
					$('#matchResult').addClass('red');
				}
			}); //ajax close
		}

		return false;
	});



	/* validation of form with each keypress 
	$('#change-pass input').keypress(validate());*/

	/* document click */
	$(document).mouseup(function (e) {
		/* to close all settings */
		var container = $('.settings');

		if(!container.is(e.target)
				&& container.has(e.target).length === 0) {
			$('.panel').slideUp('slow');
			$('.flip').removeClass('active');
			$('#matchResult').html("");
		}
		/* --------------------- */
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
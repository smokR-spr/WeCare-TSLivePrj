/**
 * 
 */

$(function() {
	$('#forgot-form form').submit(function(e) {
		var form = $('#forgot-form form'); 
		var resultDiv = $('#user-id-search-result');
		resultDiv.html('');
		resultDiv.append('<img src="images/loading.gif">');

		$.ajax({
			type: 'post',
			url: 'GetUser',
			data: form.serialize(),
			success: function(data) {
				/*alert('done');*/
				/*alert("test")
				alert(data.uID);*/
				//clearing the div
				resultDiv.html('');
				/*resultDiv.css('color','black');*/
				/*var result = '${userDetails}';
					alert(result);*/
				/*alert(data + "!!");*/

				//creating a new form using jQuery for the security Q & A
				/*resultDiv.append($('<p></p>')
						.css({
							'text-align':'left',
							'font-weight': 'bold'})
						.text('Answer the question below'))
						.append($('<form>')
								.attr('id','sec-form')
								.append(data.securityQuestion + " ")
								.append($('<input>')
										.attr('type','text')
										.attr('name','sec-a')
										.attr('size','35')));
				$('#sec-form input').focus();*/

				resultDiv.append(
						$('<p></p>')
						/*.css({
							'text-align':'left',
							'font-weight':'normal'
						})*/
						.text('Click the reset button below to send the reset link to your registered mail.')
				)
				.append(
						$('<form>')
						.attr('id','reset')
						.append(
								$('<input>')
								.attr('name','reset-uid')
								.attr('type','hidden')
								.attr('value',data.uID)
						)
						.append(
								$('<button>')
								.attr('type','submit')
								.attr('class','button')
								.text('Reset Password')
						)

				);
			},
			error: function(jqXHR, textStatus, errorThrown) {
				//alert('fail');
				resultDiv.html('');
				/*resultDiv.css('color','red');*/
				resultDiv.append($('<p></p>')
						.css({
							'color':'red',
							'text-align':'center'
						})
						.text(jqXHR.responseText)
				);
			}
		});

		/*alert('test');*/
		e.preventDefault(); //prevents default submission of the form
	});


	//sending email if user exists
	$(document).on('submit', '#reset', function(e) {
		var form = $('#reset'); 
		var resultDiv = $('#user-id-search-result');
		resultDiv.html('');
		resultDiv.append('<img src="images/loading.gif">');

		$.ajax({
			type: 'post',
			url: 'SendResetPasswordLink',
			data: form.serialize(),
			success: function(data) {
				//alert(data);

				//clearing the div
				resultDiv.html('');
				
				resultDiv.append($('<p></p>')
						.css({
							'color':'green',
							'text-align':'center'
						})
						.text(data)
				);
			},
			error: function(jqXHR, textStatus, errorThrown) {
				//alert('fail');
				resultDiv.html('');
				/*resultDiv.css('color','red');*/
				resultDiv.append($('<p></p>')
						.css({
							'color':'red',
							'text-align':'center'
						})
						.text(jqXHR.responseText)
				);
			}
		});

		/*alert('test');*/
		e.preventDefault(); //prevents default submission of the form
	});
});
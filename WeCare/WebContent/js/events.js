$(function() {
	$('.marquee').marquee({
		//speed in milliseconds of the marquee
		duration: 5500,
		//gap in pixels between the tickers
		gap: 350,
		//time in milliseconds before the marquee will start animating
		delayBeforeStart: 0,
		//'left' or 'right'
		direction: 'up',
		pauseOnHover: true,
		//true or false - should the marquee be duplicated to show an effect of continues flow
		duplicated: true
	});//marquee
	
	$('#all-events').hide();
	
	$('.events').hover(function() {
		var link = $('#all-events');
		link.fadeIn();
	}
	,
	function() {
		$('#all-events').fadeOut();
	});
	
	(function refreshEvents() {
		$.ajax({
			url: 'events.jsp',
			success: function(data) {
				
			},
			complete: function() {
				setTimeout(refreshEvents, 3600000);
			}
		});
	})();
	
});//doc.ready
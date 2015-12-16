/**
 * 
 */

$(document).ready(function() {
	/* $('.header').animate({
			background-color: "rgba(240, 225, 123, 0.8)",
			margin-top: "0px"
		}), 800
	); */
	/*$('#nav-main').addClass('nav-appear',500);*/
	$('#nav-main').removeClass('nav-hidden',1000)/*.fadeIn('slow')*/;
	/*$('#nav-main').fadeIn(500);*/
	
	//hovering over the button fades rest of them
	var elems = $('.nav-button');
    elems.on('mouseenter mouseleave', function(e) {
        elems.not(this).stop(true).fadeTo('fast', e.type=='mouseenter'?0.1:1);
    });
    
    /*$('.nav-button').hover(function() {
	    $(this).toggleClass('bg-nav',200);
	    $('.nav-button').not(this).stop(true).removeClass('bg-nav',500);
	    if(e.type=='mouseleave') $(this).removeClass('bg-nav',500);
    });*/
    
});
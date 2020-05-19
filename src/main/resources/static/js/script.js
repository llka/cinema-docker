$( document ).ready(function() {

	$('.slider').slick({
			dots: true,
			infinite: true,
			speed: 300,
			slidesToShow: 1,
      adaptiveHeight: true,
      prevArrow:"<button type='button' class='slick-prev pull-left slider_arrow_prev'></button>",
      nextArrow:"<button type='button' class='slick-next pull-right slider_arrow_next'></button>"
	});
	

});


$(document).ready(function() {
	var im;
	$('#im1').hover(function() {
		im = $(this);
		im.fadeOut(function() {
			$(this).load(function() { $(this).fadeIn(); }); 
  			$(this).attr("src", "Slpics/1.jpg"); 
		});

		}, function() {
			$(this).load(function() { $(this).fadeIn(); }); 
  			$(this).attr("src", "Slpics/2.jpg"); 
		}
	);

	$('#im2').hover(function() {
		im = $(this);
		im.fadeOut(function() {
			$(this).load(function() { $(this).fadeIn(); }); 
  			$(this).attr("src", "Slpics/3.jpg"); 
		});

		}, function() {
			$(this).load(function() { $(this).fadeIn(); }); 
  			$(this).attr("src", "Slpics/4.jpg"); 
		}
	);

	$('#im3').hover(function() {
		im = $(this);
		im.fadeOut(function() {
			$(this).load(function() { $(this).fadeIn(); }); 
  			$(this).attr("src", "Slpics/5.jpg"); 
		});

		}, function() {
			$(this).load(function() { $(this).fadeIn(); }); 
  			$(this).attr("src", "Slpics/6.jpg"); 
		}
	);

	$('#im4').hover(function() {
		im = $(this);
		//$(this).css('width', '450px');
		//$(this).css('height', '325px');
		//$(this).css('z-index', '1');
		//$(this).css('border', 'solid black 10px');
		im.fadeOut(function() {
			$(this).load(function() { $(this).fadeIn(); }); 
  			$(this).attr("src", "Slpics/7.jpg"); 
		});

		}, function() {
			$(this).load(function() { $(this).fadeIn(); }); 
  			$(this).attr("src", "Slpics/8.jpg"); 
			//im.load('Slpics/1.jpg');
			//$(this).css('width', '400px');
			//$(this).css('height', '300px');
			//$(this).css('z-index', '0');
		}
	);

	$('#im5').hover(function() {
		im = $(this);
		//$(this).css('width', '450px');
		//$(this).css('height', '325px');
		//$(this).css('z-index', '1');
		//$(this).css('border', 'solid black 10px');
		im.fadeOut(function() {
			$(this).load(function() { $(this).fadeIn(); }); 
  			$(this).attr("src", "Slpics/9.jpg"); 
		});

		}, function() {
			$(this).load(function() { $(this).fadeIn(); }); 
  			$(this).attr("src", "Slpics/10.jpg"); 
			//im.load('Slpics/1.jpg');
			//$(this).css('width', '400px');
			//$(this).css('height', '300px');
			//$(this).css('z-index', '0');
		}
	);

	$('#im6').hover(function() {
		im = $(this);
		//$(this).css('width', '450px');
		//$(this).css('height', '325px');
		//$(this).css('z-index', '1');
		//$(this).css('border', 'solid black 10px');
		im.fadeOut(function() {
			$(this).load(function() { $(this).fadeIn(); }); 
  			$(this).attr("src", "Slpics/11.jpg"); 
		});

		}, function() {
			$(this).load(function() { $(this).fadeIn(); }); 
  			$(this).attr("src", "Slpics/12.jpg"); 
			//im.load('Slpics/1.jpg');
			//$(this).css('width', '400px');
			//$(this).css('height', '300px');
			//$(this).css('z-index', '0');
		}
	);
});

function pictureShuffle(pic) {

}


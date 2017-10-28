$(document).ready(function(){



	function board(width, height){
		if(width < 3){
			width = 3;
		}
		if(height < 3){
			height = 3;
		}

		var tileSize = "100";

		this.createBoard = function(){
			var html = "";
			for(var i = 0; i < height; i++){
				for(var j = 0; j < width; j++){
					html += j == (width-1) ? "<div class='rowEnd'></div>" : "<div></div>";
				}
			}

			$("#board").html(html).css({"grid-template-columns": "repeat("+width+", "+tileSize+"px)",
									 "grid-template-rows": "repeat("+height+", "+tileSize+"px) "});
		}
	}


	$(".createBoard").on("click", function(){
		var w = $("#width").val();
		var h = $("#height").val();

		var b = new board(w,h);
		b.createBoard();
	});



	$(".playerCount").on("change", function(){
		var pc2 = $(".psTwo");
		var plural = $(".plural");
		if(parseInt($("input[name='playerCount']:checked").val()) === 1){
			plural.hide();
			pc2.hide();
			pc2.find("input").prop('disabled', true);
		}else{
			plural.show();
			pc2.show();
			pc2.find("input").prop('disabled', false);
		}
	}).trigger("change");


	$(".btn.symbol").on("click", function(){
		$(".symbolList.show").removeClass("show");
		$(this).next(".symbolList").toggleClass("show");
	})

	$(".symbolList > [class^='icon-']").on("click", function(){
		var cssClass = $(this).attr("class");
		$(".symbolList").removeClass("show");
		$(this).parent().prev(".btn.symbol").attr("class", "btn symbol "+cssClass );
		$(this).parent().next("input").val(cssClass.substr(5));
	});

});
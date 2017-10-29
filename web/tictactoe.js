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

});
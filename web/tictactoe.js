$(document).ready(function(){



	function board(width, height){
		if(width < 3){
			width = 3;
		}
		if(height < 3){
			height = 3;
		}

		var tileSize = "50";

		this.createBoard = function(){
			var html = "";
			for(var i = 0; i < height*width; i++){
				html += "<div></div>";
			}

			$("#board").html(html).css("grid-template-columns", "repeat("+width+", "+tileSize+"px)");

		}
	}


	$(".createBoard").on("click", function(){
		var w = $("#width").val();
		var h = $("#height").val();

		var b = new board(w,h);
		b.createBoard();
	});

});
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


	var validate = function(){
		var error = [];
		var players = parseInt($("input[name='playerCount']:checked").val());
		var pc1name = $("#pcOneName").val() != "";

		if(!pc1name){
			error.push("Player One - Missing Name");
		}

		if(players == 2){
			var pc2name = $("#pcTwoName").val() != "";
			var symbols = $("#pcOneSymbol").val() != $("#pcTwoSymbol").val();

			if(!pc2name){
				error.push("Player Two - Missing Name");
			}

			if($("#pcOneName").val() == $("#pcTwoName").val() && pc1name && pc2name ){
				error.push("Player names must not be identical");
			}

			if(!symbols){
				error.push("Player One and Player Two cannot have the same symbol");
			}
		}
		return error;
	}


	var resetForm = function(){
		$("#pcOneName").val("")
		$("#pcOneSymbol").val("brand")
		$("#pcOneSymbolLabel").next(".symbol").attr("class", "btn symbol icon-brand")
		$("#pcTwoName").val("")
		$("#pcTwoSymbol").val("brand2")
		$("#pcTwoSymbolLabel").next(".symbol").attr("class", "btn symbol icon-brand2")
		$("#pcOne").trigger("click");
	}

	var processForm = function(){
		var json = {"players" : [{"name" : $("#pcOneName").val() , "symbol" : $("#pcOneSymbol").val()}],
					"boardWidth" : $("#tWidth").val(),
					"boardHeight" : $("#tHeight").val(),
					"winCondition" : $("#winRule").val()
					}

	    if (parseInt($("input[name='playerCount']:checked").val()) === 2){
			json.players.push({"name" : $("#pcTwoName").val() , "symbol" : $("#pcTwoSymbol").val()})
	    }
		return json;	
	}

	$(".btn.reset").on("click", resetForm);

	$(".btn.startGame").on("click", function(){
		var error = validate();
		if(error.length == 0){
			$(".error").removeClass("show");
			alert(JSON.stringify(processForm()));
		}
		else{
			$(".error").text(error).addClass("show");
		}
	});

});
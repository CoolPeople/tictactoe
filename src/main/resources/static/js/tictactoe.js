$(document).ready(function () {

    var game = {};

    function board(width, height) {
        if (width < 3) {
            width = 3;
        }
        if (height < 3) {
            height = 3;
        }

        var tileSize = "100";

        this.createBoard = function () {
            var html = "";
            for (var i = 0; i < height; i++) {
                for (var j = 0; j < width; j++) {
                    html += j == (width - 1) ? "<div class='gameTile rowEnd'></div>" : "<div class='gameTile'></div>";
                }
            }

            $("#board").html(html).css({
                "grid-template-columns": "repeat(" + width + ", " + tileSize + "px)",
                "grid-template-rows": "repeat(" + height + ", " + tileSize + "px) "
            });
        }
    }


    $(".playerCount").on("change", function () {
        game.playerCount = parseInt($("input[name='playerCount']:checked").val());
        var pc2 = $(".psTwo");
        var plural = $(".plural");
        if (game.playerCount === 1) {
            plural.hide();
            pc2.hide();
            pc2.find("input").prop('disabled', true);
        } else {
            plural.show();
            pc2.show();
            pc2.find("input").prop('disabled', false);
        }
    }).trigger("change");


    $(".btn.symbol").on("click", function () {
        if (!$(this).next(".symbolList").hasClass("show")) {
            $(".symbolList.show").removeClass("show");
            $(this).next(".symbolList").toggleClass("show");
        }
        else {
            $(this).next(".symbolList").removeClass("show")
        }
    })

    $(".symbolList > [class^='icon-']").on("click", function () {
        var cssClass = $(this).attr("class");
        $(".symbolList").removeClass("show");
        $(this).parent().prev(".btn.symbol").attr("class", "btn symbol " + cssClass);
        $(this).parent().next("input").val(cssClass.substr(5));
    });


    var validate = function () {
        var error = [];
        var pc1name = $("#pcOneName").val() != "";

        if (!pc1name) {
            error.push("Player One - Missing Name");
        }

        if (game.playerCount === 2) {
            var pc2name = $("#pcTwoName").val() != "";
            var symbols = $("#pcOneSymbol").val() != $("#pcTwoSymbol").val();

            if (!pc2name) {
                error.push("Player Two - Missing Name");
            }

            if ($("#pcOneName").val() == $("#pcTwoName").val() && pc1name && pc2name) {
                error.push("Player names must not be identical");
            }

            if (!symbols) {
                error.push("Player One and Player Two cannot have the same symbol");
            }
        }
        return error;
    }


    var resetForm = function () {
        $("#pcOneName").val("");
        $("#pcOneSymbol").val("brand");
        $("#pcOneSymbolLabel").next(".symbol").attr("class", "btn symbol icon-brand");
        $("#pcTwoName").val("");
        $("#pcTwoSymbol").val("brand2");
        $("#pcTwoSymbolLabel").next(".symbol").attr("class", "btn symbol icon-brand2");
        $("#pcOne").trigger("click");
        $("#tWidth").val(3);
        $("#tHeight").val(3);
        $("#winRule").val(3);
    }

    var processForm = function () {
        var json = {
            "players": [{
                "name": $("#pcOneName").val(),
                "symbol": $("#pcOneSymbol").val()
            }],
            "boardWidth": $("#tWidth").val(),
            "boardHeight": $("#tHeight").val(),
            "winCondition": $("#winRule").val()
        }

        if (game.playerCount === 2) {
            json.players.push({
                "name": $("#pcTwoName").val(),
                "symbol": $("#pcTwoSymbol").val()
            });
        }
        return json;
    }

    var toggleMenu = function () {
        $("#menuWrapper").toggleClass("show");
        $("#gameWrapper").toggleClass("show");
    }

    $(".btn.reset").on("click", resetForm);

    $(".btn.startGame").on("click", function () {
        var error = validate();
        if (error.length == 0) {
            var json = processForm();
            $(".error").removeClass("show");

            game.players = json.players;
            setTurn(0);
            $("#gameOver").removeClass("show");
            $("#gameTurn").addClass("show");

            if (game.playerCount == 1) {
                game.players.push({"name": "ai", "symbol": "ai"});
                game.ai = new ai();
            }

            var b = new board(json.boardWidth, json.boardHeight);
            b.createBoard();

            console.log("new game", json);

            $.ajax({
                url: "/newGame",
                type: "POST",
                contentType: "application/json",
                data: JSON.stringify(processForm()),
                dataType: "json",
                cache: false,
                success: function () {
                    console.log("success");
                }
            });

            game.pause = false;
            toggleMenu();
        }
        else {
            $(".error").text(error).addClass("show");
        }
    });

    var setTurn = function (playerIndex) {
        game.currentPlayer = playerIndex;
        $("#gameTurn").text(game.players[playerIndex].name + "'s turn");
    }


    var gameOver = function (winner) {
        game.pause = true;
        $("#gameOver").addClass("show");
        $("#gameTurn").removeClass("show");

        if (winner != "") {
            $("#gameOverMessage").text(winner + " has won!");
        }
        else {
            $("#gameOverMessage").text("It's a draw");
        }
    }

    $("#menuButton").on("click", toggleMenu);


    $(document).on("click", '.gameTile:not(.marked)', function () {
        if (!game.pause) {
            doTurn($(this).index());
        }
    });

    var doTurn = function (gameTileIndex) {
        var pTurn = game.currentPlayer;
        var pName = game.players[pTurn].name;
        var pSymb = game.players[pTurn].symbol;
        console.log(pName + " " + gameTileIndex);

        //talk to backend about the turn happening
        var turnResult;
        $.ajax({method: "POST",
            url: "/doTurn",
            data:  {slot: gameTileIndex},
            async: false,
            complete :  function(result){
                turnResult = result.responseText;
            }});

        if(turnResult == 0){ //invalid move
            console.log("invalid move");
        }
        else if(turnResult == 1) //valid move
        {

            $(".gameTile").eq(gameTileIndex).addClass("marked icon-" + pSymb);

            setTurn(pTurn != game.players.length - 1 ? game.currentPlayer + 1 : 0);

            //queue ai turn
            if (pName != "ai" && game.playerCount == 1 && pTurn == 0) {
                game.pause = true;
                setTimeout(function () {
                    game.ai.simulateTurn();
                    game.pause = false;
                }, 1000);
            }

        }else if(turnResult == 2){ //game over

            $(".gameTile").eq(gameTileIndex).addClass("marked icon-" + pSymb);
            gameOver(pName);
        }
    }

    function ai() {
        this.getRandomTile = function () {
            var avalTiles = $(".gameTile").not(".marked");
            var rand = Math.floor((Math.random() * avalTiles.length));

            console.log(length);
            return $(avalTiles[rand]).index();
        }

        this.simulateTurn = function () {
            var tile = this.getRandomTile();
            if (tile != -1) {
                doTurn(tile);
            }
        }
    }

    $("#playAgainButton").on("click", function(){
        toggleMenu();
        $(".btn.startGame").trigger("click");
    });

});
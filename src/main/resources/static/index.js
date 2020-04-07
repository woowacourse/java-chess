$(document).ready(function() {
    var checkCount = 0;
    var startX;
    var startY;
    var targetX;
    var targetY;

    $continue = function() {
        jQuery.ajax({
            type: "POST",
            url: "/continue-game",
            dataType: "json",
            success: function(data) {
                $setPieces(data);
            }
        });
    }

    $continue();

    $('.start_new_game').on('click', function() {
        jQuery.ajax({
            type: "POST",
            url: "/new-game",
            dataType: "json",
            success: function(data) {
                $setPieces(data);
            }
        });
    });

    $('.cell').on('click', function() {
        if (checkCount == 0 && $(this).text() == "") {
            return;
        }
        $(this).css("box-shadow", "0 0 0 3px red inset");
        checkCount = checkCount + 1;
        if (checkCount == 2) {
            $targetClicked(this);
            return;
        }
        startX = $(this).attr('row');
        startY = $(this).attr('column');
    });

    $targetClicked = function(cell) {
        checkCount = 0;
        $('.cell').css("box-shadow", "");
        targetX = $(cell).attr('row');
        targetY = $(cell).attr('column');
        $postMovingInfo();
    }

    $postMovingInfo = function() {
        var allData = {
            "startX": startX,
            "startY": startY,
            "targetX": targetX,
            "targetY": targetY
        };
        jQuery.ajax({
            type: "POST",
            url: "/move",
            dataType: "json",
            data: allData,
            success: function(data) {
                $setPieces(data);
            },
            error : function(request){
                alert(request.responseText);
            },
        });
    }

    $setPieces = function(data) {
        $clearBoard();
        for (var i in data.chessPieces) {
            $setPiece(data.chessPieces[i]);
        }
    }

    $setPiece = function(chessPiece) {
        $('.cell[row=' + chessPiece.x + '][column=' + chessPiece.y + ']').text($translateToEmoji(chessPiece.name));
    }

    $clearBoard = function() {
        $('.cell').text("");
    }

    $translateToEmoji = function(letter) {
        if (letter == "p") {
            return "♙";
        }
        if (letter == "r") {
            return "♖";
        }
        if (letter == "n") {
            return "♘";
        }
        if (letter == "b") {
            return "♗";
        }
        if (letter == "q") {
            return "♕";
        }
        if (letter == "k") {
            return "♔";
        }
        if (letter == "P") {
            return "♟";
        }
        if (letter == "R") {
            return "♜";
        }
        if (letter == "N") {
            return "♞";
        }
        if (letter == "B") {
            return "♝";
        }
        if (letter == "Q") {
            return "♛";
        }
        if (letter == "K") {
            return "♚";
        }
    }
});
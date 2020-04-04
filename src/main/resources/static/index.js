$(document).ready(function(){
    var checkCount = 0;
    var startX;
    var startY;
    var targetX;
    var targetY;

    $continue = function(){
        jQuery.ajax({
            type:"POST",
            url:"/continue_game",
            dataType: "json",
            success : function(data) {
                $setPieces(data);
            }
        });
    }

    $continue();

    $('.start_new_game').on('click',function(){
        jQuery.ajax({
            type:"POST",
            url:"/new_game",
            dataType: "json",
            success : function(data) {
                $setPieces(data);
            }
        });
    });

    $('.continue').on('click',function(){
        jQuery.ajax({
            type:"POST",
            url:"/continue_game",
            dataType: "json",
            success : function(data) {
                $setPieces(data);
            }
        });
    });

    $('.cell').on('click',function(){
        $(this).css("box-shadow", "0 0 0 3px red inset");
        checkCount = checkCount + 1;
        if(checkCount == 2){
            checkCount = 0;
            $('.cell').css("box-shadow", "");
            targetX = $(this).attr('row');
            targetY = $(this).attr('column');
            $postMovingInfo();
        }
        else{
            startX = $(this).attr('row');
            startY = $(this).attr('column');
        }
    });

    $postMovingInfo = function() {
        var allData = {"startX": startX, "startY": startY, "targetX": targetX, "targetY": targetY};
        jQuery.ajax({
            type:"POST",
            url:"/move",
            dataType: "json",
            data: allData,
            success : function(data) {
                $setPieces(data);
            }
        });
    }

    $setPieces = function(data){
        $clearBoard();
        for(var i in data.chessPieces){
            $setPiece(data.chessPieces[i]);
        }
    }

    $setPiece = function(chessPiece) {
        $('.cell[row='+chessPiece.x+'][column='+chessPiece.y+']').text($translateToEmoji(chessPiece.name));
    }

    $clearBoard = function(){
        for(var i = 1; i <= 8; i++){
            for(var j = 1; j <= 8 ;j++){
                $('.cell[row='+i+'][column='+j+']').text("");
            }
        }
    }

    $translateToEmoji = function(letter){
        if(letter == "p"){
            return "♙";
        }
        if(letter == "r"){
            return "♖";
        }
        if(letter == "n"){
            return "♘";
        }
        if(letter == "b"){
            return "♗";
        }
        if(letter == "q"){
            return "♔";
        }
        if(letter == "k"){
            return "♕";
        }
        if(letter == "P"){
            return "♟";
        }
        if(letter == "R"){
            return "♜";
        }
        if(letter == "N"){
            return "♞";
        }
        if(letter == "B"){
            return "♝";
        }
        if(letter == "Q"){
            return "♚";
        }
        if(letter == "K"){
            return "♛";
        }
    }
});

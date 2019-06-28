const chessPieceUnicodeMap = {
    k: '\u2654',
    q: '\u2655',
    r: '\u2656',
    b: '\u2657',
    n: '\u2658',
    p: '\u2659',
    K: '\u265A',
    Q: '\u265B',
    R: '\u265C',
    B: '\u265D',
    N: '\u265E',
    P: '\u265F',
    S: ' '
};

function appendChessBlock() {
    for (var i = 0; i < 8; i++) {
        var chessRow = document.querySelector("#chess-row-template").innerText;
        var chessBlocks = "";
        for (var j = 0; j < 8; j++) {
            var chessBlock = document.querySelector("#chess-block-template").innerText.split("{ij}").join(i + "" + j);
            chessBlock = (i + j) % 2 ? chessBlock.replace("{backgroundColor}", "btn-dark") : chessBlock.replace("{backgroundColor}", "btn-light");
            chessBlocks += chessBlock;
        }
        document.querySelector("#chess-block-area").innerHTML += chessRow.replace("{chessBlocks}", chessBlocks);
    }
}

function requestChessScores() {
    axios.get("/chessScore")
        .then(function (response) {
            var blackScore = response.data.blackScore;
            var whiteScore = response.data.whiteScore;
            document.querySelectorAll(".chess-block").forEach(function (v) {
                v.disabled = true;
            });
            var result = "Black Team: " + blackScore + ", White Team: " + whiteScore;
            alert(result);
        })
        .catch(alertError);
}

function requestChessPieces() {
    axios.get("/chessBoard")
        .then(function (response) {
            mapChessPieces(response);
            if (response.data.isGameOver) {
                requestChessScores();
            }
        })
        .catch(alertError);
}

function postChessPieceMoving(source, target) {
    axios.post("/chessBoard", {
        sourceY: parseInt(source[0]),
        sourceX: parseInt(source[1]),
        targetY: parseInt(target[0]),
        targetX: parseInt(target[1])
    })
        .then(function(response){
            mapChessPieces(response);
            if (response.data.isGameOver) {
                requestChessScores();
            }
        })
        .catch(alert);
}

function clickChessBlock(chessBlockId) {
    var chessBlocks = document.querySelectorAll(".chess-block");
    var clickedButtonCount = 0;
    var source = "";
    document.querySelector("#" + chessBlockId).setAttribute("disabled", true);
    for (var i = 0, len = chessBlocks.length; i < len; i++) {
        if (chessBlocks[i].disabled) {
            clickedButtonCount++;
            if (chessBlocks[i].id !== chessBlockId) {
                source = chessBlocks[i].id;
            }
        }
    }

    if (clickedButtonCount < 2) {
        return;
    }

    chessBlocks.forEach(function (v) {
        v.removeAttribute("disabled");
    });

    var target = chessBlockId.replace("chess-block", "");
    source = source.replace("chess-block", "");

    postChessPieceMoving(source, target);
}

function mapChessPieces(response) {
    if (response.data === "error") {
        return;
    }

    var chessPieces = response.data;
    for (var i = 0, len = chessPieces.rows.length; i < len; i++) {
        var row = chessPieces.rows[i];
        for (var j = 0; j < 8; j++) {
            document.querySelector("#chess-block" + i + j).innerText = chessPieceUnicodeMap[row[j]];
        }
    }
}

function newGame(){
    axios.post("/newRound")
        .then(function(response){
            document.querySelector("#chess-block-area").innerHTML = "";
            appendChessBlock();
            mapChessPieces(response);
        })
        .catch(alertError)
}

window.addEventListener("load", function () {
    appendChessBlock();
    requestChessPieces();
    document.querySelector("#restart-button").addEventListener(("click"), newGame);
});
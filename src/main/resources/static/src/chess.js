function startChess() {
    $.ajax({
        url: "/pieces",
        type: 'get',
        success(data) {
            document.getElementById("board").hidden = false;
            document.getElementById("start_btn").hidden = true;
            document.getElementById("status_btn").hidden = false;
            document.getElementById("end_btn").hidden = false;

            let pieces = JSON.parse(data);
            $.each(pieces, function (idx, piece) {
                setPiece(piece.position, piece.symbol);
            });
        }
    });
}

function setPiece(position, symbol) {
    let pieceImageSrc = findPieceImageSrc(symbol);
    document.getElementById(position).innerHTML = '<img src=' + pieceImageSrc + ' class=piece alt="">';
}

let pieceImages = {
    "B": "/images/black_bishop.png",
    "K": "/images/black_king.png",
    "N": "/images/black_knight.png",
    "P": "/images/black_pawn.png",
    "Q": "/images/black_queen.png",
    "R": "/images/black_rook.png",

    "b": "/images/white_bishop.png",
    "k": "/images/white_king.png",
    "n": "/images/white_knight.png",
    "p": "/images/white_pawn.png",
    "q": "/images/white_queen.png",
    "r": "/images/white_rook.png"
}

function findPieceImageSrc(symbol) {
    return pieceImages[symbol];
}

let positions = []

function move(position) {
    positions.push(position);
    if (positions.length < 2) {
        return;
    }

    let sourcePosition = positions[0];
    let targetPosition = positions[1];
    $.ajax({
        url: "/move",
        type: 'post',
        traditional: true,
        data: {
            source: sourcePosition,
            target: targetPosition
        },
        success() {
            document.getElementById(targetPosition).innerHTML = document.getElementById(sourcePosition).innerHTML;
            document.getElementById(sourcePosition).innerHTML = "";
        },
        error(request) {
            alert(request.responseText);
        }
    });
    positions = []
}

function showStatus() {
    $.ajax({
        url: "/status",
        type: 'get',
        success(data) {
            let status = JSON.parse(data);
            var message = "";
            $.each(status.scores, function (idx, score) {
                message += score.name + " : " + score.score + "점\n";
            });
            if (status.winnerName === "") {
                message += "동점입니다.";
            } else {
                message += status.winnerName + " 진영이 이기고 있습니다.";
            }
            alert(message);
        }
    });
}
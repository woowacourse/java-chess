let pieceImages = {
    "B": "static/images/black_bishop.png",
    "K": "static/images/black_king.png",
    "N": "static/images/black_knight.png",
    "P": "static/images/black_pawn.png",
    "Q": "static/images/black_queen.png",
    "R": "static/images/black_rook.png",

    "b": "static/images/white_bishop.png",
    "k": "static/images/white_king.png",
    "n": "static/images/white_knight.png",
    "p": "static/images/white_pawn.png",
    "q": "static/images/white_queen.png",
    "r": "static/images/white_rook.png"
}

function findPiece(position, symbol) {
    document.getElementById(position).innerHTML = `<img src = ${pieceImages[symbol]} class = piece>`
}

let positions = []

function move(position) {
    positions.push(position);
    if (positions.length < 2) {
        return;
    }

    $.ajax({
        url: "/move",                    // 전송 URL
        type: 'POST',                // GET or POST 방식
        traditional: true,
        data: {
            source: positions[0],       // 보내고자 하는 data 변수 설정
            target: positions[1]       // 보내고자 하는 data 변수 설정
        }
    });
    positions = []
}
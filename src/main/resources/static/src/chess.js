function startChess() {
    $.ajax({
        url: "/pieces",
        type: 'get',
        success(data) {
            let pieces = JSON.parse(data);
            $.each(pieces, function (idx, piece) {
                setPiece(piece.position, piece.symbol);
            });
        }
    });
}

function setPiece(position, symbol) {
    let pieceImageSrc = findPieceImageSrc(symbol);
    document.getElementById(position).innerHTML = '<img src='+ pieceImageSrc +' class=piece alt="">';
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

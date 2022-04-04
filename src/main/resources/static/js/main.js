createChessBoard()

function createChessBoard() {
    const pieces = [
        ["rook_black", "knight_black", "bishop_black", "queen_black", "king_black", "bishop_black", "knight_black", "rook_black"],
        new Array(8).fill("pawn_black"),
        new Array(),
        new Array(),
        new Array(),
        new Array(),
        new Array(8).fill("pawn_white"),
        ["rook_white", "knight_white", "bishop_white", "queen_white", "king_white", "bishop_white", "knight_white", "rook_white"]
    ];
    makeTable(pieces);
}

function makeTable(pieces) {
    const table = document.getElementById("chess-board");
    for (let i = 0; i < 8; i++) {
        table.appendChild(makeTr(pieces));
    }
}

function makeTr(pieces, row) {
    const newTr = document.createElement("tr");
    for (let j = 0; j < row; j++) {
        newTr.appendChild(makeTd(pieces, row, j));
    }
    return newTr;
}

// <td id="h8"><img src="images/rook_black.png" className="-ing"></td>
function makeTd(pieces, row, col) {
    const newTd = document.createElement("td");
    newTd.className = String.fromCharCode('a'.charCodeAt(0) + col) + String(8 - row);
    if (pieces[row][col]) {
        const piece = document.createElement("img");
        piece.src = "images/" + pieces[row][col] + ".png";
        newTd.appendChild(piece);
    }
    return newTd;
}

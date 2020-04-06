const PIECES = {
    BLACK_KING: `<img src="../image/king_black.png" class="black king piece"/>`,
    BLACK_QUEEN: `<img src="../image/queen_black.png" class="black queen piece"/>`,
    BLACK_ROOK: `<img src="../image/rook_black.png" class="black rook piece"/>`,
    BLACK_BISHOP: `<img src="../image/bishop_black.png" class="black bishop piece"/>`,
    BLACK_KNIGHT: `<img src="../image/knight_black.png" class="black knight piece"/>`,
    BLACK_PAWN: `<img src="../image/pawn_black.png" class="black piece pawn"/>`,
    WHITE_KING: `<img src="../image/king_white.png" class="white king piece"/>`,
    WHITE_QUEEN: `<img src="../image/queen_white.png" class="white queen piece"/>`,
    WHITE_ROOK: `<img src="../image/rook_white.png" class="white rook piece"/>`,
    WHITE_BISHOP: `<img src="../image/bishop_white.png" class="white bishop piece"/>`,
    WHITE_KNIGHT: `<img src="../image/knight_white.png" class="white knight piece"/>`,
    WHITE_PAWN: `<img src="../image/pawn_white.png" class="white pawn piece"/>`,
};

async function clearBoard() {
    document.querySelectorAll(".coordinates").forEach(element => {
        while (element.hasChildNodes()) {
            element.removeChild(element.lastElementChild);
        }
    });
}

async function setBoard(board) {
    await clearBoard();
    for (const piece of board.pieces) {
        document.getElementById(piece.position.toLowerCase()).innerHTML = PIECES[piece.pieceType];
    }
}

async function getBoard() {
    const response = await fetch("http://localhost:8080/start");
    return await response.json();
}

async function initializeBoard() {
    const board = await getBoard();
    await setBoard(board);
}
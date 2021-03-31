const PIECES = {
    BLACK_KING: `<img src="../img/king_black.png" class="piece" alt="king_black"/>`,
    BLACK_QUEEN: `<img src="../img/queen_black.png" class="piece" alt="queen_black"/>`,
    BLACK_ROOK: `<img src="../img/rook_black.png" class="piece" alt="rook black"/>`,
    BLACK_BISHOP: `<img src="../img/bishop_black.png" class="piece" alt="bishop black"/>`,
    BLACK_KNIGHT: `<img src="../img/knight_black.png" class="piece" alt="knight black"/>`,
    BLACK_PAWN: `<img src="../img/pawn_black.png" class="piece" alt="pawn black"/>`,
    WHITE_KING: `<img src="../img/king_white.png" class="piece" alt="king white"/>`,
    WHITE_QUEEN: `<img src="../img/queen_white.png" class="piece" alt="queen white"/>`,
    WHITE_ROOK: `<img src="../img/rook_white.png" class="piece" alt="rook_white"/>`,
    WHITE_BISHOP: `<img src="../img/bishop_white.png" class="piece" alt="bishop white"/>`,
    WHITE_KNIGHT: `<img src="../img/knight_white.png" class="piece" alt="knight white"/>`,
    WHITE_PAWN: `<img src="../img/pawn_white.png" class="piece" alt="pawn white"/>`,
}

document.addEventListener("DOMContentLoaded", buildPieces);

const $newBoard = document.getElementById("new-board");

function buildPieces() {
    const pieces = $newBoard.textContent.split(",");
    Array.from(pieces)
        .map(piece => piece.trim().replace("\n", ""))
        .filter(piece => Boolean(piece))
        .forEach(piece => {
            const pieceToken = piece.split(":");
            const position = pieceToken[0];
            const pieceName = pieceToken[1];
            if (!pieceName.includes("BLANK")) {
                document.getElementById(position).innerHTML = PIECES[pieceName];
            }
        })
    $newBoard.remove();
}

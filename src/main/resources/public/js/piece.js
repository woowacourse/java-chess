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

async function inputImageAtBoard(chessId) {
    const response = await fetch('/chess/' + chessId + '/pieces');
    const data = await response.json();
    const json = JSON.parse(data.content);

    Array.from(json)
        .filter(piece => piece.name !== "BLANK")
        .forEach(piece => {
            const position = piece.position;
            const pieceName = piece.color + "_" + piece.name;
            document.getElementById(position).innerHTML = PIECES[pieceName];
        });
    return data;
}

async function buildPieces() {
    const chessId = getCookie("chessId");
    await inputImageAtBoard(chessId);
}

function getCookie(name) {
    return document.cookie.split("; ").find(row => row.startsWith(name)).split("=")[1];
}
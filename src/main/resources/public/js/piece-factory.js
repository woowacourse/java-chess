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

function buildPieces() {
    fetch("/chess")
        .then(res => res.json())
        .then(data => {
            if (data.body.turn === "BLACK") {
                document.getElementById("black-versus").classList.add("currentTurn");
                document.getElementById("white-versus").classList.remove("currentTurn");
            } else {
                document.getElementById("black-versus").classList.remove("currentTurn");
                document.getElementById("white-versus").classList.add("currentTurn");
            }
            Array.from(data.body.boardDTO.pieceDTOS)
                .filter(piece => piece.name !== "BLANK")
                .forEach(piece => {
                    const position = piece.position;
                    const pieceName = piece.color + "_" + piece.name;
                    document.getElementById(position).innerHTML = PIECES[pieceName];
                })
        })
}

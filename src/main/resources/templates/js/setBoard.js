let piece = {
    "P": '<img src="../img/piece/pawn_black.png" width="80px" height="80px"/>',
    "p": '<img src="../img/piece/pawn_white.png"  width="80px" height="80px"/>',
    "R": '<img src="../img/piece/rook_black.png" width="80px" height="80px"/>',
    "r": '<img src="../img/piece/rook_white.png" width="80px" height="80px"/>',
    "B": '<img src="../img/piece/bishop_black.png" width="80px" height="80px"/>',
    "b": '<img src="../img/piece/bishop_white.png" width="80px" height="80px"/>',
    "N": '<img src="../img/piece/knight_black.png" width="80px" height="80px"/>',
    "n": '<img src="../img/piece/knight_white.png" width="80px" height="80px"/>',
    "Q": '<img src="../img/piece/queen_black.png" width="80px" height="80px"/>',
    "q": '<img src="../img/piece/queen_white.png" width="80px" height="80px"/>',
    "K": '<img src="../img/piece/king_black.png" width="80px" height="80px"/>',
    "k": '<img src="../img/piece/king_white.png" width="80px" height="80px"/>',
}

function findById(position, symbol) {
    if (piece[symbol] !== undefined) {
        document.getElementById(position).innerHTML = piece[symbol];
    }
}

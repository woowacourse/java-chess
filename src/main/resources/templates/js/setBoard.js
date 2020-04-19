const piece = {
    P: "<img src='../piece/pawn_black.png' style='max-width: 100%' alt='blackPawn'>",
    R: "<img src='../piece/rook_black.png' style='max-width: 100%' alt='blackRook'>",
    N: "<img src='../piece/knight_black.png' style='max-width: 100%' alt='blackKnight'>",
    B: "<img src='../piece/bishop_black.png' style='max-width: 100%' alt=blackBishop'>",
    Q: "<img src='../piece/queen_black.png' style='max-width: 100%' alt='blackQueen'>",
    K: "<img src='../piece/king_black.png' style='max-width: 100%' alt='blackKing'>",
    p: "<img src='../piece/pawn_white.png' style='max-width: 100%' alt='whitePawn'>",
    r: "<img src='../piece/rook_white.png' style='max-width: 100%' alt='whiteRook'>",
    n: "<img src='../piece/knight_white.png' style='max-width: 100%' alt='whiteKnight'>",
    b: "<img src='../piece/bishop_white.png' style='max-width: 100%' alt='whiteBishop'>",
    q: "<img src='../piece/queen_white.png' style='max-width: 100%' alt='whiteQueen'>",
    k: "<img src='../piece/king_white.png' style='max-width: 100%' alt='whiteKing'>"
};

function findPieceBy(value) {
    if (value === 'p') {
        return `${piece.p}`
    }
    if (value === 'r') {
        return `${piece.r}`
    }
    if (value === 'n') {
        return `${piece.n}`
    }
    if (value === 'b') {
        return `${piece.b}`
    }
    if (value === 'q') {
        return `${piece.q}`
    }
    if (value === 'k') {
        return `${piece.k}`
    }
    if (value === 'P') {
        return `${piece.P}`
    }
    if (value === 'R') {
        return `${piece.R}`
    }
    if (value === 'N') {
        return `${piece.N}`
    }
    if (value === 'B') {
        return `${piece.B}`
    }
    if (value === 'Q') {
        return `${piece.Q}`
    }
    if (value === 'K') {
        return `${piece.K}`
    }
}

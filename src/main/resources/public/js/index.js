const $chessBoard = document.getElementById("chess-board");

const initChessBoard = () => {
    initPieceRank();
}

const initPieceKind = (position) => {
    const map = new Map([
        ['a1', 'white_rook'], ['b1', 'white_knight'], ['c1', 'white_bishop'], ['d1', 'white_queen'],
        ['e1', 'white_king'], ['f1', 'white_bishop'], ['g1', 'white_knight'], ['h1', 'white_rook'],
        ['a2', 'white_pawn'], ['b2', 'white_pawn'], ['c2', 'white_pawn'], ['d2', 'white_pawn'],
        ['e2', 'white_pawn'], ['f2', 'white_pawn'], ['g2', 'white_pawn'], ['h2', 'white_pawn'],
        ['a7', 'black_pawn'], ['b7', 'black_pawn'], ['c7', 'black_pawn'], ['d7', 'black_pawn'],
        ['e7', 'black_pawn'], ['f7', 'black_pawn'], ['g7', 'black_pawn'], ['h7', 'black_pawn'],
        ['a8', 'black_rook'], ['b8', 'black_knight'], ['c8', 'black_bishop'], ['d8', 'black_queen'],
        ['e8', 'black_king'], ['f8', 'black_bishop'], ['g8', 'black_knight'], ['h8', 'black_rook']
    ]);
    return map.get(position);
}

const fileToInt = (value) => {
    const map = new Map([
        [0, "a"], [1, "b"], [2, "c"], [3, "d"],
        [4, "e"], [5, "f"], [6, "g"], [7, "h"]
    ]);
    return map.get(value);
}

const initPieceRank = () => {
    for (let rank = 1; rank <= 2; rank++) {
        initPieceFile(rank.toString());
    }
    for (let rank = 7; rank <= 8; rank++) {
        initPieceFile(rank.toString());
    }
}

const initPieceFile = (rank) => {
    for (let file = 0; file < 8; file++) {
        const position = fileToInt(file) + rank;
        const positionTag = document.getElementById(fileToInt(file) + rank);
        initPiece(positionTag, initPieceKind(position));
    }
}

const initPiece = (position, pieceKind) => {
    const whitePiece = document.createElement("img");
    whitePiece.className = 'chess-piece';
    whitePiece.src = "images/" + pieceKind + ".png";
    position.appendChild(whitePiece);
}
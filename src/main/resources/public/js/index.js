let currentClickPosition = '';
let currentPiece = '';
let destinationClickPosition = '';

const initChessBoard = () => {
    initPieceRank();
    initMapEvent();
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

const intToFile = (value) => {
    const map = new Map([
        [0, "a"], [1, "b"], [2, "c"], [3, "d"],
        [4, "e"], [5, "f"], [6, "g"], [7, "h"]
    ]);
    return map.get(value);
}

const toPieceFullName = (name) => {
    const map = new Map([
        ['P', 'black_pawn'], ['R', 'black_rook'], ['N', 'black_knight'], ['B', 'black_bishop'],
        ['Q', 'black_queen'], ['K', 'black_king'], ['p', 'white_pawn'], ['r', 'white_rook'],
        ['n', 'white_knight'], ['b', 'white_bishop'], ['q', 'white_queen'], ['k', 'white_king']
    ]);
    return map.get(name);
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
        const position = intToFile(file) + rank;
        const positionTag = document.getElementById(intToFile(file) + rank);
        markPiece(positionTag, initPieceKind(position));
    }
}

const markPiece = (position, pieceKind) => {
    const piece = document.createElement("img");
    piece.className = 'chess-piece';
    piece.src = "images/" + pieceKind + ".png";
    position.appendChild(piece);
}

const initMapEvent = () => {
    for (let file = 0; file < 8; file++) {
        for (let rank = 1; rank <= 8; rank++) {
            const positionTag = document.getElementById(intToFile(file) + rank);
            positionTag.addEventListener('click', clickToMove);
        }
    }
}

const clickToMove = async (e) => {
    if (currentClickPosition === '' && e.target.classList.contains('chess-piece')) {
        markCurrentPiece(e);
        return;
    }
    if (currentClickPosition !== '') {
        await markDestinationPiece(e);
    }
}

const markCurrentPiece = (e) => {
    currentClickPosition = e.currentTarget;
    currentPiece = e.target;
    currentClickPosition.style.backgroundColor = 'red';
}

const markDestinationPiece = async (e) => {
    destinationClickPosition = e.currentTarget;
    currentClickPosition.style.backgroundColor = '';
    const chessMap = await movePiece();
    showChessMap(chessMap.chessMap);
    checkEndGame(chessMap.isRunning);
}

const showChessMap = (chessMap) => {
    for (let file = 0; file < 8; file++) {
        for (let rank = 1; rank <= 8; rank++) {
            const positionTag = document.getElementById(intToFile(file) + rank);
            if (positionTag.hasChildNodes()) {
                positionTag.removeChild(positionTag.firstChild);
            }
            if (chessMap[8 - rank][file] === '.') {
                continue;
            }
            markPiece(positionTag, toPieceFullName(chessMap[8 - rank][file]));
        }
    }
}

const movePiece = async () => {
    const bodyValue = {
        currentPosition: currentClickPosition.id,
        destinationPosition: destinationClickPosition.id
    };
    let chessMap = await fetch('/move', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8',
            'Accept': 'application/json'
        },
        body: JSON.stringify(bodyValue)
    });
    currentClickPosition = '';
    destinationClickPosition = '';
    chessMap = await chessMap.json();
    return chessMap;
}

const checkEndGame = (isRunning) => {
    if (!isRunning) {
        alert('게임 종료');
        return showResult();
    }
}

const showStatus = async () => {
    let status = await fetch('/status');
    status = await status.json();
    alert(status.scoreStatus);
}

const showResult = async () => {
    let result = await fetch('/end');
    result = await result.json();
    alert(result.result);
}
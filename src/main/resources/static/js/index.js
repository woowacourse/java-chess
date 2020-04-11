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

let movingPiece = null;
let turn = document.getElementById("turn");
let score = document.getElementById("score");

document.querySelectorAll(".coordinates").forEach((piece) => {
    piece.onclick = () => {
        if (isSamePiece(piece)) {
            clearClickedCoordinates();
            return;
        }
        if (isFirstClickedCellEmpty(piece) || isNotTurn(piece)) {
            return;
        }
        if (isFirstClick()) {
            checkAndFillClickedColor(piece);
        } else {
            move(piece);
        }
    }
});

function isSamePiece(piece) {
    return piece === movingPiece;
}

function isFirstClickedCellEmpty(piece) {
    return isFirstClick() && !piece.hasChildNodes();
}

function isNotTurn(piece) {
    return isFirstClick() && piece.hasChildNodes() && !piece.firstChild.className.includes(turn.innerText);
}

function isFirstClick() {
    return movingPiece === null;
}

async function move(destination) {
    const result = await fetchMove(destination);
    if (result["isEndOfGame"]) {
        alert(turn.innerText + "이(가) 승리했습니다!");
        await clearBoard();
    } else if (result["isSuccessToMove"]) {
        await movePiece(destination);
        updateScore(result);
    } else {
        alert(result["message"]);
    }
    await clearClickedCoordinates();
}

function clearClickedCoordinates() {
    movingPiece.style.background = "";
    movingPiece = null;
}

function checkAndFillClickedColor(piece) {
    movingPiece = piece;
    movingPiece.style.background = "skyblue";
}

async function clearBoard() {
    document.querySelectorAll(".coordinates").forEach(element => {
        while (element.hasChildNodes()) {
            element.removeChild(element.lastElementChild);
        }
    });
    turn.innerText = "";
    score.innerText = "";
}

async function setBoard(board) {
    await clearBoard();
    for (const piece of board["pieces"]) {
        document.getElementById(piece.position.toLowerCase()).innerHTML = PIECES[piece["pieceType"]];
    }
    turn.innerText = board["turn"].toLowerCase();
}

async function fetchBoard(operation) {
    const response = await fetch("http://localhost:8080/" + operation);
    return await response.json();
}

async function initializeBoard() {
    const board = await fetchBoard("start");
    await setBoard(board);
}

async function resumeGame() {
    const board = await fetchBoard("resume");
    await setBoard(board);
}

async function fetchMove(destination) {
    const data = {
        from: movingPiece.id,
        to: destination.id
    };
    const response = await fetch("http://localhost:8080/move", {
        method: 'POST',
        body: JSON.stringify(data),
        headers: {
            'Content-Type': 'application/json'
        }
    });
    return await response.json();
}

function movePiece(destination) {
    changeTurn();
    while (destination.hasChildNodes()) {
        destination.removeChild(destination.lastElementChild);
    }
    destination.appendChild(movingPiece.firstChild);
}

function changeTurn() {
    turn.innerText = (turn.innerText === "white") ? "black" : "white";
}

function updateScore(result) {
    score.innerText = "[점수] white: " + result["whiteScore"] + ", black: " + result["blackScore"];
}

const startButton = document.getElementById("start");

startButton.addEventListener("click", async function startOrEnd() {
    if (startButton.textContent === "Start") {
        await start();
        startButton.textContent = "End";
        return;
    }
    await end();
    startButton.textContent = "Start"
});


async function start() {
    const board = await initBoard();
    setBoard(board);
    setInfo("시작");
}

async function initBoard() {
    let initialBoard = await fetch("/start");
    initialBoard = await initialBoard.json();
    return initialBoard;
}

function setBoard(board) {
    for (let position in board) {
        const piece = board[position];
        let div = document.getElementById(position)
        putPiece(piece, div);
    }
}

function setInfo(message) {
    let info = document.getElementById("info");
    info.textContent = message;
}

function putPiece(piece, div) {
    const pieceImage = document.createElement("img")
    pieceImage.src = "/images/" + piece.color.toLowerCase() + "-" + piece.pieceType.toLowerCase() + ".png";
    div.appendChild(pieceImage);
}

async function end() {
    clearBoard();
    setInfo("끝");
}

function clearBoard() {
    const boardSquare = document.getElementsByClassName("board-square");
    for (let i = 0; i < boardSquare.length; i++) {
        if (boardSquare[i].hasChildNodes()) {
            boardSquare[i].removeChild(boardSquare[i].childNodes[0]);
        }
    }
}

async function clickPosition() {

}

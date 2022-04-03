const startButton = document.getElementById("startButton");
let from = "";
let to = "";

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
    await setBoard(board);
    await setTurn();
}

async function initBoard() {
    return await fetch("/start")
        .then(response => response.json());
}

async function setTurn() {
    const turnMessages = {"WHITE_TURN": "흰색 팀의 순서입니다", "BLACK_TURN": "검은색 팀의 순서입니다", "END": "게임이 종료되었습니다."}
    let message = await fetch("/turn")
        .then(response => response.json());

    let turn = document.getElementById("turn");
    turn.textContent = turnMessages[message];
}

async function setBoard(board) {
    await clearBoard();
    for (let position in board) {
        const piece = board[position];
        let div = document.getElementById(position)
        putPiece(piece, div);
    }
}

function putPiece(piece, div) {
    const pieceImage = document.createElement("img")
    pieceImage.src = "/images/" + piece.color.toLowerCase() + "-" + piece.pieceType.toLowerCase() + ".png";
    div.appendChild(pieceImage);
}

async function end() {
    await fetch("/end");
    await clearBoard();
    await setTurn();
    document.getElementById("score").textContent = "";
}

async function clearBoard() {
    const boardSquares = document.getElementsByClassName("board-square");
    for (let i = 0; i < boardSquares.length; i++) {
        deletePiece(boardSquares[i]);
    }
}

function deletePiece(div) {
    if (div.hasChildNodes()) {
        div.removeChild(div.childNodes[0]);
    }
}

async function clickPosition(id) {
    if (from === "") {
        const div = document.getElementById(id);
        div.style.border = "3px solid #878787";

        from = id;
        return;
    }
    if (from !== "" && to === "") {
        const div = document.getElementById(from);
        div.style.border = "none";

        to = id;
        const boardAfterMove = await movePiece(from, to);
        await setBoard(boardAfterMove);
        await setTurn();

        from = "";
        to = "";
    }
}

async function movePiece(from, to) {
    return await fetch("/move", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
            from: from,
            to: to,
        }),
    })
        .then(response => response.json());
}

async function getScore() {
    const score = await fetch("/status")
        .then(response => response.json());
    const scoreMessages = {"WHITE": " 흰색 팀 : ", "BLACK": " 검은색 팀 : "}

    let message = "현재 점수 =" + scoreMessages["WHITE"] + score["WHITE"] + scoreMessages["BLACK"] + score["BLACK"];

    const scoreDiv = document.getElementById("score");
    scoreDiv.textContent = message;
}
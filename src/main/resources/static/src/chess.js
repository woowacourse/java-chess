const newGameButton = document.getElementById("newGame");
const resumeGameButton = document.getElementById("resumeGame");
const startButton = document.getElementById("startButton");
const stopButton = document.getElementById("stopButton");
const statusButton = document.getElementById("statusButton")
let from = "";
let to = "";
let currentRoomName = "";

newGameButton.addEventListener("click", async function () {
    alert("123");
    let board = newGame();
    await initializeBoard(board);
});

startButton.addEventListener("click", async function () {
    let board = startGame();
    await initializeBoard(board);
})

statusButton.addEventListener("click", async function () {
    await getStatus();
});

stopButton.addEventListener("click", async function () {
    await finish();
})

async function startGame() {
    let boardAndTurnInfo = await fetch("/start")
        .then(handleErrors)
        .catch(function (error) {
            alert(error.message);
        });
    boardAndTurnInfo = await boardAndTurnInfo.json();
    document.getElementById("turnInfo").innerHTML = "현재 턴: " + boardAndTurnInfo.turnColor;
    return boardAndTurnInfo.board;
}

async function newGame() {
    currentRoomName = document.getElementById('newRoomName').value;

    let boardAndTurnInfo = await fetch("/start/newGame", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
            roomName: currentRoomName,
            turnColor: "white",
        }),
    }).then(handleErrors)
        .catch(function (error) {
            alert(error.message);
        })
    boardAndTurnInfo = await boardAndTurnInfo.json();
    document.getElementById("turnInfo").innerHTML = "현재 턴: " + boardAndTurnInfo.turnColor;
    return boardAndTurnInfo.board;
}

async function getStatus() {
    let status = await fetch("/status")
        .then(handleErrors)
        .catch(function (error) {
            alert(error.message);
        });
    status = await status.json();
    let result = "현재점수<br>WHITE 점수: " + status.whiteScore +
        "<br>BLACK 점수: " + status.blackScore;
    if (result.winner === "WHITE") {
        result += "<br>흰 진영이 이기고 있습니다.";
    } else if (result.winner === "BLACK") {
        result += "<br>검정 진영이 이기고 있습니다.";
    } else {
        result += "<br>두 유저의 점수가 같습니다.";
    }

    document.getElementById("score").innerHTML = result;
}

async function finish() {
    await fetch("/finish");
    alert("게임이 종료되었습니다.");
}

async function initializeBoard(board) {
    board.then(res => Object.keys(res).forEach(function (value) {
        let pointId = document.getElementById(value);
        putPiece(pointId, res, value)
    }))
}

function putPiece(pointId, board, value) {
    if (pointId.hasChildNodes()) {
        pointId.removeChild(pointId.firstChild);
    }
    const img = document.createElement("img");
    img.style.width = '50px';
    img.style.height = '50px';
    img.style.display = 'block';
    img.style.margin = '15px auto';
    img.src = "/images/pieces/" + board[value] + ".svg";
    if (board[value] !== "none-empty") {
        pointId.appendChild(img);
    }
}

function clickPiece(pieceId) {
    if (from === "") {
        from = pieceId;
        return;
    }
    if (from !== "" && to === "") {
        to = pieceId;
        movePiece(from, to);
        from = "";
        to = "";
    }
}

async function movePiece(from, to) {
    let board = requestMovePiece(from, to);
    await initializeBoard(board);
}

async function requestMovePiece(from, to) {
    let boardAndTurnInfo = await fetch("/move", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
            from: from,
            to: to,
        }),
    }).then(handleErrors)
        .catch(function (error) {
            alert(error.message);
        });
    boardAndTurnInfo = await boardAndTurnInfo.json();
    if (boardAndTurnInfo.isRunnable !== true) {
        alert("킹이 죽어 게임이 종료됩니다.");
    }
    document.getElementById("turnInfo").innerHTML = "현재 턴: " + boardAndTurnInfo.turnColor;
    document.getElementById("score").innerHTML = "";
    return boardAndTurnInfo.board;
}

async function handleErrors(response) {
    if (!response.ok) {
        let errorMessage = await response.json();
        throw Error(errorMessage.message);
    }
    return response;
}



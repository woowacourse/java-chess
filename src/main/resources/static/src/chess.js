const startButton = document.getElementById("startButton");
let from = "";
let to = "";


startButton.addEventListener("click", async function () {
    let board = startGame();
    await initializeBoard(board);
})

async function startGame() {
    alert("start");
    let boardAndTurnInfo = await fetch("/start")
        .then(handleErrors)
        .catch(function (error) {
            alert(error.message);
        });
    boardAndTurnInfo = await boardAndTurnInfo.json();
    document.getElementById("turnInfo").innerHTML = "현재 턴: " + boardAndTurnInfo.turnColor;
    return boardAndTurnInfo.board;
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
    document.getElementById("turnInfo").innerHTML = "현재 턴: " + boardAndTurnInfo.turnColor;
    return boardAndTurnInfo.board;
}

async function handleErrors(response) {
    if (!response.ok) {
        let message = await response.json();
        throw Error(message.errorMessage);
    }
    return response;
}



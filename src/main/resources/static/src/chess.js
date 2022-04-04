const startButton = document.getElementById("startButton");

startButton.addEventListener("click", async function() {
    let board = startGame();
    await initializeBoard(board);
})

async function startGame() {
    alert("start");
    let savedBoard = await fetch("/start")
        .then(handleErrors)
        .catch(function (error) {
            alert(error.message);
        });
    savedBoard = await savedBoard.json();
    return savedBoard.board;
}

async function initializeBoard(board) {
    board.then(res => Object.keys(res).forEach(function(value) {
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

async function handleErrors(response) {
    if (!response.ok) {
        let message = await response.json();
        throw Error(message.errorMessage);
    }
    return response;
}

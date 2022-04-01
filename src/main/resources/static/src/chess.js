const startButton = document.querySelector("#startButton")
let gameOver = ""

let source = "";
let target = "";

startButton.addEventListener('click', async function () {
    if (startButton.textContent == "Start") {
        let board = startGame();
        await initializeBoard(board)
        startButton.textContent = "End";
        return
    }

    let board = endGame();
    await initializeBoard(board);
    startButton.textContent = "Start";

})

async function startGame() {
    let savedBoard = await fetch("/start")
    savedBoard = await savedBoard.json()
    gameOver = savedBoard.gameOver
    return savedBoard.board;
}

async function endGame() {
    await getScore()
    let savedBoard = await fetch("/end")
    savedBoard = await savedBoard.json()
    gameOver = savedBoard.gameOver
    return savedBoard.board;
}

async function initializeBoard(board) {
    board.then(res => Object.keys(res).forEach(function (value) {
        let eachDiv = document.querySelector("#" + value)
        putPiece(eachDiv, res, value)
        //eachDiv.innerHTML = res[value];
    }))
}

function putPiece(eachDiv, board, value) {
    if (eachDiv.hasChildNodes()) {
        eachDiv.removeChild(eachDiv.firstChild)
    }
    const img = document.createElement("img")
    img.style.width = '30px'
    img.style.height = '40px'
    img.src = "/images/" + board[value] + ".png"
    img.style.display = 'block'
    img.style.margin = 'auto'
    eachDiv.appendChild(img)
}

function clickMovePosition(e) {
    if (gameOver === "true" || gameOver === "") {
        alert("게임이 시작되지 않아 선택 불가")
        return;
    }
    if (source === "") {
        source = e
        document.getElementById(source).style.backgroundColor = 'yellow'
        return;
    }

    if (source !== "" && target === "") {
        target = e
        document.getElementById(source).style.backgroundColor = ''
        movePiece(source, target)
        source = ""
        target = ""
    }
}

async function movePiece(source, target) {
    const board = await sendMoveInformation(source, target);
    console.log(board.board)
    await updateBoard(board.board);
    await checkGameOver(board.gameOver);
}

async function updateBoard(board) {
    Object.keys(board).forEach(function (value) {
        let eachDiv = document.querySelector("#" + value);
        putPiece(eachDiv, board, value)
    })
}

async function sendMoveInformation(source, target) {
    const bodyValue = {
        source: source,
        target: target
    }

    let movedBoard = await fetch("/move", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8',
            'Accept': 'application/json'
        },
        body: JSON.stringify(bodyValue)
    })
    movedBoard = await movedBoard.json();
    return movedBoard;
}

async function checkGameOver(gameOverMessage) {
    gameOver = gameOverMessage
    if (gameOver === "true") {
        alert("게임이 종료되었습니다.");
        let board = endGame();
        await initializeBoard(board);
        startButton.textContent = "Start";
    }
}

async function getScore() {
    if (gameOver === "") {
        alert("게임이 시작되지 않아 선택 불가")
        return;
    }
    let score = await fetch("/status")
    score = await score.json()
    alert("Black 팀 점수 = " + score.blackScore + "\nWhite 팀 점수 = " + score.whiteScore + "\n 현재 승자 = " + score.winningTeam)

}
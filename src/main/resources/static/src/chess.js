const startButton = document.querySelector("#startButton")
let gameOver = ""

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
    let savedBoard = await fetch("/end")
    savedBoard = await savedBoard.json()
    await getScore()
    gameOver = savedBoard.gameOver
    return savedBoard.board;
}

async function initializeBoard(board) {
    board.then(res => Object.keys(res).forEach(function (value) {
        let eachDiv = document.querySelector("#" + value);
        eachDiv.innerHTML = res[value];
    }))
}

async function getScore() {
    if (gameOver === "true") {
        alert("게임이 종료되어 확인할 수 없습니다.")
        return;
    }
    let score = await fetch("/status")
    score = await score.json()
    alert("Black 팀 점수 = " + score.blackScore + "\nWhite 팀 점수 = " + score.whiteScore + "\n 현재 승자 = " + score.winningTeam)

}
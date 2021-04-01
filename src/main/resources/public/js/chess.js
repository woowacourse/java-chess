addClickListener();

unicodeMap = {
    "R": "♜",
    "B": "♝",
    "Q": "♚",
    "N": "♞",
    "P": "♟",
    "K": "♛",
    "r": "♖",
    "p": "♙",
    "b": "♗",
    "q": "♔",
    "n": "♘",
    "k": "♕",
    ".": ""
}

function addClickListener() {
    document.getElementById("chessboard").addEventListener("click", selectPieces)
    document.getElementById("startButton").addEventListener("click", start)
    document.getElementById("homeButton").addEventListener("click", refresh)
    document.getElementById("saveButton").addEventListener("click", save)
    document.getElementById("loadButton").addEventListener("click", load)
}

function showAndHideButtons() {
    document.getElementById("saveButton").style.display = null;
    document.getElementById("loadButton").style.display = "none";
    document.getElementById("homeButton").style.display = null;
    document.getElementById("startButton").innerText = "RESET";
}

function refresh() {
    location.reload();
}

async function loadGrid() {
    let response = await fetch('http://localhost:4567/pieces')
    response = await response.json()
    for(let position in response){
        document.getElementById(position).innerText = unicodeMap[response[position]]
    }
}

async function start() {
    await postData("/reset")
    await postData("/start")
    loadGrid()
    showTurn()
    showScores()
    showAndHideButtons()
}

async function save() {
    let response = await postData("/save")
    if (response === 200) {
        alert("게임을 저장합니다.")
    }
}

async function load() {
    let response = await postData("/load")
    if (response === 200) {
        alert("게임을 불러옵니다.")
    }
    await postData("/start")
    loadGrid()
    showTurn()
    showScores()
    showAndHideButtons()
}

async function move(source, target) {
    let response = await postData("/move", {source: source.id, target: target.id})
    if (response === 400) {
        alert("잘못된 움직임 입니다.")
    }
    if (response === 200) {
        document.getElementById(target.id).innerText = document.getElementById(source.id).textContent;
        document.getElementById(source.id).innerText = "";
    }
    showTurnReverse()
    showScores()
    checkGameOver()
}

async function showTurn() {
    let turn = await fetch('http://localhost:4567/turn')
    turn = await turn.json()
    document.getElementById("status").innerText = "TURN: " + turn;
}

async function showTurnReverse() {
    let turn = fetch('http://localhost:4567/turn')
    if (turn === "BLACK") {
        turn = "WHITE"
    } else {
        turn = "BLACK"
    }
    document.getElementById("status").innerText = "TURN: " + turn;
}

async function showScores() {
    let blackScore = await fetch('http://localhost:4567/score/black')
    blackScore = await blackScore.json()
    document.getElementById("blackScore").innerText = "BLACK SCORE: " + blackScore;

    let whiteScore = await fetch('http://localhost:4567/score/white')
    whiteScore = await whiteScore.json()
    document.getElementById("whiteScore").innerText = "WHITE SCORE: " + whiteScore;
}

async function checkGameOver() {
    let response = await fetch('http://localhost:4567/gameover')
    response = await response.json()
    if (response[0] === true) {
        alert("게임 종료!" + response[1] + " 승리!")
        document.getElementById("status").innerText = response[1] + " 승리!";
    }
}

async function selectPieces(event) {
    let source = clickedPieces();
    let target = event.target;
    if (source && source !== target) {
        await move(source, target)
        source.classList.toggle("clicked")
        target.classList.toggle("clicked")
    }
    event.target.classList.toggle("clicked")
}

function clickedPieces() {
    let pieces = document.querySelector("#chessboard").querySelectorAll("div")
    for (let i = 0; i < pieces.length; i++) {
        if (pieces[i].classList.contains("clicked")) {
            return pieces[i];
        }
    }
    return null;
}

async function postData(url = '', data = {}) {
    const response = await fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
    return response.json();
}



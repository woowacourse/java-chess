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

function addClickListener(){
    document.getElementById("chessboard").addEventListener("click", selectPieces)
    document.getElementById("startButton").addEventListener("click", start)
    document.getElementById("resetButton").addEventListener("click", reset)
    document.getElementById("saveButton").addEventListener("click", save)
    document.getElementById("loadButton").addEventListener("click", load)
}

function init(){
    fetch('http://localhost:4567/pieces')
        .then(response => response.json())
        .then(function (pieces){
            for(let position in pieces){
                document.getElementById(position).innerText = unicodeMap[pieces[position]]
            }
        });
}

function start(){
    let response = postData("/start")
    response.then(function (result){
        if(result == 200){
            alert("게임을 시작합니다.")
        }
        if(result == 201){
            alert("진행중 게임을 불러옵니다.")
        }
        init()
        showTurn()
        showWhiteScore()
        showBlackScore()
        showResetButton()
        showSaveButtonHideLoadButton()
    })
}

function showSaveButtonHideLoadButton() {
    document.getElementById("saveButton").style.display = null;
    document.getElementById("loadButton").style.display = "none";
}

function reset(){
    let response = postData("/reset")
    response.then(function (result){
        if(result == 200){
            alert("게임을 리셋합니다.")
        }
        start()
    })
}

function save(){
    let response = postData("/save")
    response.then(function (result){
        if(result == 200){
            alert("게임을 저장합니다.")
        }
    })
}

function load(){
    let response = postData("/load")
    response.then(function (result){
        if(result == 200){
            alert("게임을 불러옵니다.")
        }
        start()
    })
}

function showResetButton() {
    document.getElementById("resetButton").style.display = null;
}

async function move(source, target) {
    let response = postData("/move", {source:source.id, target:target.id})
    await response.then(function (result){
        if(result == 400){
            alert("잘못된 움직임 입니다.")
        }
        if(result == 200){
            let sourceText = document.getElementById(source.id).textContent;
            document.getElementById(target.id).innerText = sourceText;
            document.getElementById(source.id).innerText = "";
        }
    })
    showTurnReverse()
    showWhiteScore()
    showBlackScore()
    checkGameOver()
}

function showTurn(){
    fetch('http://localhost:4567/turn')
        .then(response => response.json())
        .then(function (turn){
            document.getElementById("status").innerText = "TURN: " + turn;
        });
}

function showTurnReverse(){
    fetch('http://localhost:4567/turn')
        .then(response => response.json())
        .then(function (turn){
            if(turn == "BLACK"){
                turn = "WHITE"
            }
            else{
                turn = "BLACK"
            }
            document.getElementById("status").innerText = "TURN: " + turn;
        });
}

function showWhiteScore(){
    fetch('http://localhost:4567/score/white')
        .then(response => response.json())
        .then(function (score){
            document.getElementById("whiteScore").innerText = "WHITE SCORE: " + score;
        });
}

function showBlackScore(){
    fetch('http://localhost:4567/score/black')
        .then(response => response.json())
        .then(function (score){
            document.getElementById("blackScore").innerText = "BLACK SCORE: " + score;
        });
}

function checkGameOver(){
    fetch('http://localhost:4567/gameover')
        .then(response => response.json())
        .then(function (gameOver){
            if(gameOver[0] == true){
                alert("게임 종료!" + gameOver[1]+ " 승리!")
                document.getElementById("status").innerText = gameOver[1]+ " 승리!";
            }
        });
}

function selectPieces(event){
    let source = clickedPieces();
    let target = event.target;
    if(source){
        if(source != target){
            move(source, target)
            source.classList.toggle("clicked")
            target.classList.toggle("clicked")
        }
    }
    event.target.classList.toggle("clicked")
}

function clickedPieces(){
    let pieces = document.querySelector("#chessboard").querySelectorAll("div")
    for(let i = 0; i < pieces.length; i++){
        if(pieces[i].classList.contains("clicked")){
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



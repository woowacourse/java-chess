const button = document.getElementById("button");
const status_button = document.getElementById("status-button");
const squares = document.getElementsByClassName("piece");

source = ""
target = ""

async function startChess() {
    let session = document.getElementById("result-session");
    while (session.hasChildNodes()) {
        session.removeChild(session.firstChild);
    }
    let board = await fetch("/start");
    board = await board.json();
    putPieceInSquare(board);
}

async function endChess() {
    let board = await fetch("/end");
    board = await board.json();
    removePieceInSquare(board);
}

function putPieceInSquare(board) {
   for (key in board)  {
      let position = document.getElementById(key);
      if (position.hasChildNodes()) {
        position.removeChild(position.firstChild);
      }
      const img = document.createElement("img");
      img.src = "images/" + board[key].symbol + "_" + board[key].team +".png";
      img.className = "piece-img"
      position.appendChild(img);
   }
}

function removePieceInSquare(board) {
   for (key in board)  {
      let position = document.getElementById(key);
      position.removeChild(position.firstChild);
   }
}

function clickPosition(position) {
    if (source === "") {
        source = position;
        let nowPosition = document.getElementById(source);
        nowPosition.classList.add("selected");
        return;
    }
    if (source !== "" && target === "") {
        target = position;
        let nowPosition = document.getElementById(source);
        nowPosition.classList.remove("selected");
        movePiece(source, target);
        source = "";
        target = "";
    }
}

async function movePiece(source, target) {
     let board = await fetch("/move", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            source: source,
            target: target,
        }),
    })
    board = await board.json();
    putPieceInSquare(board);
}

async function printResult() {
    let board = await fetch("/status");
    board = await board.json();
    let session = document.getElementById("result-session");
    const whiteScore = document.createElement("div");
    const blackScore = document.createElement("div");
    const winner = document.createElement("div");
    const newStart = document.createElement("div");
    whiteScore.innerHTML = "화이트 점수 : " + board.score.WHITE;
    blackScore.innerHTML = "블랙 점수 : " + board.score.WHITE;
    winner.innerHTML = "승자 : " + board.winner;
    newStart.innerHTML = "게임이 종료 되었습니다. 재시작 하려면 Start 버튼을 눌러주세요.";
    session.appendChild(whiteScore);
    session.appendChild(blackScore);
    session.appendChild(winner);
    session.appendChild(newStart);
}

button.addEventListener("click", function () {
    const form = document.getElementById("form");
    if(button.innerText == "Start") {
        startChess();
        button.innerText = "End";
        return;
    }
    endChess();
    button.innerText = "Start";
});

status_button.addEventListener("click", function () {
    button.innerText = "Start";
    printResult();
});

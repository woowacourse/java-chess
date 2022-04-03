const button = document.getElementById("button");
const squares = document.getElementsByClassName("piece");

source = ""
target = ""

async function startChess() {
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

button.addEventListener("click", function () {
    const form = document.getElementById("form");
    if(button.innerText == "Start") {
        startChess();
        button.innerText = "End";
        return;
    }
    endChess();
    button.innerText = "Start";
})

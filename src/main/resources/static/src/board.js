const button = document.getElementById("button");
const squares = document.getElementsByClassName("piece");

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

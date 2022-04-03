const button = document.getElementById("button");
const squares = document.getElementsByClassName("piece");

async function initBoard() {
    let board = await fetch("/start");
    board = await board.json();
    putPieceInSquare(board);
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

button.addEventListener("click", function () {
    const form = document.getElementById("form");
    if(button.innerText == "Start") {
        initBoard();
        button.innerText = "End";
        return;
    }
    button.innerText = "Start";
})

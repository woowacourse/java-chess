import ChessService from "./ChessService.js";

const apiService = new ChessService();

const $chessTable = document.querySelector(".chessboard");
const alpha = ['', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h']
const pieceTypeIcon = {
  "KING" : '<i class="fas fa-chess-king"></i>',
  "QUEEN" : '<i class="fas fa-chess-queen"></i>',
  "BISHOP" : '<i class="fas fa-chess-bishop"></i>',
  "ROOK" : '<i class="fas fa-chess-rook"></i>',
  "PAWN" : '<i class="fas fa-chess-pawn"></i>',
  "KNIGHT" : '<i class="fas fa-chess-knight"></i>',
  "BLANK" : ''
}

function drawTable(data) {
  let innerValue = ''

  for (let row = 8; row > 0; row--) {
    innerValue +=
        `<div class="row"> 
          <div class="position">${row}</div>`;

    for (let j = 1; j < 9; j++) {
      const column = alpha[j]
      const id = column + row;
      const piece = data[id]['pieceType'];
      innerValue += `<div class="cell ${(row + j) % 2 === 0 ? "light"
          : "dark"}" id="${id}">${pieceTypeIcon[piece]}</div>`;
    }

    innerValue += "</div>";
  }

  $chessTable.insertAdjacentHTML('beforeend', innerValue);
}

apiService.getChessBoard().then(drawTable);

let currentP;
let targetP;
let count = 0;

function checkIfBoard(event) {
  const {target} = event;
  if (target.classList.contains("cell")) {
    count++;
    target.classList.toggle("clicked")
    if (count === 1) {
      currentP = target;
    }
    if (count === 2) {
      targetP = target;
      const source = currentP.innerText;
      currentP.innerText = ''
      targetP.innerText = source;
    }
    if (count > 2) {
      alert("NO~")
    }
    console.log("current", currentP, "target", targetP);
  }
}

$chessTable.addEventListener("click", checkIfBoard)
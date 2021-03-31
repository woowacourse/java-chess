import ChessService from "./ChessService.js";

const apiService = new ChessService();

const $chessTable = document.querySelector(".chessboard");
const alpha = ['', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h']

function drawTable(data) {
  let innerValue = ''

  for (let row = 8; row > 0; row--) {
    innerValue +=
        `<div class="row"> 
          <div class="position">${row}</div>`;

    for (let j = 1; j < 9; j++) {
      const column = alpha[j]
      const id = column + row;
      const pieceObj = data[id]
      const piece = pieceObj['pieceType'].toLowerCase();
      const color = pieceObj['teamColor'].toLowerCase();

      const icon = `<i class="fas fa-chess-${piece} ${color}"></i>`
      if(piece === "blank"){
        innerValue += `<div class="cell ${(row + j) % 2 === 0 ? "light"
            : "dark"}" id="${id}"></div>`;
      }else{
        innerValue += `<div class="cell ${(row + j) % 2 === 0 ? "light"
            : "dark"}" id="${id}">${icon}</div>`;
      }
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
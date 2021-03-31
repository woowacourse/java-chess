import data from "../json/sample.js";

const $chessTable = document.querySelector(".chessboard");

function drawTable(data) {
  let innerValue = ''

  for (let row = 8; row > 0; row--) {
    innerValue +=
        `<div class="row"> 
          <div class="position">${row}</div>`;

    for (let column = 1; column < 9; column++) {
      innerValue += `<div class="cell ${(row + column) % 2 === 0 ? "light"
          : "dark"}" id="${row}${column}">${data[row][column]}</div>`;
    }

    innerValue += "</div>";
  }

  $chessTable.insertAdjacentHTML('beforeend', innerValue);
}

drawTable(data);

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
    if(count ===2){
      targetP = target;
      const source = currentP.innerText;
      currentP.innerText = ''
      targetP.innerText = source;
    }
    if(count >2){
      alert("NO~")
    }
    console.log("current", currentP, "target", targetP);
  }
}

$chessTable.addEventListener("click", checkIfBoard)
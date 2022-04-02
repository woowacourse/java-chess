const columns = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'];
const rows = ['8', '7', '6', '5', '4', '3', '2', '1'];

const canvas = document.getElementById("chess-board");
const context = canvas.getContext("2d");
const section = document.getElementById("chess-section");

const lightCellColor = '#ddb180';
const darkCellColor = '#7c330c';

const x = 0;
const y = 0;
const chessBoardWidth = canvas.offsetWidth;
const chessBoardHeight = canvas.offsetHeight;
const delta = chessBoardWidth / 8;

window.onload = function () {
  context.strokeStyle = "black";
  context.strokeRect(x, y, chessBoardWidth, chessBoardHeight);

  for (let i = 0; i < 8; i++) {
    fillRow(i);
  }

  for (let i = 0; i < 8; i++) {
    const row = document.createElement("div");
    row.classList.add("row");
    makeRow(row, i);
    section.appendChild(row);
  }
}

function fillRow(row) {
  for (let col = 0; col < 8; col++) {
    context.fillStyle = (row + col) % 2 === 0 ? darkCellColor : lightCellColor;
    context.fillRect(x + delta * row, y + delta * col, delta, delta);
  }
}

function makeRow(rowDiv, rowIndex) {
  for (let col = 0; col < 8; col++) {
    const square = document.createElement("div");
    square.classList.add("square");
    square.id = createSquareId(col, rowIndex);
    rowDiv.appendChild(square);
  }
}

function createSquareId(column, row) {
  return columns[column] + rows[row];
}

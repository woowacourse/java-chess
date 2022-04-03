const columns = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'];
const rows = ['8', '7', '6', '5', '4', '3', '2', '1'];

const section = document.getElementById("chess-section");

const lightCellColor = '#ddb180';
const darkCellColor = '#7c330c';

window.onload = function () {
  for (let i = 0; i < 8; i++) {
    const row = document.createElement("div");
    row.classList.add("row");
    makeRow(row, i);
    section.appendChild(row);
  }

  fetch("/api/start")
      .then(res => res.json())
      .then(data => console.log(data));
}

function makeRow(rowDiv, rowIndex) {
  for (let colIndex = 0; colIndex < 8; colIndex++) {
    const square = document.createElement("div");
    square.classList.add("square");
    square.id = createSquareId(colIndex, rowIndex);
    square.style.background = decideCellColor(colIndex, rowIndex);
    rowDiv.appendChild(square);
  }
}

function createSquareId(column, row) {
  return columns[column] + rows[row];
}

function decideCellColor(column, row) {
  if ((column + row) % 2 === 0) {
    return lightCellColor;
  }
  return darkCellColor;
}

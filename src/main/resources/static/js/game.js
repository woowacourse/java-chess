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
      .then(data => data.pieces.forEach(piece => createPieceImage(piece.position, piece.pieceType)));
}

function makeRow(rowDiv, rowIndex) {
  for (let colIndex = 0; colIndex < 8; colIndex++) {
    const cell = document.createElement("div");
    cell.classList.add("cell");
    cell.id = createSquareId(colIndex, rowIndex);
    cell.style.background = decideCellColor(colIndex, rowIndex);
    rowDiv.appendChild(cell);
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

function createPieceImage(position, pieceType) {
  const cell = document.getElementById(position);
  const piece = document.createElement("img");
  piece.classList.add("piece-image");
  piece.src = `/images/${pieceType}.png`
  cell.appendChild(piece);
}

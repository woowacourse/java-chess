const columns = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'];
const rows = ['8', '7', '6', '5', '4', '3', '2', '1'];

const section = document.getElementById("chess-section");

const lightCellColor = "#ffffff";
const darkCellColor = "#8977ad";

let firstClicked;
let secondClicked;

window.onload = async function () {
  for (let i = 0; i < 8; i++) {
    const row = document.createElement("div");
    row.classList.add("row");
    makeRow(row, i);
    section.appendChild(row);
  }
  const res = await fetch("/api/start")
      .then(res => res.json())
  rendBoard(res.board.pieces);
}

function makeRow(rowDiv, rowIndex) {
  for (let colIndex = 0; colIndex < 8; colIndex++) {
    const cell = document.createElement("div");
    cell.classList.add("cell");
    cell.id = createSquareId(colIndex, rowIndex);
    cell.style.background = decideCellColor(colIndex, rowIndex);
    cell.addEventListener("click", onclick);
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

function rendBoard(pieces) {
  clearBoard();
  pieces.forEach(piece => createPieceImage(piece.position, piece.pieceType));
}

function clearBoard() {
  const cells = document.querySelectorAll("div.cell");
  cells.forEach(cell => removePiece(cell));
}

function removePiece(cell) {
  if (cell.hasChildNodes()) {
    cell.removeChild(cell.firstChild);
  }
}

function createPieceImage(position, pieceType) {
  const cell = document.getElementById(position);
  const piece = document.createElement("img");
  piece.classList.add("piece-image");
  piece.src = `/images/${pieceType}.png`
  cell.appendChild(piece);
}

async function onclick(event) {
  const cell = event.currentTarget;
  decideClicked(cell);
  if (firstClicked && secondClicked) {
    res = await move();
    firstClicked.childNodes[0].classList.remove("selected");
    firstClicked = null;
    secondClicked = null;
    rendBoard(res.board.pieces);
  }
}

function decideClicked(cell) {
  if (!firstClicked && !cell.hasChildNodes()) {
    return;
  }
  if (firstClicked === cell) {
    const piece = firstClicked.childNodes[0];
    piece.classList.remove("selected");
    firstClicked = null;
    return;
  }
  if (!firstClicked) {
    firstClicked = cell;
    highlightClickedCell();
    return;
  }
  secondClicked = cell;
}

function highlightClickedCell() {
  const piece = firstClicked.childNodes[0];
  piece.classList.add("selected");
}

function move() {
  return fetch("/api/move", {
    method: "post",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      start: firstClicked.getAttribute("id"),
      target: secondClicked.getAttribute("id"),
    }),
  })
      .then(res => res.json())
      .catch(res => {
        alert(res);
        firstClicked.childNodes[0].classList.remove("selected");
        firstClicked = null;
        secondClicked = null;
      })
}

const columns = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'];
const rows = ['8', '7', '6', '5', '4', '3', '2', '1'];

const section = document.getElementById("chess-section");
const startButton = document.getElementById("start-button");
const turnInfo = document.getElementById("turn-info");
const statusButton = document.getElementById("status-button");
const score = document.getElementById("score");

const turn = {
  WHITE_RUNNING: "백",
  BLACK_RUNNING: "흑",
  WHITE_WIN: "백",
  BLACK_WIN: "흑",
};

const lightCellColor = "#ffffff";
const darkCellColor = "#8977ad";

let firstSelected;
let secondSelected;

window.onload = async function () {
  for (let i = 0; i < 8; i++) {
    const row = document.createElement("div");
    row.classList.add("row");
    makeRow(row, i);
    section.appendChild(row);
  }
  startButton.addEventListener("click", start);
  statusButton.addEventListener("click", getStatus);
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

function printTurn(res) {
  turnInfo.innerText = `${turn[res.gameState]}의 턴입니다.`;
}

async function start() {
  let res = await fetch("/api/start");
  res = await res.json();
  rendBoard(res.board.pieces);
  printTurn(res);
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
  decideSelection(cell);
  if (firstSelected && secondSelected) {
    const res = await move();
    rendBoard(res.board.pieces);
    if (res.gameState === "WHITE_WIN" || res.gameState === "BLACK_WIN") {
      turnInfo.innerText = `${turn[res.gameState]}의 승리입니다.`;
      score.innerText = null;
      return;
    }
    printTurn(res);
  }
}

function decideSelection(cell) {
  if (!firstSelected && !cell.hasChildNodes()) {
    return;
  }
  if (firstSelected === cell) {
    const piece = firstSelected.childNodes[0];
    piece.classList.remove("selected");
    firstSelected = null;
    return;
  }
  if (!firstSelected) {
    firstSelected = cell;
    highlightSelectedCell(cell);
    return;
  }
  secondSelected = cell;
}

function highlightSelectedCell(cell) {
  const piece = cell.childNodes[0];
  piece.classList.add("selected");
}

async function move() {
  try {
    const res = await fetch("/api/move", {
      method: "post",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        start: firstSelected.getAttribute("id"),
        target: secondSelected.getAttribute("id"),
      }),
    });
    return await res.json();
  } catch (err) {
    alert(err);
  } finally {
    clearSelection();
  }
}

function clearSelection() {
  firstSelected.childNodes[0].classList.remove("selected");
  firstSelected = null;
  secondSelected = null;
}

async function getStatus() {
  let res = await fetch("/api/status");
  res = await res.json();
  score.innerText = `백: ${res.whiteScore}점
  흑: ${res.blackScore}점`;
}

const columns = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'];
const rows = ['8', '7', '6', '5', '4', '3', '2', '1'];

const section = document.getElementById("chess-section");
const startButton = document.getElementById("start-button");
const turnInfo = document.getElementById("turn-info");
const statusButton = document.getElementById("status-button");
const score = document.getElementById("score");
const endButton = document.getElementById("end-button");

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
  endButton.addEventListener("click", end);

  const res = await fetch("/api/load");
  const data = await res.json();
  if (!res.ok) {
    alert(data.message);
    return;
  }
  createOrLoad(data);
}

function createOrLoad(data) {
  if (data.gameState === "READY") {
    turnInfo.innerText = "게임을 시작해주세요.";
    return;
  }
  rendBoard(data.board.pieces);
  printTurn(data);
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

function printTurn(data) {
  score.innerText = null;
  if (data.gameState === "WHITE_WIN" || data.gameState === "BLACK_WIN") {
    turnInfo.innerText = `${turn[data.gameState]}의 승리입니다.`;
    alert(`${turn[data.gameState]}의 승리입니다.`);
    return;
  }
  turnInfo.innerText = `${turn[data.gameState]}의 턴입니다.`;
}

async function start() {
  const res = await fetch("/api/start");
  const data = await res.json();
  if (!res.ok) {
    alert(data.message);
    return;
  }
  rendBoard(data.board.pieces);
  printTurn(data);
}

function rendBoard(pieces) {
  clearBoard();
  pieces.forEach(piece => createPieceImage(piece.position, piece.pieceType, piece.color));
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

function createPieceImage(position, pieceType, color) {
  const cell = document.getElementById(position);
  const piece = document.createElement("img");
  piece.classList.add("piece-image");
  piece.src = `/images/${pieceType}${color}.png`
  cell.appendChild(piece);
}

async function move() {
  const res = await requestMove();
  const data = await res.json();
  clearSelection();
  if (!res.ok) {
    alert(data.message);
    return;
  }
  rendBoard(data.board.pieces);
  printTurn(data);
}

async function onclick(event) {
  const cell = event.currentTarget;
  decideSelection(cell);
  if (firstSelected && secondSelected) {
    await move();
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

async function requestMove() {
  return await fetch("/api/move", {
    method: "post",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      start: firstSelected.getAttribute("id"),
      target: secondSelected.getAttribute("id"),
    }),
  });
}

function clearSelection() {
  firstSelected.childNodes[0].classList.remove("selected");
  firstSelected = null;
  secondSelected = null;
}

async function getStatus() {
  const res = await fetch("/api/status");
  const data = await res.json();
  if (!res.ok) {
    alert(data.message);
    return;
  }
  score.innerText = `백: ${data.whiteScore}점
  흑: ${data.blackScore}점`;
}

async function end() {
  const res = await fetch("/api/end");
  const data = await res.json();
  if (!res.ok) {
    alert(data.message);
    return;
  }
  alert("게임을 종료합니다.");
}

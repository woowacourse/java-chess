createChessBoard();

function createChessBoard() {
  const pieces = [
    ["BR", "BN", "BB", "BQ", "BK", "BB", "BN", "BR"],
    new Array(8).fill("BP"),
    new Array(8),
    new Array(8),
    new Array(8),
    new Array(8),
    new Array(8).fill("WP"),
    ["WR", "WN", "WB", "WQ", "WK", "WB", "WN", "WR"]
  ]
  makeTable(pieces);
  addClickEventListener();
}

function makeTable(pieces) {
  const table = document.getElementById("chess-board");
  for (let i = 0; i < 8; i++) {
    table.appendChild(makeTr(pieces, i));
  }
}

function makeTr(pieces, row) {
  const newTr = document.createElement("tr");
  for (let j = 0; j < 8; j++) {
    newTr.appendChild(makeTd(pieces, row, j));
  }
  return newTr;
}

function makeTd(pieces, row, col) {
  const newTd = document.createElement("td");
  newTd.id = String.fromCharCode('a'.charCodeAt(0) + col) + String(8 - row);
  if (pieces[row][col]) {
    const piece = document.createElement("img");
    piece.src = "img/" + pieces[row][col] + ".png";
    newTd.appendChild(piece);
  }
  decideCellStyle(newTd, row, col)
  return newTd;
}

function decideCellStyle(td, row, col) {
  if ((row % 2 === 0 && col % 2 === 0) || (row % 2 === 1 && col % 2 === 1)) {
    td.setAttribute("style",
        "background-color: rgb(204, 204, 204); width: 80px; height: 82px;");
    return;
  }
  td.setAttribute("style",
      "background-color: rgb(000, 102, 051); width: 80px; height: 82px;");
}

function addClickEventListener() {
  const table = document.getElementById("chess-board");
  table.addEventListener("click", onSelectPiece);
}

function onSelectPiece(event) {
  const clickPiece = event.target.closest("td");
  if (clickPiece.childElementCount === 0) {
    alert("빈 공간은 선택할 수 없습니다!");
    return;
  }

  if (clickPiece.className !== "clicked") {
    clickPiece.classList.toggle("clicked");
    return;
  }
  clickPiece.classList.toggle("clicked");
}
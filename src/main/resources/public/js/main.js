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

  const table = document.getElementById("chess-board");
  for (let i = 0; i < 8; i++) {
    const newTr = document.createElement("tr");
    for (let j = 0; j < 8; j++) {
      const newTd = document.createElement("td");
      const row = String(8 - i);
      newTd.id = String.fromCharCode('a'.charCodeAt(0) + j) + row;
      if (pieces[i][j]) {
        const piece = document.createElement("img");
        piece.src = "img/" + pieces[i][j] + ".png";
        newTd.appendChild(piece);
      }

      decideCellStyle(newTd, i, j)
      newTr.appendChild(newTd);
    }
    table.appendChild(newTr);
  }
}

function decideCellStyle(td, i, j) {
  if ((i % 2 === 0 && j % 2 === 0) || (i % 2 === 1 && j % 2 === 1)) {
    td.setAttribute("style",
        "background-color: rgb(204, 204, 204); width: 60px; height: 60px;");
    return;
  }
  td.setAttribute("style",
      "background-color: rgb(000, 102, 051); width: 60px; height: 60px;");
}
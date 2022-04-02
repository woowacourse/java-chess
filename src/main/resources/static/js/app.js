function drawChessBoard() {
  let chessboard = document.getElementById("chessboard-container");
  for (let i = 0; i < 8; i++) {
    for (let j = 0; j < 8; j++) {
      let square = document.createElement("div");
      square.id = "cell-" + i + "-" + j;
      if ((i + j) % 2 == 0) {
        square.className = "black square";
      } else {
        square.className = "white square";
      }
      chessboard.appendChild(square);
    }
  }
  let path = window.location.pathname;

  console.log(path);
}

function placePieces() {
  for (let i = 0; i < 8; i++) {
    for (let j = 0; j < 8; j++) {
      let square = document.getElementById("cell-" + i + "-" + j);
      if (i == 0) {
        if (j == 0 || j == 7) {
          square.innerHTML =
            "<img src='../css/images/white-rook.svg' alt='white-rook' class='piece'/>";
        } else if (j == 1 || j == 6) {
          square.innerHTML =
            "<img src='../css/images/white-knight.svg' alt='white-knight' class='piece'/>";
        } else if (j == 2 || j == 5) {
          square.innerHTML =
            "<img src='../css/images/white-bishop.svg' alt='white-bishop' class='piece'/>";
        } else if (j == 3) {
          square.innerHTML =
            "<img src='../css/images/white-queen.svg' alt='white-queen' class='piece'/>";
        } else if (j == 4) {
          square.innerHTML =
            "<img src='../css/images/white-king.svg' alt='white-king' class='piece'/>";
        }
      } else if (i == 1) {
        square.innerHTML =
          "<img src='../css/images/white-pawn.svg' alt='white-pawn' class='piece'/>";
      } else if (i == 6) {
        square.innerHTML =
          "<img src='../css/images/black-pawn.svg' alt='black-pawn' class='piece'/>";
      } else if (i == 7) {
        if (j == 0 || j == 7) {
          square.innerHTML =
            "<img src='../css/images/black-rook.svg' alt='black-rook' class='piece'/>";
        } else if (j == 1 || j == 6) {
          square.innerHTML =
            "<img src='../css/images/black-knight.svg' alt='black-knight' class='piece'/>";
        } else if (j == 2 || j == 5) {
          square.innerHTML =
            "<img src='../css/images/black-bishop.svg' alt='black-bishop' class='piece'/>";
        } else if (j == 3) {
          square.innerHTML =
            "<img src='../css/images/black-queen.svg' alt='black-queen' class='piece'/>";
        } else if (j == 4) {
          square.innerHTML =
            "<img src='../css/images/black-king.svg' alt='black-king' class='piece'/>";
        }
      }
    }
  }
}

drawChessBoard();
placePieces();

let $chessBoard = document.querySelector(".chessBoard");
const $gameId = document.querySelector("#gameId")
const button = document.querySelector("button");
let gameFinished = false;

createChessBoard();

$chessBoard.addEventListener("click", clickPosition);
button.addEventListener("click", restart);

function gameId() {
  return $gameId.value;
}

function createChessBoard() {
  chessBoardInit();
  updateBoard();
  turn();
}

function chessBoardInit() {
  for (let i = 0; i < 8; i++) {
    let boardRow = document.createElement("div");
    boardRow.setAttribute("class", "boardRow");

    for (let j = 0; j < 8; j++) {
      let boardColumn = document.createElement("div");
      boardColumn.setAttribute("class", "boardColumn");
      boardColumn.style = initChessBoardColor(i, j);
      boardColumn.id = initPiecePosition(i, j);

      let pieceImg = document.createElement("img");
      boardColumn.appendChild(pieceImg);
      boardRow.appendChild(boardColumn);
    }
    $chessBoard.appendChild(boardRow);
  }
}

function initPieceImg() {
  for (let i = 0; i < 8; i++) {
    for (let j = 0; j < 8; j++) {
      let cell = document.getElementById(String(initPiecePosition(i, j)));
      if (cell.firstChild) {
        cell.firstChild.remove();
      }
    }
  }
  updateBoard();
}

function initChessBoardColor(row, column) {
  if ((row + column) % 2 === 0) {
    return "background-color: #fcf6f5";
  }
  if ((row + column) % 2 === 1) {
    return "background-color: #e09a2b";
  }
}

function initPiecePosition(row, column) {
  const rowTable = {0: "8", 1: "7", 2: "6", 3: "5", 4: "4", 5: "3", 6: "2", 7: "1"};
  const columnTable = {0: "A", 1: "B", 2: "C", 3: "D", 4: "E", 5: "F", 6: "G", 7: "H"};
  return columnTable[column] + rowTable[row];
}

function clickPosition(event) {
  if (gameFinished) {
    return;
  }
  const positions = document.querySelectorAll(".boardColumn");
  for (let i = 0; i < positions.length; i++) {
    if (positions[i].classList.contains("clicked")) {
      positions[i].classList.remove("clicked");
      move(positions[i].id, event.target.closest("div").id, gameId());
      return;
    }
  }
  if (event.target.closest("img") === null) {
    return;
  }
  event.target.closest(".boardColumn").classList.toggle("clicked");
}

async function move(from, to, id) {
  const data = {
    from: from,
    to: to,
    id: id
  };
  fetch("/game/" + gameId() + "/move", {
    method: 'PUT',
    header: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(data)
  }).then(res => {
    return res.json();
  }).then(obj => {
    if (obj.code === "400") {
      alert(obj.message);
      return;
    }
    if (obj.code === "300") {
      changeImg(from, to);
      gameFinished = true;
      document.querySelector(".currentTurn").textContent = " 승자 :";
      alert(obj.turn + " 승리!");
      location.href = "/";
      return;
    }
    if (obj.code === "200") {
      changeImg(from, to);
      turn();
      score();
    }
  })
}

function changeImg(fromPosition, toPosition) {
  const from = document.getElementById(fromPosition);
  const to = document.getElementById(toPosition);
  const piece = from.getElementsByTagName("img")[0];
  if (to.getElementsByTagName("img")[0]) {
    to.getElementsByTagName("img")[0].remove();
  }
  to.appendChild(piece);
}

async function turn() {
  const turn = await fetch("/game/" + gameId() + "/turn", {
    method: 'GET',
    header: {
      'Content-Type': 'application/json'
    }
  }).then(res => {
    return res.json();
  });
  const turnMessage = document.querySelector(".turn");
  turnMessage.textContent = turn;
}

async function score() {
  const score = await fetch("/game/" + gameId() + "/score",{
    method: 'GET',
    headers: {
      'Content-Type': 'application/json'
    }
  }).then(res => {
    return res.json();
  });
  const scores = Object.values(score);
  console.log(scores);
  const blackScoreMessage = document.querySelector(".black");
  blackScoreMessage.textContent = scores[1];

  const whiteScore = document.querySelector(".white");
  whiteScore.textContent = scores[0];
}

function restart() {
  fetch("/restart", {
    method: 'POST',
    header: {
      'Content-Type': 'application/json'
    }
  }).then(function () {
    gameFinished = false;
    const turnMessage = document.querySelector(".currentTurn");
    turnMessage.textContent = " 현재 턴 :";
    initPieceImg();
    turn();
    score();
  });
}

async function updateBoard() {
  console.log(gameId());
  const board = await fetch("/game/" + gameId() + "/board", {
    method: 'GET',
    header: {
      'Content-Type': 'application/json'
    }
  }).then(res => {
    return res.json();
  });
  const positions = Object.keys(board);
  const pieces = Object.values(board);

  const columns = document.querySelectorAll(".boardColumn");
  for (let i = 0; i < columns.length; i++) {
    if (columns[i].getElementsByTagName("img")[0]) {
      columns[i].getElementsByTagName("img")[0].remove();
    }
  }

  for (let i = 0; i < positions.length; i++) {
    const position = document.getElementById(positions[i]);
    const piece = document.createElement("img");

    piece.src = "img/" + pieces[i] + ".png";
    position.appendChild(piece);
  }
}

const templateQueen = (color) =>
  `<div class="chess-piece queen ${color}" draggable="true" ondrop="onDrop(event)" ondragstart="onDragStart(event)" ondragover="onDragOver(event)"></div>`;
const templateKing = (color) =>
  `<div class="chess-piece king ${color}" draggable="true" ondrop="onDrop(event)" ondragstart="onDragStart(event)" ondragover="onDragOver(event)"></div>`;
const templateBishop = (color) =>
  `<div class="chess-piece bishop ${color}" draggable="true" ondrop="onDrop(event)" ondragstart="onDragStart(event)" ondragover="onDragOver(event)"></div>`;
const templateKnight = (color) =>
  `<div class="chess-piece knight ${color}" draggable="true" ondrop="onDrop(event)" ondragstart="onDragStart(event)" ondragover="onDragOver(event)"></div>`;
const templateRook = (color) =>
  `<div class="chess-piece rook ${color}" draggable="true" ondrop="onDrop(event)" ondragstart="onDragStart(event)" ondragover="onDragOver(event)"></div>`;
const templatePawn = (color) =>
  `<div class="chess-piece pawn ${color}" draggable="true" ondrop="onDrop(event)" ondragstart="onDragStart(event)" ondragover="onDragOver(event)"></div>`;
const templateBlank = `<div class="chess-piece blank" draggable="true" ondrop="onDrop(event)" ondragstart="onDragStart(event)" ondragover="onDragOver(event)"></div>`;
const templateGame = (id) =>
  `<a href="/game/${id}" class="chess-game"><div class="chess-game-title">체스 게임 ${id}</div></a>`;

const blackScoreElement = document.querySelector('#black-score');
const whiteScoreElement = document.querySelector('#white-score');
const chessAlertElement = document.querySelector('.chess-alert');
const chessAlertMessageElement = document.querySelector('.chess-alert-message');

const chessResultElement = document.querySelector('.chess-result');
const chessResultMessageElement = document.querySelector(
  '.chess-result-message'
);
const chessResultRestartElement = document.querySelector(
  '.chess-result-restart'
);

const chessGamesElement = document.querySelector('.chess-games');
const chessCreateSubmitElement = document.querySelector('.chess-create-submit');

const chessCellElements = document.querySelectorAll('.chess-col');

chessCreateSubmitElement.onclick = () => {
  fetch('/create', {
    method: 'POST',
  })
    .then((response) => response.json())
    .then((data) => {
      if (data.statusCode == 1) {
        location.replace('/game/' + data.dto);
      }
    });
};

chessResultRestartElement.onclick = () => {
  fetch('/restart/' + gameId, {
    method: 'POST',
  })
    .then((response) => response.json())
    .then((data) => {
      if (data.statusCode == 1) {
        location.reload();
      }
    });
};

function showAlert(message, delay) {
  show(chessAlertElement);
  chessAlertMessageElement.innerHTML = message;
  setTimeout(() => {
    hide(chessAlertElement);
  }, delay);
}

function show(element) {
  element.classList.remove('hide');
  element.classList.add('show');
}

function hide(element) {
  element.classList.remove('show');
  element.classList.add('hide');
}

const createTemplate = {
  p: templatePawn('white'),
  q: templateQueen('white'),
  k: templateKing('white'),
  r: templateRook('white'),
  b: templateBishop('white'),
  n: templateKnight('white'),
  P: templatePawn('black'),
  Q: templateQueen('black'),
  K: templateKing('black'),
  R: templateRook('black'),
  B: templateBishop('black'),
  N: templateKnight('black'),
};

fetch('http://localhost:4567/board/' + gameId)
  .then((response) => response.json())
  .then((data) => drawChessGame(data.dto));

fetch('http://localhost:4567/games')
  .then((response) => response.json())
  .then((data) => drawGameList(data.dto));

function drawGameList(games) {
  games.forEach((id) => (chessGamesElement.innerHTML += templateGame(id)));
}

function drawChessGame({ boardDto, turnDto, statusDto, isFinished }) {
  drawBoard(boardDto.board);
  drawStatus(statusDto);
  drawResult(statusDto, isFinished);
}

function drawResult(statusDto, isFinished) {
  if (!isFinished) {
    return;
  }
  show(chessResultElement);
  chessResultMessageElement.innerHTML = statusDto.winner + ' 승';
}

function drawBoard(board) {
  chessCellElements.forEach((element, i) => {
    const symbol = board[i];
    element.innerHTML = symbol == '.' ? templateBlank : createTemplate[symbol];
  });
}

function drawStatus(statusDto) {
  blackScoreElement.innerHTML = statusDto.black.score;
  whiteScoreElement.innerHTML = statusDto.white.score;
}

function onDragStart(e) {
  e.dataTransfer.setData('x', e.target.parentElement.dataset.x);
  e.dataTransfer.setData('y', e.target.parentElement.dataset.y);
}

function onDragOver(e) {
  e.preventDefault();
}

function onDrop(e) {
  fetch('http://localhost:4567/move/' + gameId, {
    method: 'POST',
    body: JSON.stringify({
      sx: parseInt(e.dataTransfer.getData('x')),
      sy: parseInt(e.dataTransfer.getData('y')),
      tx: parseInt(e.target.parentElement.dataset.x),
      ty: parseInt(e.target.parentElement.dataset.y),
    }),
  })
    .then((response) => response.json())
    .then((data) => {
      if (data.statusCode === 1) {
        drawChessGame(data.dto);
        return;
      }
      showAlert(data.dto, 1000);
    });
}

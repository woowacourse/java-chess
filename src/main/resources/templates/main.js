async function getBoard() {
  return await fetch(
    'http://localhost:3000/chessboard'
  )
  .then(res => res.json())
  .then(data => data);
}

async function init() {
  this.$chessBoard = document.querySelector('.chessBoard')
  await setBoard()
  moveHandler()
}

async function setBoard() {
  this.$chessBoard.innerHTML = ''
  this.chessBoard = await getBoard();
  this.chessBoard.map((board) => {
    this.$chessBoard.insertAdjacentHTML('beforeend',
      boardTemplate(board.position, board.piece))
  })
}

function moveHandler() {
  let source
  let target
  this.$chessBoard.addEventListener("drag", function (e) {
  }, false);

  this.$chessBoard.addEventListener("dragstart", function (e) {
    source = e.target.closest('div');
  }, false);

  this.$chessBoard.addEventListener("dragend", function (e) {
  }, false);

  this.$chessBoard.addEventListener("dragover", function (e) {
    e.preventDefault();
  }, false);

  this.$chessBoard.addEventListener("dragenter", function (e) {
    e.target.style.background = "blue";
  }, false);

  this.$chessBoard.addEventListener("dragleave", function (e) {
    e.target.style.background = "";
  }, false);

  this.$chessBoard.addEventListener("drop", function (e) {
    e.preventDefault();
    e.target.style.background = "";
    target = e.target.closest('div')
    if (movable(source, target)) {
      move(source, target)
    }
  }, false);
}

function move(source, target) {
  const sourcePiece = {
    type: source.classList[3],
    color: source.classList[2]
  }
  const blankPiece = {
    type: 'blank',
    color: 'none'
  }
  target.innerHTML = boardTemplate(target.id, sourcePiece)
  source.innerHTML = boardTemplate(source.id, blankPiece)
}

async function movable(source, target) {
  const sourcePosition = source.id
  const targetPosition = target.id
  let movable = true;

  await fetch(
    `http://localhost:3000/chessboard/${sourcePosition}`,
    {
      method: 'PATCH',
      body: JSON.stringify({
        piece: {
          type: 'blank',
          color: 'none'
        }
      }),
      headers: {
        'Content-type': 'application/json; charset=UTF-8'
      }
    }
  ).then(res => res.json()).then(data => data).catch(() => movable = false)

  await fetch(
    `http://localhost:3000/chessboard/${targetPosition}`,
    {
      method: 'PATCH',
      body: JSON.stringify({
        piece: {
          type: source.classList[3],
          color: source.classList[2]
        }
      }),
      headers: {
        'Content-type': 'application/json; charset=UTF-8'
      }
    }
  ).then(res => res.json()).then(data => data).catch(() => movable = false)

  return movable
}

function boardTemplate(position, piece) {
  return `<div id=${position} class='square ${positionColor(
    position)} ${piece.color} ${piece.type}'>
          <img class='piece' src=${pieceImage(piece)} alt=${piece}/>
        </div>`
}

function positionColor(position) {
  if (position[1] % 2 === 0) {
    return position[0].charCodeAt(0) % 2 === 0 ? 'b-white' : 'b-black'
  }
  return position[0].charCodeAt(0) % 2 === 0 ? 'b-black' : 'b-white'
}

function pieceImage(piece) {
  if (piece.type === 'rook') {
    return piece.color === 'WHITE' ? './images/rook_white.png'
      : './images/rook_black.png'
  }
  if (piece.type === 'knight') {
    return piece.color === 'WHITE' ? './images/knight_white.png'
      : './images/knight_black.png'
  }
  if (piece.type === 'bishop') {
    return piece.color === 'WHITE' ? './images/bishop_white.png'
      : './images/bishop_black.png'
  }
  if (piece.type === 'queen') {
    return piece.color === 'WHITE' ? './images/queen_white.png'
      : './images/queen_black.png'
  }
  if (piece.type === 'king') {
    return piece.color === 'WHITE' ? './images/king_white.png'
      : './images/king_black.png'
  }
  if (piece.type === 'pawn') {
    return piece.color === 'WHITE' ? './images/pawn_white.png'
      : './images/pawn_black.png'
  }
  return './images/blank.png'
}

init();
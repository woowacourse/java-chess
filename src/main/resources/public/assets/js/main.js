async function getBoard() {
  return await fetch(
    '/chessboard'
  )
  .then(res => res.json())
  .then(data => data);
}

async function init() {
  this.$chessBoard = document.querySelector('.chessBoard')
  await setBoard()
  await moveHandler()
  await changeTurn(await getTurn())
}

async function setBoard() {
  this.$chessBoard.innerHTML = ''
  this.chessBoard = await getBoard();
  this.chessBoard.map((board) => {
    this.$chessBoard.insertAdjacentHTML('beforeend',
      boardTemplate(board.position, board.piece))
  })
}

async function getTurn() {
  return await fetch(
    'http://localhost:3001/turn'
  )
  .then(res => res.json())
  .then(data => data)
}

function moveHandler() {
  let source
  let target
  this.$chessBoard.addEventListener("drag", function (e) {
  }, false);

  this.$chessBoard.addEventListener("dragstart", function (e) {
    source = e.target.closest('div').parentNode
  }, false);

  this.$chessBoard.addEventListener("dragend", function (e) {
  }, false);

  this.$chessBoard.addEventListener("dragover", function (e) {
    e.preventDefault();
  }, false);

  this.$chessBoard.addEventListener("dragenter", function (e) {
    e.target.style.background = "#78c2c6";
  }, false);

  this.$chessBoard.addEventListener("dragleave", function (e) {
    e.target.style.background = "";
  }, false);

  this.$chessBoard.addEventListener("drop", async function (e) {
    e.preventDefault();
    e.target.style.background = "";
    target = e.target.closest('div').parentNode
    if (await movable(source, target)) {
      move(source, target)
      await changeTurn(await getTurn())
    }
  }, false);
}

function move(source, target) {
  const sourcePiece = {
    type: source.children[0].classList[3],
    color: source.children[0].classList[2]
  }
  const blankPiece = {
    type: 'blank',
    color: 'none'
  }
  target.innerHTML = squareTemplate(target.id, sourcePiece)
  source.innerHTML = squareTemplate(source.id, blankPiece)
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
          type: source.children[0].classList[3],
          color: source.children[0].classList[2]
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
  return `<div id=${position}>
  ${squareTemplate(position, piece)}
  </div>`
}

function squareTemplate(position, piece) {
  return `<div id=${position} class='square ${positionColor(position)} ${piece.color} ${piece.type}'>
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
  console.log(piece)
  if (piece.type ===  'r' || piece.type === 'R') {
    return piece.color === 'WHITE' ? './images/rook_white.png'
      : './images/rook_black.png'
  }
  if (piece.type ===  'n' || piece.type === 'N') {
    return piece.color === 'WHITE' ? './images/knight_white.png'
      : './images/knight_black.png'
  }
  if (piece.type ===  'b' || piece.type === 'B') {
    return piece.color === 'WHITE' ? './images/bishop_white.png'
      : './images/bishop_black.png'
  }
  if (piece.type ===  'q' || piece.type === 'Q') {
    return piece.color === 'WHITE' ? './images/queen_white.png'
      : './images/queen_black.png'
  }
  if (piece.type ===  'k' || piece.type === 'K') {
    return piece.color === 'WHITE' ? './images/king_white.png'
      : './images/king_black.png'
  }
  if (piece.type ===  'p' || piece.type === 'P') {
    return piece.color === 'WHITE' ? './images/pawn_white.png'
      : './images/pawn_black.png'
  }
  return './images/blank.png'
}

async function changeTurn(turn) {
  console.log(turn.turn)
  const $blackTurn = document.querySelector('.black-turn')
  const $whiteTurn = document.querySelector('.white-turn')
  if (turn.turn === 'WHITE') {
    $blackTurn.src = 'images/up.png'
    $whiteTurn.src = 'images/down_turn.png'
    console.log($blackTurn)
    await fetch(
      'http://localhost:3001/turn',
      {
        method: 'PATCH',
        body: JSON.stringify({
          turn: 'BLACK'
        }),
        headers: {
          'Content-type': 'application/json; charset=UTF-8'
        }
      }
    )
  }
  if (turn.turn === 'BLACK') {
    $blackTurn.src = 'images/up_turn.png'
    $whiteTurn.src = 'images/down.png'
    console.log($blackTurn)
    await fetch(
      'http://localhost:3001/turn',
      {
        method: 'PATCH',
        body: JSON.stringify({
          turn: 'WHITE'
        }),
        headers: {
          'Content-type': 'application/json; charset=UTF-8'
        }
      }
    )
  }
}

init();


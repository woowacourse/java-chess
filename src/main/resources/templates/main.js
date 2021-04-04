async function getBoard() {
  return await fetch(
      'http://localhost:3000/chessboard'
  )
  .then(res => res.json())
  .then(data => data);
}

async function init() {
  const $chessBoard = document.querySelector('.chessBoard')
  const chessBoard = await getBoard();
  chessBoard.map((board) => {
    $chessBoard.insertAdjacentHTML('beforeend', boardTemplate(board.position, board.piece))
  })
}

function boardTemplate(position, piece) {
  return `<div id=${position} class='square ${positionColor(position)}'>
<img class='piece' src=${pieceImage(piece)} />
</div>`
}

function positionColor(position) {
  if (position[1] % 2 === 0) {
    return position[0].charCodeAt(0) % 2 === 0 ? 'white' : 'black'
  }
  return position[0].charCodeAt(0) % 2 === 0 ? 'black' : 'white'
}

function pieceImage(piece) {
  if (piece.type === 'rook') {
    return piece.color === 'WHITE' ? './images/rook_white.png' : './images/rook_black.png'
  }
  if (piece.type === 'knight') {
    return piece.color === 'WHITE' ? './images/knight_white.png' : './images/knight_black.png'
  }
  if (piece.type === 'bishop') {
    return piece.color === 'WHITE' ? './images/bishop_white.png' : './images/bishop_black.png'
  }
  if (piece.type === 'queen') {
    return piece.color === 'WHITE' ? './images/queen_white.png' : './images/queen_black.png'
  }
  if (piece.type === 'king') {
    return piece.color === 'WHITE' ? './images/king_white.png' : './images/king_black.png'
  }
  if (piece.type === 'pawn') {
    return piece.color === 'WHITE' ? './images/pawn_white.png' : './images/pawn_black.png'
  }
  return './images/blank.png'
}

init();
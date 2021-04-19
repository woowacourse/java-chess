async function init() {
  this.$chessBoard = document.querySelector('.chessBoard')
  this.$controller = document.querySelector('.controller')
  this.$controller.addEventListener('click', btnHandler)

  this.$blackResult = document.getElementById('BLACK')
  this.$whiteResult = document.getElementById('WHITE')
  displayResult('none')

  const url = window.location.href.split('/')
  this.gameId = url[url.length - 1]

  await setBoard()
  await checkFinished()
  await moveHandler()
  await changeTurn(await getTurn())
}

async function getBoard() {
  return await fetch(
      `/chessboard/${this.gameId}`
  )
  .then(res => res.json())
  .then(data => data);
}

async function setBoard() {
  this.$chessBoard.innerHTML = ''
  this.chessBoard = await getBoard();
  this.chessBoard.map((board) => {
    this.$chessBoard.insertAdjacentHTML('beforeend',
        boardTemplate(board.position, board.piece))
  })
}

async function checkFinished() {
  const finished = await fetch(
      `${this.gameId}/finish`
  ).then(res => res.json())
  .then(data => data)
  if (finished === true) {
    alert('게임이 종료되었습니다.')
    toggleFinish()
  }
}

async function getTurn() {
  return await fetch(
      `/${this.gameId}/turn`
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
    try {
      move(await movable(source, target))
      await changeTurn(await getTurn())
    } catch (e) {
      alert('잘못된 이동입니다.')
    }
  }, false);
}

function move(response) {
  if (response.isOver === true) {
    alert('게임이 종료되었습니다.')
    toggleFinish()
  }

  const $source = document.getElementById(response.source.id)
  const $target = document.getElementById(response.target.id)
  $source.innerHTML = squareTemplate(response.source.id, response.source.piece)
  $target.innerHTML = squareTemplate(response.target.id, response.target.piece)
}

async function movable(source, target) {
  return await fetch(
      `/${this.gameId}/move`,
      {
        method: 'PUT',
        body: JSON.stringify({
          source: source.id,
          target: target.id
        }),
        headers: {
          'Content-type': 'application/json; charset=UTF-8',
          'Accept': 'application/json'
        }
      }
  ).then(res => res.json()).then(data => data).catch(err => err)
}

function boardTemplate(position, piece) {
  return `<div id=${position}>
  ${squareTemplate(position, piece)}
  </div>`
}

function squareTemplate(position, piece) {
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
  if (piece.type === 'r' || piece.type === 'R') {
    return piece.color === 'WHITE' ? './images/rook_white.png'
        : './images/rook_black.png'
  }
  if (piece.type === 'n' || piece.type === 'N') {
    return piece.color === 'WHITE' ? './images/knight_white.png'
        : './images/knight_black.png'
  }
  if (piece.type === 'b' || piece.type === 'B') {
    return piece.color === 'WHITE' ? './images/bishop_white.png'
        : './images/bishop_black.png'
  }
  if (piece.type === 'q' || piece.type === 'Q') {
    return piece.color === 'WHITE' ? './images/queen_white.png'
        : './images/queen_black.png'
  }
  if (piece.type === 'k' || piece.type === 'K') {
    return piece.color === 'WHITE' ? './images/king_white.png'
        : './images/king_black.png'
  }
  if (piece.type === 'p' || piece.type === 'P') {
    return piece.color === 'WHITE' ? './images/pawn_white.png'
        : './images/pawn_black.png'
  }
  return './images/blank.png'
}

function changeTurn(turn) {
  const $blackTurn = document.querySelector('.black-turn')
  const $whiteTurn = document.querySelector('.white-turn')
  if (turn === 'WHITE') {
    $blackTurn.src = 'images/up.png'
    $whiteTurn.src = 'images/down_turn.png'
  }
  if (turn === 'BLACK') {
    $blackTurn.src = 'images/up_turn.png'
    $whiteTurn.src = 'images/down.png'
  }
}

async function result() {
  const result = await fetch(
      `/${this.gameId}/result`
  ).then(res => res.json()).then(data => data)

  const blackResult = result.black
  const whiteResult = result.white

  this.$blackResult.getElementsByClassName(
      'score')[0].innerHTML = `<span>${blackResult.score}</span>`
  this.$whiteResult.getElementsByClassName(
      'score')[0].innerHTML = `<span>${whiteResult.score}</span>`

  if (blackResult.outcome === '승') {
    this.$blackResult.getElementsByTagName(
        'img')[0].src = "./images/player_win.png"
    this.$whiteResult.getElementsByTagName(
        'img')[0].src = "./images/player_lose.png"
    return
  }
  if (blackResult.outcome === '패') {
    this.$blackResult.getElementsByTagName(
        'img')[0].src = "./images/player_lose.png"
    this.$whiteResult.getElementsByTagName(
        'img')[0].src = "./images/player_win.png"
    return
  }
  this.$blackResult.getElementsByTagName(
      'img')[0].src = "./images/player_lose.png"
  this.$whiteResult.getElementsByTagName(
      'img')[0].src = "./images/player_lose.png"
}

function displayResult(val) {
  this.$blackResult.style.display = val;
  this.$whiteResult.style.display = val;
}

async function btnHandler({target}) {
  if (target.id === 'restart') {
    await newGame()
  }
  if (target.id === "finish") {
    if (await finish() === 202) {
      toggleFinish()
    }
  }
  if (target.id === "status") {
    displayResult('flex')
    await result()
    target.disabled = true;
  }
}

async function finish() {
  const url = window.location.href.split('/')
  const gameId = url[url.length - 1]
  return await fetch(
      `${gameId}/finish`,
      {
        method: 'POST'
      }
  ).then(res => res.json())
}

function toggleFinish() {
  document.querySelector('#finish').disabled = true
  document.querySelector('#status').disabled = false
}

window.onload = () => {
  init()
}


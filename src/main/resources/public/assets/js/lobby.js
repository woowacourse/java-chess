function init() {
  const $startBtn = document.getElementById('start')
  const $loadBtn = document.getElementById('load')

  $startBtn.addEventListener('click', newGame)
  $loadBtn.addEventListener('click', loadGame)
}

async function newGame() {
  const gameId = await fetch(
    '/lobby/new'
  )
  .then(res => res.json())
  .then(data => data)
  if (gameId > 0) {
    alert(`${gameId}번 게임에 입장합니다.`)
    window.location.href = `/${gameId}`
  }
}

async function loadGame() {
  const gameId = prompt('게임 번호를 입력해주세요.')
  alert(`${gameId}번 게임에 입장합니다.`)
  window.location.href = `/${gameId}`
}

window.onload = () => {
  init()
}
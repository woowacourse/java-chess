const $board = document.querySelector('.board');
const $gameStatus = document.querySelector('.game-status');
const $reload = document.querySelector('#reload');
const $exit = document.querySelector('#exit');
const $save = document.querySelector('#save');
const $askForm = document.querySelector('#ask-form');
document.addEventListener('DOMContentLoaded', eventHandler);

const fetchService = new FetchService();
const board = new Board();
let gameId;

function eventHandler() {
  $board.addEventListener('click', clickEvent);
  $reload.addEventListener('click', reload);
  $exit.addEventListener('click', exit);
  $askForm.addEventListener('keyup', setGameId)
  $save.addEventListener('click', save);
}

function setGameId({key, target}) {
  if (key === 'Enter' && target.value.length > 0) {
    gameId = target.value;
    createBoard();
  }
}

function createBoard() {
  fetchService.get(`http://localhost:4567/${gameId}/pieces`)
  .then(pieces => {
    board.drawBoard(pieces);
    updateRoundStatus();
  });
}

function updateRoundStatus() {
  fetchService.get(`http://localhost:4567/${gameId}/roundstatus`)
  .then(result => {
    board.updateBoardStatus(result);
    const score = board.getScore()
    $gameStatus.innerHTML = `<h2> 게임 번호 : ${gameId} <br> <h3> 현재 점수 <br> 화이트 : ${score.whiteTeamScore}   블랙 : ${score.blackTeamScore}`;
  });
}

function clickEvent({target}) {
  if (!target.classList.contains('ask-form')) {
    let targetBoardItem = target.closest(".board-item");

    if (targetBoardItem.classList.contains('movable')) {
      movePosition(targetBoardItem);
    } else {
      board.selectItem(targetBoardItem);
    }
  }

}

function movePosition(targetBoardItem) {
  let data = {
    currentPosition: board.getSelectedItem().id,
    targetPosition: targetBoardItem.id
  };

  fetchService.post(`http://localhost:4567/${gameId}/move`, data)
  .then(result => {
    board.move(targetBoardItem);
    board.updateBoardStatus(result);
    board.validateContinuable();
  });
}

function reload() {
  window.location.reload();
}

function exit() {
  alert('게임을 종료합니다.');
  fetchService.delete(`http://localhost:4567/exit/${gameId}`);
  window.location.reload();
}

function save() {
  alert(`${gameId} 번 의 게임을 저장합니다.`);
  fetchService.post(`http://localhost:4567/save/${gameId}`);
}


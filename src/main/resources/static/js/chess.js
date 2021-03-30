const $board = document.querySelector('.board');
const $restart = document.querySelector('#restart');
document.addEventListener('DOMContentLoaded', eventHandler);

const fetchService = new FetchService();
const board = new Board();

function eventHandler() {
  createBoard();
  $board.addEventListener('click', clickEvent);
  $restart.addEventListener('click', restart);
}

function createBoard() {
  fetchService.get('http://localhost:4567/pieces')
  .then(pieces => {
    board.drawBoard(pieces);
    updateRoundStatus();
  });
}

function updateRoundStatus() {
  fetchService.get('http://localhost:4567/roundstatus')
  .then(result => board.updateRoundStatus(result));
}

function clickEvent({target}) {
  let targetBoardItem = target.closest(".board-item");

  if (targetBoardItem.classList.contains('movable')) {
    movePosition(targetBoardItem);
  } else {
    board.selectItem(targetBoardItem);
  }

}

function movePosition(targetBoardItem) {
  let data = {
    currentPosition: board.getSelectedItem().id,
    targetPosition: targetBoardItem.id
  };

  fetchService.post('http://localhost:4567/move', data)
  .then(result => {
    board.move(targetBoardItem);
    board.updateRoundStatus(result);
    board.validateContinuable();
  });
}

function restart() {
  alert('다시 시작합니다.');
  fetchService.get('http://localhost:4567/restart')
  .then(result => createBoard());
}
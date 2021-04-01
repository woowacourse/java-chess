const url = "ws://" + location.hostname + ":" + location.port + "/chess";
const webSocket = new WebSocket(url);
const requestForm = '<request>';
const commandExecutor = new CommandExecutor();
const board = new Board();
const $askForm = document.querySelector('#ask-form');
const $board = document.querySelector('.board');
const $newGame = document.getElementById('new-game');
const $message = document.getElementById('message');
let roomId;
let teamColor;

document.addEventListener('DOMContentLoaded', eventHandler);

webSocket.onmessage = function (message) {
  commandExecutor.execute(JSON.parse(message.data), board);
}

function eventHandler() {
  $board.addEventListener('click', clickEvent);
  $askForm.addEventListener('keyup', setRoomId);
  $message.addEventListener('keyup', sendMessage);
  $newGame.addEventListener('click', newGame);
}

function newGame() {
  window.location.reload();
}

function sendMessage({key, target}) {
  if (key === 'Enter' && target.value.length > 0) {
    webSocket.send(target.value);
    target.value = '';
  }
}

function setRoomId({key, target}) {
  if (key === 'Enter' && target.value.length > 0) {
    roomId = target.value;
    webSocket.send(`${requestForm} enter ${roomId}`);
    webSocket.send(`${requestForm} pieces`);
  }
}

function clickEvent({target}) {
  if (!target.classList.contains('ask-form') && board.getColor() === teamColor) {
    let targetBoardItem = target.closest(".board-item");

    if (targetBoardItem.classList.contains('movable')) {
      movePosition(targetBoardItem);
    } else {
      board.selectItem(targetBoardItem);
    }
  }
}

function movePosition(targetBoardItem) {
  const currentPosition = board.getSelectedItem().id;
  const targetPosition = targetBoardItem.id;

  webSocket.send(`${requestForm} move ${currentPosition} ${targetPosition}`);
  board.move(targetBoardItem);
}
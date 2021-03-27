const $board = document.querySelector('.board');
document.addEventListener('DOMContentLoaded', createBoard);

function createBoard() {
  fetch('http://localhost:4567/pieces')
  .then(function (response) {
    return response.json();
  })
  .then(function (jsonData) {
    const jsonString = JSON.stringify(jsonData);
    board(JSON.parse(jsonString));
  })
}

function board(pieces) {
  for (let i = 7; i >= 0; i--) {
    for (let j = 0; j < 8; j++) {
      const boardElement = document.createElement('div');
      boardElement.className = 'board-item';
      let color = '';
      if ((j - i) % 2 === 0) {
        color = 'white';
      } else {
        color = 'black';
      }
      boardElement.classList.add(color)
      $board.appendChild(boardElement);

      let piece = pieces.find(piece => piece.currentPosition === `${j}${i}`);
      if (piece !== undefined) {
        boardElement.innerHTML = `<i class="fas fa-chess-${piece.name} ${piece.teamColor
        === 'WHITE' ? 'piece_white' : 'piece_black'}"></i>`;
      }
    }
  }
}



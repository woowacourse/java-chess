const $board = document.querySelector('.board');
document.addEventListener('DOMContentLoaded', createBoard);

function createBoard() {
  for (let i = 0; i < 8; i++) {
    for (let j = 0; j < 8; j++) {
      const boardElement = document.createElement('div');
      boardElement.className = 'board-item';
      let color = '';
      if((j-i)%2 === 0) {
        color = 'white';
      } else {
        color = 'black';
      }
      boardElement.classList.add(color)
      $board.appendChild(boardElement);
      if(i === 0 && (j===0 || j===7)) {
        boardElement.innerHTML = `<i class="fas fa-chess-rook piece_black"></i>`;
      }
    }
  }
}


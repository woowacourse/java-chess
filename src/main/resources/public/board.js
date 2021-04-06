class Board {
  $board = document.querySelector('.board');
  #selectedItem;
  #roundStatus;

  drawBoard(pieces) {
    this.$board.innerHTML = '';
    for (let i = 7; i >= 0; i--) {
      for (let j = 0; j < 8; j++) {
        const boardElement = document.createElement('div');
        const position = `${j}${i}`;
        boardElement.id = position;
        boardElement.className = 'board-item';
        let color = '';
        if ((j - i) % 2 === 0) {
          color = 'white';
        } else {
          color = 'black';
        }
        boardElement.classList.add(color)
        this.$board.appendChild(boardElement);

        let piece = pieces.find(piece => piece.currentPosition === position);
        if (piece !== undefined) {
          boardElement.innerHTML = `<i class="fas fa-chess-${piece.name} ${piece.teamColor
          === 'WHITE' ? 'piece_white' : 'piece_black'}"></i>`;
        }
      }
    }
  }

  move(targetBoardItem) {
    targetBoardItem.innerHTML = this.#selectedItem.innerHTML;
    this.#selectedItem.innerHTML = '';
    this.clearSelectedItem();
  }

  selectItem(boardItem) {
    if (this.#selectedItem === boardItem || !(boardItem.id
        in this.#roundStatus.movablePositions)) {
      this.clearSelectedItem();
      return;
    }
    this.clearSelectedItem();
    this.#selectedItem = boardItem;
    let availablePositions = this.#roundStatus.movablePositions[`${boardItem.id}`];
    Array.from($board.children).filter(
        item => availablePositions.includes(item.id)
    ).forEach(element => element.classList.add('movable'));
  }

  clearSelectedItem() {
    this.#selectedItem = '';
    Array.from($board.children)
    .forEach(child => child.classList.remove('movable'));
  }

  updateBoardStatus(roundStatus) {
    this.#roundStatus = roundStatus;
  }

  getSelectedItem() {
    return this.#selectedItem;
  }

  validateContinuable() {
    let kingDead = this.#roundStatus.kingDead;
    if (kingDead) {
      const winner = this.#roundStatus.currentColor === 'WHITE' ? 'BLACK'
          : 'WHITE';
      alert(`왕이 죽었습니다. ${winner} 승리`);
      exit();
    }
  }

  getScore() {
    return this.#roundStatus.score;
  }

  getColor() {
    return this.#roundStatus.currentColor;
  }
}
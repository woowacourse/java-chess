const piece = {
  white_k: '&#9812;',
  white_q: '&#9813;',
  white_r: '&#9814;',
  white_b: '&#9815;',
  white_n: '&#9816;',
  white_p: '&#9817;',
  white_mp: '&#9817;',
  black_k: '&#9818;',
  black_q: '&#9819;',
  black_r: '&#9820;',
  black_b: '&#9821;',
  black_n: '&#9822;',
  black_p: '&#9823;',
  black_mp: '&#9823;',
  empty: '&#x20;'
}

const chessGame = {
  move: {
    get source() {
      return this._source;
    },
    set source(square) {
      this._source = square;
    },
    get target() {
      return this._target;
    },
    set target(square) {
      this._target = square;
    }
  },
  get board() {
    return this._board;
  },
  set board(boardVal) {
    for (let x = 0; x < 8; x++) {
      for (let y = 0; y < 8; y++) {
        let query = `${x},${y}`;
        let el = document.querySelector(`div[data-square="${query}"]`);
        if (boardVal[query] !== undefined) {
          let team = boardVal[query]['team'];
          let type = boardVal[query]['type'];
          let pieceQuery = `${team}_${type}`;
          el.innerHTML = piece[pieceQuery];
        } else {
          el.innerHTML = piece['empty'];
        }
      }
    }
    this._board = boardVal;
  },
  get team() {
    return this._team;
  },
  set team(teamVal) {
    this._team = teamVal;
  },
  score: {
    get white() {
      return _this.white;
    },
    set white(score) {
      this._white = score;
    },
    get black() {
      return this._black;
    },
    set black(score) {
      this._score = score;
    }
  },
  get winner() {
    return this._winner;
  },
  set winner(winnerVal) {
    this._winner = winnerVal;
  }
}

window.onload = function () {
  let initBoard = {
    '0,0': {
      team: 'black',
      type: 'r'
    },
    '1,0': {
      team: 'black',
      type: 'n'
    },
    '2,0': {
      team: 'black',
      type: 'b'
    },
    '3,0': {
      team: 'black',
      type: 'q'
    },
    '4,0': {
      team: 'black',
      type: 'k'
    },
    '5,0': {
      team: 'black',
      type: 'b'
    },
    '6,0': {
      team: 'black',
      type: 'n'
    },
    '7,0': {
      team: 'black',
      type: 'r'
    },
  };
  chessGame.board = initBoard;
  const inputSource = document.querySelector('input[data-name="source"]');
  const inputTarget = document.querySelector('input[data-name="target"]');
  const btnReset = document.querySelector('input[data-btn="reset"]');
  const btnMove = document.querySelector('input[data-btn="move"]');
  const elTeam = document.querySelector('div[data-name="team"]');
  const elScoreWhite = document.querySelector('div[data-name="score_white"]');
  const elScoreBlack = document.querySelector('div[data-name="score_black"]');
  const elWinner = document.querySelector('div[data-name="winner"]');
  inputSource.addEventListener('input', function () {
    chessGame.move.source = this.value;
  });
  inputTarget.addEventListener('input', function () {
    chessGame.move.target = this.value;
  });
  btnReset.addEventListener('click', function () {
    chessGame.move.source = '';
    chessGame.move.target = '';

    document.querySelector('input[data-name="source"]').value = '';
    document.querySelector('input[data-name="target"]').value = '';
    console.log('reset');
  });
  btnMove.addEventListener('click', function () {
    // ajax
    console.log('move');
  });
}
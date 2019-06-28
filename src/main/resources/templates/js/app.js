const piece = {
    WHITE_KING: '&#9812;',
    WHITE_QUEEN: '&#9813;',
    WHITE_ROOK: '&#9814;',
    WHITE_BISHOP: '&#9815;',
    WHITE_KNIGHT: '&#9816;',
    WHITE_PAWN: '&#9817;',
    WHITE_MOVEDPAWN: '&#9817;',
    BLACK_KING: '&#9818;',
    BLACK_QUEEN: '&#9819;',
    BLACK_ROOK: '&#9820;',
    BLACK_BISHOP: '&#9821;',
    BLACK_KNIGHT: '&#9822;',
    BLACK_PAWN: '&#9823;',
    BLACK_MOVEDPAWN: '&#9823;',
    EMPTY: '&#x20;'
};

let outputData;
window.onload = function () {
    const inputSource = document.querySelector('input[data-name="source"]');
    const inputTarget = document.querySelector('input[data-name="target"]');
    const btnReset = document.querySelector('input[data-btn="reset"]');
    const btnMove = document.querySelector('input[data-btn="move"]');
    const elTeam = document.querySelector('div[data-name="team"]');
    const elScoreWhite = document.querySelector('div[data-name="score_white"]');
    const elScoreBlack = document.querySelector('div[data-name="score_black"]');
    const elWinner = document.querySelector('div[data-name="winner"]');

    function requestBoard() {
        $.ajax({
            method: "GET",
            url: "/chessGame",
            contentType: 'application/json; charset=utf-8',
            dataType: "json"
        })
            .done(function (data) {
                if (!data.message) {
                    chessGame.board = data.board;
                    chessGame.team = data.lastUser;
                    chessGame.score.black = data.blackScore;
                    chessGame.score.white = data.whiteScore;
                    if (data.winner) {
                        chessGame.winner = data.winner
                    }

                    console.log(data);
                } else {
                    alert(data.message);
                }
            });
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
          let queryName = `Square{x=${x}, y=${y}}`;
                    let query = `${x},${y}`;
                    let el = document.querySelector(`div[data-square="${query}"]`);
          el.dataset.name = queryName;
          if (boardVal[queryName] !== undefined) {
              let team = boardVal[queryName]['team'];
              let type = boardVal[queryName]['type'];
              let pieceQuery = `${team}_${type}`;
              el.innerHTML = piece[pieceQuery];
          } else {
              el.innerHTML = piece['EMPTY'];
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
            elTeam.innerHTML = this._team;
        },
        score: {
            get white() {
                return this._white;
            },
            set white(score) {
                this._white = score;
                elScoreWhite.innerHTML = this._white;
            },
            get black() {
                return this._black;
            },
            set black(score) {
                this._black = score;
                elScoreBlack.innerHTML = this._black;
            }
        },
        get winner() {
            return this._winner;
        },
        set winner(winnerVal) {
            this._winner = winnerVal;
            elWinner.innerHTML = this._winner;
        }
    };

    requestBoard();

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
    $.ajax({
      method: "POST",
        url: "/chessGame",
      contentType: 'application/json; charset=utf-8',
      dataType: "json",
        data: JSON.stringify({
            sourceX: chessGame.move.source.split(',')[0],
            sourceY: chessGame.move.source.split(',')[1],
            targetX: chessGame.move.target.split(',')[0],
            targetY: chessGame.move.target.split(',')[1],
        })
    })
        .done(function (data) {
            if (!data.message) {
                chessGame.board = data.board;
                chessGame.team = data.lastUser;
                chessGame.score.black = data.blackScore;
                chessGame.score.white = data.whiteScore;
                if (data.winner) {
                    chessGame.winner = data.winner
                }

                console.log(data);
            } else {
                alert(data.message);
            }
        });
  });
};
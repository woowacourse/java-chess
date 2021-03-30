
const App = function() {
  this.$chessBoard = document.querySelector("#chess_board");

  this.renderEmptyBoard = function() {
    this.$chessBoard.innerHTML = '';

    for (let r = 1; r <= 8; r++) {
      const row = document.createElement("tr");
      for (let c = 1; c <= 8; c++) {
        const column = document.createElement("td");
        column.setAttribute("class", "chess_cell");
        column.setAttribute("id", `${c}${8 - r + 1}`);
        column.innerText = ".";
        row.appendChild(column);
      }
      this.$chessBoard.appendChild(row);
    }
  }.bind(this);

  this.renderBoard = function(response) {
    this.renderEmptyBoard();

    response = JSON.parse(response);

    const turn = response.turn;
    const pieces = response.pieces;
    const score = 0;

    pieces.forEach((piece) => {
      const viewPiece = document.getElementById(piece.location);
      viewPiece.innerText = piece.team + piece.type;
    });

  }.bind(this);
}

window.onload = () => {
  const app = new App();

  const testJson = `{
                      "turn": "B",
                      "pieces": [
                        {
                          "location": "11",
                          "team": "W",
                          "type": "R"
                        },
                        {
                          "location": "21",
                          "team": "W",
                          "type": "N"
                        },
                        {
                          "location": "31",
                          "team": "W",
                          "type": "B"
                        },
                        {
                          "location": "41",
                          "team": "W",
                          "type": "Q"
                        }
                      ]
                    }`;

  app.renderBoard(testJson);
}
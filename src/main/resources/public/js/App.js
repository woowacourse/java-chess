const App = function() {
  this.$chessBoard = document.querySelector("#chess_board");
  this.$startBtn = document.querySelector("#start");
  this.$endBtn = document.querySelector("#end");
  this.$currentTurn = document.querySelector("#current_turn");
  this.$blackScore = document.querySelector("#black_score");
  this.$whiteScore = document.querySelector("#white_score");
  this.$winner = document.querySelector("#winner");
  this.roomName = undefined;

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

    response.forEach((piece) => {
      const viewPiece = document.getElementById(piece.location);
      viewPiece.innerText = piece.team + piece.signature;
    })

  }.bind(this);

  this.renderMessage = function(payload) {
    const blackScore = payload.scoreDto.blackScore;
    const whiteScore = payload.scoreDto.whiteScore;
    const currentTeam = payload.currentTeam;
    const winner = payload.scoreDto.winner;

    this.$blackScore.innerHTML = `블랙 점수 : ${blackScore}`;
    this.$whiteScore.innerHTML = `화이트 점수 : ${whiteScore}`;
    this.$currentTurn.innerHTML = `현재 턴 : ${currentTeam}`;

    if (blackScore == 0 || whiteScore == 0) {
      alert(`축하합니다!! 승자 : ${winner}`);
      this.$winner.innerHTML = `승자 : ${winner}`;
    }

  }.bind(this);

  this.getClickedPiece = function() {
    const pieces = document.getElementsByTagName("td");
    for (let i = 0; i < pieces.length; i++) {
      if (pieces[i].classList.contains("clicked")) {
        return pieces[i];
      }
    }
    return null;
  }.bind(this);

  this.onClickPiece = function(event) {
    const clickPiece = event.target.closest("td");
    const clickedPiece = this.getClickedPiece();
    if (clickedPiece) {
      clickedPiece.classList.remove("clickedTile");
      clickedPiece.classList.toggle("clicked");
      if (clickedPiece != clickPiece) {
        this.move(clickedPiece.id, clickPiece.id);
      }
    } else {
      clickPiece.classList.toggle("clicked");
      clickPiece.classList.add("clickedTile");
    }
  }.bind(this);

  this.move = function(source, target) {
    const payload = {
      source: source,
      target: target
    }

    fetch(`http://localhost:8080/room/${this.roomName}/move`, {
      method: 'POST',
      headers: {'content-type': 'application/json'},
      body: JSON.stringify(payload)
    })
    .then(response => response.json())
    .then(result => {
      if (result.status === "ERROR") {
        alert(result.message);
        return;
      }
      this.renderBoard(result.payload.pieces);
      this.renderMessage(result.payload);
    })
    .catch(err => alert(err));
  }.bind(this);

  this.$chessBoard.addEventListener('click', this.onClickPiece);
  this.$startBtn.addEventListener('click', () => {
    fetch(`http://localhost:8080/room/${this.roomName}/start`, {
      method: 'GET'
    })
      .then(response => response.json())
      .then(result => {
        if (result.status === "ERROR") {
          alert(result.message);
          return;
        }
        this.renderBoard(result.payload.pieces);
        this.renderMessage(result.payload);
      })
      .catch(err => alert(err));
  })

  this.$endBtn.addEventListener('click', () => {
    fetch(`http://localhost:8080/room/${this.roomName}/end`, {
      method: 'GET'
    })
      .then(response => response.json())
      .then(result => {
        if (result.status === "ERROR") {
          alert(result.message);
          return;
        }
        this.renderBoard(result.payload.pieces);
        this.renderMessage(result.payload);
      })
      .catch(err => alert(err));
  })

  this.constructor = function() {
    this.renderEmptyBoard();

    const roomName = prompt("입장하실 방의 이름을 입력해주세요.\n 존재하지 않은 이름을 입력시 새로운 방이 만들어집니다.");
    if (roomName === "") {
      alert("다시 입력해주세요 :)");
      return this.constructor();
    }

    fetch(`http://localhost:8080/room/${roomName}`)
      .then(response => response.json())
      .then(result => {
        if (result.status === "ERROR" && result.message === "[ERROR] 존재하지 않는 방입니다.") {
          alert("존재하지 않는 방이므로, 새로 방을 만듭니다.");
          fetch(`http://localhost:8080/createroom/${roomName}`)
            .then(response => response.json())
            .then(result => this.roomName = roomName);
          this.renderEmptyBoard();
          return;
        }
        if (result.status === "ERROR" && result.message === "[ERROR] 아직 시작되지 않은 방입니다.") {
          this.roomName = roomName;
          this.renderEmptyBoard();
          return;
        }
        this.roomName = roomName;
        this.renderBoard(result.payload.pieces);
        this.renderMessage(result.payload);
      })
      .catch(err => alert(err));
  }.bind(this);
}

window.onload = () => {
  const app = new App();
  app.constructor();
}



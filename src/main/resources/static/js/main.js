let mainPage;

document.addEventListener("DOMContentLoaded", function () {
  mainPage = new MainPage();
  mainPage.initMainPage();
});

function MainPage() {
  this.getPiecesUrl = "http://localhost:8080/api/pieces";
  this.getScoreUrl = "http://localhost:8080/api/score";
  this.putPiecesUrl = "http://localhost:8080/api/pieces";
  this.putChessGameUrl = "http://localhost:8080/api/chessGame";
  this.imageUrl = "http://localhost:8080/images/";
  this.isPlaying = false;
  this.gameId = -1;
}

MainPage.prototype.initMainPage = function () {
  this.registerGameIdSetEvent();
  this.registerGameStartEvent();
  this.registerGameExitEvent();
  this.registerPieceMoveEvent();
  this.registerGetScoreEvent();
  this.registerGameRestartEvent();
}

MainPage.prototype.registerGameIdSetEvent = function () {
  document.querySelector(".room-button")
  .addEventListener("click", function () {
    mainPage.gameId = document.querySelector(
        ".chess-game-select-gameId-button input").value;
    alert(mainPage.gameId + "번 방에 입장 했습니다.");
    mainPage.deleteAllPieces();
    mainPage.putChessGame(false, true);
  });
}

MainPage.prototype.registerGameStartEvent = function () {
  document.querySelector(".chess-game-start-button")
  .addEventListener("click", function () {
    mainPage.validateGameStarted();
    alert("게임을 진행합니다.");
    mainPage.putChessGame(false, true);
  });
}

MainPage.prototype.registerGameExitEvent = function () {
  document.querySelector(".chess-game-exit-button")
  .addEventListener("click", function () {
    mainPage.validateGameEnded();
    alert("게임을 종료합니다.");
    mainPage.putChessGame(false, false);
  });
}

MainPage.prototype.registerGameRestartEvent = function () {
  document.querySelector(".chess-game-restart-button")
  .addEventListener("click", function () {
    mainPage.validateGameEnded();
    mainPage.putChessGame(true, true);
  });
}

MainPage.prototype.putChessGame = function (isRestart, isPlaying) {

  const request = {
    gameId: mainPage.gameId,
    isRestart: isRestart,
    isPlaying: isPlaying
  };

  const obj = {
    body: JSON.stringify(request),
    headers: {
      'Content-Type': 'application/json',
    },
    method: 'PUT'
  }

  fetch(mainPage.putChessGameUrl, obj)
  .then(function (response) {
    if (response.ok) {
      return response.json();
    }
    response.text().then(function (data) {
      alert(data);

      throw new Error(data);
    })
  })
  .then(function (data) {
    mainPage.isPlaying = data.isPlaying;
    if (mainPage.isPlaying) {
      mainPage.templatePieces(data.pieces);
    } else {
      mainPage.deleteAllPieces();
    }
  })

}

MainPage.prototype.validateGameStarted = function () {
  if (mainPage.isPlaying === true) {
    alert("이미 게임이 진행중 입니다.");
    throw new Error("이미 게임이 진행중 입니다.");
  }
}

MainPage.prototype.exitGame = function () {
  mainPage.validateGameEnded();
  mainPage.putChessGame(false);
  mainPage.isPlaying = false;
  mainPage.deleteAllPieces();
  alert("게임이 종료되었습니다.");
}

MainPage.prototype.validateGameEnded = function () {
  if (mainPage.isPlaying === false) {
    alert("아직 게임을 시작하지 않았습니다.");
    throw new Error("아직 게임을 시작하지 않았습니다.");
  }
}

MainPage.prototype.registerGetScoreEvent = function () {
  document.querySelector(".chess-game-score-button")
  .addEventListener("click", function () {
    mainPage.validateGameEnded();
    mainPage.getScore();
  });
}

MainPage.prototype.getScore = function () {
  const oReq = new XMLHttpRequest();

  oReq.addEventListener("load", function () {
    let scores = JSON.parse(this.responseText);
    alert("검은색 : " + scores.blackScore + "점, 흰색 : " + scores.whiteScore + "점");
  });

  oReq.open("GET", this.getScoreUrl + "?" + mainPage.gameId);
  oReq.send();
}

MainPage.prototype.getPieces = function () {
  mainPage.deleteAllPieces();

  const oReq = new XMLHttpRequest();

  oReq.addEventListener("load", function () {
    let pieces = JSON.parse(this.responseText);
    mainPage.templatePieces(pieces);
  });

  oReq.open("GET", this.getPiecesUrl);
  oReq.send();

}

MainPage.prototype.deleteAllPieces = function () {
  document.querySelectorAll('.chess-piece').forEach(item => {
    item.remove();
  });
}

MainPage.prototype.templatePieces = function (pieces) {
  mainPage.deleteAllPieces();
  for (let i = 0; i < pieces.length; i++) {
    if (pieces[i].name === ".") {
      continue;
    }

    document.querySelector("." + pieces[i].coordinate)
    .insertAdjacentHTML("beforeend", mainPage.pieceElement(pieces[i]));
  }
}

MainPage.prototype.pieceElement = function (piece) {

  let color;
  if (piece.isBlack) {
    color = "b";
  } else {
    color = "w";
  }

  const url = mainPage.imageUrl + color + piece.name.toUpperCase() + ".png";
  return `<img src="${url}" class="chess-piece">`;
}

MainPage.prototype.registerPieceMoveEvent = function () {
  document.querySelector(".move-button")
  .addEventListener("click", function () {
    mainPage.validateGameEnded();
    mainPage.putPieces();
  });
}

MainPage.prototype.putPieces = function () {
  let source = document.querySelector('.chess-game-move-button .source').value;
  document.querySelector('.chess-game-move-button .source').value = "";
  let target = document.querySelector('.chess-game-move-button .target').value;
  document.querySelector('.chess-game-move-button .target').value = "";

  const moveData = {
    gameId: mainPage.gameId,
    source: source,
    target: target
  };

  const obj = {
    body: JSON.stringify(moveData),
    headers: {
      'Content-Type': 'application/json',
    },
    method: 'PUT'
  }

  fetch(mainPage.putPiecesUrl, obj)
  .then(function (response) {
    if (response.ok) {
      return response.json();
    }
    response.text().then(function (data) {
      alert(data);

      throw new Error(data);
    })
  })
  .then(function (data) {
    if (!data.isPlaying) {
      alert(data.winner);
      mainPage.putChessGame(true, false);
    }
    mainPage.isPlaying = data.isPlaying;
    mainPage.templatePieces(data.pieces);
    mainPage.winner = data.winner;
  })

}


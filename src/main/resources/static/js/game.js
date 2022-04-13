let chessGameName = "";
let isRunning = "";

async function startGame() {
  const startButton = document.getElementById("start-btn");
  const endButton = document.getElementById("end-btn");
  const statusButton = document.getElementById("status-btn");
  const gameName = document.getElementById("game-name");

  const chessMap = await newGame(gameName.value);

  startButton.disabled = true;
  endButton.disabled = false;
  statusButton.disabled = false;
  gameName.disabled = true;

  setChessMap(chessMap);
}

async function newGame(gameName) {
  if (gameName === '') {
    alert("게임 이름을 입력해주세요.");
    return;
  }

  let chessGame = await fetch("/new-game", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      gameName: gameName
    }),
  })
  .then((response) => response.json())
  .then((response) => {
    if (response.gameName === undefined) {
      alert(response.message);
      throw new Error(response.message);
    }
  });

  chessGame = await chessGame.json();
  chessGameName = chessGame.gameName;
  isRunning = chessGame.isRunning;
  document.getElementById("current-turn").innerHTML = "현재 턴: " + chessGame.turn;
  return chessGame.chessMap;
}

function setChessMap(chessMap) {
  for (let file = 0; file < 8; file++) {
    for (let rank = 1; rank <= 8; rank++) {
      const eachDiv = document.getElementById(toFileName(file) + rank);
      let mapValue = chessMap[8 - rank][file];
      if (mapValue === ".") {
        continue;
      }
      const img = document.createElement("img");
      img.src = "/images/" + toPieceImageName(mapValue) + ".png";
      img.style.width = '80px';
      img.style.height = '70px';
      eachDiv.appendChild(img);
    }
  }
}

function toFileName(file) {
  const fileNames = new Map([
    [0, "a"], [1, "b"], [2, "c"], [3, "d"], [4, "e"], [5, "f"], [6, "g"],
    [7, "h"]
  ]);
  return fileNames.get(file);
}

function toPieceImageName(mapValue) {
  const imageNames = new Map([
    ["P", "black-pawn"], ["R", "black-rook"], ["N", "black-knight"],
    ["B", "black-bishop"],
    ["Q", "black-queen"], ["K", "black-king"], ["p", "white-pawn"],
    ["r", "white-rook"],
    ["n", "white-knight"], ["b", "white-bishop"], ["q", "white-queen"],
    ["k", "white-king"]
  ]);
  return imageNames.get(mapValue);
}

async function findStatus() {
  let status = await fetch("/status/" + chessGameName)
  status = await status.json();

  const whitePlayerScore = status.whitePlayerScore;
  const blackPlayerScore = status.blackPlayerScore;
  const whitePlayerResult = status.whitePlayerResult;
  const blackPlayerResult = status.blackPlayerResult;

  alert("[White 팀] 점수 : " + whitePlayerScore + ", 결과 : " + whitePlayerResult
      + "\n[Black 팀] 점수 : " + blackPlayerScore + ", 결과 : "
      + blackPlayerResult);
}

async function finishGame() {
  const startButton = document.getElementById("start-btn");
  const statusButton = document.getElementById("status-btn");
  const endButton = document.getElementById("end-btn");

  startButton.disabled = false;
  statusButton.disabled = true;
  endButton.disabled = true;

  const status = await fetch("/end")
  .then((response) => response.json());

  const whitePlayerScore = status.whitePlayerScore;
  const blackPlayerScore = status.blackPlayerScore;
  const whitePlayerResult = status.whitePlayerResult;
  const blackPlayerResult = status.blackPlayerResult;

  alert("게임이 종료되었습니다.");
  alert("[White 팀] 점수 : " + whitePlayerScore + ", 결과 : " + whitePlayerResult
      + "\n[Black 팀] 점수 : " + blackPlayerScore + ", 결과 : "
      + blackPlayerResult);

  window.location.href = "/";
}

async function move() {
  const current = document.getElementById("current").value;
  const destination = document.getElementById("destination").value;
  await fetch("/move", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      current: current,
      destination: destination
    }),
  }).then((response) => console.log(response));

  document.getElementById("current").value = "";
  document.getElementById("destination").value = "";
}

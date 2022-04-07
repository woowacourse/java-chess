async function startGame() {
  const startButton = document.getElementById("start-btn");
  const endButton = document.getElementById("end-btn");
  const statusButton = document.getElementById("status-btn");

  startButton.disabled = true;
  endButton.disabled = false;
  statusButton.disabled = false;
  const chessMap = await createChessMap();
  setChessMap(chessMap);
}

async function createChessMap() {
  return await fetch("/start")
  .then((response) => response.json());
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
  const status = await fetch("/status")
  .then((response) => response.json());

  const whitePlayerScore = status.whitePlayerScore;
  const blackPlayerScore = status.blackPlayerScore;
  const whitePlayerResult = status.whitePlayerResult;
  const blackPlayerResult = status.blackPlayerResult;

  alert("[White 팀] 점수 : " + whitePlayerScore + ", 결과 : " + whitePlayerResult
      + "\n[Black 팀] 점수 : " + blackPlayerScore + ", 결과 : "
      + blackPlayerResult);
}

const BOARD = document.querySelector("#board");
const POSITIONS = BOARD.querySelectorAll("div");
const API_URL = "http://localhost:4567/";
const PIECE_IMAGE_MAP = {
  "p": "./images/whitePawn.png",
  "P": "./images/blackPawn.png",
  "r": "./images/whiteRook.png",
  "R": "./images/blackRook.png",
  "n": "./images/whiteKnight.png",
  "N": "./images/blackKnight.png",
  "b": "./images/whiteBishop.png",
  "B": "./images/blackBishop.png",
  "q": "./images/whiteQueen.png",
  "Q": "./images/blackQueen.png",
  "k": "./images/whiteKing.png",
  "K": "./images/blackKing.png",
}
let boardInfo = {};
let positionSelected = false;
let source = null;

window.onload = () => {
  initializeBoard();
  for (const position of POSITIONS) {
    position.addEventListener("click", selectPosition);
  }
};

function initializeBoard() {
  fetch(API_URL + "new")
  .then((response) => {
    if (!response.ok) {
      throw new Error(" ");
    }
    return response.json();
  })
  .then(placePieces)
  .catch(error => console.log(error));
}

function placePieces(responseData) {
  boardInfo = responseData;
  for (const position of POSITIONS) {
    const key = position.getAttribute("id");
    if (PIECE_IMAGE_MAP[responseData[key]] !== undefined) {
      position.style.backgroundImage = "url(" + PIECE_IMAGE_MAP[responseData[key]] + ")";
    } else {
      position.style.backgroundImage = null;
    }
  }
}

function selectPosition(event) {
  const selectedPosition = event.target.getAttribute("id");
  if (positionSelected === false) {
    if (boardInfo[selectedPosition] === ".") {
      console.log("빈 공간은 움직일 수 없습니다.")
      return;
    }
    // getMovablePositions(targetPosition);
    positionSelected = true;
    source = event.target;
    source.style.backgroundColor = "yellow";
  } else {
    console.log(selectedPosition);
    // console.log(movablePosition);
    // if (source !== event.target) {
    //   alert("움직일 수 없는 위치입니다.");
    //   return;
    // }
    // movablePositionSetting(false);
    if (source === event.target) {
      positionSelected = false;
      source.style.backgroundColor = "";
      source = null;
      return;
    }
    const sourcePosition = source.getAttribute("id");
    positionSelected = false;
    source.style.backgroundColor = "";
    movePieceByPosition(sourcePosition, selectedPosition);
  }
}

function movePieceByPosition(source, target) {
  const newData = {
    "source": source,
    "target": target
  }
  const option = getOption("PUT", newData);

  fetch(API_URL + "move", option)
  .then((response) => {
    if (!response.ok) {
      throw new Error(" ");
    }
    return response.json();
  })
  .then(placePieces)
  .catch((error) => {
    console.log(error);
  });
}

function getOption(methodType, bodyData) {
  return {
    method: methodType,
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(bodyData)
  };
}

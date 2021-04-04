const BOARD = document.querySelector("#board");
const BUTTONS = document.querySelector("#buttons");
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

const startButton = BUTTONS.querySelector(".start");
const saveButton = BUTTONS.querySelector(".save");
const loadButton = BUTTONS.querySelector(".load");

let boardInfo = {};
let availablePositions = [];
const AVAILABLE_POSITION_CLASS = "available";
let positionSelected = false;
let source = null;

window.onload = () => {
  initializeBoard();
  for (const position of POSITIONS) {
    position.addEventListener("click", selectPosition);
  }
  startButton.addEventListener("click", initializeBoard);
  saveButton.addEventListener("click", saveBoard);
  loadButton.addEventListener("click", loadBoard);
};

function saveBoard() {
  fetch(API_URL + "save")
  .then((response) => {
    if (!response.ok) {
      throw new Error(" ");
    }
  })
  .catch(error => console.log(error));
}

function loadBoard() {
  fetch(API_URL + "load")
  .then((response) => {
    if (!response.ok) {
      throw new Error(" ");
    }
    return response.json();
  })
  .then(placePieces)
  .catch(error => console.log(error));
  checkState();
}

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
  checkState();
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
    getAvailablePositions(selectedPosition);
    positionSelected = true;
    source = event.target;
    source.style.backgroundColor = "yellow";
  } else {
    console.log(selectedPosition);
    console.log(availablePositions);

    visualizeAvailablePositions(false);
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
  checkState();
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

function getAvailablePositions(sourcePosition) {
  fetch(API_URL + "availablePositions?source=" + sourcePosition)
  .then((response) => {
    if (!response.ok) {
      throw new Error(" ");
    }
    return response.json();
  })
  .then((responseData) => {
    availablePositions = responseData;
    visualizeAvailablePositions(true);
  })
  .catch((error) => {
    console.log(error);
  });
}

function visualizeAvailablePositions(visible) {
  for (const id of availablePositions) {
    const item = document.getElementById(id);
    if (visible === true) {
      item.classList.add(AVAILABLE_POSITION_CLASS);
    } else {
      item.classList.remove(AVAILABLE_POSITION_CLASS);
    }
  }
}

function checkState() {
  fetch(API_URL + "currentState")
  .then((response) => {
    if (!response.ok) {
      throw new Error(" ");
    }
    return response.json();
  })
  .then((responseData) => {
    const turnName = document.querySelector(".state strong");
    turnName.textContent = responseData["turn"];
  })
  .catch((error) => {
    console.log(error);
  });
}

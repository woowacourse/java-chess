const BOARD = document.querySelector("#board");
const POSITIONS = BOARD.querySelectorAll("div");
const API_URL = "https://localhost:4567/";
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

window.onload = () => {
  initializeBoard();
  for (const position of POSITIONS) {
    position.addEventListener("click", something);
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

function something() {

}

import {getInitializedBoard, reloadBoard} from "./board.js";
import {updateTurnBadge} from "./turnBadge.js";
import {addMovables, removeAllMovables} from "./movables.js";
import {move, reloadSquares, removeHighlight} from "./squares.js";
import {checkGameState} from "./gameState.js";

const clickSquare = function (event) {
  removeHighlight();
  if (event.target.classList.contains("movable")) {
    move(event.target.value, event.target.closest(".square").id);
    removeAllMovables();
    return;
  }
  removeAllMovables();
  document.querySelector("#" + event.target.id).classList.add("selected");
  addMovables(event.target.id);
};

const start = function () {
  getInitializedBoard().then(board => {
    reloadSquares(board["board"]);
    checkGameState();
    updateTurnBadge();
  });
}

const addEventToSquares = () => {
  const squares = document.querySelectorAll(".square");
  squares.forEach(square =>
      square.addEventListener("click", clickSquare)
  );
}

const exitGame = () => {
  fetch("./exit").then(response => {
    checkGameState();
  });
}

const addEventToScreen = () => {
  document.querySelector(".launch").addEventListener("click", start);
  document.querySelector(".result").addEventListener("click", start);
  document.querySelector(".exit").addEventListener("click", exitGame);
}

window.onload = () => {
  checkGameState();
  reloadBoard();
  updateTurnBadge();
  addEventToSquares();
  addEventToScreen();
};

window.onresize = () => {
  reloadBoard();
}

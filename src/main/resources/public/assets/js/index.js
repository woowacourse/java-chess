import {getInitializedBoard, reloadBoard} from "./board.js";
import {addMovables, removeAllMovables} from "./movables.js";
import {move, reloadSquares, removeHighlight} from "./squares.js";
import {updateGameState} from "./gameState.js";

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
    updateGameState();
  });
}

const addEventToSquares = () => {
  const squares = document.querySelectorAll(".square");
  squares.forEach(square =>
      square.addEventListener("click", clickSquare)
  );
}

const exitGame = () => {
  fetch("./exit", {
    method: "PUT"
  }).then(response => {
    updateGameState();
  });
}

const addEventToScreen = () => {
  document.querySelector(".launch").addEventListener("click", start);
  document.querySelector(".result").addEventListener("click", start);
  document.querySelector(".exit").addEventListener("click", exitGame);
}

window.onload = () => {
  updateGameState();
  reloadBoard();
  addEventToSquares();
  addEventToScreen();
};

window.onresize = () => {
  reloadBoard();
}

import {getInitializedBoard, reloadBoard} from "./board.js";
import {addMovables, removeAllMovables} from "./movables.js";
import {move, reloadSquares, removeHighlight} from "./squares.js";
import {updateGameState} from "./gameState.js";

const roomId = document.querySelector(".board").id;

const clickSquare = function (event) {
  removeHighlight();
  if (event.target.classList.contains("movable")) {
    move(event.target.value, event.target.closest(".square").id, roomId);
    removeAllMovables();
    return;
  }
  removeAllMovables();
  document.querySelector("#" + event.target.id).classList.add("selected");
  addMovables(event.target.id, roomId);
};

const start = function () {
  getInitializedBoard(roomId).then(board => {
    reloadSquares(board["board"]);
    updateGameState(roomId);
  });
}

const addEventToSquares = () => {
  const squares = document.querySelectorAll(".square");
  squares.forEach(square =>
      square.addEventListener("click", clickSquare)
  );
}

const exitGame = () => {
  fetch("./" + roomId + "/exit", {
    method: "PUT"
  }).then(response => {
    updateGameState(roomId);
  });
}

const addEventToScreen = () => {
  document.querySelector(".launch").addEventListener("click", start);
  document.querySelector(".result").addEventListener("click", start);
  document.querySelector(".exit").addEventListener("click", exitGame);
}

document.querySelector(".title").addEventListener("click", () => {
  window.location.href = window.location.href = "../";
});

window.onload = () => {
  updateGameState(roomId);
  reloadBoard();
  addEventToSquares();
  addEventToScreen();
};

window.onresize = () => {
  reloadBoard();
}

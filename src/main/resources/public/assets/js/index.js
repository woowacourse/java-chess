import {reloadBoard} from "./board.js";
import {updateTurnBadge} from "./turnBadge.js";
import {addMovables, removeAllMovables} from "./movables.js";
import {move, removeHighlight} from "./squares.js";

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

const addEventToSquares = () => {
  const squares = document.querySelectorAll(".square");
  squares.forEach(square =>
      square.addEventListener("click", clickSquare)
  );
}

window.onload = () => {
  reloadBoard();
  updateTurnBadge();
  addEventToSquares();
};

window.onresize = () => {
  reloadBoard();
  updateTurnBadge();
}

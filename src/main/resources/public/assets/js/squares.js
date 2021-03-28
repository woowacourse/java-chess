import {updateTurnBadge} from "./turnBadge.js";
import {getBoardAfterMove} from "./board.js";
import {checkGameState} from "./gameState.js";

export const removeHighlight = () => {
  document.querySelectorAll(".selected").forEach(selected => {
    selected.classList.remove("selected");
  });
}

export const move = (source, destination) => {
  getBoardAfterMove(source, destination).then(board => {
    const boardDto = board["board"];
    reloadSquares(board["board"]["board"]);
    updateTurnBadge();
    checkGameState();
  });
}

export const reloadSquares = (board) => {
  for (const i in board) {
    const newSquare = board[i];
    const square = document.querySelector("#" + newSquare.x + newSquare.y);
    square.setAttribute("class",
        "square " + newSquare["piece"] + newSquare["team"]);
  }
}
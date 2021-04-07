import {getBoardAfterMove} from "./board.js";
import {updateGameState} from "./gameState.js";

export const removeHighlight = () => {
  document.querySelectorAll(".selected").forEach(selected => {
    selected.classList.remove("selected");
  });
}

export const move = (source, destination, roomId) => {
  getBoardAfterMove(source, destination, roomId).then(board => {
    updateGameState(roomId);
    reloadSquares(board["board"]);
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
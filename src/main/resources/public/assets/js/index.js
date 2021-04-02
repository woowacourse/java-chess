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

const start = async () => {
  const board = await getInitializedBoard(roomId)
  reloadSquares(board["board"]);
  updateGameState(roomId);
}

const usersTemplate = (users) => `<p><strong>BLACK</strong>  닉네임:${users.blackName} 승:${users.blackWin} 패:${users.blackLose}</p>
    <p><strong>WHITE</strong>  닉네임:${users.whiteName} 승:${users.whiteWin} 패:${users.whiteLose}</p>`;

const reloadUsers = async () => {
  const response = await fetch("./" + roomId + "/stat");
  const result = await response.json();
  console.log(result);
  console.log(result);
  document.querySelector(".stat").innerHTML = usersTemplate(result);
}

const restart = async () => {
  await start();
  await reloadUsers();
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
  document.querySelector(".result").addEventListener("click", restart);
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

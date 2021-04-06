import {updateTurnBadge} from "./turnBadge.js";

const teams = {
  w: "white",
  b: "black",
  n: "none"
};

const blurs = ["header", ".square", ".board", ".blackTurn", ".whiteTurn"];

const launch = document.querySelector(".launch");
const result = document.querySelector(".result");

async function getGameStatus(roomId) {
  const response = await fetch("./" + roomId + "/getGameStatus");
  const result = await response.json();
  if (response.ok) {
    return result;
  } else {
    alert("HTTP-Error: " + result["message"]);
  }
}

const blurContents = (isBlur) => {
  if (isBlur) {
    blurs.forEach(blur => {
      document.querySelectorAll(blur).forEach(
          content => content.classList.add("blur"))
    });
    return;
  }
  blurs.forEach(blur => {
    document.querySelectorAll(blur).forEach(
        content => content.classList.remove("blur"))
  });
}

export const updateGameState = (roomId) => {
  getGameStatus(roomId).then(gameStatus => {
    updateTurnBadge(gameStatus["turn"]);
    if (gameStatus["gameState"] === "Ready") {
      launch.style.visibility = "visible";
      result.style.visibility = "hidden";
      blurContents(true);
      return;
    }
    if (gameStatus["gameState"] === "Finished") {
      launch.style.visibility = "hidden";
      result.style.visibility = "visible";
      result.innerHTML = `<p>Winner is ${teams[gameStatus["winner"]]}</p><p>Click anywhere to start</p>`;
      blurContents(true);
      return;
    }
    launch.style.visibility = "hidden";
    result.style.visibility = "hidden";
    blurContents(false);
  });
}
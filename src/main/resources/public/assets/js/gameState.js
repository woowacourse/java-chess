const teams = {
  w: "white",
  b: "black",
  n: "none"
};

const launch = document.querySelector(".launch");
const result = document.querySelector(".result");
const blur = document.querySelector(".blur")

async function getGameStatus() {
  const response = await fetch("./getGameStatus");
  return await response.json();
}

export const checkGameState = () => {
  getGameStatus().then(gameStatus => {
    if (gameStatus["gameState"] === "Ready") {
      launch.style.visibility = "visible";
      result.style.visibility = "hidden";
      blur.disabled = false;
      return;
    }
    if (gameStatus["gameState"] === "Finished") {
      launch.style.visibility = "hidden";
      blur.disabled = false;
      result.style.visibility = "visible";
      result.innerHTML = `<p>Winner is ${teams[gameStatus["winner"]]}</p><p>Click anywhere to start</p>`;
      return;
    }
    launch.style.visibility = "hidden";
    result.style.visibility = "hidden";
    document.querySelector(".blur").disabled = true;
  });
}
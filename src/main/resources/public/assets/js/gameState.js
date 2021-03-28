const teams = {
  w: "white",
  b: "black",
  n: "none"
};

const blurs = ["header", ".square", ".board", ".blackTurn", ".whiteTurn"];

const launch = document.querySelector(".launch");
const result = document.querySelector(".result");

async function getGameStatus() {
  const response = await fetch("./getGameStatus");
  return await response.json();
}

const blurContents = (isBlur) => {
  if (isBlur) {
    blurs.forEach(blur => {
      document.querySelectorAll(blur).forEach(content => content.classList.add("blur"))
    });
    return;
  }
  blurs.forEach(blur => {
    document.querySelectorAll(blur).forEach(content => content.classList.remove("blur"))
  });
}

export const checkGameState = () => {
  getGameStatus().then(gameStatus => {
    console.log(gameStatus);
    if (gameStatus["gameState"] === "Ready") {
      launch.style.visibility = "visible";
      result.style.visibility = "hidden";
      blurContents(true);
      return;
    }
    if (gameStatus["gameState"] === "Finished") {
      launch.style.visibility = "hidden";
      blurContents(true);
      result.style.visibility = "visible";
      result.innerHTML = `<p>Winner is ${teams[gameStatus["winner"]]}</p><p>Click anywhere to start</p>`;
      return;
    }
    launch.style.visibility = "hidden";
    result.style.visibility = "hidden";
    blurContents(false);
  });
}
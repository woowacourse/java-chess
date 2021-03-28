const teams = {
  w: "white",
  b: "black",
  n: "none"
};

async function getGameStatus() {
  const response = await fetch("./getGameStatus");
  return await response.json();
}

export const checkGameState = () => {
  getGameStatus().then(gameStatus => {
    if (gameStatus["isOngoing"] === "false") {
      const resultDiv = document.createElement("div");
      resultDiv.classList.add("result");
      resultDiv.innerHTML = `<p>Winner is ${teams[gameStatus["winner"]]}</p>`;
      document.querySelector("body").insertAdjacentElement("afterbegin", resultDiv);
      const style = document.createElement('style');
      style.innerHTML = `
      header, .square, .board {
        filter: blur(2px);
      }`;
      document.head.appendChild(style);
    }
  });
}
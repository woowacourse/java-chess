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
    if (gameStatus["gameState"] === "Ready") {
      document.querySelector(".launch").style.visibility = "visible";
      document.querySelector(".blur").disabled = false;
      return;
    }
    document.querySelector(".launch").style.visibility = "hidden";
    if (gameStatus["gameState"] === "Finished") {
      document.querySelector(".blur").disabled = false;
      const resultDiv = document.createElement("div");
      resultDiv.classList.add("result");
      resultDiv.innerHTML = `<p>Winner is ${teams[gameStatus["winner"]]}</p>`;
      document.querySelector("body").insertAdjacentElement("afterbegin", resultDiv);
      return;
    }
    document.querySelector(".blur").disabled = true;
  });
}
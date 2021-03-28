async function getCurrentTurn() {
  const response = await fetch("./currentTurn");
  return await response.json();
}

export const updateTurnBadge = () => {
  getCurrentTurn().then(currentTurn => {
    const whiteBadge = document.querySelector(".whiteTurn");
    const blackBadge = document.querySelector(".blackTurn");

    if (currentTurn === "BLACK") {
      whiteBadge.style.visibility = "hidden";
      blackBadge.style.visibility = "visible";
    } else {
      whiteBadge.style.visibility = "visible";
      blackBadge.style.visibility = "hidden";
    }
  });
};
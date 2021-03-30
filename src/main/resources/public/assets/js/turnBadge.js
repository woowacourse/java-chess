export const updateTurnBadge = (currentTurn) => {
  const whiteBadge = document.querySelector(".whiteTurn");
  const blackBadge = document.querySelector(".blackTurn");

  if (currentTurn === "b") {
    whiteBadge.style.visibility = "hidden";
    blackBadge.style.visibility = "visible";
  } else {
    whiteBadge.style.visibility = "visible";
    blackBadge.style.visibility = "hidden";
  }
};
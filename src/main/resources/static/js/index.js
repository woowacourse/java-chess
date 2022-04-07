const randomStartButton = document.getElementById("random-start-button");

randomStartButton.addEventListener("click", () => {
  const gameId = Math.floor(Math.random() * 10000);
  location.href = `/game/${gameId}`;
});

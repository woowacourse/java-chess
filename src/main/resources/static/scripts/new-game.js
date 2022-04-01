const initGameAndGetId = async () => {
    const result = await fetch("/new-game", {method: "post"})
        .then(response => response.json());
    const newGameId = result.gameId;
    window.location.replace(`/game/${newGameId}`);
}

const newGameBtn = document.querySelector("button#init_game");

const init = () => {
    newGameBtn.addEventListener("click", initGameAndGetId);
}

init();
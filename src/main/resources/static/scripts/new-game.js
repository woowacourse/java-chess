const initGameAndGetId = async () => {
    const result = await fetch("/", {method: "post"});
    const {gameId} = await result.json();
    window.location.replace(`/game/${gameId}`);
}

const newGameBtn = document.querySelector("button#init_game");

const init = () => {
    newGameBtn.addEventListener("click", initGameAndGetId);
}

init();
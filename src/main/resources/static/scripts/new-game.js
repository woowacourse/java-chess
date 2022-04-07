const initGameAndGetId = async () => {
    const result = await fetch("/", {method: "post"});
    const {ok, error, body} = await result.json();
    if (!ok) {
        alert(error);
        return;
    }
    window.location.replace(`/game/${body.gameId}`);
}

const newGameBtn = document.querySelector("button#init_game");

const init = () => {
    newGameBtn.addEventListener("click", initGameAndGetId);
}

init();
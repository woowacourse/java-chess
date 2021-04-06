const $start = document.querySelector("#start-btn");

$start.addEventListener("click", startGame);

function startGame(event) {
    const room = {
        id : Date.now().toString()
    }

    const option = {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(room)
    }

    fetch("http://localhost:4567/game", option)
        .then(data => {
            if (!data.ok) {
                throw new Error(data.status);
            }
            location.href = "http://localhost:4567/game";
        })
        .catch(error => {
            console.log(error);
        })
}
const $start = document.querySelector("#start-btn");

$start.addEventListener("click", startGame);

function startGame(event) {
    const $roomName = document.querySelector("#room-name").value;
    const $whiteUsername = document.querySelector("#white-username").value;
    const $blackUsername = document.querySelector("#black-username").value;

    const room = {
        roomName: $roomName,
        whiteUsername: $whiteUsername,
        blackUsername: $blackUsername
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
            return data.json();
        })
        .then(post => {
            location.href = "http://localhost:4567/game/" + post.gameID;
        })
        .catch(error => {
            console.log(error);
        })
}
const startButton = document.querySelector(".start");
startButton.addEventListener("click", start);

async function start() {
    await fetch("/start", {
        method: 'POST',
        header: {
            'Content-Type': 'application/json'
        }
    }).then(response => response.json())
        .then(response => {
            console.log(response);
            location.href = `/game/` + response;
        });
}

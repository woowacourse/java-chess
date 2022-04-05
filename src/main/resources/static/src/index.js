
const startGame = () => {
    const response = fetch(`/start`, {
        method: "GET",
        headers: {"Content-Type": "application/json"}
    });
    response.then(data => data.json())
        .then(body => {
            Object.entries(body).forEach((entry) => {
                if (entry[1].includes('.')) {
                    return;
                }
                const block = document.getElementById(entry[0]);
                const imgSrc ="/images/" + entry[1] + ".svg";
                block.innerHTML = '<img id = "piece-image" class="piece-image" src=' + imgSrc + '/>'
            })
            const button = document.getElementById("start-button")
            button.innerText = "end!";
            const turnBox = document.getElementById("turn-box")
            turnBox.innerText = "white 팀 차례!";
        })
}
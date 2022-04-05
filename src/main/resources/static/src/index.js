const startGame = () => {
    const response = fetch(`/start`, {
        method: "GET",
        headers: {"Content-Type": "application/json"}
    });
    response.then(data => data.json())
        .then(body => {
            Object.entries(body).forEach((entry) => {
                const piece = entry[1];
                if (entry[1].includes('.')) {
                    return;
                }
                const key = document.getElementById(entry[0]);
                const imgSrc ="/images/" + entry[1] + ".svg";
                key.innerHTML = '<img id = "piece-image" class="piece-image" src=' + imgSrc + '/>'
            })
        })
}
function move() {
    const from = document.getElementById('from').value;
    const to = document.getElementById('to').value;

    fetch("/move", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
            from: from,
            to: to
        }),
    }).then(response => {
        if (response.redirected) {
            window.location.href = response.url;
        }
    })
}

async function scoreButton() {
    let response = await fetch("/score");
    const score = await response.json();
    alert("블랙 : " + score.model.BLACK + ", 화이트 : " + score.model.WHITE);
}

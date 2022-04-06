async function move() {
    const from = document.getElementById('from').value;
    const to = document.getElementById('to').value;

    const url = window.location.href
    const split = url.split("/");
    const boardId = split[split.length - 1];

    const response = await fetch("/move/" + boardId, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
            from: from,
            to: to
        }),
    });

    if ((await response).status === 400) {
        let newVar = await response.json();
        alert(newVar.model.message);
    }
    if (response.redirected) {
        window.location.href = response.url;
    }
}

function start() {
    fetch("/start", {
        method: "POST",
    }).then(response => {
        console.log(response.url);
        window.location.href = response.url;
    })
}

async function scoreButton() {
    const url = window.location.href
    const split = url.split("/");
    const boardId = split[split.length - 1];

    const response = await fetch("/score/" + boardId);
    const score = await response.json();
    alert("블랙 : " + score.model.BLACK + ", 화이트 : " + score.model.WHITE);
}

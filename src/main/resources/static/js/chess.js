const start = document.getElementById('start-button');
const status = document.getElementById('status-button');
const board = document.querySelector("#board");

let source = "";
let target = "";

addBoardClickEvent();

start.addEventListener('click', function () {
    fetch('/start')
        .then(handleErrors)
        .then(res => res.json())
        .then(updatePieceContainer)
        .catch(function (error) {
            alert(error.message)
        })
})

function addBoardClickEvent() {
    const divs = board.querySelectorAll("div");

    for (const div of divs) {
        if (div.className !== "rank") {
            div.addEventListener("click", move);
        }
    }
}

function move(event) {
    if (source === "") {
        if (event.target.id === "") {
            source = event.target.parentElement.id;
        } else {
            source = event.target.id;
        }
        document.getElementById(source).style.backgroundColor = 'blue';
        return;
    }

    if (source !== "" && target === "") {
        if (event.target.id === "") {
            target = event.target.parentElement.id;
        } else {
            target = event.target.id;
        }
        document.getElementById(source).style.backgroundColor = '';
        movePiece(source, target);
        source = "";
        target = "";
    }
}

function movePiece(source, target) {
    const request = {
        source: source,
        target: target
    }
    fetch('/move', {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(request),
    }).then(handleErrors)
        .then(res => res.json())
        .then(res => updatePieceContainer(res))
        .catch(function (error) {
            alert(error.message)
        });
}

function updatePieceContainer(response) {
    const divs = board.querySelectorAll("div");
    let pieces = response["board"];
    for (const div of divs) {
        if (div.className === "rank") {
            continue;
        }
        if (div.hasChildNodes()) {
            div.removeChild(div.firstChild);
        }
        const key = div.getAttribute("id");
        if (pieces[key] !== undefined) {
            const img = document.createElement("img");
            img.className = 'piece';
            img.src = "/image/pieces/" + pieces[key];
            div.appendChild(img);
        }
    }
}

status.addEventListener('click', function () {
    fetch('/status')
        .then(handleErrors)
        .then(res => res.json())
        .then(alertStatus)
        .catch(function (error) {
            alert(error.message)
        })
})

function alertStatus(scoreResult) {
    const blackScore = scoreResult["blackScore"];
    const whiteScore = scoreResult["whiteScore"];
    const winner = scoreResult["winner"];

    window.alert("Black 팀: " + blackScore + "\n" + "White 팀: " + whiteScore + "\n" + "승자: " + winner);
}

async function handleErrors(res) {
    if (!res.ok) {
        let error = await res.json();
        throw Error(error["errorMessage"])
    }
    return res;
}

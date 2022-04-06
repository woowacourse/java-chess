const start = document.getElementById('start-button');
const status = document.getElementById('status-button');
const end = document.getElementById('end-button');
const load = document.getElementById('load-button');
const board = document.querySelector("#board");

let source = "";
let target = "";
var game = "";

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
            alert("빈 칸을 옮길 수 없습니다.");
            return;
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
        gameId: game,
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
    game = response["gameId"];
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

    if (winner === "neutrality") {
        alert("Black 팀: " + blackScore + "\n" + "White 팀: " + whiteScore + "\n" + "무승부 입니다");
        return;
    }
    alert("Black 팀: " + blackScore + "\n" + "White 팀: " + whiteScore + "\n" + "승자: " + winner);
}

end.addEventListener('click', function () {
    fetch('/end/' + game)
        .then(handleErrors)
        .then(res => res.json())
        .then(alertWinner)
        .catch(function (error) {
            alert(error.message)
        })
})

function openWindowPop(url, name) {
    var options = 'top=10, left=10, width=500, height=600, status=no, menubar=no, toolbar=no, resizable=no';
    var win = window.open(url, name, options);
    win.loadGame = function (id) {
        console.log(id);
        game = id;
        fetch('/load/' + id)
            .then(handleErrors)
            .then(res => res.json())
            .then(res => updatePieceContainer(res))
            .catch(function (error) {
                alert(error.message)
            });
    }
}

load.addEventListener('click', function () {
    openWindowPop('/findGames', '게임 불러오기')
})

function alertWinner(winnerResponse) {
    const winner = winnerResponse["winner"];
    if (winner === "neutrality") {
        alert("승부가 나지 않고 게임이 종료 되었습니다.");
        return;
    }
    alert(winner + " 팀이 King을 잡아서 승리했습니다.");
}

async function handleErrors(res) {
    if (!res.ok) {
        let error = await res.json();
        throw Error(error["errorMessage"])
    }
    return res;
}

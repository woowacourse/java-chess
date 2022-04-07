let gameStatus = "";

async function refreshAndDisplayBoard() {
    await refreshBoard().then(displayBoard);
}

async function refreshBoard() {
    const board = document.getElementsByClassName("chess-ui")[0].childNodes;
    board.forEach(await function (value) {
        if (value.hasChildNodes()) {
            value.removeChild(document.getElementById("piece_img"));
        }
    })
}

async function displayBoard() {
    Array.from(await getBoard()).forEach(
        function (element) {
            let position = document.getElementById(element.position);
            if (position.hasChildNodes()) {
                position.removeChild(document.getElementById("piece_img"));
            }
            const imgTeg = document.createElement("img");
            imgTeg.setAttribute("id", "piece_img");
            const imgPath = `images/${element.color}_${element.name}.png`;

            imgTeg.setAttribute("src", imgPath);
            document.getElementById(element.position).appendChild(imgTeg);
        }
    )
}

async function loadButton() {
    if (gameStatus === "") {
        await refreshAndDisplayBoard();
        gameStatus = "run status";
    } else {
        alert("이미 게임이 로딩되었습니다.");
    }
}

function getBoard() {
    return fetch("/board")
        .then((response) => response.json());
}

async function startChessGame() {
    await fetch("/start", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        }
    }).then(response => handlingException(response))
        .then(refreshAndDisplayBoard)
        .catch(error => {
            alert(error.message);
        });
}

async function promotionButton() {
    const promotionPiece = document.getElementById("promotion").value;
    const promotion = {
        promotionValue: promotionPiece
    }

    await fetch("/promotion", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(promotion)
    }).then(response => handlingException(response))
        .then(refreshAndDisplayBoard)
        .catch(error => {
            alert(error.message);
        });
    document.getElementById("promotion").value = "";
}

async function moveButton() {
    const source = document.getElementById("source").value;
    const target = document.getElementById("target").value;
    const move = {
        source: source,
        target: target
    }

    fetch("/move", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(move)
    }).then(response => handlingException(response))
        .then(refreshAndDisplayBoard)
        .then(checkEndGame)
        .catch(error => {
            alert(error.message);
        });
    document.getElementById("source").value = "";
    document.getElementById("target").value = "";
}

async function checkEndGame() {
    const gameStatus = await fetch("/status")
        .then((response) => response.json());
    if (gameStatus.isEnd) {
        await fetch("/winner")
            .then(response => handlingException(response))
            .then(response => displayWinner(response))
            .catch(error => {
                alert(error.message);
            });
    }
}

async function displayWinner(response) {
    const result = await response.json();
    alert(`우승자는 ${result.winner}입니다.`);
}

async function scoreButton() {
    const value = await fetch("/score")
        .then((response) => response.json());
    if (gameStatus !== "") {
        alert(`${value[0].color}의 점수는 ${value[0].score}\n${value[1].color}의 점수는 ${value[1].score}`);
    } else {
        alert("게임을 로드하지 않았습니다.");
    }
}

async function handlingException(response) {
    if (response.ok) {
        return response;
    }
    const error = await response.json();
    throw Error(error.message);
}

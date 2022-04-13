const API_HOST = "http://localhost:4567"

const X_AXES = ["a", "b", "c", "d", "e", "f", "g", "h"];
const Y_AXES = ["8", "7", "6", "5", "4", "3", "2", "1"];

let from = null;
let to = null;

async function fetchAsGet(path) {
    const response = await fetch(`${API_HOST}/${path}`, {
        method: "GET"
    });

    return response.json();
}

const fetchBoard = async () => {
    return fetchAsGet("/board");
}

const fetchCurrentTurn = async () => {
    return fetchAsGet("/turn");
}

const fetchScore = async () => {
    return fetchAsGet("/score");
}

const fetchWinner = async () => {
    return fetchAsGet("/winner");
}

const move = async (from, to) => {
    await fetch("/move", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({from, to})
    });
    await render();
}

const initialize = async () => {
    await fetch("/initialize", {
        method: "POST"
    })
    await render();
}

const renderBoard = async () => {
    clear();

    const board = await fetchBoard();
    let html = "";
    Y_AXES.forEach((yAxis, i) => {
        html += "<div class='row'>";

        X_AXES.forEach((xAxis, j) => {
            const coordinate = xAxis + yAxis;
            const pieceName = board[coordinate];
            if (pieceName !== undefined) {
                const imgTag = `<div class="column" onclick="handleClickTile('${coordinate}')"><img src='images/${pieceName.toLowerCase()}.svg'/></div>`;
                html += imgTag;
            } else {
                const imgTag = `<div class="column" onclick="handleClickTile('${coordinate}')"></div>`;
                html += imgTag;
            }
        });

        html += "</div>";
    });

    document.getElementById("board").innerHTML = html;
}

const handleClickTile = async (coordinate) => {
    if (!from) {
        from = coordinate;
    } else if (!to) {
        to = coordinate;

        await move(from, to);
        from = null;
        to = null;
    }
}

const renderCurrentTurn = async () => {
    const currentTurn = await fetchCurrentTurn();

    let turn = "";
    if (currentTurn.pieceColor === "WHITE") {
        turn = "백";
    }

    if (currentTurn.pieceColor === "BLACK") {
        turn = "흑";
    }

    document.getElementById("turn").innerHTML = `현재차례: ${turn}`;
}

const renderScore = async () => {
    const score = await fetchScore();

    let html = "";
    html += `백: ${score.white}<br/>`;
    html += `흑: ${score.black}`;

    document.getElementById("score").innerHTML = html;
}

const renderWinner = async () => {
    const winner = await fetchWinner();

    if (!winner.error) {
        let color = "";
        if (winner.pieceColor === "WHITE") {
            color = "백";
        } else {
            color = "흑";
        }

        document.getElementById("score").innerHTML = `승자는 ${color}`;
    }
}

const clear = () => {
    document.getElementById("board").innerHTML = "";
    document.getElementById("turn").innerHTML = "";
    document.getElementById("score").innerHTML = "";
    document.getElementById("winner").innerHTML = "";
}

const render = async () => {
    clear();
    await renderBoard();
    await renderCurrentTurn();
    await renderScore();
    await renderWinner();
}

window.onload = async () => {
    await render();
}


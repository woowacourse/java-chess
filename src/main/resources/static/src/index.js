let source = "";
let target = "";

document.addEventListener("DOMContentLoaded", sendLoad);

async function sendLoad() {
    await fetch("/load")
        .then(response => response.json())
        .then(data => {
            loadStateAndBoard(data);
        });
}

async function sendStart() {
    await fetch("/start")
        .then(response => response.json())
        .then(data => {
            if (data.code === "success") {
                loadStateAndBoard(data);
            } else {
                alert(data.message);
            }
        });
}

async function sendEnd() {
    await fetch("/end")
        .then(response => response.json())
        .then(data => {
            if (data.code === "success") {
                loadStateAndBoard(data);
            } else {
                alert(data.message);
            }
        });
}

async function sendStatus() {
    await fetch("/status")
        .then(response => response.json())
        .then(data => {
            if (data.code === "success") {
                alert(data.myTurn + " : " + data.myScore + "점\n"
                    + data.opponentTurn + " : " + data.opponentScore + "점\n"
                    + "당신(" + data.myTurn + ")은 " + data.result + "입니다.");
            } else {
                alert(data.message);
            }
        });
}

async function move(id) {
    if (source === "") {
        source = id;
        document.getElementById(id).className += " selected";
        return;
    }
    if (source !== "" && target === "") {
        target = id;
        document.getElementById(id).className += " selected";
        await sendMove();
    }
}

async function sendMove() {
    await fetch("/move", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify({
            source: source,
            target: target
        })})
        .then(response => response.json())
        .then(data => {
            if (data.code === "success") {
                loadStateAndBoard(data);
            } else {
                alert(data.message);
            }
            document.getElementById(source).classList.remove('selected');
            document.getElementById(target).classList.remove('selected');
        });

    source = "";
    target = "";
}

async function loadStateAndBoard(data) {
    await loadBoard(data.board);
    await loadState(data.state, data.turn);
}

async function loadState(state, turn) {
    document.getElementsByClassName('state')[0].textContent = state;

    if (state === "Started") {
        document.getElementsByClassName('chess-ui')[0].style.visibility = 'visible';
        document.getElementsByClassName('state')[0].textContent += "(" + turn + ")";
    }
    if (state === "Ended") {
        alert("게임이 종료되었습니다.");
    }
}

async function loadBoard(board) {
    for (key in board) {
        const div = document.getElementById(key);
        await resetPiece(div);
        await setPiece(div, board[key]);
    }
}

async function resetPiece(div) {
    if (div.hasChildNodes()) {
        div.removeChild(div.firstChild);
    }
}

async function setPiece(div, piece) {
    if (piece !== "empty_none") {
        const img = document.createElement("img");
        img.src = "/images/" + piece + ".png";
        div.appendChild(img);
    }
}

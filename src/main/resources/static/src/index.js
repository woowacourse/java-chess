let source = "";
let target = "";

window.onload = function () {
    fetch("/load")
        .then(response => response.json())
        .then(data => {
            initializePosition(data.board);
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

async function move(event) {
    const id = event.currentTarget.id;
    const divClassName = document.getElementById(id).classList;
    if (source === "") {
        source = id;
        divClassName.add('selected');
        return;
    }
    if (source !== "" && target === "") {
        target = id;
        divClassName.add('selected');
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

function initializePosition(board) {
    const section = document.getElementsByClassName('chess-ui')[0];
    for (key in board) {
        addPosition(section, key);
    }
}

function addPosition(section, key) {
    const div = document.createElement('div');
    div.id = key;
    div.className = 'square';
    div.addEventListener('click', move);
    section.appendChild(div);
}

function loadStateAndBoard(data) {
    loadBoard(data.board);
    loadState(data.state, data.turn);
}

function loadState(state, turn) {
    document.getElementsByClassName('state')[0].textContent = state;

    if (state === "Started") {
        document.getElementsByClassName('chess-ui')[0].style.visibility = 'visible';
        document.getElementsByClassName('state')[0].textContent += "(" + turn + ")";
    }
    if (state === "Ended") {
        document.getElementsByClassName('chess-ui')[0].style.visibility = 'visible';
        alert("게임이 종료되었습니다.");
    }
}

function loadBoard(board) {
    for (key in board) {
        const div = document.getElementById(key);
        resetPiece(div);
        setPiece(div, board[key]);
    }
}

function resetPiece(div) {
    if (div.hasChildNodes()) {
        div.removeChild(div.firstChild);
    }
}

function setPiece(div, piece) {
    if (piece !== "empty_none") {
        const img = document.createElement("img");
        img.src = "/images/" + piece + ".png";
        div.appendChild(img);
    }
}

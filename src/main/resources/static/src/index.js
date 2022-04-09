let source = "";
let target = "";

window.onload = function () {
    fetch("/load")
        .then(response => response.json())
        .then(data => {
            initSquarePosition(data.board);
            loadData(data);
        });
}

async function requestStart() {
    await fetch("/start")
        .then(response => response.json())
        .then(data => {
            if (data.code === "success") {
                initSquarePosition(data.board);
                loadData(data);
            } else {
                alert(data.message);
            }
        });
}

async function requestEnd() {
    await fetch("/end")
        .then(response => response.json())
        .then(data => {
            if (data.code === "success") {
                loadData(data);
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
                if (data.result == "NONE") {
                    alert("현재 동점입니다.");
                }
                if (data.result != "NONE") {
                    alert("WHITE 팀 : " + data.whiteScore + " BLACK 팀 : " + data.blackScore
                        + "으로 현재 " + data.result + "팀이 유리합니다.");
                }
            } else {
                alert(data.message);
            }
        });
}

async function sendBoard() {
    let state = "";
    await fetch("/load")
        .then(response => response.json())
        .then(data => {
            if (data.code === "success") {
                loadData(data);
                state = data.state;
            } else {
                alert(data.message);
            }
        });

    if(state === "finished") {
        alert("게임이 종료되었습니다.");
    }
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
        await requestMove();
    }
}

async function requestMove() {
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
                sendBoard();
            } else {
                alert(data.message);
            }
            document.getElementById(source).classList.remove('selected');
            document.getElementById(target).classList.remove('selected');
        });

    source = "";
    target = "";
}

function initSquarePosition(board) {
    const section = document.getElementsByClassName('chessBox')[0];
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

function loadData(data) {
    loadBoard(data.board);
    loadState(data.state, data.turn);
}

function loadBoard(board) {
    for (key in board) {
        const div = document.getElementById(key);
        resetPiece(div);
        setPiece(div, board[key]);
    }
}

function resetPiece(div) {
    console.log(div);
    if (div.hasChildNodes()) {
        div.removeChild(div.firstChild);
    }
}

function setPiece(div, piece) {
    if (piece !== "._NONE") {
        const img = document.createElement("img");
        img.src = "/images/" + piece + ".png";
        div.appendChild(img);
    }
}


async function loadState(state, turn) {
    document.getElementsByClassName('state')[0].textContent = state;
    document.getElementsByClassName('chessBox')[0].style.visibility = 'visible';

    if (state === "ready") {
        document.getElementsByClassName('chessBox')[0].style.visibility = 'visible';
        document.getElementsByClassName('state')[0].textContent += " " + turn + " ";
    }
    if (state === "finished") {
        document.getElementsByClassName('chessBox')[0].style.visibility = 'visible';
    }
}

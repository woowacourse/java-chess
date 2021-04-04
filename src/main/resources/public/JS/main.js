createChessBoard();
const startButton = document.getElementById("start");
startButton.addEventListener("click", clickStart);

function createChessBoard() {
    makeTable();
    syncBoard();
    changeTurn();
    addEvent();
}

function makeTable() {
    const table = document.getElementById("chess-board");
    for (let i = 0; i < 8; i++) {
        const newTr = document.createElement("tr");
        for (let j = 0; j < 8; j++) {
            const newTd = document.createElement("td");

            const row = String(8 - i);
            const column = String.fromCharCode('a'.charCodeAt(0) + j);
            newTd.id = column + row;
            if ((i % 2 === 0 && j % 2 === 0) || (i % 2 !== 0 && j % 2 !== 0)) {
                newTd.className = "whiteTile";
            } else {
                newTd.className = "blackTile";
            }
            table.appendChild(newTd);
        }
        table.appendChild(newTr);
    }
}

function addEvent() {
    const table = document.getElementById("chess-board");
    table.addEventListener("click", selectTile);
}

function selectTile(event) {
    const clickPiece = event.target.closest("td");
    const clickedPiece = getClickedPiece();
    if (clickedPiece) {
        clickedPiece.classList.remove("clickedTile");
        clickedPiece.classList.toggle("clicked");
        if (clickedPiece !== clickPiece) {
            move(clickedPiece.id, clickPiece.id);
        }
    } else {
        clickPiece.classList.toggle("clicked");
        clickPiece.classList.add("clickedTile");
    }
}

function getClickedPiece() {
    const pieces = document.getElementsByTagName("td");
    for (let i = 0; i < pieces.length; i++) {
        if (pieces[i].classList.contains("clicked")) {
            return pieces[i];
        }
    }
    return null;
}

async function move(from, to) {
    let data = {
        from: from,
        to: to
    }
    const response = await fetch('/move', {
        method: 'post',
        body: JSON.stringify(data),
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(res => {
        return res.json();
    });

    if (response.code === "200") {
        changeImage(from, to);
        await changeTurn();
        return;
    }
    if (response.code === "300") {
        changeImage(from, to);
        const currentTurn = document.querySelector('.turn');
        currentTurn.textContent = response.turn;
        alert(response.message + "가 승리했습니다!");
        return;
    }
    if (response.code === "400") {
        alert(response.message);
        return;
    }
}

function changeImage(sourcePosition, targetPosition) {
    const source = document.getElementById(sourcePosition);
    const target = document.getElementById(targetPosition);
    const piece = source.getElementsByTagName("img")[0];
    if (target.getElementsByTagName("img")[0]) {
        target.getElementsByTagName("img")[0].remove();
    }
    target.appendChild(piece);
}

async function changeTurn() {
    const turn = await fetch('/currentTurn', {
        method: 'post',
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(res => {
        return res.json();
    });
    const currentTurn = document.querySelector('.turn');
    currentTurn.textContent = turn;
}

function clickStart() {
    fetch('/restart', {
        method: 'post',
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(function () {
        syncBoard();
        changeTurn();
    });
}

async function syncBoard() {
    const board = await fetch('/currentBoard', {
        method: 'post',
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(res => {
        return res.json();
    });

    const positions = Object.keys(board);
    const pieces = Object.values(board);
    for (let i = 0; i < positions.length; i++) {
        const position = document.getElementById(positions[i]);
        if (position.getElementsByTagName("img")[0]) {
            position.getElementsByTagName("img")[0].remove();
        }
        if (pieces[i] === ".") {
            continue;
        }
        const piece = document.createElement("img");

        piece.src = "images/" + pieces[i] + ".png";
        position.appendChild(piece);
    }
}
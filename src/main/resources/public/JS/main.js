createChessBoard();
const startButton = document.getElementById("start");
startButton.addEventListener("click", clickStart);
function createChessBoard() {
    const pieces = [
        ["BR", "BN", "BB", "BQ", "BK", "BB", "BN", "BR"],
        new Array(8).fill("BP"),
        new Array(8).fill("."),
        new Array(8).fill("."),
        new Array(8).fill("."),
        new Array(8).fill("."),
        new Array(8).fill("WP"),
        ["WR", "WN", "WB", "WQ", "WK", "WB", "WN", "WR"]
    ]

    makeTable(pieces);
    addEvent();
}

function makeTable(pieces) {
    const table = document.getElementById("chess-board");
    for (let i = 0; i < 8; i++) {
        const newTr = document.createElement("tr");
        for (let j = 0; j < 8; j++) {
            const newTd = document.createElement("td");

            const row = String(8 - i);
            const column = String.fromCharCode('a'.charCodeAt(0) + j);
            newTd.id = column + row;
            let pieceName = pieces[i][j];
            if (pieceName !== ".") {
                const piece = document.createElement("img");
                piece.src = "images/" + pieceName + ".png";
                piece.id = newTd.id;
                newTd.appendChild(piece);
            }
            if ((i % 2 === 0 && j % 2 === 0) || (i % 2 !== 0 && j % 2 !== 0)) {
                newTd.className = "whiteTile";
            }
            else {
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
    if(clickedPiece) {
        clickedPiece.classList.remove("clickedTile");
        clickedPiece.classList.toggle("clicked");
        if(clickedPiece !== clickPiece) {
            move(clickedPiece.id, clickPiece.id);
        }
    }else {
        clickPiece.classList.toggle("clicked");
        clickPiece.classList.add("clickedTile");
    }
}

function getClickedPiece() {
    const pieces = document.getElementsByTagName("td");
    for (let i = 0; i < pieces.length; i++) {
        if(pieces[i].classList.contains("clicked")) {
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
    }).then(res => res.json());

    if(response.code === "200") {
        changeImage(from, to);
        changeTurn();
    }
    else if(response.code === "300") {
        changeImage(from, to);
        changeTurn();
        alert(response.message + "가 승리했습니다!");
    }
    else if(response.code === "400") {
        alert(response.message);
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

function changeTurn() {
    const currentTurn = document.querySelector('.turn');
    if(currentTurn.textContent === "White Turn") {
        currentTurn.textContent = "Black Turn";
    }
    else {
        currentTurn.textContent = "White Turn";
    }
}

function clickStart() {
    console.log("1");
}
let $chessBoard = document.querySelector(".chessBoard");
const startButton = document.querySelector(".start");
const statusButton = document.querySelector(".status");
const endButton = document.querySelector(".end");
let gameFinished = false;

initChessBoard();

$chessBoard.addEventListener("click", clickPosition);
startButton.addEventListener("click", start);
statusButton.addEventListener("click", status);
endButton.addEventListener("click", end);
//ondragstart, onmousemove

function initChessBoard() {
    for (let i = 0; i < 8; i++) {
        let chessBoardRow = document.createElement("div");
        chessBoardRow.setAttribute("class", "chessRow");

        for (let j = 0; j < 8; j++) {
            let chessBoardColumn = document.createElement("div");
            chessBoardColumn.setAttribute("class", "chessColumn");

            chessBoardColumn.style = initChessBoardColor(i, j);
            chessBoardColumn.id = initPiecePosition(i, j);

            let pieceImg = document.createElement("img");

            chessBoardColumn.appendChild(pieceImg);
            chessBoardRow.appendChild(chessBoardColumn);
        }
        $chessBoard.appendChild(chessBoardRow);
    }
}

function initChessBoardColor(row, column) {
    return (row + column) % 2
        ? "background-color: #ffc0cb"
        : "background-color: #FCF6F5";
}

function initPiecePosition(row, column) {
    const rowTable = {
        0: "8",
        1: "7",
        2: "6",
        3: "5",
        4: "4",
        5: "3",
        6: "2",
        7: "1",
    };
    const columnTable = {
        0: "0",
        1: "1",
        2: "2",
        3: "3",
        4: "4",
        5: "5",
        6: "6",
        7: "7",
    };
    return columnTable[column]+rowTable[row];
}

function start() {
    fetch("/start", {
        method: 'GET',
        header: {
            'Content-Type': 'application/json'
        }
    }).then(function () {
        alert("chess game을 시작하겠습니다!");
        gameFinished = false;
        const turnMessage = document.querySelector(".currentTurn");
        turnMessage.textContent = " Current Turn :";
        removeChessImage()
        syncBoard();
        changeTurn();
    });
}

function initChessImg() {
    for (let i = 0; i < 8; i++) {
        for (let j = 0; j < 8; j++) {
            let cell = document.getElementById(String(initPiecePosition(i, j)));
            if (cell.firstChild) {
                cell.firstChild.remove();
            }
        }
    }
    syncBoard();
}

async function syncBoard() {
    const board = await fetch("/board", {
        method: 'GET',
        header: {
            'Content-Type': 'application/json'
        }
    }).then(res => {
        return res.json();
    });
    const positions = Object.keys(board);
    const pieces = Object.values(board);

    const cells = document.querySelectorAll(".chessColumn");
    for (let i = 0; i < cells.length; i++) {
        if (cells[i].getElementsByTagName("img")[0]) {
            cells[i].getElementsByTagName("img")[0].remove();
        }
    }

    for (let i = 0; i < positions.length; i++) {
        const position = document.getElementById(positions[i]);
        const piece = document.createElement("img");

        if (pieces[i] === ".") {
            continue;
        }

        piece.src = "/image/" + pieces[i] + ".png";
        position.appendChild(piece);
    }
}

async function changeTurn() {
    const turn = await fetch("/turn", {
        method: 'GET',
        header: {
            'Content-Type': 'application/json'
        }
    }).then(res => {
        return res.json();
    });
    const turnMessage = document.querySelector(".turn");
    turnMessage.textContent = turn;
}

function clickPosition(event) {
    if (gameFinished) {
        return;
    }
    const positions = document.querySelectorAll(".chessColumn");
    for (let i = 0; i < positions.length; i++) {
        if (positions[i].classList.contains("clicked")) {
            positions[i].classList.remove("clicked");
            move(positions[i].id, event.target.closest("div").id);
            return;
        }
    }
    if (event.target.closest("img") === null) {
        return;
    }
    event.target.closest(".chessColumn").classList.toggle("clicked");
}

async function move(from, to) {
    const data = {
        from: from,
        to: to
    };
    fetch("/move", {
        method: 'POST',
        header: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }).then(res => {
        return res.json();
    }).then(obj => {
        if (obj.code === "400") {
            alert(obj.message);
            return;
        }
        if (obj.code === "300") {
            changeImg(from, to);
            gameFinished = true;
            document.querySelector(".currentTurn").textContent = " Winner :";
            alert(obj.turn + " 승리!");
            return;
        }
        if (obj.code === "200") {
            changeImg(from, to);
            changeTurn();
        }
    });
}


function changeImg(fromPosition, toPosition) {
    const from = document.getElementById(fromPosition);
    const to = document.getElementById(toPosition);
    const piece = from.getElementsByTagName("img")[0];//형태는 리스트인데 하나만 있음
    if (to.getElementsByTagName("img")[0]) {
        to.getElementsByTagName("img")[0].remove();
    }
    to.appendChild(piece);
}

function status() {
    fetch("/status", {
        method: 'GET',
        header: {
            'Content-Type': 'application/json'
        }
    }).then(res => {
        return res.json();
    }).then(obj => {
        alert("white의 점수는 " + obj.whiteScore + "입니다.\n"
            + "black의 점수는 " + obj.blackScore + "입니다.\n"
            + "white는 " + obj.whiteResult + "했습니다.\n"
            + "black는 " + obj.blackResult + "했습니다.\n");
    });
}

function end() {
    fetch("/end", {
        method: 'GET',
        header: {
            'Content-Type': 'application/json'
        }
    }).then(function (){
        alert("chess game이 종료되었습니다.\n다시 시작하려면 start를 눌러주세요!");
        gameFinished = true;
        removeChessImage();
    })
}

function removeChessImage() {
    for (let i = 0; i < 8; i++) {
        for (let j = 0; j < 8; j++) {
            let cell = document.getElementById(String(initPiecePosition(i, j)));
            if (cell.firstChild) {
                cell.firstChild.remove();
            }
        }
    }
}

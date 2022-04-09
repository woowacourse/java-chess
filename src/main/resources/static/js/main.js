let $chessBoard = document.querySelector(".chessBoard");
const statusButton = document.querySelector(".status");
const endButton = document.querySelector(".end");
let gameFinished = false;
const gameId = window.location.pathname.split("/")[2];
console.log(URL);

$chessBoard.addEventListener("click", clickPosition);
statusButton.addEventListener("click", status);
endButton.addEventListener("click", end);

window.onload = async function initChessBoard() {
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
    await syncBoard();
    await changeTurn();
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
        0: "1",
        1: "2",
        2: "3",
        3: "4",
        4: "5",
        5: "6",
        6: "7",
        7: "8",
    };
    return columnTable[column] + rowTable[row];
}

async function syncBoard() {
    console.log("syncBoard 시작");
    console.log(gameId);
    const data = {
        gameId: gameId
    };
    await fetch("/board", {
        method: 'POST',
        header: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }).then(res => res.json())
        .then(res => {
            const positions = Object.keys(res);
            const pieces = Object.values(res);
            if (positions.length!==64){
                alert("잘못된 게임이 생성됐습니다!\n처음 화면으로 돌아갑니다.")
                window.location.replace("http://localhost:8080/");
            }
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
        )
}

async function changeTurn() {
    const data = {
        gameId: gameId
    };
    const turn = await fetch("/turn", {
        method: 'POST',
        header: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }).then(res => {
        return res.json();
    });
    console.log(turn);
    if (turn === ""){
        alert("잘못된 게임이 생성됐습니다!\n처음 화면으로 돌아갑니다.")
        window.location.replace("http://localhost:8080/");
    }
    const turnMessage = document.querySelector(".turn");
    turnMessage.textContent = turn;
}

async function clickPosition(event) {
    if (gameFinished) {
        return;
    }
    const positions = document.querySelectorAll(".chessColumn");
    for (let i = 0; i < positions.length; i++) {
        if (positions[i].classList.contains("clicked")) {
            positions[i].classList.remove("clicked");
            await move(positions[i].id, event.target.closest("div").id);
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
        gameId: gameId,
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
            removeChessImage();
            return;
        }
        if (obj.code === "200") {
            changeImg(from, to);
            changeTurn();
        }
    });
}


async function changeImg(fromPosition, toPosition) {
    const from = document.getElementById(fromPosition);
    const to = document.getElementById(toPosition);
    const piece = from.getElementsByTagName("img")[0];//형태는 리스트인데 하나만 있음
    if (to.getElementsByTagName("img")[0]) {
        to.getElementsByTagName("img")[0].remove();
    }
    to.appendChild(piece);
}

function status() {
    const data = {
        gameId: gameId
    };
    fetch("/status", {
        method: 'POST',
        header: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
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
    if (gameFinished===true){
        alert("chess game이 이미 종료되었습니다!");
        return;
    }
    const data = {
        gameId: gameId
    };
    fetch("/end", {
        method: 'POST',
        header: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }).then(res => {
        return res.json();
    }).then(function () {
        alert("chess game이 종료되었습니다.");
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

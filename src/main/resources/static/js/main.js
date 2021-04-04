let $chessBoard = document.querySelector(".chessBoard");
const button = document.querySelector("button");
let gameFinished = false;

createChessBoard();

$chessBoard.addEventListener("click", clickPosition);
button.addEventListener("click", restart);

function createChessBoard() {
    initChessBoard();
    syncBoard();
    changeTurn();
}

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

function initChessImg() {
    for (let i = 0; i < 8; i++) {
        for (let j = 0; j < 8; j++) {
            let cell = document.getElementById(String(initPiecePosition(i, j)));
            if (cell.firstChild) {
                cell.firstChild.remove();
            }
            // if (pieceImg !== null) {
            //     let imgSrc = "../img/" + String(initPieceImage(i, j)) + ".png";
            //     console.log(imgSrc);
            //     pieceImg.src = imgSrc;
            // }
        }
    }
    syncBoard();
}

function initChessBoardColor(row, column) {
    return (row + column) % 2
        ? "background-color: #7b9acc"
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
            0: "a",
            1: "b",
            2: "c",
            3: "d",
            4: "e",
            5: "f",
            6: "g",
            7: "h",
    };
    return columnTable[column]+rowTable[row];
}

// function initPieceImage(row, column) {
//     const blackSymbol = "B";
//     const whiteSymbol = "W";
//     const generalColumn = {
//         0: "R",
//         1: "K",
//         2: "B",
//         3: "Q",
//         4: "K",
//         5: "B",
//         6: "K",
//         7: "R",
//     };
//     const pawnSymbol = "P";
//
//     if (row === 0) {
//         return blackSymbol + generalColumn[column];
//     }
//
//     if (row === 1) {
//         return blackSymbol + pawnSymbol;
//     }
//     if (row === 6) {
//         return whiteSymbol + pawnSymbol;
//     }
//
//     if (row === 7) {
//         return whiteSymbol + generalColumn[column];
//     }
//     return "";
// }

function clickPosition(event) {
    if (gameFinished) {
        return;
    }
    const positions = document.querySelectorAll(".chessColumn");
    for (let i = 0; i < positions.length; i++) {
        if (positions[i].classList.contains("clicked")) {
            console.log(positions[i].id + ", " + event.target.closest("div").id);
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
            document.querySelector(".currentTurn").textContent = " 🎉 Winner :";
            alert(obj.turn + " 승리!");
            return;
        }
        if (obj.code === "200") {
            changeImg(from, to);
            console.log(obj);
            changeTurn();
        }
    });
}

function changeImg(fromPosition, toPosition) {
    const from = document.getElementById(fromPosition);
    const to = document.getElementById(toPosition);
    const piece = from.getElementsByTagName("img")[0];
    if (to.getElementsByTagName("img")[0]) {
        to.getElementsByTagName("img")[0].remove();
    }
    to.appendChild(piece);
}

async function changeTurn() {
    const turn = await fetch("/turn", {
        method: 'POST',
        header: {
            'Content-Type': 'application/json'
        }
    }).then(res => {
        return res.json();
    });
    console.log(turn);
    const turnMessage = document.querySelector(".turn");
    turnMessage.textContent = turn;
}

function restart() {
    fetch("/restart", {
        method: 'POST',
        header: {
            'Content-Type': 'application/json'
        }
    }).then(function () {
        gameFinished = false;
        const turnMessage = document.querySelector(".currentTurn");
        turnMessage.textContent = " 💙 Current Turn :";
        initChessImg();
        changeTurn();
    });
}

async function syncBoard() {
    const board = await fetch("/board", {
        method: 'POST',
        header: {
            'Content-Type': 'application/json'
        }
    }).then(res => {
        return res.json();
    });
    console.log(board);
    const positions = Object.keys(board);
    const pieces = Object.values(board);

    // for (let i = 0; i < positions.length; i++) {
    //     console.log(pieces[i])
    // }

    const cells = document.querySelectorAll(".chessColumn");
    for (let i = 0; i < cells.length; i++) {
        if (cells[i].getElementsByTagName("img")[0]) {
            cells[i].getElementsByTagName("img")[0].remove();
        }
    }

    for (let i = 0; i < positions.length; i++) {
        const position = document.getElementById(positions[i]);
        const piece = document.createElement("img");

        piece.src = "img/" + pieces[i] + ".png";
        position.appendChild(piece);
    }
}

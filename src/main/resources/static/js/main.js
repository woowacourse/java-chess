const BRook = "../img/BR.png";
const BKnight = "../img/BN.png";
const BBishop = "../img/BB.png";
const BKing = "../img/BK.png";
const BQueen = "../img/BQ.png";
const BPawn = "../img/BP.png";
const WPawn = "../img/WP.png";
const WRook = "../img/WR.png";
const WKnight = "../img/WN.png";
const WBishop = "../img/WB.png";
const WKing = "../img/WK.png";
const WQueen = "../img/WQ.png";

let $chessBoard = document.querySelector(".chessBoard");

initChessBoard();
$chessBoard.addEventListener("click", clickPosition);

function initChessBoard() {
    const turnText = document.createElement("h2");
    turnText.innerHTML = "현재 턴 : <span id='user-turn'> WHITE</span>";

    for (let i = 0; i < 8; i++) {
        let chessBoardRow = document.createElement("div");
        chessBoardRow.setAttribute("class", "chessRow");

        for (let j = 0; j < 8; j++) {
            let chessBoardColumn = document.createElement("div");
            chessBoardColumn.setAttribute("class", "chessColumn");

            chessBoardColumn.style = initChessBoardColor(i, j);

            let pieceImg = document.createElement("img");
            let positionPiece = initPieceImage(i, j);

            if (positionPiece === "") {
                pieceImg.style = "display: none";
            }
            pieceImg.src = positionPiece;

            chessBoardColumn.id = initPiecePosition(i, j);
            chessBoardColumn.appendChild(pieceImg);
            chessBoardRow.appendChild(chessBoardColumn);
        }
        $chessBoard.appendChild(chessBoardRow);
        $chessBoard.appendChild(turnText);
    }
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

function initPieceImage(row, column) {
    if (row === 0) {
        const blackColumn = {
            0: BRook,
            1: BKnight,
            2: BBishop,
            3: BKing,
            4: BQueen,
            5: BBishop,
            6: BKnight,
            7: BRook,
        };
        return blackColumn[column];
    }

    if (row === 1) {
        return BPawn;
    }
    if (row === 6) {
        return WPawn;
    }

    if (row === 7) {
        const whiteColumn = {
            0: WRook,
            1: WKnight,
            2: WBishop,
            3: WKing,
            4: WQueen,
            5: WBishop,
            6: WKnight,
            7: WRook,
        };
        return whiteColumn[column];
    }
    return "";
}

function clickPosition(event) {
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

function move(from, to) {
    const data = {
        from: from,
        to: to
    };

    console.log(JSON.stringify(data))
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
        changeImg(from, to);
        changeTurnText();
        if (obj.code === "300") {
            alert(obj.turn + " 승리!");
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

function changeTurnText() {
    if (document.getElementById("user-turn").innerText === 'WHITE') {
        document.getElementById("user-turn").innerText = 'BLACK';
        return;
    }
    document.getElementById("user-turn").innerText = 'WHITE';
}
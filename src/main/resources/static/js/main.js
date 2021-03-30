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
    const fromPosition = event.target.closest("img");
    fromPosition.classList.toggle("clicked");
}
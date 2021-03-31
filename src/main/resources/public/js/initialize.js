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

export let chessBoard = document.getElementById('chessBoard');

initChessBoard();

function initChessBoard() {
    for (let i = 0; i < 8; i++) {
        let chessBoardRow = document.createElement('div');
        chessBoardRow.setAttribute("class", "chessRow");
        for (let j = 0; j < 8; j++) {
            let chessBoardColumn = document.createElement('div');
            chessBoardColumn.setAttribute("class", "chessColumn");
            let boardInitial = getBoardInitial(i, j);
            chessBoardColumn.setAttribute("id", boardInitial);
            chessBoardColumn.style = initChessBoardColor(i, j);

            let pieceImg = document.createElement('img');
            let positionPiece = initPieceImage(i, j);
            if (positionPiece === "") {
                pieceImg.style = "display: none";
            }
            pieceImg.src = positionPiece;

            chessBoardColumn.appendChild(pieceImg);
            chessBoardRow.appendChild(chessBoardColumn);
        }
        chessBoard.appendChild(chessBoardRow);
    }
}

function getBoardInitial(row, column) {
    const rowArray = ["8", "7", "6", "5", "4", "3", "2", "1"];
    const columnArray = ["a", "b", "c", "d", "e", "f", "g", "h"];
    return columnArray[column] + rowArray[row];
}

function initChessBoardColor(row, column) {
    if ((row + column) % 2 === 0) {
        return "background-color: #522632";
    }
    return "background-color: #F3E4DF";
}

function initPieceImage(row, column) {
    if (row === 0) {
        if (column === 0 || column === 7) {
            return BRook;
        }
        if (column === 1 || column === 6) {
            return BKnight;
        }
        if (column === 2 || column === 5) {
            return BBishop;
        }
        if (column === 4) {
            return BKing;
        }
        return BQueen;
    }
    if (row === 1) {
        return BPawn;
    }
    if (row === 6) {
        return WPawn;
    }
    if (row === 7) {
        if (column === 0 || column === 7) {
            return WRook;
        }
        if (column === 1 || column === 6) {
            return WKnight;
        }
        if (column === 2 || column === 5) {
            return WBishop;
        }
        if (column === 4) {
            return WKing;
        }
        return WQueen;
    }
    return "";
}

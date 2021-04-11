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
export let gameResultWindow = document.getElementById("gameResult");
export const whiteTeamCurrentTurn = document.getElementById("whiteTeamCurrentTurn");
export const blackTeamCurrentTurn = document.getElementById("blackTeamCurrentTurn");

import {updateScoreUI} from "./movement.js";

const rowArray = ["8", "7", "6", "5", "4", "3", "2", "1"];
const columnArray = ["a", "b", "c", "d", "e", "f", "g", "h"];

export function initChessBoard(data) {
    for (let i = 0; i < 8; i++) {
        let chessBoardRow = document.createElement('div');
        chessBoardRow.setAttribute("class", "chessRow");
        for (let j = 0; j < 8; j++) {
            let chessBoardColumn = document.createElement('div');
            chessBoardColumn.setAttribute("class", "chessColumn");
            let boardInitial = getBoardInitial(i, j);
            chessBoardColumn.setAttribute("id", boardInitial);
            chessBoardColumn.style.backgroundColor = initChessBoardColor(i, j);

            let pieceImg = document.createElement('img');
            pieceImg.src = "";
            pieceImg.style.display = "none";
            chessBoardColumn.appendChild(pieceImg);

            chessBoardRow.appendChild(chessBoardColumn);
        }
        chessBoard.appendChild(chessBoardRow);
    }
    drawPieceImage(data);
    initCurrentTurn(data);
}

function getBoardInitial(row, column) {
    return columnArray[column] + rowArray[row];
}

export function drawPieceImage(data) {
    emptyBoardImage();
    appendWhitePieceImage(data.piecePositionByTeam.white);
    appendBlackPieceImage(data.piecePositionByTeam.black);
    updateScoreUI(data.teamScore.white, data.teamScore.black);
}

function emptyBoardImage() {
    for (let i = 0; i < 8; i++) {
        for (let j = 0; j < 8; j++) {
            let boardInitial = getBoardInitial(i, j);
            let boardPosition = document.getElementById(boardInitial);
            boardPosition.firstChild.src = "";
            boardPosition.firstChild.style.display = "none";
        }
    }
}

function appendWhitePieceImage(whitePosition) {
    let whitePositionMap = new Map(Object.entries(whitePosition));
    let whitePositionArray = Array.from(whitePositionMap.keys());
    for (let i = 0; i < whitePositionArray.length; i++) {
        let boardPosition = document.getElementById(String(whitePositionArray[i]));
        boardPosition.firstChild.src = whitePieceImageSource(whitePositionMap.get(whitePositionArray[i]));
        boardPosition.firstChild.style.display = "block";
    }
}

function whitePieceImageSource(piece) {
    if (piece === "Rook") {
        return WRook;
    }
    if (piece === "Bishop") {
        return WBishop;
    }
    if (piece === "King") {
        return WKing;
    }
    if (piece === "Knight") {
        return WKnight;
    }
    if (piece === "Queen") {
        return WQueen;
    }
    return WPawn;
}

function appendBlackPieceImage(blackPosition) {
    let blackPositionMap = new Map(Object.entries(blackPosition));
    let blackPositionArray = Array.from(blackPositionMap.keys());
    for (let i = 0; i < blackPositionArray.length; i++) {
        let boardPosition = document.getElementById(String(blackPositionArray[i]));
        boardPosition.firstChild.src = blackPieceImageSource(blackPositionMap.get(blackPositionArray[i]));
        boardPosition.firstChild.style.display = "block";
    }
}

function blackPieceImageSource(piece) {
    if (piece === "Rook") {
        return BRook;
    }
    if (piece === "Bishop") {
        return BBishop;
    }
    if (piece === "King") {
        return BKing;
    }
    if (piece === "Knight") {
        return BKnight;
    }
    if (piece === "Queen") {
        return BQueen;
    }
    return BPawn;
}

function initChessBoardColor(row, column) {
    if ((row + column) % 2 === 0) {
        return "#522632";
    }
    return "#F3E4DF";
}

function initCurrentTurn(data) {
    if (data.currentTurnTeam === "white") {
        whiteTeamCurrentTurn.innerText = "Current Turn";
        blackTeamCurrentTurn.innerText = "";
    } else {
        whiteTeamCurrentTurn.innerText = "";
        blackTeamCurrentTurn.innerText = "Current Turn";
    }
}
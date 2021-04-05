const FILES = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'];
const WHITE_SQUARE = "white-square";
const BLACK_SQUARE = "black-square";

document.addEventListener("DOMContentLoaded", buildBoard);

const PATCH = {
    method: 'PATCH',
    headers: {
        'Content-Type': 'application/json'
    },
    "body": "",
}

const DELETE = {
    method: 'DELETE',
}

const CHESS_DTO = {
    "turn": '',
    "boardDTO": ''
}

async function buildBoard() {
    let $board = document.getElementById("board");
    if ($board == null) {
        document.querySelector("body").insertAdjacentHTML("afterbegin", build());
        $board = document.getElementById("board");
    }
    $board.addEventListener("click", onMove);
    $board.addEventListener("mouseover", onMouseOverSquare);
    $board.addEventListener("mouseout", onRevertSquareColor);
    color = await getColor();
    borderTurn(color);
}

async function chessBoard(chessId) {
    const response = await fetch('/chess/' + chessId + '/pieces');
    const data = await response.json();
    return JSON.parse(data.content);
}

function getCookie(name) {
    return document.cookie.split("; ").find(row => row.startsWith(name)).split("=")[1];
}

async function chessTurn(chessId) {
    const response = await fetch('/chess/' + chessId + '/turn');
    const data = await response.json();
    return data.content;
}

async function patchMovePiece(chessId, source, target) {
    const moveResult = await fetch('/chess/' + chessId + '/pieces/move?source=' + source + '&target=' + target, PATCH);
    if (moveResult.ok) {
        await patchChangeTurn(chessId);
        movePiece(source, target);
        borderTurn();
        return await moveResult.json();
    }

    alert("해당 위치로 이동할 수 없습니다.");
    return "잘못된 위치입니다.";
}

async function getColor() {
    const chessId = getCookie("chessId");
    const response = await fetch('/chess/' + chessId + '/turn');
    const data = await response.json();
    return data.content;
}

let color;

function borderTurn() {
    console.log(color);
    if (color === "BLACK") {
        document.getElementById("black-versus").classList.add("currentTurn");
        document.getElementById("white-versus").classList.remove("currentTurn");
        color = "WHITE";
    } else {
        document.getElementById("black-versus").classList.remove("currentTurn");
        document.getElementById("white-versus").classList.add("currentTurn");
        color = "BLACK";
    }
}

async function patchChangeTurn(chessId) {
    const response = await fetch('/chess/' + chessId + '/turn', PATCH);
    return await response.json();
}

async function deleteEndChess(chessId) {
    await fetch('/chess/' + chessId + '/pieces', DELETE);
    await fetch('/chess/' + chessId, DELETE);
}

async function onMove(event) {
    const $position = document.getElementsByClassName("selected");
    if (!$position.length) {
        onChangeColorOfSquare(event);
        return;
    }

    if (event.target.closest("div").classList.contains("selected")) {
        event.target.closest("div").classList.remove("selected");
        return;
    }

    const source = $position[0].id;
    const target = event.target.closest("div").id;

    const chessId = getCookie('chessId');
    const turn = await chessTurn(chessId);
    const data = await chessBoard(chessId);
    CHESS_DTO.turn = turn;
    CHESS_DTO.boardDTO = data;
    PATCH["body"] = JSON.stringify(CHESS_DTO);

    const moveResult = await patchMovePiece(chessId, source, target);
    if (moveResult.content === "왕이 죽었습니다.") {
        alert("왕이 죽었습니다. 게임을 종료합니다.")
        await deleteEndChess(chessId);
        window.location.href = "/";
        return;
    }

    revertSquareColor($position);
}

function movePiece(source, target) {
    const $sourcePosition = document.getElementById(source);
    const $targetPosition = document.getElementById(target);

    $targetPosition.innerHTML = $sourcePosition.innerHTML;
    $sourcePosition.innerHTML = "";
}

function onChangeColorOfSquare(event) {
    let squareClassList = event.target.closest("div").classList;
    if (squareClassList.contains("selected")) {
        squareClassList.remove("selected");
    } else {
        squareClassList.add("selected");
    }
}

function revertSquareColor($position) {
    for (const $positionElement of $position) {
        $positionElement.classList.remove("selected");
    }
}

function onMouseOverSquare(event) {
    event.target.closest("div").classList.add("over-square");
}

function onRevertSquareColor(event) {
    event.target.closest("div").classList.remove("over-square");
}

function build() {
    let html = '<div id="board">';
    for (let rank = 8; rank >= 1; rank--) {
        html += addSquaresAtRank(rank);
    }
    html += "</div>";
    return html;
}

function addSquaresAtRank(rank) {
    let cellHtmlOfRank = '';
    for (let fileIndex = 0; fileIndex < 8; fileIndex++) {
        cellHtmlOfRank += addSquare(rank, fileIndex);
    }
    return cellHtmlOfRank;
}

function addSquare(rank, fileIndex) {
    let color = getSquareColor(rank, fileIndex);
    return `<div class=${color} id=${FILES[fileIndex] + rank}></div>`;
}

function getSquareColor(rank, fileIndex) {
    return isWhite(rank, fileIndex) ? WHITE_SQUARE : BLACK_SQUARE;
}

function isWhite(rank, fileIndex) {
    if ((rank % 2 === 0) && (fileIndex % 2 === 0)) {
        return true;
    }

    return (rank % 2 === 1) && (fileIndex % 2 === 1);
}


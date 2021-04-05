const FILES = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'];
const WHITE_SQUARE = "white-square";
const BLACK_SQUARE = "black-square";

document.addEventListener("DOMContentLoaded", buildBoard);

const OPTION = {
    method: 'POST',
    headers: {
        'Content-Type': 'application/json'
    },
    "body": "",
}

const CHESS_DTO = {
    "turn": '',
    "boardDTO": ''
}

function buildBoard() {
    let $board = document.getElementById("board");
    if ($board == null) {
        document.querySelector("body").insertAdjacentHTML("afterbegin", build());
        $board = document.getElementById("board");
    }
    $board.addEventListener("click", onMove);
    $board.addEventListener("mouseover", onMouseOverSquare);
    $board.addEventListener("mouseout", onRevertSquareColor);
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
    OPTION["body"] = JSON.stringify(CHESS_DTO);

    fetch('/chess/' + chessId + '/pieces/move?source=' + source + '&target=' + target, OPTION)
        .then(res => {
            if (!res.ok) {
                throw new Error(res.status);
            }
            return res.json();
        })
        .then(data => {
            movePiece(source, target);
            if (data.content === "왕이 죽었습니다.") {
                alert("왕이 죽었습니다. 게임을 종료합니다.")
                window.location.replace(data.url);
                return data;
            }
            return data;
        })
        .catch(() => alert("해당 위치로 이동할 수 없습니다."));
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


const FILES = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'];
const WHITE_SQUARE = "white-square";
const BLACK_SQUARE = "black-square";

document.addEventListener("DOMContentLoaded", buildBoard);

function buildBoard() {
    let $board = document.getElementById("board");
    if ($board == null) {
        document.querySelector("body").insertAdjacentHTML("afterbegin", build());
        $board = document.getElementById("board");
    }
    $board.addEventListener("click", onSelectSquareColor);
    $board.addEventListener("mouseover", onMouseOverSquare);
    $board.addEventListener("mouseout", onRevertSquareColor);
}

function onSelectSquareColor(event) {
    let squareClassList = event.target.closest("div").classList;
    if (squareClassList.contains("selected")) {
        squareClassList.remove("selected");
    } else {
        squareClassList.add("selected");
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


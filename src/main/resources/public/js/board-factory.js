const FILES = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'];
const WHITE_SQUARE = "white-square";
const BLACK_SQUARE = "black-square";

document.addEventListener("DOMContentLoaded", buildBoard);

const movePosition = {
    "source": "",
    "target": "",
}

const option = {
    method: 'POST',
    headers: {
        'Content-Type': 'application/json'
    },
    "body": "",
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

function onMove(event) {
    const $position = document.getElementsByClassName("selected");
    if (!$position.length) {
        onChangeColorOfSquare(event);
    } else {
        const source = $position[0].id;
        const target = event.target.closest("div").id;
        movePosition["source"] = source;
        movePosition["target"] = target;

        option["body"] = JSON.stringify(movePosition);
        fetch('/rooms/' +  0 + '/move', option)
            .then(res => {
                if (!res.ok) {
                    throw new Error(res.status);
                }
                return movePiece(source, target);
            })
            .catch(() => alert("해당 위치로 이동할 수 없습니다."));
        revertSquareColor($position);
    }
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


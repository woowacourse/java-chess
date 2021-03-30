console.log("js 로드 성공")

let movePoint = {
    sourcePoint: null,
    targetPoint: null
};

const $board = window.document.querySelector(".board");
$board.addEventListener('click', clicked)

const template = (i) =>
    ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'].map((value, j) =>
        `<button id="${value}${8 - i}" class="item ${(i % 2 === j % 2) ? "bright" : "dark"}"></button>`).join('')

$board.innerHTML = Array(8).fill().map((_, i) => template(i)).join('')

const $squares = $board.children

function move(movePoint) {
    return fetch("/move", {
        method: 'POST',
        body: JSON.stringify(movePoint)
    }).then(res => res.json())
}

async function updatePoint(id) {
    if (movePoint.sourcePoint === null && movePoint.targetPoint == null) {
        movePoint.sourcePoint = id;
    } else if (movePoint.sourcePoint !== null && movePoint.targetPoint === null) {
        movePoint.targetPoint = id;
        const response = await move(movePoint);
        if (response === 200) {
            updateBoard(movePoint);
            movePoint.sourcePoint = null;
            movePoint.targetPoint = null;
            alert('성공')
        } else {
            movePoint.sourcePoint = null;
            movePoint.targetPoint = null;
            alert('실패')
        }
    }
}

function updateBoard(movePoint) {
    document.getElementById(movePoint.targetPoint).innerHTML = document.getElementById(movePoint.sourcePoint).innerHTML;
    document.getElementById(movePoint.sourcePoint).innerHTML = "";
}

async function clicked(event) {
    await updatePoint(event.target.closest("button").id)
}

async function renderImage(point) {
    const piece = await getPiece(point)
    if (piece.name.name !== ".") {
        document.getElementById(point).innerHTML = `<img src= "./images/${piece.color}_${piece.name.name}.svg"/>`
    }
}

function getInitialBoard() {
    Array.from($squares).forEach(square => renderImage(square.id))
}

function getPiece(point) {
    return fetch("/point", {
        method: 'POST',
        body: point
    }).then(res => res.json())
}

getInitialBoard();


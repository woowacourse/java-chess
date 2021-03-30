console.log("js 로드 성공")

const $board = window.document.querySelector(".board");
$board.addEventListener('click', clicked)

const $squares = $board.children

function clicked(event) {
    console.log(event.target.closest("button"))
}

async function renderImage(point) {
    let piece = await getPiece(point)
    document.getElementById(point).innerHTML = `<img src= "./images/${piece.color}_${piece.name.name}.svg"/>`
}

function getInitialBoard() {
    // const promises = Array.from($squares).map(square => getPiece(square.id))
    // const pieces = await Promise.all(promises);
    // console.log(pieces);
    Array.from($squares).forEach(square => renderImage(square.id))
    // const pieces = await Promise.all(promises);
    // console.log(pieces);
}

function getPiece(point) {
    let url = "/point";
    return fetch(url, {
        method: 'POST',
        body: point
    }).then(res => res.json())
}

getInitialBoard();


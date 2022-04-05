const $board = document.getElementById('board');
$board.addEventListener('click', onClickSquare)

let firstId = null;

function onClickSquare(e) {
    if (firstId === null) {
        firstId = e.target.id;
        return;
    }

    location.href = "/chess/move?source=" + firstId + "&target=" + e.target.id;
}

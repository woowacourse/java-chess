const $board = document.getElementById('board');
$board.addEventListener('click', onClickSquare)

let sourceId = null;

let spiltedPath = location.pathname.split('/');
let gameId = spiltedPath[3];

function onClickSquare(e) {
    if (sourceId === null) {
        sourceId = e.target.id;
        return;
    }

    location.href = "/chess/game/" + gameId + "/move?target=" + sourceId
            + '&source=' + e.target.id;
}

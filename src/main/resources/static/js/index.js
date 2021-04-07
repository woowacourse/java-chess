const $board = document.querySelector('#board');
const $gameId = document.querySelector('#gameId');
const $currentTurn = document.querySelector('#currentTurn');

const $gameSetResult = document.querySelector('#game-result');
const $winner = document.querySelector('#winner');
const $blackScore = document.querySelector('#black-score');
const $whiteScore = document.querySelector('#white-score');

$board.addEventListener('click', onClickSquare)

let sourceId = null;

function gameId() {
    return $gameId.value
}

function onClickSquare(e) {
    if (sourceId === null) {
        sourceId = e.target.id;
        return;
    }
    move(sourceId, e.target.id)
}

function move(source, target) {
    $.ajax({
        type: "POST",
        // url: "/chess/game/" + gameId() + "/move",
        url: "/chess/game/" + "999" + "/move",
        data: {
            "source": source,
            "target": target
        },
        dataType: "json",
        success: update,
        error: alertError,
        complete: initSource
    })
}

function initBoard() {
    $.ajax({
        type: "GET",
        url: "/chess/game/" + gameId() + "/board",
        dataType: "json",
        success: update,
        error: alertError,
        complete: initSource
    })
}

function update(response) {
    $board.innerHTML = response.board;
    $currentTurn.innerHTML = response.currentTurn;

    if (response.isGameSet) {
        gameSet(response)
    }
}

function gameSet(response) {
    $winner.innerHTML = response.winner
    $blackScore.innerHTML = response.blackScore
    $whiteScore.innerHTML = response.whiteScore

    $gameSetResult.classList.toggle("none")
    $board.removeEventListener('click', onClickSquare)
}

function alertError(response) {
    alert(response.responseText);
}

function initSource() {
    sourceId = null;
}

initBoard();
const $board = document.querySelector('#board');
const $gameId = document.querySelector('#gameId');
const $currentTurn = document.querySelector('#currentTurn');

const $gameSetResult = document.querySelector('#game-result');
const $winner = document.querySelector('#winner');
const $blackScore = document.querySelector('#black-score');
const $whiteScore = document.querySelector('#white-score');

$board.addEventListener('click', onClickSquare)

let sourceId;
let turn;

function gameId() {
    return $gameId.value
}

function onClickSquare(e) {
    if (sourceId === null) {
        sourceId = e.target.id;
        return;
    }
    move(sourceId, e.target.id, turn)
}

function move(source, target, turn) {
    $.ajax({
        type: "POST",
        url: "/chess/game/" + gameId() + "/move",
        data: {
            "source": source,
            "target": target,
            "turn": turn
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
    turn = response.currentTurn;

    if (response.isGameSet) {
        showResult(response.gameResult)
    }
}

function showResult(result) {
    $winner.innerHTML = result.winner
    $blackScore.innerHTML = result.blackScore
    $whiteScore.innerHTML = result.whiteScore

    $gameSetResult.classList.remove("none")
    $board.removeEventListener('click', onClickSquare)
}

function alertError(response) {
    alert(response.responseText);
}

function initSource() {
    sourceId = null;
}

initBoard();
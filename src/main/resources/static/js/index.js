const $board = document.querySelector('#board');
const $gameId = document.querySelector('#gameId');
const $currentTurn = document.querySelector('#currentTurn');

const $gameSetResult = document.querySelector('#game-result');
const $winner = document.querySelector('#winner');
const $currentBlackScore = document.querySelector('#currentBlackScore');
const $currentWhiteScore = document.querySelector('#currentWhiteScore');

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

function move(source, target, team) {
    $.ajax({
        type: "POST",
        url: "/chess/game/" + gameId() + "/move",
        data: {
            "source": source,
            "target": target,
            "team": team
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
    $currentBlackScore.innerHTML = response.currentBlackScore;
    $currentWhiteScore.innerHTML = response.currentWhiteScore;

    if (response.isGameSet) {
        showResult(response.gameResult)
    }
}

function showResult(result) {
    $winner.innerHTML = result.winner

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

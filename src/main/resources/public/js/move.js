let source = null;
let target = null;

const squares = document.getElementsByClassName("square");
for (let i = 0; i < squares.length; i++) {
    squares.item(i).addEventListener("click", function () {
        mark(this);
        canMove(this);
    });
}

function gameId() {
    let element = document.getElementById("gameId");
    if (element == null)
        return null;
    return element.innerText;
}

function move(source, target) {
    $.ajax({
        type: "POST",
        url: '/play/move',
        data: {
            "source": source.id,
            "target": target.id,
            "gameId": gameId(),
        },
        dataType: "json",
        success: update,
        error: showError,
        complete: initialize,
    })
}

function update(response) {
    const board = response.squares;
    const turn = response.turn;
    const scores = response.scores;

    for (let i = 0; i < board.length; i++) {
        let pieceId = board[i].position.file + board[i].position.rank;
        let piece = document.getElementById(pieceId);

        if (board[i].piece) {
            let pieceImage = board[i].piece.name + "_" + board[i].piece.team.toLowerCase();
            piece.firstElementChild.src = "/images/" + pieceImage + ".png";
        } else {
            piece.firstElementChild.src = "/images/blank.png";
        }
    }

    const nowTurn = document.getElementById("turn");
    nowTurn.innerText = turn + "팀 차례입니다.";

    let message = "";

    function getInnerText(team, scores) {
        return team + " 점수 | " + scores.score;
    }

    for (let i = 0; i < scores.length; i++) {
        const team = scores[i].team.toLowerCase();
        const score = document.getElementById(team);
        score.innerText = getInnerText(team, scores[i]);
        message += getInnerText(team, scores[i]) + "\n";
    }

    const winner = response.winner;
    if (winner != null) {
        message += winner + "팀이 이겼습니다.🤭";
        alert(message);
        window.location = "http://localhost:4567/play";
    }
}

function showError(response) {
    alert(response.responseText);
}

function initialize() {
    initializeBoxShadow(source);
    initializeBoxShadow(target);
    source = null;
    target = null;
}

function initializeBoxShadow(location) {
    location.style.boxShadow = "";
}

function canMove(clickedLocation) {
    if (source === null) {
        source = clickedLocation;
        return;
    }
    if (target === null) {
        target = clickedLocation;
        move(source, target);
    }
}

function mark(clickedLocation) {
    if (clickedLocation.style.boxShadow) {
        clickedLocation.style.boxShadow = "";
    } else {
        clickedLocation.style.boxShadow = "inset 0px 0px 10px 3px #ffff60";
    }
}

function endMessage() {
    if (gameId() != null) {
        alert(`플레이를 저장했습니다!`);
        return true;
    }
    return confirmEnd();
}

function confirmEnd() {
    return confirm("게임 시작 시 이름을 입력하지 않으셨으므로, 종료 시 이어하기가 불가합니다.😱\n그래도 종료하시겠습니까?");
}
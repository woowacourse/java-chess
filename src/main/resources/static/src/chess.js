window.onload = function () {
    getChess();
};

function getChess() {
    $.ajax({
        url: "/chess",
        type: 'get',
        success(data) {
            let obj = JSON.parse(data);
            setButton(obj.status);
            setPieces(obj.pieces);
        }
    });
}

function setButton(status) {
    if (status === "playing") {
        document.getElementById("board_div").className = "selectable";
        document.getElementById("status_btn").hidden = false;
        document.getElementById("end_btn").hidden = false;
        document.getElementById("restart_btn").hidden = true;
        document.getElementById("start_btn").hidden = true;
        return;
    }
    document.getElementById("board_div").className = "non_selectable";
    document.getElementById("status_btn").hidden = true;
    document.getElementById("end_btn").hidden = true;
    document.getElementById("restart_btn").hidden = true;
    document.getElementById("start_btn").hidden = false;
}

function setPieces(pieces) {
    $.each(pieces, function (idx, piece) {
        setPiece(piece.position, piece.color, piece.type);
    });
}

function start() {
    $.ajax({
        url: "/start",
        type: 'get',
        success(data) {
            let obj = JSON.parse(data);
            setButton(obj.status);
            setPieces(obj.pieces);
        }
    });
}

function restart() {
    $.ajax({
        url: "/restart",
        type: 'get',
        success(data) {
            document.getElementById("board_div").className = "selectable";
            document.getElementById("status_btn").hidden = false;
            document.getElementById("end_btn").hidden = false;

            let pieces = JSON.parse(data);
            $.each(pieces, function (idx, piece) {
                setPiece(piece.position, piece.color, piece.type);
            });
        }
    });
}

function setPiece(position, color, type) {
    document.getElementById(position).innerHTML = '<img src=/images/' + color + '_' + type + '.png' + ' class=piece alt="">';
}

let positions = []

function move(position) {
    positions.push(position);
    if (positions.length < 2) {
        return;
    }

    let sourcePosition = positions[0];
    let targetPosition = positions[1];
    $.ajax({
        url: "/move",
        type: 'post',
        traditional: true,
        data: {
            source: sourcePosition,
            target: targetPosition
        },
        success() {
            document.getElementById(targetPosition).innerHTML = document.getElementById(sourcePosition).innerHTML;
            document.getElementById(sourcePosition).innerHTML = "";
        },
        error(request) {
            alert(request.responseText);
        }
    });
    positions = []
}

function showScore() {
    $.ajax({
        url: "/score",
        type: 'get',
        success(data) {
            let score = JSON.parse(data);
            var message = "";
            $.each(score.scores, function (idx, score) {
                message += score.name + " : " + score.score + "점\n";
            });
            if (score.winnerName === "") {
                message += "동점입니다.";
            } else {
                message += score.winnerName + " 진영이 이기고 있습니다.";
            }
            alert(message);
        }
    });
}

function end() {
    $.ajax({
        url: "/end",
        type: 'post',
        success(data) {
            document.getElementById("board_div").className = "non_selectable";
            document.getElementById("status_btn").hidden = true;
            document.getElementById("end_btn").hidden = true;
            document.getElementById("restart_btn").hidden = false;

            let status = JSON.parse(data);
            var message = "♟ 게임 결과 ♟\n";
            $.each(status.scores, function (idx, score) {
                message += score.name + " : " + score.score + "점\n";
            });
            if (status.winnerName === "") {
                message += "동점입니다.";
            } else {
                message += status.winnerName + " 진영이 이겼습니다.";
            }
            alert(message);
        }
    });
}
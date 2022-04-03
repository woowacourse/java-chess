var finished = false;
var source = undefined;
var destination = undefined;

function gameStart() {
    $.ajax({
        type: 'GET',
        url: '/initialize'
    }).done(function() {
        initializePosition();
        finished = false;
        location.reload();
    }).fail(function(error) {
        alert(JSON.stringify(error));
    });
}

function clickPiece(e) {
    if (finished) {
        alert('게임이 종료되었습니다! 게임을 다시 시작해주세요.');
        return;
    }
    if (source == undefined) {
        source = e;
        return;
    }
    if (source != undefined) {
        destination = e;
        sendMoveCommand();
    }
}

function sendMoveCommand() {
    var data = {
        source: source,
        destination: destination
    };

    $.ajax({
        type: 'POST',
        url: '/move',
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        data: JSON.stringify(data)
    }).done(function(res) {
        initializePosition();
        if (res.success) {
            move(res);
            return;
        }
        printError(res.error);
    }).fail(function(error) {
        initializePosition();
        alert(JSON.stringify(error));
    });
}

function printStatus() {
    $.ajax({
        type: 'GET',
        url: '/status',
        contentType: 'application/json; charset=utf-8',
        dataType: 'json'
    }).done(function(res) {
        var output = '블랙팀의 점수 : ' + res.response.blackScore + '\n' + '화이트팀의 점수 : ' + res.response.whiteScore;
        alert(output);
    }).fail(function(error) {
        alert(JSON.stringify(error));
    });
}

function initializePosition() {
    source = undefined;
    destination = undefined;
}

function move(res) {
    movePiece(res.response.source, res.response.destination);
    addMovingHistory(res.response.source, res.response.destination);
    if (res.response.finished) {
        finished = true;
        var message = '왕이 잡혔습니다. 게임을 종료합니다.';
        alert(message);
        addSomethingInElement('running-state', message);
    }
}

function printError(error) {
    alert(error);
}

function movePiece(source, destination) {
    replaceElement(destination, document.getElementById(source).innerHTML);
    removeElement(source);
}

function addMovingHistory(source, destination) {
    var elementId = 'history';
    var output = '시작점 : ' + source + ', 도착점 : ' + destination + '<br>';
    addSomethingInElement(elementId, output);
}

function addSomethingInElement(elementId, text) {
    var element = document.getElementById(elementId);
    element.innerHTML += text;
}

function replaceElement(elementId, replaceText) {
    var element = document.getElementById(elementId);
    element.innerHTML = replaceText;
}

function removeElement(elementId) {
    var element = document.getElementById(elementId);
    element.innerHTML = '';
}

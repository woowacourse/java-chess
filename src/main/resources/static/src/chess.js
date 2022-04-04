var finished = false;
var source = undefined;
var destination = undefined;

function gameStart() {
    fetch('/initialize'
    ).then((response) => {
        initializePosition();
        finished = false;
        location.reload();
    }).catch((error) => {
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

    fetch('/move', {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(data)}
    ).then(response => response.json()
    ).then((data) => {
        initializePosition();
        if (data.success) {
            move(data);
            return;
        }
        printError(data.error);
    }).catch((error) => {
        initializePosition();
        alert(JSON.stringify(error));
    });
}

function printStatus() {
    fetch('/status', {
        headers: {
            "Content-Type": "application/json",
        }}
    ).then(response => response.json()
    ).then((data) => {
        var output = '블랙팀의 점수 : ' + data.response.blackScore + '\n' + '화이트팀의 점수 : ' + data.response.whiteScore;
        alert(output);
    }).catch((error) => {
        alert(JSON.stringify(error));
    });
}

function initializePosition() {
    source = undefined;
    destination = undefined;
}

function move(data) {
    movePiece(data.response.source, data.response.destination);
    addMovingHistory(data.response.source, data.response.destination);
    if (data.response.finished) {
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

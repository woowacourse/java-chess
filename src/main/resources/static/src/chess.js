var source = undefined;
var destination = undefined;

function clickPiece(e) {
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
        movePiece(res.source, res.destination);
        addMovingHistory(res.source, res.destination);
        initializePosition();
    }).fail(function(error) {
        initializePosition();
        alert(JSON.stringify(error));
    });
}

function initializePosition() {
    source = undefined;
    destination = undefined;
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

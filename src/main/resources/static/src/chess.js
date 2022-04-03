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
        source = undefined;
        destination = undefined;
    }).fail(function(error) {
        source = undefined;
        destination = undefined;
        alert(JSON.stringify(error));
    });
}

function movePiece(source, destination) {
    var sourceDiv = document.getElementById(source);
    var destinationDiv = document.getElementById(destination);

    destinationDiv.innerHTML = sourceDiv.innerHTML;
    sourceDiv.innerHTML = '';
}

function addMovingHistory(source, destination) {
    var historyDiv = document.getElementById('history');
    var output = '시작점 : ' + source + ', 도착점 : ' + destination;
    historyDiv.innerHTML += output + '<br>';
}

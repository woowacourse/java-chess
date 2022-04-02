var source = undefined;
var destination = undefined;
var turn = 'WHITE';

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
        data: JSON.stringify(data)
    }).done(function() {
        location.reload(true);
        source = undefined;
        destination = undefined;
        if (turn == 'WHITE') {
            turn = 'BLACK';
            return;
        }
        turn = 'WHITE';
    }).fail(function(error) {
        location.reload(true);
        source = undefined;
        destination = undefined;
        alert(JSON.stringify(error));
    });
}

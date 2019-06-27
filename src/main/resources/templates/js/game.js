window.onload = function () {
    var chessBoardBox = document.querySelector('#chessBoardBox');

    for (var y = 7; y >= 0; y--) {
        for (var x = 0; x <= 7; x++) {
            chessBoardBox.append(chessBoardRender(x, y));
        }
        chessBoardBox.append(document.createElement("BR"));
    }

    var chessBoard = document.querySelector('#chessBoard').value;
    var pieces = JSON.parse(chessBoard).units;

    for (var key in pieces) {
        var classText = pieces[key].team.toLowerCase() + "_" + pieces[key].name.toLowerCase();
        document.querySelector('#position' + key).classList.add(classText);
    }
}

function chessBoardRender(x, y) {
    var div = document.createElement('DIV');

    div.id = 'position' + x + '' + y;
    div.className = 'tile';

    return div;
}
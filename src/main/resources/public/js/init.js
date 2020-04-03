$(document).ready(function () {
    boardCreate();
});

function boardCreate(callbak) {
    for (var i = 1; i <= 8; i++) {
        var row = $.parseHTML(`<div class="row"></div>`);
        for (var j = 0; j < 8; j++) {
            var colSymbol = String.fromCharCode(97 + j);
            var rowSymbol = 9 - i;

            if (j % 2 === i % 2) {
                $(row).append(
                    `<div class="col-1 border test" id="${colSymbol +
                    rowSymbol}"></div>`
                );
            } else {
                $(row).append(
                    `<div class="col-1 border test black" id=${colSymbol +
                    rowSymbol}></div>`
                );
            }
        }
        $("#table").append(row);
    }

    readChessBoard(() => {
        var dict = {};
        dict['b'] = 'bishop_white';
        dict['B'] = 'bishop_black';

        dict['k'] = 'king_white';
        dict['K'] = 'king_black';

        dict['n'] = 'knight_white';
        dict['N'] = 'knight_black';

        dict['p'] = 'pawn_white';
        dict['P'] = 'pawn_black';

        dict['q'] = 'queen_white';
        dict['Q'] = 'queen_black';

        dict['r'] = 'rook_white';
        dict['R'] = 'rook_black';


        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                //document.getElementById("print").innerHTML = this.responseText;
                var response = JSON.parse(this.responseText);
                addPieces(() => {
                    let gameManager = response
                    for (let location in gameManager["chessBoard"]["board"]) {
                        // console.log(location);
                        // console.log(gameManager["chessBoard"]["board"][location]);
                        console.log(location);
                        let fileName = dict[gameManager["chessBoard"]["board"][location]["name"]];
                        $('#' + location).append(`<img src="../img/${fileName}.png" alt="테스트">`);
                    }
                    gameManager["gameState"];
                });
            }
        };
        xhttp.open("GET", "http://localhost:8080/board", false);
        xhttp.setRequestHeader("X-Custom-Header", "testvalue");
        xhttp.send();
    });
}

function readChessBoard(cb) {
    cb();
}


function addPieces(cb) {
    cb()
}
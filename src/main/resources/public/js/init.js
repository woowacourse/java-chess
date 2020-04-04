$(document).ready(function () {
    run = async () => {
        await boardCreate();
        let response = await start();
        await addPieces(response);
    }
    run();

});

function enterKey() {
    if (window.event.keyCode === 13) {
        let move_action = async () => {
            let response = await move();
            if (response["error"]) {
                alert(response["error"]);
                return;
            }
            await addPieces(response);
        }

        move_action();
    }
}

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


function boardCreate() {
    return new Promise(((resolve, reject) => {
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
        return resolve();
    }));
}

function start() {
    return new Promise(((resolve, reject) => {
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                return resolve(JSON.parse(this.responseText));
            }
        };
        xhttp.open("GET", "http://localhost:8080/board", false);
        xhttp.setRequestHeader("X-Custom-Header", "testvalue");
        xhttp.send();
    }))
}


function addPieces(response) {
    return new Promise(((resolve, reject) => {
        let gameManager = response
        for (let location in gameManager["chessBoard"]["board"]) {
            // console.log(location);
            // console.log(gameManager["chessBoard"]["board"][location]);
            console.log(location);
            let fileName = dict[gameManager["chessBoard"]["board"][location]["name"]];
            $('#' + location).append(`<img src="../img/${fileName}.png" alt="테스트">`);
        }
    }))
}

function move() {
    return new Promise(((resolve, reject) => {
        var queryString = $("form[name=move-form]").serialize();
        console.log(queryString);
        $.ajax({
            type: 'get',
            url: '/move',
            data: queryString,
            dataType: 'json',
            error: function (xhr, status, error) {
                alert(error);
            },
            success: function (json) {
                return resolve(json);
            }
        });
    }))
}

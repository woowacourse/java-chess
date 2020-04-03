var start;
var end;

var main = {
    variables: {
        pieces: {
            k: {
                img: '&#9812;'
            },
            q: {
                img: '&#9813;'
            },
            b: {
                img: '&#9815;'
            },
            n: {
                img: '&#9816;'
            },
            r: {
                img: '&#9814;'
            },
            p: {
                img: '&#9817;'
            },
            K: {
                img: '&#9818;'
            },
            Q: {
                img: '&#9819;'
            },
            B: {
                img: '&#9821;'
            },
            N: {
                img: '&#9822;'
            },
            R: {
                img: '&#9820;'
            },
            P: {
                img: '&#9823;'
            }
        }
    }
}

function getChessBoard() {
    $.ajax({
        url: "/start/board",
        type: "get",
        success: function (data) {
            var jsonData = JSON.parse(data);
            for (var i = 0; i < jsonData.boardValue.length; i++) {
                var piece = jsonData.boardValue[i];
                $('#' + piece.location).html(main.variables.pieces[piece.pieceName].img);
                $('#' + piece.location).attr('chess', piece.pieceName);
            }
        },
        error: function (errorThrown) {
            alert(errorThrown);
        },
    });
}

function convertPiece(pieceName) {
    for (let piecesKey in main.variables.pieces) {
        if (piecesKey == pieceName) {
            return piecesKey;
        }
    }
    return null;
}


function allowDrop(ev) {
    // 고유의 동작 제거
    ev.preventDefault();
}

function drag(ev) {
    ev.dataTransfer.setData("text", ev.target.id);
}

function drop(ev) {
    ev.preventDefault();
    var now = ev.dataTransfer.getData("text");
    var des = ev.target.id;
    // console.log(now + " to " + des.toString());
    postChessBoard({"now": now.toString(), "des": des.toString()});
}

function postChessBoard(json) {
    $.ajax({
        type: 'post',
        url: '/start/move',
        data: json,
        dataType: 'text',
        error: function (xhr, status, error) {
            alert(error.toString());
        },
        success: function (data) {
            if (data == "CONTINUE") {
                var nowImg = $('#' + json.now).html();
                $('#' + json.des).html(nowImg);
                $('#' + json.des).attr('chess', $('#' + json.now).chess);
                $('#' + json.now).html('');
                $('#' + json.now).attr('chess', 'null');
            }
            if (data == "ERROR") {
                alert("움직일 수 없는 경우입니다.");
            }
            if (data == "END") {
                getChessBoardResult();
            }
        }
    });
}

function getChessBoardResult() {
    $.ajax({
        url: "/start/winner",
        type: "get",
        success: function (data) {
            alert("승자는" + data.name + "입니다.")
        },
        error: function (errorThrown) {
            alert(errorThrown);
        },
    });
}

$(document).ready(function () {
    $('.gamecell').attr({
        "draggable": "true",
        "ondragstart": "drag(event)",
        "ondrop": "drop(event)",
        "ondragover": "allowDrop(event)"
    });
});


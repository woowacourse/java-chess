$(document).ready(function () {
    // 보드판 초기화
    const rows = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'];
    const columns = [1, 2, 3, 4, 5, 6, 7, 8];

    let board = document.createElement("div");
    board.id = "board-container";
    for (let i in columns.reverse()) {
        let column = columns[i];
        let div = document.createElement("div");
        div.className = "row";
        for (let j in rows) {
            let row = rows[j];
            let square = document.createElement("div");
            square.className = "square";
            square.id = row + column;
            div.appendChild(square);
        }
        board.appendChild(div);
    }

    $("#wrap").append(board);

    // piece 이미지 초기화
    $.ajax({
        type: 'get',
        url: '/loadGame',
        dataType: 'json',
        error: function (res) {
            console.dir(res);
            swal(res.responseText);
        },
        success: printChessBoardAndStatus
    });

    // drag and drop event 추가
    $('.square').on("dragstart", function (event) {
        event.originalEvent.dataTransfer.setData("from", event.currentTarget.id);
    });

    $('.square').on("dragover", function (event) {
        event.preventDefault();
    }).on("drop", function (event) {
        event.preventDefault();
        let from = event.originalEvent.dataTransfer.getData("from");
        let to = event.currentTarget.id;
        $.ajax({
            type: 'get',
            url: '/move',
            dataType:  'json',
            data: "from=" + from + "&to=" + to,
            error: function (res) {
                swal(res.responseText);
            },
            success: printChessBoardAndStatus
        });

    });
});

// 체스 기물 초기화
function printChessBoardAndStatus(response) {
    let isGameFinished = response["result"]["isGameFinished"];
    console.log("isGameFinished: " + isGameFinished);
    if (isGameFinished) {
        location.replace("/end.html");
    }

    let chessBoardDto = response["chessBoardDto"];
    $(".piece").remove();

    chessBoardDto['tiles'].forEach(function (item, index, array) {
        var piece = document.createElement("img");
        piece.src = "./img/piece/" + item.piece + ".png";
        piece.id = item.piece;
        piece.className = "piece";
        $("#" + item.position).append(piece);
    });

    let scores = response["result"]["scores"];
    console.log("Score");
    console.dir(response["result"]);
    scores.forEach(function (item, index, array) {
        if (item.player == "WHITE") {
            $("#white-score").text(item.score);
        }
        if (item.player == "BLACK") {
            $("#black-score").text(item.score);
        }
    });

    let turn = response["turn"];
    $("#turn").text("turn: " + turn);
    if (turn == "BALCK") {
        $("#turn").text("turn: " + turn).css("color", "black");
    } else if (turn == "WHITE") {
        $("#turn").text("turn: " + turn).css("color", "white");
    }
}
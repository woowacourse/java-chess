$(document).ready(function () {
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
            swal(res.responseText);
        },
        success: printChessBoardAndStatus
    });

    // drag and drop event 추가
    $('.square').on("dragstart", function (event) {
        console.log("drag start");
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
            dataType: 'json',
            data: "from=" + from + "&to=" + to,
            error: function (res) {
                swal(res.responseText);
            },
            success: printChessBoardAndStatus
        });

    });
});

function printChessBoardAndStatus(response) {
    console.log(response);
    let chessBoardDto = response["chessBoardDto"];
    $(".piece").remove();

    chessBoardDto['tiles'].forEach(function (item, index, array) {
        var piece = document.createElement("img");
        piece.src = "./img/piece/" + item.pieceDto.name + "_" + item.pieceDto.player + ".png";
        piece.id = item.piece;
        piece.className = "piece";
        $("#" + item.position).append(piece);

        if (response.isKing === true) {
            swal(response.turn + "가 이겼습니다.");
        }
    });

    let scores = response["result"]["statuses"];
    scores.forEach(function (item, index, array) {
        if (item.player == "WHITE") {
            $("#white-score").text(item.score);
        }
        if (item.player == "BLACK") {
            $("#black-score").text(item.score);
        }
    });

    let turn = response["turn"];

    if (turn == "BLACK") {
        $("#turn").text("turn: " + turn).css("color", "black");
    } else if (turn == "WHITE") {
        $("#turn").text("turn: " + turn).css("color", "white");
    }
}
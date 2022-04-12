$(document).ready(function () {
    // 보드판 초기화
    const files = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'];
    const ranks = [1, 2, 3, 4, 5, 6, 7, 8];

    let board = document.createElement("div");
    board.id = "board-container";
    for (let j in ranks.reverse()) {
        let rank = ranks[j];
        let div = document.createElement("div");
        div.className = "file";
        for (let i in files) {
            let file = files[i];
            let square = document.createElement("div");
            square.className = "square";
            square.id = file + rank;
            div.appendChild(square);
        }
        board.appendChild(div);
    }

    $("#wrap").append(board);

    // piece 이미지 초기화
    $.ajax({
        type: 'get',
        url: '/load',
        dataType: 'json',
        error: function (res) {
            console.dir(res);
            alert(res.responseText);
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
            dataType: 'json',
            data: "from=" + from + "&to=" + to,
            error: function (res) {
                alert(res.responseText);
            },
            success: printChessBoardAndStatus
        });
    });
});

// 체스 기물 초기화
function printChessBoardAndStatus(response) {
    let isGameFinished = response["result"]["isGameFinished"];
    if (isGameFinished) {
        location.replace("/end.html");
    }

    let chessBoardDto = response["boardDto"];
    $(".piece").remove();

    chessBoardDto['grids'].forEach(function (item) {
        var piece = document.createElement("img");
        var pieceName = item.pieceType + item.color;
        piece.src = "../img/piece/" + pieceName + ".png";
        piece.id = pieceName;
        piece.className = "piece";
        $("#" + item.position).append(piece);
    });

    let scores = response["result"]["scores"];
    scores.forEach(function (item) {
        if (item.color == "WHITE") {
            $("#white-score").text(item.score + " pt");
        }
        if (item.color == "BLACK") {
            $("#black-score").text(item.score + " pt");
        }
    });

    let turn = response["turn"];
    $("#turn").text(turn + "  Turn");

}
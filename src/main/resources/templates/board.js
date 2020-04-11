var click_flag = 0;
var source = "";
var target = "";
var team = "";
var way = "";
var roomId = "";
var end_flag = false;
var timeOutTerm = 1000;
var playerNumber = 0;

document.addEventListener("DOMContentLoaded", function () {

    $(document).ready(function () {
        roomId = location.href.substr(
            location.href.lastIndexOf('=') + 1
        );
    });

    setInterval(function () {
        console.log(roomId)
        if (!end_flag) {
            $.ajax({
                type: "GET",
                url: "/room/status?roomId=" + roomId,
                dataType: 'text',// xml, json, script, html
                success: function (data) {
                    jsonData = JSON.parse(data);
                    if (jsonData.isEnd) {
                        end_flag = true
                    }
                    playerNumber = 0
                    console.log(jsonData)
                    if (jsonData.blackUserId !== -1) {
                        console.log(jsonData.blackUserId)
                        playerNumber++
                    }
                    if (jsonData.whiteUserId !== -1) {
                        console.log(jsonData.whiteUserId)
                        playerNumber++
                    }

                    if (team === "" && playerNumber === 1) {
                        team = "WHITE"
                    }
                    if (team === "" && playerNumber === 2) {
                        team = "BLACK"
                    }
                },
                error: function (e) {
                    console.log(e.message);
                }
            });
            if (playerNumber === 2) {
                $.ajax({
                    type: "GET",
                    url: "/chess/renew?roomId=" + roomId,
                    dataType: 'text',// xml, json, script, html
                    success: function (data) {
                        console.log(data)
                        drawBoard(data)
                    },
                    error: function (e) {
                        console.log(e.message);
                    }
                });
            } else {
                printDelay()
            }
        }
    }, timeOutTerm);

    $('.btn-quit').click(function () {
        $.ajax({
            type: "POST",
            url: "/room/quit",
            async: true,
            data: {
                roomId: roomId
            },
            success: function (data) {
                console.log(data);
                window.location.href = "index.html"
            },
            error: function (e) {
                console.log(e.message);
            }
        });
    });


    $('.square').click(function () {
        if (click_flag === 0) {
            source = $(this).attr('id');
            $.ajax({
                url: '/chess/way?roomId=' + roomId + '&team=' + team + '&coordinate=' + source,
                async: true,
                type: 'GET',
                dataType: 'text',// xml, json, script, html

                success: function (data) {
                    console.log(data)
                    drawWay(data)
                    click_flag = 1;
                },// 요청 완료 시
                error: function (e) {
                    console.log("false");
                    console.log(e.message);
                },// 요청 실패.
                complete: function (jqXHR) {
                    console.log("finish");
                }// 요청의 실패, 성공과 상관 없이 완료 될 경우 호출
            });
        } else {
            target = $(this).attr('id');
            click_flag = 0;
            removeWay();
            $.ajax({
                url: '/chess/move',
                async: true,
                type: 'POST',
                data: {
                    roomId: roomId,
                    source: source,
                    target: target
                },
                dataType: 'text',// xml, json, script, html

                success: function (data) {
                    if (data === "win") {
                        printWinner()
                        end_flag = true
                    }
                    console.log("success");
                },// 요청 완료 시
                error: function (e) {
                    console.log("false");
                    console.log(e.message);
                },// 요청 실패.
                complete: function (jqXHR) {
                    console.log("finish");
                }// 요청의 실패, 성공과 상관 없이 완료 될 경우 호출
            });
        }
    });
});

function drawBoard(data) {
    let jsonData = JSON.parse(data)
    let board = jsonData.board
    let blackScore = jsonData.blackScore
    let whiteScore = jsonData.whiteScore

    jQuery.each(board, function (key, value) {
        $(`#${key}`).html(value)
    })

    $(`#${"roomId"}`).html(roomId)
    $(`#${"blackScore"}`).html(blackScore)
    $(`#${"whiteScore"}`).html(whiteScore)

}

function drawWay(data) {
    let jsonData = JSON.parse(data);
    way = jsonData;
    console.log(jsonData)
    jQuery.each(jsonData, function (key, value) {
        $(`#${value}`).css("background-color", "red")
    })
}

function removeWay() {
    jQuery.each(way, function (key, value) {
        if ((value.charCodeAt(0) + value.charCodeAt(1)) % 2 === 0) {
            $(`#${value}`).css("background-color", "#3d3d3d")
        } else {
            $(`#${value}`).css("background-color", "#d3d3d3")
        }
    })
}

function printWinner() {
    $(`#${"a4"}`).html("Y")
    $(`#${"b4"}`).html("O")
    $(`#${"c4"}`).html("U")
    $(`#${"d4"}`).html(" ")
    $(`#${"e4"}`).html("W")
    $(`#${"f4"}`).html("I")
    $(`#${"g4"}`).html("N")
    $(`#${"h4"}`).html("!")
}

function printLoser() {
    $(`#${"a4"}`).html("Y")
    $(`#${"b4"}`).html("O")
    $(`#${"c4"}`).html("U")
    $(`#${"d4"}`).html(" ")
    $(`#${"e4"}`).html("L")
    $(`#${"f4"}`).html("O")
    $(`#${"g4"}`).html("S")
    $(`#${"h4"}`).html("E")
}

function printDelay() {
    $(`#${"a4"}`).html("연")
    $(`#${"b4"}`).html("결")
    $(`#${"c4"}`).html("대")
    $(`#${"d4"}`).html("기")
    $(`#${"e4"}`).html("중")
    $(`#${"f4"}`).html(".")
    $(`#${"g4"}`).html(".")
    $(`#${"h4"}`).html(".")
}

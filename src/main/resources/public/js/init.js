let now = null;


$(document).ready(function () {
    run = async () => {
        await boardCreate();
        let response = await resume();
        await addPieces(response);
    };
    run();
});

function start_button() {
    run = async () => {
        let response = await start();
        await deletePieces();
        await addPieces(response);
    };
    run();
};

function move(now, dest) {
    let move_action = async () => {
        let response = await moveRequest(now, dest);
        if (response["error"]) {
            alert(response["error"]);
            return;
        }
        console.log(response);
        await deletePieces();
        await addPieces(response);
    };
    move_action();
}

function deletePieces() {
    return new Promise((resolve, reject) => {
        $(".test").each((index, item) => {
            $(item).removeClass("p P q Q n N r R k K b B");
        });
        return resolve();
    });
}

function boardCreate() {
    return new Promise((resolve, reject) => {
        for (var i = 1; i <= 8; i++) {
            var row = $.parseHTML(`<div class="row test-row"></div>`);
            for (var j = 0; j < 8; j++) {
                var colSymbol = String.fromCharCode(97 + j);
                var rowSymbol = 9 - i;

                if (j % 2 === i % 2) {
                    $(row).append(
                        `<div onclick="divClick(this)" class="border test" id="${colSymbol +
                        rowSymbol}"></div>`
                    );
                } else {
                    $(row).append(
                        `<div onclick="divClick(this)" class="border test black" id=${colSymbol +
                        rowSymbol}></div>`
                    );
                }
            }
            $("#table").append(row);
        }
        return resolve();
    });
}

function start() {
    return new Promise((resolve, reject) => {
        let roomID = $("#roomID").val();
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                console.log("start 함수")
                console.log(JSON.parse(this.responseText))
                return resolve(JSON.parse(this.responseText));
            }
        };
        xhttp.open("GET", "http://localhost:8080/start/" + roomID, false);
        xhttp.setRequestHeader("X-Custom-Header", "testvalue");
        xhttp.send();
    });
}

function resume() {
    return new Promise((resolve, reject) => {
        let roomID = $("#roomID").val();
        console.log(roomID);
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                return resolve(JSON.parse(this.responseText));
            }
        };
        xhttp.open("GET", "http://localhost:8080/resume/" + roomID, false);
        xhttp.setRequestHeader("X-Custom-Header", "testvalue");
        xhttp.send();
    });
}

function addPieces(response) {
    return new Promise((resolve, reject) => {
        for (let location in response["chessBoard"]["board"]) {
            let fileName = response["chessBoard"]["board"][location]["name"];
            $("#" + location).addClass(fileName);
        }
        if (response["gameState"] === "RUNNING_WHITE_TURN") {
            $("#turn").removeClass("black-color");
            $("#turn").addClass("white-color");
        } else if (response["gameState"] === "RUNNING_BLACK_TURN") {
            $("#turn").removeClass("white-color");
            $("#turn").addClass("black-color");
        } else {
            $("#turn").remove();
            let roomID = $("#roomID").val();
            $.ajax({
                type: "get",
                url: "/winner/" + roomID,
                dataType: "json",
                error: function (xhr, status, error) {
                    return reject();
                },
                success: function (json) {
                    $("#guide").text("게임 종료! " + json["winner"] + " 승리!");
                }
            });
        }
        return resolve();
    });
}

function moveRequest(now, destination) {
    return new Promise((resolve, reject) => {
        let roomID = $("#roomID").val();
        console.log("테스트")
        console.log(now);
        console.log(destination);
        var queryString = `now=${now}&destination=${destination}`;
        $.ajax({
            type: "post",
            url: "/move/" + roomID,
            data: queryString,
            dataType: "json",
            error: function (xhr, status, error) {
                alert(error);
                return resolve();
            },
            success: function (json) {
                return resolve(json);
            }
        });
    });
}

function isNotPieces(div) {
    let arr = ["b", "B", "k", "K", "n", "N", "p", "P", "q", "Q", "r", "R"];
    let flag = true;
    arr.forEach(item => {
        let hasClass = $(div).hasClass(item);
        if (hasClass === true) {
            flag = false;
        }
    });
    return flag;
}

function divClick(div) {
    if (now == null && isNotPieces(div)) {
        return;
    }

    if (now == null) {
        now = div.id;
        $(div).removeClass("border");
        $(div).addClass("border-3");
    } else {
        $("#" + now).removeClass("border-3");
        $(div).addClass("border");
        let nowCopy = now;
        now = null;
        move(nowCopy, div.id);
    }
}

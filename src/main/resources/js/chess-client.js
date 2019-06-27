const chessStart = () => {
    $.ajax({
        type: "POST",
        url: "/api/init",
        success: function(response) {
            if (response.queryStatus === "ok") {
                board = Chessboard("myBoard", makeConfig(response.board));
            };
        }
    });
}

const chessInit = () => {
    $.ajax({
        type: "GET",
        url: "/api/board",
        success: function(response) {
            if (response.queryStatus === "ok") {
                board = Chessboard("myBoard", makeConfig(response.board));
            }
        }
    });
};

const pieceMove = (source, target) => {
    const move = {source, target};
    return $.ajax({
        type: "POST",
        url: "/api/pieceMove",
        data: JSON.stringify(move),
        dataType: "json",
        async: false
    }).responseJSON;
};

const onDrop = (source, target) => {
    const result = pieceMove(source, target);
    if (result.queryStatus === "ok") {
        isGameover(result);
        return;
    };
    return "snapback";
};

const getScores = () => {
    $.ajax({
        type: "GET",
        url: "/api/score",
        success: function(response) {
            if (response.queryStatus === "ok") {
                const message = "백: " + response.score.w + "점\n흑: " + response.score.b + "점";
                alert(message);
            }
        }
    });
};

const isGameover = (result) => {
    if (result.gameover) {
        alert("게임 종료");
        getScores();
    }
}

const configTemplate = {
    draggable: true,
    position: "start",
    onDrop: onDrop
};

const makeConfig = position => {
    config = configTemplate;
    config.position = position;
    return config;
}

let board = Chessboard("myBoard");
chessInit();

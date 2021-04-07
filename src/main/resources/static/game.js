window.onload = start();

function start() {
    let isNewGame = getParameterByName('newGame');
    if (isNewGame === 'yes') {
        startNewGame();
    } else {
        continueGame();
    }
}

function startNewGame() {
    $.ajax({
        type: "POST",
        url: "/game",
        data: {
            roomName: getParameterByName('roomName')
        },
        dataType: "json",
        success: setBoard,
        error: errorMessage
    });
}

function continueGame() {
    $.ajax({
        type: "POST",
        url: "/continue",
        data: {
            roomName: getParameterByName('roomName')
        },
        dataType: "json",
        success: setBoard,
        error: errorMessage
    });
}

function setBoard(res) {
    console.log("setBoard");
    let squares = res.squares;
    for (let i = 0; i < squares.length; i++) {
        if (squares[i].piece !== "empty_.") {
            document.getElementById(squares[i].position).firstElementChild.src = "../img/" + squares[i].piece + ".png";
        } else {
            document.getElementById(squares[i].position).firstElementChild.src = "../img/Blank.png";
        }
    }
    turnSetting(res.turn);
    status();
}

function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results == null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}

document.addEventListener("click", squareClick);
document.getElementById("end").addEventListener("click", endGame);
document.getElementById("restart").addEventListener("click", startNewGame);

function squareClick(event) {
    if (event.target.parentNode.parentNode.parentNode.parentNode.classList.contains("board")
        && !event.target.parentNode.classList.contains("selected")) {
        event.target.parentNode.classList.add("selected");

        let numberOfSelectedSquares = document.getElementsByClassName("selected").length;
        let positionName = "";

        if (numberOfSelectedSquares <= 1) {
            positionName = "source";
        } else if (numberOfSelectedSquares > 1) {
            positionName = "target";
        }

        event.target.parentNode.classList.add(positionName);

        if (positionName === "target") {
            move();
        }
    } else if (event.target.parentNode.classList.contains("selected")) {
        event.target.parentNode.classList.remove("selected");
        event.target.parentNode.classList.remove("source");
    }
}

function endGame() {
    $.ajax({
        type: "POST",
        url: "/end",
        data: {
            roomName: getParameterByName('roomName')
        },
        dataType: "json",
    });

    goHome();
}

function goHome() {
    window.location.href = "/";
}

function move() {
    $.ajax({
        type: "POST",
        url: "/move",
        data: {
            source: document.getElementsByClassName("source")[0].id,
            target: document.getElementsByClassName("target")[0].id,
            roomName:  getParameterByName('roomName')
        },
        dataType: "text",
        success: switchPiece,
        error: errorMessage,
        complete: clearSelect()
    });
}

function switchPiece(res) {
    let result = res.split(" ");

    let sourceElement = document.getElementById(result[0]).firstElementChild;
    let targetElement = document.getElementById(result[1]).firstElementChild;
    let blankImgUrl = "./img/Blank.png";

    targetElement.src = sourceElement.src;
    sourceElement.src = blankImgUrl;

    if (result[2] === "흑" || result[2] === "백") {
        alert(result[2] + " 이 이겼습니다.");
        goHome();
    } else {
        turnSetting(result[2]);
        status();
    }
}

function turnSetting(turn) {
    let turnBoard = document.getElementById("turn");
    let turnColor;
    if (turn === "W") {
        turnColor = "White"
    } else {
        turnColor = "Black"
    }

    turnBoard.innerHTML = turnColor;
}

function status() {
    $.ajax({
        type: "POST",
        url: "/status",
        data: {
            roomName: getParameterByName('roomName')
        },
        success: printStatus,
        error: errorMessage,
    });
}

function errorMessage(res) {
    alert(res.responseText);
}

function clearSelect() {
    let source = document.getElementsByClassName("source")[0];
    let target = document.getElementsByClassName("target")[0]
    let selected = document.getElementsByClassName("selected");

    source.classList.remove("source");
    target.classList.remove("target");

    selected[1].classList.remove("selected");
    selected[0].classList.remove("selected");
}

function printStatus(res) {
    let twoStatus = res.split(" ");
    let whiteScore = twoStatus[0];
    let blackScore = twoStatus[1];

    let whiteScoreBoard = document.getElementById("white");
    let blackScoreBoard = document.getElementById("black");

    whiteScoreBoard.innerHTML = `White: ${whiteScore}`;
    blackScoreBoard.innerHTML = `Black: ${blackScore}`;
}

const $initBoard = document.querySelector("#initStart");
$initBoard.addEventListener("click", onInitStart);

const $board = document.querySelector(".board");
$board.addEventListener("click", onMovePiece);

async function onInitStart(event) {
    const whiteUserId = document.querySelector("#white-user-id").value;
    const blackUserId = document.querySelector("#black-user-id").value;

    const response = await fetch("./init", {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({
            "whiteUserId": whiteUserId,
            "blackUserId": blackUserId
        })
    });

    boardAndInfo(await response.json());
}

function state(chessGame) {
    let message1 = "";
    let message2 = "";
    let score = "";

    if (chessGame.state.includes("WhiteTurn")) {
        message1 = "백색 차례 입니다.";
    }
    if (chessGame.state.includes("BlackTurn")) {
        message1 = "흑색 차례 입니다.";
    }
    if (chessGame.state.includes("WhiteWin")) {
        message1 = "🎉 백색의 승리입니다! 🎉";
        message2 = "새로운 게임 진행을 원하면 시작버튼을 눌러주세요."
        score = "백색 " + chessGame.score.white + "점  /  ";
        score += "흑색 " + chessGame.score.black + "점";

    }
    if (chessGame.state.includes("BlackWin")) {
        message1 = "🎉 흑색의 승리입니다! 🎉";
        message2 = "새로운 게임 진행을 원하면 시작버튼을 눌러주세요."
        score = "백색 " + chessGame.score.white + "점  /  ";
        score += "흑색 " + chessGame.score.black + "점";
    }

    const messageTag1 = document.querySelector(".current-chess-game-message1");
    const messageTag2 = document.querySelector(".current-chess-game-message2");
    const scoreTag = document.querySelector(".current-chess-game-score");
    messageTag1.innerText = message1;
    messageTag2.innerText = message2;
    scoreTag.innerText = score;
}

function boardAndInfo(chessGame) {
    state(chessGame);
    vacateAllPositions();

    for (let i = 0; i < 64; i++) {
        const position = chessGame.squareDtos[i].position;
        const piece = chessGame.squareDtos[i].piece;
        const positionTag = $board.querySelector("#" + position);
        const imgTag = positionTag.firstElementChild;

        if (piece === ".") {                     // 빈 칸일 경우
            if (imgTag) {
                positionTag.removeChild(imgTag);
            }
            continue;
        }

        if (piece === piece.toLowerCase()) {    // 소문자 -> 백색 말일 경우
            const newImgTag = document.createElement("img");
            newImgTag.src = "/img/whitePieces/" + piece + ".png";
            positionTag.appendChild(newImgTag);
        }

        if (piece === piece.toUpperCase()) {    // 대문자 -> 흑색 말일 경우
            const newImgTag = document.createElement("img");
            newImgTag.src = "../img/blackPieces/" + piece + ".png";
            positionTag.appendChild(newImgTag);
        }
    }
}

function vacateAllPositions() {
    const positions = $board.querySelectorAll(".position");
    for (const position of positions) {
        const pieceImage = position.firstElementChild;
        if (pieceImage) {
            position.removeChild(pieceImage);
        }
    }
}

async function onMovePiece(event) {
    if (event.target && event.target.parentElement.className === "position") {
        const position = event.target.parentElement;
        offSelectedPosition();
        offMovableAllPositions();

        position.classList.add("selected");

        const response = await fetch("./movable", {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({"id": position.id})
        });

        const result = await response.json();

        for (const dto of result.positionDtos) {
            $board.querySelector("#" + dto.id).classList.add("movable");
        }
        return;
    }

    if (event.target && event.target.parentElement.className
        === "position movable") {
        const sourcePosition = $board.querySelector(".selected");
        const targetPosition = event.target.parentElement;

        const response = await fetch("./move", {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({
                "source": sourcePosition.id,
                "target": targetPosition.id
            })
        });

        boardAndInfo(await response.json());

        offSelectedPosition();
        offMovableAllPositions();
        return;
    }

    if (event.target && event.target.classList.contains("movable")) {
        const sourcePosition = $board.querySelector(".selected");
        const targetPosition = event.target;

        const response = await fetch("./move", {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({
                "source": sourcePosition.id,
                "target": targetPosition.id
            })
        });

        boardAndInfo(await response.json());

        offSelectedPosition();
        offMovableAllPositions();
        return;
    }

    if (event.target && event.target.className === "position") {
        offSelectedPosition();
        offMovableAllPositions();
    }
}

function offSelectedPosition() {
    const position = $board.querySelector(".selected");
    if (position && position.classList.contains("selected")) {
        position.classList.remove("selected");
    }
}

function offMovableAllPositions() {
    const positions = $board.querySelectorAll(".movable");
    positions.forEach(function (position) {
        position.classList.remove("movable");
    })
}
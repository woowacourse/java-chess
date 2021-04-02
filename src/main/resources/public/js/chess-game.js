const gameName = document.querySelector("#game-name").innerText;

window.addEventListener('load', onloaded);

async function onloaded() {

    const response = await fetch(gameName + "/load", {
        method: 'POST',
        headers: {'Content-Type': 'application/json'}
    });
    boardAndInfo(await response.json());

}

const $startGame = document.querySelector("#start-game");
$startGame.addEventListener("click", onStartGame);

const $board = document.querySelector(".board");
$board.addEventListener("click", onMovePiece);

async function onStartGame(event) {
    const whiteUserId = document.querySelector("#white-user-id").value;
    const blackUserId = document.querySelector("#black-user-id").value;

    const response = await fetch(gameName + "/start", {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({
            "whiteUserId": whiteUserId,
            "blackUserId": blackUserId
        })
    });

    boardAndInfo(await response.json());
}

async function state(chessGame) {
    const whiteUserId = document.querySelector("#white-user-id");
    const blackUserId = document.querySelector("#black-user-id");
    const startGameBlock = document.querySelector("#start-game-block");

    const messageTag1 = document.querySelector(".current-chess-game-message1");
    const messageTag2 = document.querySelector(".current-chess-game-message2");
    const scoreTag = document.querySelector(".current-chess-game-score");

    messageTag1.innerText = chessGame.state;
    let message2 = "";
    let scoreMessage = "";

    if (messageTag1.innerText === "게임 시작 전") {
        message2 = "새로운 게임 진행을 원하면 시작 버튼을 눌러주세요.";
    }

    if (messageTag1.innerText === "백색 차례" || messageTag1.innerText === "흑색 차례") {
        startGameBlock.style.display = "none";
        whiteUserId.disabled = true;
        blackUserId.disabled = true;
    }

    if (messageTag1.innerText === "백색 승리") {
        messageTag1.innerText = "🎉 백색의 승리입니다! 🎉";
    }

    if (messageTag1.innerText === "흑색 승리") {
        messageTag1.innerText = "🎉 흑색의 승리입니다! 🎉";
    }

    if (messageTag1.innerText.includes(" 승리")) {
        message2 = "새로운 게임 진행을 원하면 시작버튼을 눌러주세요."

        const response = await fetch(gameName + "/score", {
            method: 'POST',
            headers: {'Content-Type': 'application/json'}
        });

        const score = await response.json();

        scoreMessage = "백색 " + score.white + "점  /  ";
        scoreMessage += "흑색 " + score.black + "점";
        startGameBlock.style.display = "none";
        whiteUserId.disabled = true;
        blackUserId.disabled = true;
    }

    messageTag2.innerText = message2;
    scoreTag.innerText = scoreMessage;
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

        const response = await fetch(gameName + "/movable", {
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

        const response = await fetch(gameName + "/move", {
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

        const response = await fetch(gameName + "/move", {
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
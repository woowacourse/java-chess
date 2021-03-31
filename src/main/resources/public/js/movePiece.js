const $initBoard = document.querySelector("#initStart");
$initBoard.addEventListener("click", onInitBoard);

const $board = document.querySelector(".board");
$board.addEventListener("click", onMovePiece);

async function onInitBoard(event) {
    const response = await fetch("./init", {
        method: 'POST',
        headers: {'Content-Type': 'application/json'}
    });

    boardAndState(await response.json());
}

function state(state) {
    let message;

    if (state.includes("Init")) {
        message = "게임 시작 전 입니다.";
    }
    if (state.includes("WhiteTurn")) {
        message = "백색 차례 입니다.";
    }
    if (state.includes("BlackTurn")) {
        message = "흑색 차례 입니다.";
    }
    if (state.includes("WhiteWin")) {
        message = "백색의 승리입니다!";
    }
    if (state.includes("BlackWin")) {
        message = "흑색의 승리입니다!";
    }
    if (state.includes("End")) {
        message = "게임이 종료되었습니다.";
    }

    const pTag = document.querySelector(".current-chess-game-message");
    pTag.innerText = message;
}

function boardAndState(board) {
    state(board.state);
    vacateAllPositions();

    for (let i = 0; i < 64; i++) {
        const position = board.squareDtos[i].position;
        const piece = board.squareDtos[i].piece;
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

    if(event.target && event.target.parentElement.className === "position movable") {
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

        boardAndState(await response.json());

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

        boardAndState(await response.json());

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
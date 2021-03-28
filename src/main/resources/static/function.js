const BOARD = document.querySelector("#board");
const API_URL = "http://localhost:4567/";
const DEFAULT_PATH = "./image/";
const SYMBOL_TO_IMAGE_PATH = {
    "p": DEFAULT_PATH + "whitePawn.png",
    "P": DEFAULT_PATH + "blackPawn.png",
    "r": DEFAULT_PATH + "whiteRook.png",
    "R": DEFAULT_PATH + "blackRook.png",
    "n": DEFAULT_PATH + "whiteKnight.png",
    "N": DEFAULT_PATH + "blackKnight.png",
    "b": DEFAULT_PATH + "whiteBishop.png",
    "B": DEFAULT_PATH + "blackBishop.png",
    "q": DEFAULT_PATH + "whiteQueen.png",
    "Q": DEFAULT_PATH + "blackQueen.png",
    "k": DEFAULT_PATH + "whiteKing.png",
    "K": DEFAULT_PATH + "blackKing.png"
};
let boardInfo = {};
let isClicked = false;
let source = null;

window.onload = () => {
    const divs = BOARD.querySelectorAll("div");
    boardInit();
    for (const div of divs) {
        div.addEventListener("click", divClickEvent);
    }
}

function boardInit() {
    fetch(API_URL + "start")
    .then((response) => {
        if (!response.ok) {
            throw new Error(" ");
        }
        return response.json();
    })
    .then(imageSetting)
    .catch((error) => {
        console.log(error);
    })
}

function imageSetting(responseData) {
    boardInfo = responseData;
    const divs = BOARD.querySelectorAll("div");
    for (const div of divs) {
        const key = div.getAttribute("id");
        if (SYMBOL_TO_IMAGE_PATH[responseData[key]] !== undefined) {
            div.style.backgroundImage = "url(" + SYMBOL_TO_IMAGE_PATH[responseData[key]] + ")";;
        } else {
            div.style.backgroundImage = null;
        }
    }
    // 끝났는지 체크 alter 띄우기
    if (boardInfo["isFinish"] === "true") {
        //게임이 끝났음
        restartAsk();
    }
}

function divClickEvent(event) {
    if (boardInfo["isFinish"] === "true") {
        restartAsk();
        return;
    }
    const targetPosition = event.target.getAttribute("id");
    if (isClicked === false) {
        if (boardInfo[targetPosition] === ".") {
            console.log("빈 공간은 움직일 수 없습니다.")
            return;
        }
        const isWhite = boardInfo["turn"] === "WHITE";
        if ((isWhite === true && boardInfo[targetPosition] !== boardInfo[targetPosition].toLowerCase())
        || (isWhite === false && boardInfo[targetPosition] !== boardInfo[targetPosition].toUpperCase())) {
            alert("상대방의 턴입니다.");
            return;
        }
        isClicked = true;
        source = event.target;
        source.style.backgroundColor = "yellow";
    } else {
        if (source === event.target) {
            isClicked = false;
            source.style.backgroundColor = "";
            source = null;
        } else {
            isClicked = false;
            source.style.backgroundColor = "";
            const sourcePosition = source.getAttribute("id");
            movedPieces(sourcePosition, targetPosition);
        }
    }
}

function movedPieces(source, target) {
    const newData = {
        "source": source,
        "target": target
    }
    const option = getOption("PUT", newData);

    fetch(API_URL + "move", option)
    .then((response) => {
        if (!response.ok) {
            throw new Error(" ");
        }
        return response.json();
    })
    .then(imageSetting)
    .catch((error) => {
        console.log(error);
    })
}

function getOption(methodType, bodyData) {
    return {
        method: methodType,
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(bodyData)
    };
}

function restartAsk() {
    if (confirm("게임이 끝났습니다. 시작하겠습니까?") === true) {
        boardInit();
    }
}
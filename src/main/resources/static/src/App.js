const start = document.getElementById('start-button');
const IMAGE_PATH = "./images/";
const BOARD = document.querySelector("#board");
const CURRENT_TEAM = document.querySelector("#current-player");

const SYMBOL_TO_IMAGE_PATH = {
    "p": IMAGE_PATH + "whitePawn.png",
    "P": IMAGE_PATH + "blackPawn.png",
    "r": IMAGE_PATH + "whiteRook.png",
    "R": IMAGE_PATH + "blackRook.png",
    "n": IMAGE_PATH + "whiteKnight.png",
    "N": IMAGE_PATH + "blackKnight.png",
    "b": IMAGE_PATH + "whiteBishop.png",
    "B": IMAGE_PATH + "blackBishop.png",
    "q": IMAGE_PATH + "whiteQueen.png",
    "Q": IMAGE_PATH + "blackQueen.png",
    "k": IMAGE_PATH + "whiteKing.png",
    "K": IMAGE_PATH + "blackKing.png"
};

let boardInfo = {};

start.addEventListener('click', function () {
    if (start.textContent == "Start") {
        startGame();
        //initializeBoard(board);
        start.textContent = "End";
        return
    }
    //initializeBoard(board);
    start.textContent = "Start";
})

function startGame() {
    fetch('/start')
        .then(res => res.json())
        .then(res => imageSetting(res))
}

function imageSetting(responseData) {
    boardInfo = responseData;
    const divs = BOARD.querySelectorAll("div");
    pieces = responseData["board"];

    for (const div of divs) {
        const key = div.getAttribute("id");
        console.log("piececes ===> " , pieces[key])
        console.log("key >>>> ", key)
        if (SYMBOL_TO_IMAGE_PATH[pieces[key]] !== undefined) {
            div.style.backgroundImage = "url(" + SYMBOL_TO_IMAGE_PATH[pieces[key]] + ")";
        } else {
            div.style.backgroundImage = null;
        }
    }
    dataSetting(responseData)
}

function dataSetting(data) {
    if (boardInfo["isFinish"] === true) {
        document.querySelector("#view-type").textContent = "승리자 :ㅤ";
    }
    //const isWhite = boardInfo["turn"];
    // let playerType = ""
    // if (isWhite === true) {
    //     playerType = "whitePlayer";
    // } else {
    //     playerType = "blackPlayer"
    // }

    CURRENT_TEAM.textContent = data["turn"];
}

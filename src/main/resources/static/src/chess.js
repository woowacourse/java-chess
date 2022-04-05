const BASE_URL = "http://localhost:8081/"

const START_URL = BASE_URL + "start"
const MOVE_URL = BASE_URL + "move"
const STATUS_URL = BASE_URL + "status"

let source = "";
let destination = "";

const EMOJI_MAP = {
    "white_pawn": String.fromCodePoint(0x2659),
    "white_rook": String.fromCodePoint(0x2656),
    "white_knight": String.fromCodePoint(0x2658),
    "white_bishop": String.fromCodePoint(0x2657),
    "white_queen": String.fromCodePoint(0x2655),
    "white_king": String.fromCodePoint(0x2654),
    "black_pawn": String.fromCodePoint(0x265F),
    "black_rook": String.fromCodePoint(0x265C),
    "black_knight": String.fromCodePoint(0x265E),
    "black_bishop": String.fromCodePoint(0x265D),
    "black_queen": String.fromCodePoint(0x265B),
    "black_king": String.fromCodePoint(0x265A),
}

const start = async () => {
    (await fetch(START_URL))
        .json()
        .then(value => updateChessBoard(value));

    await requestScore()
        .then(value => updateScoreBoard(value))
};

async function requestScore() {
    return (await fetch(STATUS_URL)).json();
}

const moveClickHandler = async position => {

    if (source === "") {
        source = position;
        return;
    }

    if (destination === "") {
        destination = position;
        await movePiece(source, destination);
        source = "";
        destination = "";
    }
}

async function movePiece(source, destination) {
    if (source == null || destination == null) {
        alert("source 혹은 destination이 선택되지 않음");
        return;
    }

    (await requestToMove(source, destination)).json()
        .then(value => updateChessBoard(value))

    await requestScore()
        .then(value => updateScoreBoard(value))
}


async function updateChessBoard(response) {
    let board = response.board;
    const boardElem = document.getElementById("chessboard");
    const children = boardElem.children;
    for (const child of children) {
        child.innerHTML = "";
    }

    let positions = Object.keys(board);
    for (const position of positions) {
        const positionId = parsePositionToId(position)
        let square = document.getElementById(positionId);
        square.innerHTML = EMOJI_MAP[board[position].name]
    }
}

async function updateScoreBoard(response) {

    const whiteScore = response.whiteScore;
    const blackScore = response.blackScore;
    const winningTeam = response.winningTeam;

    document.getElementById("whiteScore").innerText = "White Score: " + whiteScore;
    document.getElementById("blackScore").innerText = "Black Score: " + blackScore;
    document.getElementById("winningTeam").innerText = "Winning Team: " + winningTeam;
}

async function requestToMove(source, destination) {
    return await fetch(MOVE_URL, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8',
            'Accept': 'application/json'
        },
        body: JSON.stringify({source: source, destination: destination})
    });
}


function parsePositionToId(rawPositionString) {
    let [rank, file] = rawPositionString.split(" "); //[RANK_1, FILE_A]
    return file.slice(-1) + rank.slice(-1);
}
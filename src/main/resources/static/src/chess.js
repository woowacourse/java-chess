const BASE_URL = "http://localhost:8081/"

const START_URL = BASE_URL + "start"
const MOVE_URL = BASE_URL + "move"
const STATUS_URL = BASE_URL + "status"
const LOAD_LAST_GAME_URL = BASE_URL + "load";

let source = "";
let destination = "";
let isEnd = true;

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
        .then(value => {
            updateChessBoardAndTurn(value)
            isEnd = false;
        });

    (await requestScore())
        .json()
        .then(value => updateScoreBoard(value))
};

const loadLastGame = async () => {
    (await fetch(LOAD_LAST_GAME_URL))
        .json()
        .then(value => {
            updateChessBoardAndTurn(value)
            isEnd = false;
        }).catch(response => {
        if (!response.ok) {
            alert("저장된 게임이 존재하지 않습니다")
        }
    });

    (await requestScore())
        .json()
        .then(value => updateScoreBoard(value))
};

async function requestScore() {
    return await fetch(STATUS_URL);
}

const moveClickHandler = async position => {

    if (isEnd) {
        alert("게임을 시작해주세요");
    }

    if (source === "") {
        source = position;
        document.getElementById(source).style.backgroundColor = 'yellow';
        return;
    }

    if (destination === "") {
        destination = position;
        document.getElementById(source).style.backgroundColor = '';
        await movePiece(source, destination);
        source = "";
        destination = "";
    }
}

async function movePiece(source, destination) {

    (await requestToMove(source, destination)).json()
        .then(value => {
            updateChessBoardAndTurn(value);
            checkFinished(value);
        })
        .catch(response => {
            if (!response.ok) {
                alert("cannot move");
            }
        });

    (await requestScore())
        .json()
        .then(value => {
            updateScoreBoard(value);
        })

}


async function updateChessBoardAndTurn(response) {
    const board = response.board;
    const currentTurn = response.currentTurn;

    let turnElem = document.getElementById("currentTurn");
    turnElem.innerText = "현재 턴 = " + currentTurn;


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

async function checkFinished(response) {
    isEnd = response.end;
    if (isEnd) {
        alert("게임 종료");
    }
}

async function updateScoreBoard(response) {
    const whiteScore = response.whiteScore;
    const blackScore = response.blackScore;
    const winningTeam = response.winningTeam;

    document.getElementById("whiteScore").innerText = "White Score: " + whiteScore;
    document.getElementById("blackScore").innerText = "Black Score: " + blackScore;
    document.getElementById("winningTeam").innerText = "Winning Team: " + winningTeam;

    if (isEnd) {
        document.getElementById("winningTeam").innerText = "Winning Team: " + winningTeam + "(게임종료)";
    }
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
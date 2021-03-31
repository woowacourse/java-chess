let $board;
let $target;
let $destination;

const $boardFrame = document.getElementById("board-frame");

createBoard();

function getFetch(url) {
    return fetch(url).then(response => response.json());
}

function postFetch(url) {
    return fetch(url, {
        method: 'post',
        headers: {
            'Content-type': 'application/json',
            'Accept': 'application/json'
        },
        body: JSON.stringify({
            "target": $target,
            "destination": $destination
        })
    }).then(response => {
        if (response.ok) {
            return response.json();
        }
    });
}

async function moveBoard() {
    await postFetch("move").then(data => {
        $board = data;
    });
    clearBoard();
    refreshBoard();
}

async function createBoard() {
    await getFetch("create").then(data => {
        $board = data;
    })
    refreshBoard();
}

function clearBoard() {
    const $pieces = $boardFrame.querySelectorAll('.piece');
    $pieces.forEach(piece => piece.remove());
}

function refreshBoard() {
    let boardKeys = Object.keys($board.board);
    let boardSize = boardKeys.length;
    for (let i = 0; i < boardSize; i++) {
        let tileData = document.getElementById(boardKeys[i]);
        if ($board.board[boardKeys[i]].name != null) {
            tileData.innerHTML = `
            <img class="piece" src="../images/${$board.board[boardKeys[i]].team.toLowerCase()}-${$board.board[boardKeys[i]].name.toLowerCase()}.png"
             width="80px" height="80px">
            `;
        }
    }
}

function setTarget(e) {
    try {
        if (!e.target.id) {
            $target = e.target.closest(".tile").id;
        }
    } catch (e) {
        console.log("체스말을 선택해야합니다.");
    }
}

function setDestination(e) {
    try {
        if (!e.target.id) {
            $destination = e.target.closest(".tile").id;
            move();
            return;
        }
        $destination = e.target.id;
        move();
    }
    catch (e) {
        console.log("체스말을 선택해야합니다.");
    }
}

function createMoveCommand(e) {
    if ($target == null) {
        setTarget(e);
        return;
    }
    setDestination(e);
}

function move() {
    console.log("이동! 타겟: " + $target + ", 목적지: " + $destination);
    moveBoard();
    $target = $destination;
    $destination = null;
}

document.addEventListener("click", createMoveCommand);
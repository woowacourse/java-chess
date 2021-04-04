const $board = document.getElementById('board');
const $startButton = document.getElementById('start');
const $loadButton = document.getElementById('load');
const $exitButton = document.getElementById('exit');
const $roomNo = document.getElementById('roomNo');
const $blackScore = document.getElementById('blackScore');
const $whiteScore = document.getElementById('whiteScore');
const $turn = document.getElementById('turn');
$startButton.addEventListener('click', startBoard);
$loadButton.addEventListener('click', loadChessRoom);
$exitButton.addEventListener('click', exitChessRoom);
$board.addEventListener('click', movePiece);

async function startBoard() {
    let start = await fetch('/start');
    start = await start.json();
    initBoard(start);
}

function initBoard(data) {
    if ($board.hasChildNodes()) {
        resetBoard($board);
    }
    for (let i = 8; i >= 1; i--) {
        for (let j = 0; j < 8; j++) {
            const newDiv = document.createElement('div');
            newDiv.id = String.fromCharCode(97+j)+i;
            newDiv.classList.add('box');
            const piece = data.chessBoard[newDiv.id];
            if (piece) {
                newDiv.innerHTML = makePiece(piece);
            }
            $board.appendChild(newDiv);
        }
    }
    $roomNo.innerText = data.room_no;
    $blackScore.innerText = data.blackScore;
    $whiteScore.innerText = data.whiteScore;
    $turn.innerText = data.turn;
}

function resetBoard($board) {
    while ($board.hasChildNodes()) {
        $board.removeChild($board.firstChild);
    }
}

function makePiece(piece) {
    if (!piece) {
        return '';
    }
    return `<img src="img/${piece.color + piece.name}.png" class="piece" name="${piece.name}" color="${piece.color}" alt="" style="width:49px;height:49px;">`
}

async function loadChessRoom() {
    let load = await fetch('/load?roomNo=1');
    load = await load.json();
    initBoard(load)
}

async function exitChessRoom() {
    let exit = await fetch('/exit?roomNo=1');
    exit = await exit.json();
    if (exit === 1) {
        resetBoard($board);
        $roomNo.innerText = '';
        $blackScore.innerText = '';
        $whiteScore.innerText = '';
        $turn.innerText = '';
    }
}

let source = '';
let target = '';

function movePiece(event) {
    if (event.target.closest('div') && event.target.tagName === 'IMG') {
        if (event.target.getAttribute('color') == $turn.innerText && source == '') {
            source = event.target.closest('div').id;
            showRoute(source);
            return;
        }
    }
    if (source != '') {
        if (event.target.closest('div')) {
            selectTarget(event)
        }
    }
}

async function showRoute(source) {
    const position = {
        source: source
    }
    let route = await fetch("/route", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(position)
    })
    route = await route.json()
    addNextRoute(route)
}

function addNextRoute(data) {
    for (let value of data) {
        document.getElementById((value.x + value.y)).classList.add('suggest');
    }
}

async function selectTarget(event) {
    target = event.target.closest('div').id;
    const position = {
        source: source,
        target: target,
        turn: $turn.innerText
    }
    let piece = await fetch('/move', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(position)
    })
    piece = await piece.json();
    updateBoard(piece);
    source = ''
    target = ''
}

function updateBoard(data) {
    for (let i = 8; i >= 1; i--) {
        for (let j = 0; j < 8; j++) {
            const boxDiv = document.getElementById(String.fromCharCode(97+j)+i);
            const piece = data.chessBoard[boxDiv.id];
            boxDiv.classList.remove('suggest');
            boxDiv.innerHTML = makePiece(piece);
        }
    }
    $blackScore.innerText = data.blackScore;
    $whiteScore.innerText = data.whiteScore;
    $turn.innerText = data.turn;
}



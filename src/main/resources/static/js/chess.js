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
$board.addEventListener('click', selectSource);

async function startBoard() {
    let start = await fetch('/start');
    start = await start.json();
    if (start.error) {
        alert(start.error);
        return;
    }
    initBoard(start);
}

function initBoard(data) {
    if ($board.hasChildNodes()) {
        resetBoard($board);
    }
    for (let i = 8; i >= 1; i--) {
        for (let j = 0; j < 8; j++) {
            const newDiv = document.createElement('div');
            newDiv.id = String.fromCharCode(97 + j) + i;
            newDiv.classList.add('box');
            const piece = data.chessBoard[newDiv.id];
            if (piece) {
                newDiv.innerHTML = makePiece(piece);
            }
            $board.appendChild(newDiv);
        }
    }
    $roomNo.innerText = data.roomNo;
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
    if (load.error) {
        alert(load.error);
        return;
    }
    initBoard(load)
}

async function exitChessRoom() {
    let exit = await fetch('/exit?roomNo=1');
    exit = await exit.json();
    if (exit.error) {
        alert(exit.error);
        return;
    }
    alert(exit.success);
    resetStatus();
}

function resetStatus() {
    resetBoard($board);
    $roomNo.innerText = '';
    $blackScore.innerText = '';
    $whiteScore.innerText = '';
    $turn.innerText = '';
}

let select = {
    source: '',
    target: '',
    routes: ''
}

function selectSource(event) {
    if (event.target.closest('div') && event.target.tagName === 'IMG') {
        if (event.target.getAttribute('color') == $turn.innerText) {
            emptySelect();
            select.source = event.target.closest('div').id;
            showRoute(select.source);
            return;
        }
    }
    if (select.source != '') {
        if (event.target.closest('div')) {
            selectTarget(event)
        }
    }
}

async function showRoute(source) {
    const position = {
        source: source
    }
    let routes = await fetch("/route", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(position)
    })
    routes = await routes.json()
    addNextRoute(routes)
}

function addNextRoute(data) {
    const routes = [];
    for (let value of data) {
        routes.push(value.position);
        document.getElementById(value.position).classList.add('suggest');
    }
    select.routes = routes;
}

function selectTarget(event) {
    select.target = event.target.closest('div').id;
    const position = {
        source: select.source,
        target: select.target,
        turn: $turn.innerText
    }
    movePiece(position)
}

async function movePiece(position) {
    let piece = await fetch('/move', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(position)
    })
    piece = await piece.json();
    emptySelect();
    if (piece.error) {
        alert(piece.error);
        return;
    }
    updateBoard(piece);
}

function emptySelect() {
    select.source = '';
    select.target = '';
    for (let route of select.routes) {
        document.getElementById(route).classList.remove('suggest');
    }
}

function updateBoard(data) {
    if (data.isAliveAllKings == false) {
        alert($turn.innerText + ' 승리!');
        exitChessRoom();
        return;
    }
    for (let i = 8; i >= 1; i--) {
        for (let j = 0; j < 8; j++) {
            const boxDiv = document.getElementById(String.fromCharCode(97 + j) + i);
            const piece = data.chessRoomInfo.chessBoard[boxDiv.id];
            boxDiv.innerHTML = makePiece(piece);
        }
    }
    $blackScore.innerText = data.chessRoomInfo.blackScore;
    $whiteScore.innerText = data.chessRoomInfo.whiteScore;
    $turn.innerText = data.chessRoomInfo.turn;
}



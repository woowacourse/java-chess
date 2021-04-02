
fetchBoard();
function fetchBoard() {
    fetch('/start')
        .then(response => {
            return response.json()
        })
        .then(data => {
            initBoard(data);
        })
}

function initBoard(data) {
    const boardDiv = document.getElementById('board');
    for (let i = 8; i >= 1; i--) {
        for (let j = 0; j < 8; j++) {
            const newDiv = document.createElement('div');
            newDiv.id = String.fromCharCode(97+j)+i;
            newDiv.classList.add('box');
            const piece = data.chessBoard[newDiv.id];
            if (piece) {
                newDiv.innerHTML = makePiece(piece)
            }
            boardDiv.appendChild(newDiv);
        }
    }
    document.getElementById('blackScore').innerText = data.blackScore;
    document.getElementById('whiteScore').innerText = data.whiteScore;
    document.getElementById('turn').innerText = data.turn;
}

function makePiece(piece) {
    if (!piece) {
        return '';
    }
    return `<img src="img/${piece.color + piece.name}.png" class="piece" name="${piece.name}" color="${piece.color}" alt="" style="width:49px;height:49px;">`
}


document.getElementById('board').addEventListener('click', movePiece);

let source = '';
let target = '';

function movePiece(event) {
    if (event.target.closest('div') && event.target.tagName === 'IMG') {
        if (event.target.getAttribute('color') == document.getElementById('turn').innerText && source == '') {
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
        turn: document.getElementById('turn').innerText
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
    document.getElementById('blackScore').innerText = data.blackScore;
    document.getElementById('whiteScore').innerText = data.whiteScore;
    document.getElementById('turn').innerText = data.turn;
}

const $saveButton = document.getElementById("save");
$saveButton.addEventListener('click', saveBoard);

function saveBoard() {
    const chessBoard = [];
    for (let i = 8; i >= 1; i--) {
        for (let j = 0; j < 8; j++) {
            const boxDiv = document.getElementById(String.fromCharCode(97+j)+i);
            if (boxDiv.hasChildNodes()) {
                chessBoard.push({position : boxDiv.id, name : boxDiv.children[0].name, color : boxDiv.children[0].getAttribute('color')});
               //chessBoard.set({position : boxDiv.id}, {name : boxDiv.children[0].name, color : boxDiv.children[0].getAttribute('color')});
            }
        }
    }
    const roomInfo = {
        blackScore : document.getElementById('blackScore').innerText,
        whiteScore : document.getElementById('whiteScore').innerText,
        turn : document.getElementById('turn').innerText,
        chessBoard : chessBoard
    }
    console.log(JSON.stringify(roomInfo))
    fetch('/save', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(roomInfo)
    }).then(response => {
        return response.json();
    }).then(data => {
        if (data === 1) {
            alert('성공했습니다.');
        }
    })
}


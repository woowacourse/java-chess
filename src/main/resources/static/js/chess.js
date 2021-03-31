
function fetchBoard() {
    fetch('/board')
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
    let value = '';
    if (!piece) {
        return '';
    }
    if (piece.name.toUpperCase() == 'P') {
        value = "pawn";
    }
    if (piece.name.toUpperCase() == 'R') {
        value = "rook";
    }
    if (piece.name.toUpperCase() == 'B') {
        value = "bishop";
    }
    if (piece.name.toUpperCase() == 'Q') {
        value = "queen";
    }
    if (piece.name.toUpperCase() == 'K') {
        value = "king";
    }
    if (piece.name.toUpperCase() == 'N') {
        value = "knight";
    }
    return `<i class="fas fa-chess-${value}" style="font-size: 45px; color: ${piece.color.toLowerCase()}"></i>`
}

fetchBoard();


document.getElementById('board').addEventListener('click', movePiece);

let source = '';
let target = '';

function movePiece(event) {
    if (event.target.closest('div') && event.target.tagName === 'I') {
        console.log(event.target.style.color)
        console.log(document.getElementById('turn').innerText)
        if (event.target.style.color == document.getElementById('turn').innerText.toLowerCase() && source == '') {
            source = event.target.closest('div').id;
            return;
        }
    }
    if (source != '') {
        if (event.target.closest('div')) {
            selectTarget(event)
        }
    }
}

function selectTarget(event) {
    target = event.target.closest('div').id;
    const position = {
        source: source,
        target: target,
        turn: document.getElementById('turn').innerText
    }
    console.log(position)
    fetch('/move', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(position)
    }).then(response => {
        return response.json()
    }).then(data => {
        console.log(data)
        updateBoard(data)
    })
    source = ''
    target = ''
}

function updateBoard(data) {
    for (let i = 8; i >= 1; i--) {
        for (let j = 0; j < 8; j++) {
            const boxDiv = document.getElementById(String.fromCharCode(97+j)+i);
            const piece = data.chessBoard[boxDiv.id];
            boxDiv.innerHTML = makePiece(piece);
        }
    }
    document.getElementById('blackScore').innerText = data.blackScore;
    document.getElementById('whiteScore').innerText = data.whiteScore;
    document.getElementById('turn').innerText = data.turn;
}


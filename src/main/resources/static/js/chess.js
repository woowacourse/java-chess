
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




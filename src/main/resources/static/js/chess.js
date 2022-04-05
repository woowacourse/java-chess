const rows = [8, 7, 6, 5, 4, 3, 2, 1];
const columns = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'];

let source = '';
let target = '';

async function selectPiece(position) {
    let piece = await document.getElementById('piece-' + position);
    if (piece.className === 'piece active') {
        event.stopPropagation();
    }
    let isActive = piece.className.split(' ')[1];
    if (isActive && source === '') {
        source = position;
        lockPiece();
        unlockSquare();
    }
}

function selectSquare(position) {
    let square = document.getElementById('square-' + position);
    let isActive = square.className.split(' ')[1];
    if (isActive && target === '') {
        target = position;
        lockSquare();
    }
    if (source !== '' && target !== '') {
        fetchMove(source, target);
    }
}

function lockPiece() {
    let pieces = document.getElementsByClassName('piece');
    for (const piece of pieces) {
        piece.className = piece.className.split(' ')[0];
    }
}

function lockSquare() {
    let squares = document.getElementsByClassName('square');
    for (const square of squares) {
        square.className = square.className.split(' ')[0];
    }
}

function unlockSquare() {
    let squares = document.getElementsByClassName('square');
    for (const square of squares) {
        square.className = 'square active';
        square.style.pointerEvents = 'auto';
    }
}

function initBlocks(piecesContainer) {
    for (const row of rows) {
        for (const column of columns) {
            let square = document.createElement('div');
            square.className = 'square';
            square.id = 'square-' + column + row;
            square.onclick = function () {selectSquare(column + row)};
            piecesContainer.append(square);
        }
    }
}

function removeOldPieces() {
    for (const row of rows) {
        for (const column of columns) {
            let oldPiece = document.getElementById('piece-' + column + row);
            if (oldPiece) {
                oldPiece.remove();
            }
        }
    }
}

function setNewPieces(pieces) {
    for (const piece of pieces) {
        let square = document.getElementById('square-' + piece.position);
        let pieceImage = document.createElement('img');
        pieceImage.src = 'images/' + piece.color + '_' + piece.role + '.png';
        pieceImage.className = 'piece' + ' active';
        pieceImage.id = 'piece-' + piece.position;
        pieceImage.onclick = function () {selectPiece(piece.position)};
        square.append(pieceImage);
    }
}

async function initPieces(pieces) {
    let oldPieces = await document.getElementsByClassName('piece');
    await removeOldPieces(oldPieces);
    await setNewPieces(pieces);
    target = '';
    source = '';
}

window.onload = async function () {
    let piecesContainer = document.getElementById('pieces-container');
    await initBlocks(piecesContainer);
}

function fetchNewChess() {
    fetch('http://localhost:8080/start', {
        method: 'GET',
        headers: {
            'Content-type': 'application/json; charset=UTF-8',
        },
    })
        .then(res => res.json())
        .then(res => initPieces(res.pieces))
}

function fetchMove(source, target) {
    fetch('http://localhost:8080/move', {
        method: 'POST',
        body: JSON.stringify({
            source: source,
            target: target
        }),
        headers: {
            'Content-type': 'application/json; charset=UTF-8',
        },
    })
        .then(res => res.json())
        .then(res => initPieces(res.pieces))
}
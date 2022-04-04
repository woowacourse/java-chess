const rows = [8, 7, 6, 5, 4, 3, 2, 1];
const columns = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'];

function initBlocks(piecesContainer) {
    for (const row of rows) {
        for (const column of columns) {
            let square = document.createElement('div');
            square.className = 'board-square';
            square.id = 'board-' + column + row;
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
        let square = document.getElementById('board-' + piece.position);
        let pieceImage = document.createElement('img');
        pieceImage.src = 'images/' + piece.color + '_' + piece.role + '.png';
        pieceImage.className = 'piece';
        pieceImage.id = 'piece-' + piece.position;
        square.append(pieceImage);
    }
}

async function initPieces(pieces) {
    let oldPieces = await document.getElementsByClassName('piece');
    console.log(oldPieces);
    await removeOldPieces(oldPieces);
    await setNewPieces(pieces);
}

window.onload = async function () {
    let piecesContainer = document.getElementById('pieces-container');
    await initBlocks(piecesContainer);
}

function fetchChess() {
    fetch('http://localhost:8080/start', {
        method: 'GET',
        headers: {
            'Content-type': 'application/json; charset=UTF-8',
        },
    })
        .then(res => res.json())
        .then(res => initPieces(res.pieces))
}
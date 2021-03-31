const jsonFormatChessBoard = document.getElementById('jsonFormatChessBoard');
const jsonFormatObject = JSON.parse(jsonFormatChessBoard.innerText);

const file = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'];
const rank = ['1', '2', '3', '4', '5', '6', '7', '8'];

const cell = [];
for (let i = 0; i < file.length; i++) {
    for (let j = 0; j < rank.length; j++) {
        cell.push(file[i] + rank[j]);
    }
}

for (let i = 0; i < cell.length; i++) {
    if (jsonFormatObject[cell[i]]) {
        const divCell = document.getElementById(cell[i]);
        let piece = jsonFormatObject[cell[i]];

        const img = document.createElement('img');
        img.style.width = '100%';
        img.style.height = '100%';

        if (piece === 'P') {
            piece = '';
            img.src = '/images/black-pawn.png';
        }
        if (piece === 'R') {
            piece = '';
            img.src = '/images/black-rook.png';
        }
        if (piece === 'N') {
            piece = '';
            img.src = '/images/black-knight.png';
        }
        if (piece === 'B') {
            piece = '';
            img.src = '/images/black-bishop.png';
        }
        if (piece === 'Q') {
            piece = '';
            img.src = '/images/black-queen.png';
        }
        if (piece === 'K') {
            piece = '';
            img.src = '/images/black-king.png';
        }
        if (piece === 'p') {
            piece = '';
            img.src = '/images/white-pawn.png';
        }
        if (piece === 'r') {
            piece = '';
            img.src = '/images/white-rook.png';
        }
        if (piece === 'n') {
            piece = '';
            img.src = '/images/white-knight.png';
        }
        if (piece === 'b') {
            piece = '';
            img.src = '/images/white-bishop.png';
        }
        if (piece === 'q') {
            piece = '';
            img.src = '/images/white-queen.png';
        }
        if (piece === 'k') {
            piece = '';
            img.src = '/images/white-king.png';
        }

        divCell.appendChild(img);
    }
}

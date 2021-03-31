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
            img.id = divCell.id;
        }
        if (piece === 'R') {
            piece = '';
            img.src = '/images/black-rook.png';
            img.id = divCell.id;
        }
        if (piece === 'N') {
            piece = '';
            img.src = '/images/black-knight.png';
            img.id = divCell.id;
        }
        if (piece === 'B') {
            piece = '';
            img.src = '/images/black-bishop.png';
            img.id = divCell.id;
        }
        if (piece === 'Q') {
            piece = '';
            img.src = '/images/black-queen.png';
            img.id = divCell.id;
        }
        if (piece === 'K') {
            piece = '';
            img.src = '/images/black-king.png';
            img.id = divCell.id;
        }
        if (piece === 'p') {
            piece = '';
            img.src = '/images/white-pawn.png';
            img.id = divCell.id;
        }
        if (piece === 'r') {
            piece = '';
            img.src = '/images/white-rook.png';
            img.id = divCell.id;
        }
        if (piece === 'n') {
            piece = '';
            img.src = '/images/white-knight.png';
            img.id = divCell.id;
        }
        if (piece === 'b') {
            piece = '';
            img.src = '/images/white-bishop.png';
            img.id = divCell.id;
        }
        if (piece === 'q') {
            piece = '';
            img.src = '/images/white-queen.png';
            img.id = divCell.id;
        }
        if (piece === 'k') {
            piece = '';
            img.src = '/images/white-king.png';
            img.id = divCell.id;
        }

        divCell.appendChild(img);
    }
}

const statusBtn = document.getElementById('status-btn');

statusBtn.addEventListener('click', function() {
    const whiteScore = document.getElementById('whiteScore');
    const blackScore = document.getElementById('blackScore');
    alert('하얀색 기물 점수는: ' + whiteScore.textContent + '\n' +
        '검정색 기물 점수는: ' + blackScore.textContent);
});

let is_start_position_clicked = false;
let start_position = null;
let destination = null;
const pieceCells = document.getElementsByClassName('piece-cell');

for (let i = 0; i < pieceCells.length; i++) {
    pieceCells[i].addEventListener('click', (event) => {
        if (!is_start_position_clicked) {
            start_position = event.target.id;
            is_start_position_clicked = true;
            return;
        }
        destination = event.target.id;
        request_move_post();
    });
}

function request_move_post() {
    if (!(cell.indexOf(start_position) && cell.indexOf(destination))) {
        alert('클릭 오류입니다!');
        start_position = null;
        destination = null;
        is_start_position_clicked = false;
    }
    const xhr = new XMLHttpRequest();
    xhr.open('POST', '/move', true);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.responseType = 'json';
    xhr.send(JSON.stringify({
        source: start_position,
        target: destination
    }));
    start_position = null;
    destination = null;
    is_start_position_clicked = false;

    xhr.onload = function () {
        const move_response = xhr.response;
        if (move_response['status'] === '500') {
            alert(move_response['message']);
            return;
        }
        window.location.href = '/';
    };
}

// const is_king_dead = document.getElementById('is-king-dead');
//
// if (is_king_dead.innerText === "true") {
//     const before_turn_team_name = document.getElementById('before-turn-team-name');
//     alert(before_turn_team_name.innerText + ' 팀이 이겼습니다.');
//     window.location.href = HOME + '/delete?id=' + game_id;
// }

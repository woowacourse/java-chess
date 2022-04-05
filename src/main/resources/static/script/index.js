const gameStatusLocalStorage = 'gameStatus';

localStorage.clear();

// start button
const startButton = document.querySelector('#command-button__start');
startButton.addEventListener('click', () => {
    if (localStorage.getItem(gameStatusLocalStorage) !== null) {
        alert("기존 게임을 끝내고 시작해 주세요.");
        return;
    }
    startRequest('start');
});

function startRequest(command) {
    fetch(`http://localhost:8080/game/command/${command}`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        },
        credentials: 'include',
    })
        .then(response => response.json())
        .then(({game_status: gameStatus, pieces}) => {
            saveGameStatus(gameStatus);
            arrangePieces(pieces);
        })
        .catch(error => console.log(error));
}

// move button
const moveCommand = {
    start: '',
    end: '',
};

const spaces = document.querySelectorAll(".chess-table__space");
Array.from(spaces).map(space => {
    space.addEventListener('click', ({currentTarget: {id}}) => {
        if (moveCommand.start === '') {
            moveCommand.start = id;
            return;
        }
        moveCommand.end = id;
        moveRequest('move', moveCommand);
        initMoveCommand();
    });
});

function initMoveCommand() {
    moveCommand.start = '';
    moveCommand.end = '';
}

function moveRequest(command, {start, end} = {start: '', end: ''}) {

    fetch(`http://localhost:8080/game/command/${command}?start=${start}&end=${end}`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        },
        credentials: 'include',
    })
        .then(response => response.json())
        .then(({game_status: gameStatus, pieces}) => {
            saveGameStatus(gameStatus);
            movePieces(start, end);
        })
        .catch(error => console.log(error));
}

function movePieces(startPosition, endPosition) {
    const targetSpace = document.getElementById(endPosition);
    const piece = document.getElementById(startPosition).firstChild;
    document.getElementById(startPosition).firstChild.remove();
    document.getElementById(endPosition).firstChild?.remove();
    targetSpace.appendChild(piece);
}


function saveGameStatus(gameStatus) {
    localStorage.setItem('gameStatus', gameStatus);
}

// start game
function startGame() {
    const name = prompt("게임 진행을 위해 닉네임을 입력해 주세요.");
    fetch(`http://localhost:8080/user/name/${name}`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        },
        credentials: 'include',
    }).then(response => response.json())
        .then(({game_status: gameStatus, pieces}) => {
            console.log(gameStatus);
            if (gameStatus !== 'ready') {
                arrangePieces(pieces);
            }
        })
        .catch(err => console.log(err));
}

function arrangePieces(pieces) {
    pieces.forEach(({piece_url: pieceUrl, position, symbol}) => {
        const space = document.getElementById(position);
        space.innerHTML = `<img class="space__piece" src="${pieceUrl}" alt="${symbol}" />`;
    });
}

startGame();

// status button
const statusButton = document.querySelector('#command-button__status');
statusButton.addEventListener('click', () => {
    statusRequest('status');
});

function statusRequest(command) {
    fetch(`http://localhost:8080/game/command/${command}`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        },
        credentials: 'include',
    })
        .then(response => response.json())
        .then(({game_status: gameStatus, black_score: blackScore, white_score: whiteScore}) => {
            saveGameStatus(gameStatus);
            alertScore(blackScore, whiteScore);
        })
        .catch(error => console.log(error));
}

function alertScore(blackScore, whiteScore) {
    alert(`black score: ${blackScore}, white score: ${whiteScore}`);
}

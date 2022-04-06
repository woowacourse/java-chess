function commandRequest(command, actionAfterSuccess, mc) {
    const url = makeUrlBy(command);
    fetch(`http://localhost:8080/game/command/${url}`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        },
        credentials: 'include',
    })
        .then(errorHandling)
        .then(response => response.json())
        .then((response) => {
            if (command === 'move') {
                actionAfterSuccess(response, mc);
                return;
            }
            actionAfterSuccess(response);
        })
        .catch(error => {
            alert(error.message);
        });
}

function makeUrlBy(command) {
    if (command === 'move') {
        return `${command}?start=${moveCommand.start}&end=${moveCommand.end}`;
    }
    return command;
}

function errorHandling(res) {
    if (!res.ok) {
        return res.json().then(text => {
            throw new Error(text.error_message)
        });
    }
    return res;
}

// start button
const startButton = document.querySelector('#command-button__start');
startButton.addEventListener('click', () => {
    commandRequest('start', (response) => startSuccess(response));
});

// end button
const endButton = document.querySelector('#command-button__end');
endButton.addEventListener('click', () => {
    commandRequest('end', (response) => statusSuccess(response));
});

// status button
const statusButton = document.querySelector('#command-button__status');
statusButton.addEventListener('click', () => {
    commandRequest('status', (response) => statusSuccess(response));
});

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
        commandRequest('move', (response, moveCommand) => {
            moveSuccess(response, moveCommand);
        }, {...moveCommand});
        initMoveCommand();
    });
});

function initMoveCommand() {
    moveCommand.start = '';
    moveCommand.end = '';
}

function moveSuccess({game_status: gameStatus, pieces}, {start, end}) {
    movePieces(start, end);
}

function startSuccess({game_status: gameStatus, pieces}) {
    arrangePieces(pieces);
}

function statusSuccess(response) {
    const {game_status: gameStatus, black_score: blackScore, white_score: whiteScore} = response;
    alertScore(blackScore, whiteScore);
}

function alertScore(blackScore, whiteScore) {
    alert(`black score: ${blackScore}, white score: ${whiteScore}`);
}

function movePieces(startPosition, endPosition) {
    const targetSpace = document.getElementById(endPosition);
    const piece = document.getElementById(startPosition).firstChild;
    document.getElementById(startPosition).firstChild.remove();
    document.getElementById(endPosition).firstChild?.remove();
    targetSpace.appendChild(piece);
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
            if (gameStatus !== 'READY') {
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





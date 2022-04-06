const COMMAND = {
    START: 'start',
    MOVE: 'move',
    STATUS: 'status',
    END: 'end',
};
Object.freeze(COMMAND);

const GAME_STATUS = {
    WHITE_RUNNING: 'WHITE_RUNNING',
    BLACK_RUNNING: 'BLACK_RUNNING',
    READY: 'READY',
}
Object.freeze(GAME_STATUS);

const ORIGIN = 'http://localhost:8080';

function commandRequest(command, actionAfterSuccess, moveCommand) {
    const url = makeUrlBy(command);
    fetch(`${ORIGIN}/game/command/${url}`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        },
        credentials: 'include',
    })
        .then(errorHandling)
        .then(response => response.json())
        .then((response) => {
            if (command === COMMAND.MOVE) {
                actionAfterSuccess(response, moveCommand);
                return;
            }
            actionAfterSuccess(response);
        })
        .catch(error => {
            alert(error.message);
        });
}

function makeUrlBy(command) {
    if (command === COMMAND.MOVE) {
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
    commandRequest(COMMAND.START, (response) => startSuccess(response));
});

// end button
const endButton = document.querySelector('#command-button__end');
endButton.addEventListener('click', () => {
    commandRequest(COMMAND.END, (response) => statusSuccess(response));
});

// status button
const statusButton = document.querySelector('#command-button__status');
statusButton.addEventListener('click', () => {
    commandRequest(COMMAND.STATUS, (response) => statusSuccess(response));
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
        commandRequest(COMMAND.MOVE, (response, moveCommand) => {
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
    changeTurn(gameStatus);
}

function movePieces(startPosition, endPosition) {
    const targetSpace = document.getElementById(endPosition);
    const piece = document.getElementById(startPosition).firstChild;
    document.getElementById(startPosition).firstChild.remove();
    document.getElementById(endPosition).firstChild?.remove();
    targetSpace.appendChild(piece);
}

function changeTurn(gameStatus) {
    console.log(gameStatus);
    const blackPieceUser = document.getElementById('game-information__black-team');
    const whitePieceUser = document.getElementById('game-information__white-team');
    if (gameStatus === GAME_STATUS.WHITE_RUNNING) {
        highlightTeam(blackPieceUser);
        unhighlightTeam(whitePieceUser);
        return;
    }
    highlightTeam(whitePieceUser);
    unhighlightTeam(blackPieceUser);
}

function highlightTeam(team) {
    console.log("team" + team);
    team.style.borderColor = 'rgb(59,25,3)';
    team.style.boxShadow = '0 0 100px rgb(63,26,3)';
}

function unhighlightTeam(team) {
    team.style.borderColor = '';
    team.style.boxShadow = '';
}

function startSuccess({pieces}) {
    arrangePieces(pieces);
}

function statusSuccess(response) {
    const {black_score: blackScore, white_score: whiteScore} = response;
    alertScore(blackScore, whiteScore);
}

function alertScore(blackScore, whiteScore) {
    alert(`black score: ${blackScore}, white score: ${whiteScore}`);
}


// start game
function startGame() {
    const name = prompt("게임 진행을 위해 닉네임을 입력해 주세요.");
    fetch(`${ORIGIN}/user/name/${name}`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        },
        credentials: 'include',
    }).then(response => response.json())
        .then(({game_status: gameStatus, pieces}) => {
            if (gameStatus !== GAME_STATUS.READY) {
                arrangePieces(pieces);
                changeTurn(getTurn(gameStatus));
            }
        })
        .catch(err => console.log(err));
}

function getTurn(gameStatus) {
    if (gameStatus === GAME_STATUS.WHITE_RUNNING) {
        return GAME_STATUS.BLACK_RUNNING;
    }
    return GAME_STATUS.WHITE_RUNNING;
}

function arrangePieces(pieces) {
    pieces.forEach(({piece_url: pieceUrl, position, symbol}) => {
        const space = document.getElementById(position);
        space.innerHTML = `<img class="space__piece" src="${pieceUrl}" alt="${symbol}" />`;
    });
}

startGame();





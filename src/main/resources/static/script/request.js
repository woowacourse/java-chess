import {arrangePieces, changeTurn, COMMAND, GAME_STATUS, getTurn, ORIGIN} from "/script/util.js";
import {moveCommand} from "/script/command-button.js";

export function commandRequest(command, actionAfterSuccess, moveCommand) {
    const url = makeUrlBy(command);
    fetch(`${ORIGIN}/game/command/${url}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        credentials: 'include',
    })
        .then(errorHandling)
        .then(response => response.json())
        .then((response) => {
            console.log(response);
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

// start game
export function startGame() {
    const name = prompt("게임 진행을 위해 닉네임을 입력해 주세요.");
    fetch(`${ORIGIN}/user/name/${name}`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        },
        credentials: 'include',
    }).then(errorHandling)
        .then(response => response.json())
        .then(({game_status: gameStatus, pieces}) => {
            if (gameStatus !== GAME_STATUS.READY) {
                arrangePieces(pieces);
                changeTurn(getTurn(gameStatus));
            }
        })
        .catch(err => alert(err.message));
}


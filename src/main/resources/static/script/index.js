import {COMMAND} from "/script/util.js";
import {commandRequest, startGame} from "/script/request.js";
import {initMoveCommand, moveCommand, moveSuccess, startSuccess, statusSuccess} from "/script/command-button.js";

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

startGame();





import {arrangePieces, changeTurn} from "/script/util.js";

export function startSuccess({pieces}) {
    arrangePieces(pieces);
}

export function statusSuccess({black_score: blackScore, white_score: whiteScore}) {
    alertScore(blackScore, whiteScore);
}

function alertScore(blackScore, whiteScore) {
    alert(`black score: ${blackScore}, white score: ${whiteScore}`);
}

export const moveCommand = {
    start: '',
    end: '',
};

export function initMoveCommand() {
    moveCommand.start = '';
    moveCommand.end = '';
}

export function moveSuccess({game_status: gameStatus, pieces}, {start, end}) {
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

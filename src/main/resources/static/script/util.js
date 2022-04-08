export const COMMAND = {
    START: 'start',
    MOVE: 'move',
    STATUS: 'status',
    END: 'end',
};
Object.freeze(COMMAND);

export const GAME_STATUS = {
    WHITE_RUNNING: 'WHITE_RUNNING',
    BLACK_RUNNING: 'BLACK_RUNNING',
    READY: 'READY',
}
Object.freeze(GAME_STATUS);

export const ORIGIN = 'http://localhost:8080';

export function changeTurn(gameStatus) {
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
    team.style.borderColor = 'rgb(59,25,3)';
    team.style.boxShadow = '0 0 100px rgb(63,26,3)';
}

function unhighlightTeam(team) {
    team.style.borderColor = '';
    team.style.boxShadow = '';
}

export function getTurn(gameStatus) {
    if (gameStatus === GAME_STATUS.WHITE_RUNNING) {
        return GAME_STATUS.BLACK_RUNNING;
    }
    return GAME_STATUS.WHITE_RUNNING;
}

export function arrangePieces(pieces) {
    pieces.forEach(({image_url: imageUrl, position, symbol}) => {
        const space = document.getElementById(position);
        space.innerHTML = `<img class="space__piece" src="${imageUrl}" alt="${symbol}" />`;
    });
}

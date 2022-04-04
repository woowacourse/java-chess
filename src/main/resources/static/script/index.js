const gameStatusLocalStorage = 'gameStatus';

const moveCommand = {
    start: '',
    end: '',
};
const spaces = document.querySelectorAll(".chess-table__space");
Array.from(spaces).map(space => {
    space.addEventListener('click', ({currentTarget: {id}}) => {
        // console.log(id);
        if (moveCommand.start === '') {
            moveCommand.start = id;
            return;
        }
        moveCommand.end = id;
    });
});


localStorage.clear();

function startNewGame() {
    fetch('http://localhost:8080/game/command/start', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        },
    })
        .then(response => response.json())
        .then(({game_status: gameStatus, pieces}) => {
            saveGameStatus(gameStatus);
            arrangePieces(pieces);
        })
        .catch(error => console.log(error));
}

function arrangePieces(pieces) {
    pieces.forEach(({piece_url: pieceUrl, position, symbol}) => {
        const space = document.getElementById(position);
        space.innerHTML = `<img class="space__piece" src="${pieceUrl}" alt="${symbol}" />`;
    });
}

function saveGameStatus(gameStatus) {
    localStorage.setItem('gameStatus', gameStatus);
}

const startButton = document.querySelector('#command-button__start');
startButton.addEventListener('click', () => {
    if (localStorage.getItem(gameStatusLocalStorage) !== null) {
        alert("기존 게임을 끝내고 시작해 주세요.");
        return;
    }
    startNewGame();
});





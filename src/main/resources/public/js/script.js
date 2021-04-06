const BLOCK_SIZE_PIXEL = 80;

const $chessBoard = document.getElementById('chess-board');

const colorTranslationTable = {BLACK: '흑', WHITE:'백'};
const matchResultTranslationTable = {DRAW: '무승부', WHITE_WIN: '백 승리', BLACK_WIN: '흑 승리'};

const squareBuffer = new SquareBuffer();

addSelectionEventOnChessBoard();
addEventOnStartButton();
addEventOnRegameButton();
addEventOnSaveGameButton();
addEventOnLoadGameButton();

function processResponse(responseJsonBody, successScenarioFunction) {
    updateMessage(responseJsonBody.message);
    console.log(responseJsonBody.message);
    if (Math.floor(responseJsonBody.statusCode / 100) === 2) {
        successScenarioFunction();
    }
}

function SquareBuffer() {
    this.buffer = [];
    this.add = addAndRequestMove;
}

function addAndRequestMove(square) {
    this.buffer.push(square);
    if (this.buffer.length === 2) {
        const toSquare = this.buffer.pop();
        const fromSquare = this.buffer.pop();

        toSquare.classList.toggle('opaque');
        fromSquare.classList.toggle('opaque');

        console.log(`request [POST]/move, body: from: ${fromSquare.id}, \nto: ${toSquare.id}\n`);

        try {
            fetch("/move", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({
                    from: fromSquare.id,
                    to: toSquare.id,
                }),
            })
                .then(res => res.json())
                .then(res => processResponse(res, () => updateGameData(res)));
        } catch
            (error) {
            console.error(error.messages);
        }
    }
}

async function addSelectionEventOnChessBoard() {
    $chessBoard.addEventListener('click', event => {
        const selectedSquare = event.target.closest('div');
        squareBuffer.add(selectedSquare);
        selectedSquare.classList.toggle('opaque');
    })
}

async function addEventOnStartButton() {
    await document.getElementById('start-button').addEventListener('click', event => {
        try {
            fetch('/newgame')
                .then(res => res.json())
                .then(res => processResponse(res, () => updateGameData(res)));
            turnOnPanel();
        } catch (error) {
            console.error(error.messages);
        }
    });
}

async function addEventOnRegameButton() {
    await document.getElementById('regame-button').addEventListener('click', event => {
        try {
            fetch('/newgame')
                .then(res => res.json())
                .then(res => processResponse(res, () => updateGameData(res)));
            turnOnPanel();
        } catch (error) {
            console.error(error.messages);
        }
    });
}

function addEventOnSaveGameButton() {
    document.getElementById('save-button').addEventListener('click', event => {
        try {
            fetch('/save')
                .then(res => res.json())
                .then(res => processResponse(res, () => {}));
            turnOnPanel();
        } catch (error) {
            console.error(error.message);
        }
    })
}

function addEventOnLoadGameButton() {
    document.getElementById('load-button').addEventListener('click', event => {
        try {
            fetch('/load')
                .then(res => res.json())
                .then(res => processResponse(res, () => updateGameData(res)));
            turnOnPanel();
        } catch (error) {
            console.error(error.message);
        }
    })
}

function updateGameData(responseJsonBody) {
    updateBoard(responseJsonBody.item.chessBoard);
    if (responseJsonBody.item.isEnd) {
        updateWinner(responseJsonBody);
        updateMessage('게임이 끝났습니다.');
        turnOffPanel();
        return;
    }
    updateScoreAndTurn(responseJsonBody);
}

function updateBoard(piecesMap) {
    for (const square of $chessBoard.querySelectorAll('div')) {
        square.innerHTML = '';
    }
    for (const [position, piece] of Object.entries(piecesMap)) {
        updateSquare(position, piece);
    }
}

async function updateSquare(position, piece) {
    const square = document.getElementById(position);
    square.appendChild(makeImage(piece.color + '-' + piece.notation));
}

function makeImage(imageName) {
    const img = document.createElement('img');
    img.setAttribute('src', '/images/' + imageName + '.png');
    img.height = BLOCK_SIZE_PIXEL;
    return img;
}

function updateScoreAndTurn(responseJsonBody) {
    const blackScore = responseJsonBody.item.colorsScore.BLACK;
    const whiteScore = responseJsonBody.item.colorsScore.WHITE;
    const currentTurn = responseJsonBody.item.currentTurnColor;
    document.getElementById('score-console').innerText = `백: ${whiteScore}점 흑: ${blackScore}점\n현재 순서: ${colorTranslationTable[currentTurn]}`;
}

function updateWinner(responseJsonBody) {
    const blackScore = responseJsonBody.item.colorsScore.BLACK;
    const whiteScore = responseJsonBody.item.colorsScore.WHITE;
    const winner = responseJsonBody.item.matchResult;
    document.getElementById('score-console').innerText = `백: ${whiteScore}점 흑: ${blackScore}점\n승: ${matchResultTranslationTable[winner]}`;
}

function updateMessage(message) {
    document.getElementById('message-console').innerText = message;
}

function turnOnPanel() {
    for (const button of document.getElementById('middle-panel').querySelectorAll('button')) {
        button.style.display = 'none';
    }
    document.getElementById('save-button').style.display = 'block';
    document.getElementById('regame-button').style.display = 'block';
    document.getElementById('message-console').style.display = 'block';
    document.getElementById('score-console').style.display = 'block';
}

function turnOffPanel() {
    document.getElementById('save-button').style.display = 'none';
}

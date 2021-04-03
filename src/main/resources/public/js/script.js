const BLOCK_SIZE_PIXEL = 80;

const $controlPanel = document.getElementById('control-panel');
const $startButton = document.getElementById('start-button');
addEventOnStartButton();

async function addEventOnStartButton() {
    await $startButton.addEventListener('click', event => {
        try {
            fetch('/newgame')
                .then(res => res.json())
                .then(res => updateBoard(res.item.chessBoard));
            $startButton.style.display = 'none';
        } catch (error) {
            console.error(error.messages);
        }
    });
}

function makeImage(imageName) {
    const img = document.createElement('img');
    img.setAttribute('src', '/images/' + imageName + '.png');
    img.height = BLOCK_SIZE_PIXEL;
    return img;
}

function updateBoard(piecesMap) {
    for (const [position, piece] of Object.entries(piecesMap)) {
        updateSqaure(position, piece);
    }
}

async function updateSqaure(position, piece) {
    const square = document.getElementById(position);
    square.appendChild(makeImage(piece.color + '-' + piece.notation));
}
import * as piece from "./pieceview.js";

const startButton = document.querySelector(".start");
const saveButton = document.querySelector(".save");

window.addEventListener('DOMContentLoaded', syncBoard)
startButton.addEventListener('click', restartGame)
saveButton.addEventListener('click', saveGame)

export function restartGame() {
    axios({
        method: 'put',
        url: '/restart'
    })
        .then(function () {
            syncBoard();
        })
}

export function syncBoard() {
    axios({
        method: 'get',
        url: '/drawBoard'
    })
        .then(function (res) {
            const currentBoard = res.data;
            const positions = Object.keys(currentBoard);
            const pieces = Object.values(currentBoard);

            for (let i = 0; i < positions.length; i++) {
                document.querySelector("#"+positions[i]).innerHTML = piece.pieceImage(pieces[i]);
            }
        })
}

function saveGame() {
    axios({
        method: 'put',
        url: '/save'
    })
}


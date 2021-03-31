import * as piece from "./pieceview.js";
const button = document.querySelector("input");

window.addEventListener('DOMContentLoaded', syncBoard)
button.addEventListener('click', restartGame)

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


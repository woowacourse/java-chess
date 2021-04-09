import * as piece from "./pieceview.js";

const startButton = document.querySelector(".start");
const gameId = new URLSearchParams(window.location.search).get("id");

if (isMainPage()) {
    window.addEventListener('DOMContentLoaded', syncBoard)
    startButton.addEventListener('click', restartGame)
}

function isMainPage() {
    return window.location.pathname === "/game";
}

export function restartGame() {
    axios({
        method: 'put',
        url: '/restart',
        params: {
            gameId: gameId
        }
    })
        .then(function () {
            syncBoard();
        })
}

export function syncBoard() {
    axios({
        method: 'get',
        url: '/drawBoard',
        params: {
            gameId: gameId
        }
    })
        .then(function (res) {
            const currentBoard = res.data;
            const positions = Object.keys(currentBoard);
            const pieces = Object.values(currentBoard);

            for (let i = 0; i < positions.length; i++) {
                document.querySelector("#" + positions[i]).innerHTML = piece.pieceImage(pieces[i]);
            }
        })
    updateStatus();
}

export function updateStatus() {
    axios({
        method: 'get',
        url: '/status',
        params: {
            gameId: gameId
        }
    })
        .then(function (res) {
            const status = res.data;
            document.querySelector("#blackScore").querySelector("a").textContent = status[0];
            document.querySelector("#whiteScore").querySelector("a").textContent = status[1];
            document.querySelector("#turn").querySelector("a").textContent = status[2];
        })
}


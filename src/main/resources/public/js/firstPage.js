let startBtn = document.getElementById("startBtn");
let loadBtn = document.getElementById("loadBtn");

import {chessBoard} from "./initialize.js";
import {gameResultWindow} from "./initialize.js";
import {initChessBoard} from "./initialize.js";
import {player1} from "./movement.js";
import {player2} from "./movement.js";
import {addChessBoardEvent} from "./movement.js";

loadFirstPage();

function loadFirstPage() {
    chessBoard.style.display = "none";
    gameResultWindow.style.display = "none";
    player1.style.display = "none";
    player2.style.display = "none";
}

function startNewGame() {
    fetch("/startNewGame")
        .then(response => {
            if(!response.ok) {
                throw new Error(response.status);
            }
            return response.json();
        })
        .then(data => {
            console.log(data);
            initChessBoard(data);
            addChessBoardEvent();
            startBtn.style.display = "none";
            loadBtn.style.display = "none";
            chessBoard.style.display = "flex";
            player1.style.display = "flex";
            player2.style.display = "flex";
        })
        .catch(error => {
            console.log(error)
            alert("서버와의 통신이 실패하였습니다.");
        })
}

function loadPrevGame() {
    console.log("load prev game");
}

startBtn.addEventListener("click", startNewGame);
loadBtn.addEventListener("click", loadPrevGame);
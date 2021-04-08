let startBtn = document.getElementById("startBtn");
let loadBtn = document.getElementById("loadBtn");

import {chessBoard, gameResultWindow, initChessBoard} from "./initialize.js";
import {addChessBoardEvent, checkIsPlaying, player1, player2} from "./movement.js";

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
            return response.json();
        })
        .then(data => {
            if (data.availability === "unavailable") {
                alert("현재 진행중인 체스게임을 먼저 마무리해주세요");
            } else if (data.connection === "fail") {
                alert("서버와의 통신에 실패했습니다.")
            } else {
                initializeChessBoard(data);
            }
        })
        .catch(error => { })
}

function loadPrevGame() {
    fetch("/loadPrevGame")
        .then(response => {
            if (!response.ok) {
                throw new Error(response.status);
            }
            return response.json();
        })
        .then(data => {
            initializeChessBoard(data);
            checkIsPlaying(data);
        })
        .catch(error => {
            console.log(error)
            alert("서버와의 통신이 실패하였습니다.");
        })
}

function initializeChessBoard(data) {
    console.log(data);
    initChessBoard(data);
    addChessBoardEvent();
    startBtn.style.display = "none";
    loadBtn.style.display = "none";
    chessBoard.style.display = "flex";
    player1.style.display = "flex";
    player2.style.display = "flex";
}

startBtn.addEventListener("click", startNewGame);
loadBtn.addEventListener("click", loadPrevGame);
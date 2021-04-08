import {gameResultWindow} from "./initialize.js";

let winnerAnnouncement = document.getElementById("winnerAnnouncement");
let finalWhiteTeamScore = document.getElementById("finalWhiteTeamScore");
let finalBlackTeamScore = document.getElementById("finalBlackTeamScore");
let playAgainButton = document.getElementById("playAgainButton");

export function finishGame(data) {
    gameResultWindow.style.display = "flex";
    if (data.currentTurnTeam === "black") {
        winnerAnnouncement.innerText = "🎺 White Team Wins! 🎺";
    } else {
        winnerAnnouncement.innerText = "🎺 Black Team Wins! 🎺";
    }
    finalWhiteTeamScore.innerText = "White Team Score: " + data.teamScore.white;
    finalBlackTeamScore.innerText = "Black Team Score: " + data.teamScore.black;
}

playAgainButton.addEventListener('click', playAgain);

function playAgain() {
    location.reload();
}
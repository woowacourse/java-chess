import {gameResultWindow} from "./initialize.js";
import {saveGameBtn} from "./firstPage.js";

let winnerAnnouncement = document.getElementById("winnerAnnouncement");
let finalWhiteTeamScore = document.getElementById("finalWhiteTeamScore");
let finalBlackTeamScore = document.getElementById("finalBlackTeamScore");
let playAgainButton = document.getElementById("playAgainButton");

export function finishGame(data) {
    saveGameBtn.style.display = "block";
    gameResultWindow.style.display = "flex";
    if (data.currentTurnTeam === "black") {
        winnerAnnouncement.innerText = "🎺 White Team Wins! 🎺";
    } else {
        winnerAnnouncement.innerText = "🎺 Black Team Wins! 🎺";
    }
    finalWhiteTeamScore.innerText = "White Team Score: " + data.whiteTeamScore;
    finalBlackTeamScore.innerText = "Black Team Score: " + data.blackTeamScore;
}

playAgainButton.addEventListener('click', playAgain);

function playAgain() {
    location.reload();
}
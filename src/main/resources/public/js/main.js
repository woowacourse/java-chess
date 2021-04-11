document.getElementById("new-game").addEventListener("click", onNewGame);
document.getElementById("continue").addEventListener("click", onContinue);

const POST = {
    "method": 'POST',
    "headers": {
        'Content-Type': 'application/json',
        'Accept': 'application/json'
    },
}

function getCookie(name) {
    return document.cookie.split("; ").find(row => row.startsWith(name)).split("=")[1];
}

const moveToChessView = function () {
    const chessId = getCookie("chessId");
    window.location.href = '/chess/' + chessId + "/view";
}

async function onNewGame() {
    await fetch('/chess', POST);
    moveToChessView();
}

async function onContinue() {
    if (!document.cookie.includes("chessId")) {
        alert("진행 중인 게임이 없습니다.");
        return;
    }

    const chessId = getCookie("chessId");
    const response = await fetch("/chess/" + chessId);
    const data = await response.json();
    if (!data.status.includes("RUNNING")) {
        alert("진행 중인 게임이 없습니다.");
        return;
    }

    moveToChessView();
}

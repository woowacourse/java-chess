document.getElementById("restart").addEventListener("click", onRestart);

const POST = {
    "method": 'POST',
    "headers": {
        'Content-Type': 'application/json',
        'Accept': 'application/json'
    },
}

const moveToChessView = function () {
    const chessId = getCookie("chessId");
    window.location.href = '/chess/' + chessId + "/view";
}

async function onRestart() {
    await fetch('/chess', POST);
    moveToChessView();
}

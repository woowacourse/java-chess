document.getElementById("new-game").addEventListener("click", onNewGame);
document.getElementById("continue").addEventListener("click", onContinue);

const POST = {
    "method": 'POST',
    "headers" : {
        'Content-Type': 'application/json',
        'Accept': 'application/json'
    },
}

const PATCH = {
    "method": 'PATCH',
    "headers" : {
        'Content-Type': 'application/json',
        'Accept': 'application/json'
    },
}

const DELETE = {
    "method": 'DELETE',
    "headers": {
        'Content-Type': 'application/json',
        'Accept': 'application/json'
    },
}

async function insertNewGame() {
    await fetch('/chess', POST);

    const chessId = getCookie("chessId");
    await fetch('/chess/' + chessId + '/pieces', POST);
}

function getCookie(name) {
    return document.cookie.split("; ").find(row => row.startsWith(name)).split("=")[1];
}

async function removePreviousGame(id) {
    await fetch('/chess/' + id + '/pieces', DELETE);
    await fetch('/chess', DELETE);
}

async function onNewGame() {
    const idResponse = await fetch('/chess/ids');
    const idJson = await idResponse.json();
    const id = idJson.content;
    if (id !== 'EMPTY') {
        await removePreviousGame(id);
    }
    await insertNewGame();
    window.location.href = '/chess';
}

async function onContinue() {
    const idResponse = await fetch('/chess/ids');
    const idJson = await idResponse.json();
    const id = idJson.content;
    if (id === 'EMPTY') {
        alert("진행 중인 게임이 없습니다.");
        return;
    }

    window.location.href = '/chess';
}
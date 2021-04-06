document.getElementById("restart").addEventListener("click", onRestart);

const POST = {
    "method": 'POST',
    "headers": {
        'Content-Type': 'application/json',
        'Accept': 'application/json'
    },
}

const DELETE_OPTION = {
    "method": 'DELETE',
    "headers": {
        'Content-Type': 'application/json',
        'Accept': 'application/json'
    },
}

function getCookie(name) {
    return document.cookie.split("; ").find(row => row.startsWith(name)).split("=")[1];
}

async function onRestart() {
    let chessId = getCookie("chessId");
    await fetch('/chess/' + chessId + '/pieces', DELETE_OPTION);
    await fetch('/chess/' + chessId, DELETE_OPTION);

    await fetch('/chess', POST);

    chessId = getCookie("chessId");
    await fetch('/chess/' + chessId + '/pieces', POST);
    window.location.href = '/chess';
}
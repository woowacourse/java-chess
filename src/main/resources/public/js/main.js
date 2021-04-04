document.getElementById("new-game").addEventListener("click", onNewGame);
document.getElementById("continue").addEventListener("click", onContinue);

const OPTION = {
    "method": 'POST',
}

function onNewGame() {
    fetch('/chess/new', OPTION)
        .then(res => {
            if (!res.ok) {
                throw new Error(res.status);
            }
            window.location.replace(res.url);
            return res;
        })
        .catch(() => alert("서버가 죄송합니다..ㅠㅜ"));
}

function onContinue() {
    fetch('/chess')
        .then(res => {
            if (!res.ok) {
                throw new Error(res.status);
            }
            window.location.href = '/chess/view';
            return res;
        })
        .catch(() => alert("진행 중인 게임이 없습니다."));
}
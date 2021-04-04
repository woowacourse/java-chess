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
            window.location.replace('/chess');
            return res;
        })
        .catch(() => alert("찾지 못한 서버 문제입니다. 서버가 죄송합니다.."));
}

function onContinue() {
    fetch('/chess/continue')
        .then(res => {
            if (!res.ok) {
                throw new Error(res.status);
            }
            window.location.redirect('/chess');
            return res;
        })
        .catch(() => alert("진행 중인 게임이 없습니다."));
}
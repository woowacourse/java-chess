document.getElementById("new-game").addEventListener("click", onNewGame);

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
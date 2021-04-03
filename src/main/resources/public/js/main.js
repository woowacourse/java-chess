document.getElementById("continue").addEventListener("click", onContinue);

function onContinue() {
    fetch('/chess/continue')
        .then(res => {
            if (!res.ok) {
                throw new Error(res.status);
            }
            window.location.href = '/chess';
            return res;
        })
        .catch(() => alert("진행 중인 게임이 없습니다."));
}
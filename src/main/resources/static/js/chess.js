const status = document.getElementById('status-button');

function alertStatus(scoreResult) {
    const blackScore = scoreResult["blackScore"];
    const whiteScore = scoreResult["whiteScore"];
    const winner = scoreResult["winner"];

    window.alert("Black 팀: " + blackScore + "\n" + "White 팀: " + whiteScore + "\n" + "승자: " + winner);
}

status.addEventListener('click', function () {
    fetch('/status')
        .then(handleErrors)
        .then(res => res.json())
        .then(alertStatus)
        .catch(function (error) {
            alert(error.message)
        })
})

async function handleErrors(res) {
    if (!res.ok) {
        let error = await res.json();
        throw Error(error.message);
    }
    return res;
}

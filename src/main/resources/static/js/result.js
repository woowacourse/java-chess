const reset = document.getElementById('reset');
const winDrawLose = document.getElementById('win-draw-lose');
const submit = document.getElementById('submit');
const userNames = document.getElementById('user-names');

let userName = null;

reset.onclick = () => {
    winDrawLose.innerText = "";
};

submit.onclick = () => {
    userName = userNames.value;
    fetch('/userResult', {
        method: 'POST',
        body: JSON.stringify({
            userName
        })
    }).then(res => res.json()).then(gameResult => {
        let {winCount, drawCount, loseCount} = gameResult;
        winDrawLose.innerText = userName + ' : '
            + (winCount + drawCount + loseCount) + "전 " + winCount + "승 "
            + drawCount + "무 " + loseCount + "패";
    })
};

fetch('/viewUsers').then(res => res.json()).then(data => {
    for (let userName of data.userNames) {
        let opt = document.createElement("option");
        opt.value = userName;
        opt.textContent = userName;
        userNames.appendChild(opt);
    }
});
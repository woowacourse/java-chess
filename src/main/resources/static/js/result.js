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
    }).then(res => res.json()).then(data => {
        winDrawLose.innerText = userName + ' : ' + data.winOrDraw;
    })
};

fetch('/viewUsers').then(res => res.json()).then(data => {
    for (key in data.userNames) {
        let opt = document.createElement("option");
        opt.value = data.userNames[key];
        opt.textContent = data.userNames[key];
        userNames.appendChild(opt);
    }
});
let $squares = document.querySelector("table");
let $blackScore = document.querySelector(".black-score");
let $whiteScore = document.querySelector(".white-score");
$squares.addEventListener('click', getPoint);
$blackScore.addEventListener('click', toggleScore);
$whiteScore.addEventListener('click', toggleScore);

function hi() {
    for (let j = 1; j<= 8; j++) {
        for(let i = 0; i<8;i++) {
            let row = String.fromCharCode('a'.charCodeAt(0) + i);
            let point = row + j;
            fetch('/board', {
                method: 'POST',
                body: point,
            }).then(res => res.json()).then(data => {
                document.getElementById(point).innerHTML = renderImage(data);
            });
        }
    }
}

function renderImage(input) {
    if(input === "") {
        return `<img className="piece">`;
    } else{
        return `<img src=${input} class="piece">`;
    }
}

function toggleClicked(sourcePoint, targetPoint) {
    document.getElementById(sourcePoint).classList.toggle("clicked");
    document.getElementById(targetPoint).classList.toggle("clicked");
}

function movePiece(sourcePoint, targetPoint) {
    let sourcePiece = document.getElementById(sourcePoint).innerHTML;
    document.getElementById(sourcePoint).innerHTML = renderImage("");
    document.getElementById(targetPoint).innerHTML = sourcePiece;
    toggleClicked(sourcePoint, targetPoint);
}

function colorData(point) {
    return {
        method: 'POST',
        body: point,
        headers: {
            'Content-Type': 'application/json'
        }
    };
}

async function gameFinishedAlert(targetPoint) {
    await fetch('/color', colorData(targetPoint)).then(function (repsponse) {
        repsponse.text().then(function (text) {
            alert(text + '팀이 승리하였습니다.');
        })
    });
}

async function colorText(targetPoint) {
    await fetch('/color', colorData(targetPoint)).then(function (repsponse) {
        repsponse.text().then(function (text) {
            text;
        })
    });
}

async function move(sourcePoint, targetPoint) {
    let data = {
        source: sourcePoint,
        target: targetPoint
    }

    const response = await fetch('/move', {
        method: 'POST',
        body: JSON.stringify(data),
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(res => res.json());

    if(response === 333) {
        movePiece(sourcePoint, targetPoint);
        document.getElementById("notice").innerText = "게임 끝!";
        if(confirm('킹이 죽어 게임이 끝났습니다.\n 다시 게임하시겠습니까?')){
            await gameFinishedAlert(targetPoint);
            fetch('/rerun')
                .then(location.replace("/chess"));
        } else {
            await gameFinishedAlert(targetPoint);
        }
    }
    if(response === 200) {
        await fetch('/turn', {
            method: 'POST'
        }).then(function (repsponse) {
            repsponse.text().then(function (text) {
                document.getElementById("notice").innerText = text + "팀의 차례입니다."
            })
        });
        movePiece(sourcePoint, targetPoint);
    }
    if (response === 401) {
        alert('불가능한 이동입니다.');
        toggleClicked(sourcePoint, targetPoint);
    }
}

function getPoint(event) {
    let clickedId = event.target.closest("td").id;
    let sourcePoint;
    let targetPoint;

    const list = document.querySelectorAll("td");
    for (let i = 0; i < list.length; i++) {
        if (list[i].classList.contains("clicked")) {
            sourcePoint = list[i].id;
            targetPoint = clickedId;
            document.getElementById(targetPoint).classList.toggle("clicked");
            move(sourcePoint, targetPoint);
            return;
        }
    }
    document.getElementById(clickedId).classList.toggle("clicked");
}

function renderScore(color, score) {
    let scoreDiv = `${score}`;
    if(color === "BLACK") {
        $blackScore.innerHTML = scoreDiv;
    } else {
        $whiteScore.innerHTML = scoreDiv;
    }
}

async function toggleScore() {
    let color = event.target;
    color.classList.toggle("selected");
    if(color.classList.contains("selected")){
        score(color);
    } else {
        color.innerText = "점수 보기";
    }
}

async function score(color) {
    let colorName = color.id;
    const score = await fetch('/score', {
        method: 'POST',
        body: colorName,
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(res => res.json());
    renderScore(colorName, score);
}

hi();
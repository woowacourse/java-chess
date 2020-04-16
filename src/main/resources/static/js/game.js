let cells = document.querySelectorAll('.cell');
const turn = document.getElementById('turn');
const state = document.getElementById('state');
const clickTiming = document.getElementById('clickTiming');
const promotions = document.querySelectorAll('.promotion');
const blackScore = document.getElementById('blackScore');
const whiteScore = document.getElementById('whiteScore');
const blackName = document.getElementById('blackName');
const whiteName = document.getElementById('whiteName');
const winner = document.getElementById('winner');
const closeButton = document.getElementById('close-button');
const choiceGame = document.getElementById('choice-game');
const choiceGameId = document.getElementById('choice-game-id');
const choiceButton = document.getElementById('choice-button');
const roomButton = document.getElementById('room-button');
const resultButton = document.getElementById('result-button');
const resultGame = document.getElementById('result-game');
const newGame = document.getElementById('new-game');
const newGameId = document.getElementById('new-game-id');
const newButton = document.getElementById('new-button');
const newBlackName = document.getElementById('new-black-name');
const newWhiteName = document.getElementById('new-white-name');

let firstClick = true;
let source = null;
let target = null;
let gameId = document.getElementById('gameId').innerText;

roomButton.onclick = () => {
    location.href = '/'
};

resultButton.onclick = () => {
    resultGame.submit();
};

choiceButton.onclick = () => {
    choiceGameId.value = gameId;
    choiceGame.submit();
};

newButton.onclick = () => {
    newGameId.value = gameId;
    newGame.submit();
};

closeButton.onclick = () => {
    fetch('/end', {
        method: 'POST',
        body: JSON.stringify({
            gameId
        })
    }).then(res => res.json()).then(data => {
        gameSetting(data);
        gameFinish();
    })
};

cells.forEach(cell => {
    cell.onclick = () => {
        if (firstClick) {
            source = cell.id;
            firstClick = false;
            document.getElementById('clickTiming').innerText
                = '말이 이동할 경로(after)를 선택하세요.';
            state.innerText = "";
            cell.style.backgroundColor = 'STEELBLUE';
            fetch('/path', {
                method: 'POST',
                body: JSON.stringify({
                    source, gameId
                })
            }).then(res => res.json()).then(data => {
                cells.forEach(cell => {
                    cell.classList.remove('path');
                });
                data.path.forEach(path => {
                    document.getElementById(path).classList.add('path');
                })
            });
            return;
        }
        document.getElementById(source).removeAttribute('style');
        target = cell.id;
        firstClick = true;
        fetch('/move', {
            method: 'POST',
            body: JSON.stringify({
                source, target, gameId
            })
        }).then(res => res.json()).then(data => {
            gameSetting(data);
            if (data.state.includes("왕")) {
                gameFinish();
            }
            document.getElementById('clickTiming').innerText
                = '말이 이동할 경로(before)를 선택하세요.';
        })
    }
});

promotions.forEach(promotion => {
    promotion.onclick = () => {
        let promotionType = promotion.id;
        fetch('/promotion', {
            method: 'POST',
            body: JSON.stringify({
                promotionType, gameId
            })
        }).then(res => res.json()).then(data => {
            gameSetting(data);
        })
    }
});

fetch('/board', {
    method: 'POST',
    body: JSON.stringify({
        gameId
    })
}).then(res => res.json()).then(data => {
    gameSetting(data);
});

function gameSetting(data) {
    cells.forEach(cell => {
        cell.innerHTML = data.pieces.shift();
        cell.classList.remove('path');
    });
    if (typeof data.turn === 'undefined') {
        turn.innerText = "";
    } else {
        turn.innerText = '현재 턴 : ' + data.turn;
    }
    if (typeof data.state === 'undefined') {
        state.innerText = "";
    } else {
        state.innerText = data.state;
    }
    blackScore.innerText = data.blackScore;
    whiteScore.innerText = data.whiteScore;
    blackName.innerText = data.blackName;
    newBlackName.value = data.blackName;
    whiteName.innerText = data.whiteName;
    newWhiteName.value = data.whiteName;
    winner.innerText = data.winner;
}

function gameFinish() {
    cells.forEach(cell => {
        cell.innerHTML = "";
        cell.classList.remove('cell');
    });
    cells = null;
    clickTiming.innerText = '게임이 종료되었습니다.';
    closeButton.innerText = "종료됨";
    closeButton.disable = true;
}
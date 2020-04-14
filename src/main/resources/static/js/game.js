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

let firstClick = true;
let source = null;
let target = null;
let gameId = document.getElementById('gameId').innerText;

closeButton.onclick = () => {
    fetch('/end', {
        method: 'POST',
        body: JSON.stringify({
            gameId
        })
    }).then(res => res.json()).then(data => {
        cells.forEach(cell => {
            cell.innerHTML = data.pieces.shift();
            cell.classList.remove('path');
        });
        cells = null;
        turn.innerText = '';
        state.innerText = '';
        clickTiming.innerText = '게임이 종료되었습니다.';
        blackScore.innerText = data.blackScore;
        whiteScore.innerText = data.whiteScore;
        blackName.innerText = data.blackName;
        whiteName.innerText = data.whiteName;
        winner.innerText = data.winner;
        closeButton.innerText = "게임 종료됨";
        closeButton.disable = true;
    })
};

cells.forEach(cell => {
    cell.onclick = () => {
        if (firstClick) {
            source = cell.id;
            firstClick = false;
            document.getElementById('clickTiming').innerText
                = '말이 이동할 경로(after)를 선택하세요.';
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
        document.getElementById('clickTiming').innerText
            = '말이 이동할 경로(before)를 선택하세요.';
        document.getElementById(source).removeAttribute('style');
        target = cell.id;
        firstClick = true;
        fetch('/move', {
            method: 'POST',
            body: JSON.stringify({
                source, target, gameId
            })
        }).then(res => res.json()).then(data => {
            cells.forEach(cell => {
                cell.innerHTML = data.pieces.shift();
                cell.classList.remove('path');
            });
            turn.innerText = '현재 턴 : ' + data.turn;
            state.innerText = data.state;
            blackScore.innerText = data.blackScore;
            whiteScore.innerText = data.whiteScore;
            blackName.innerText = data.blackName;
            whiteName.innerText = data.whiteName;
            winner.innerText = data.winner;
        })
    }
});

let promotionType = null;
promotions.forEach(promotion => {
    promotion.onclick = () => {
        promotionType = promotion.id;
        fetch('/promotion', {
            method: 'POST',
            body: JSON.stringify({
                promotionType, gameId
            })
        }).then(res => res.json()).then(data => {
            cells.forEach(cell => {
                cell.innerHTML = data.pieces.shift();
                cell.classList.remove('path');
            });
            turn.innerText = '현재 턴 : ' + data.turn;
            state.innerText = data.state;
            blackScore.innerText = data.blackScore;
            whiteScore.innerText = data.whiteScore;
            blackName.innerText = data.blackName;
            whiteName.innerText = data.whiteName;
            winner.innerText = data.winner;
        })
    }
});

fetch('/board', {
    method: 'POST',
    body: JSON.stringify({
        gameId
    })
}).then(res => res.json()).then(data => {
    cells.forEach(cell => {
        cell.innerHTML = data.pieces.shift();
        cell.classList.remove('path');
    });
    turn.innerText = '현재 턴 : ' + data.turn;
    state.innerText = data.state;
    blackScore.innerText = data.blackScore;
    whiteScore.innerText = data.whiteScore;
    blackName.innerText = data.blackName;
    whiteName.innerText = data.whiteName;
    winner.innerText = data.winner;
});

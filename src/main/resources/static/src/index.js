const movePosition = {
    source: undefined,
    target: undefined
};

function changeButton(value) {
    const button = document.getElementById("game-button")
    button.innerText = value;
}

const clickButton = () => {
    const button = document.getElementById("game-button");
    const buttonText = button.innerText;

    if (buttonText.includes("start")) {
        startGame();
    } else if (buttonText.includes("end")) {
        endGame();
    } else if (buttonText.includes("status")) {
        getStatus();
    }
}

const startGame = () => {
    const response = fetch(`/start`, {
        method: "GET",
        headers: {"Content-Type": "application/json"}
    });
    response.then(data => data.json())
        .then(body => {
            drawBoard(body);
            changeButton("end!");
            drawTurnBox();
        });

    movePiece();
}

function drawBoard(body) {
    Object.entries(body).forEach((entry) => {
        const block = document.getElementById(entry[0]);
        if (entry[1].includes('.')) {
            block.innerHTML = null;
            return;
        }
        const imgSrc = "/images/" + entry[1] + ".svg";
        block.innerHTML = '<img id = "piece-image" class="piece-image" src=' + imgSrc + '/>'
    })
}

function initBoard() {
    const blocks = document.querySelectorAll('#chess-board tr td');
    blocks.forEach(block => {
        block.innerHTML = null;
    })
}

function drawTurnBox() {
    const turnBox = document.getElementById("turn-box")

    const response = fetch(`/turn`, {
        method: "GET",
        headers: {"Content-Type": "application/json"}
    });
    response
        .then(data => data.json())
        .then(body => {
            turnBox.innerText = body + "팀 차례!";
            if (body === "NONE") {
                turnBox.innerText = "게임이 끝났습니다.";
            }
        });
}


const movePiece = () => {
    const blocks = document.querySelectorAll('#chess-board tr td');

    blocks.forEach(block => {
        block.addEventListener('click', (e) => clickBLock(e, block));
    })
}

const clickBLock = (e, block) => {
    if (block.className.includes('click')) {
        block.className = block.className.replace('click', '')
        deleteMovePosition(block.id);
    } else {
        block.className = block.className + 'click'
        addMovePosition(block.id)
    }

    if (isMovePositionAllSelected()) {
        const response = fetch(`/move`, {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(movePosition),
        });

        response.then(data => data.json())
            .then(body => {
                drawBoard(body)
                drawTurnBox();
            })
            .catch(err => {
                alert("움직일 수 없는 위치입니다.")
            })
        initTurn();
        setTimeout(kingDeadEndGame);
    }
}

function endGame() {
    removeEventListener();
    changeButton("status!")
    const turnBox = document.getElementById("turn-box")
    turnBox.innerText = "게임 종료";
}

const kingDeadEndGame = () => {
    const response = fetch(`/king/dead`, {
        method: "GET",
        header: {"Content-Type": "application/json"}
    });

    response.then(data => data.json())
        .then(body => {
            if (body === true) {
                alert("왕이 죽었다!")
                endGame();
            }
        })
}

const removeEventListener = () => {
    const blocks = document.querySelectorAll('#chess-board tr td');

    blocks.forEach(block => {
        block.replaceWith(block.cloneNode(true));
    })
}

const getStatus = () => {
    const response = fetch(`/status`, {
        method: "GET",
        header: {"Content-Type": "application/json"}
    });

    response.then(data => data.json())
        .then(body => {
            const turnBox = document.getElementById("turn-box")
            turnBox.innerHTML = "<div> " +
                "<div> BLACK TEAM 점수:" + body.blackScore + "</div>" +
                "<div> WHITE TEAM 점수:" + body.whiteScore + "</div>" +
                "<div> 우승 팀:" + body.winningTeam + "</div>" +
                "</div> "
        })
}

const initTurn = () => {
    const source = document.getElementById(movePosition.source);
    source.className = source.className.replace('click', '');
    const target = document.getElementById(movePosition.target);
    target.className = target.className.replace('click', '');
    movePosition.source = undefined;
    movePosition.target = undefined;
}

const addMovePosition = (position) => {
    if (movePosition.source !== undefined && movePosition.target !== undefined) {
        alert("이미 추가되었습니다!")
        return;
    }
    if (movePosition.source === undefined) {
        movePosition.source = position;
        return;
    }
    movePosition.target = position;
}

const deleteMovePosition = (position) => {
    if (movePosition.source === undefined && movePosition.target === undefined) {
        alert("삭제할 수 없습니다!")
        return;
    }
    if (movePosition.source === position) {
        movePosition.source = undefined;
        return;
    }
    movePosition.target = undefined;
}

const isMovePositionAllSelected = () => {
    return movePosition.source !== undefined && movePosition.target !== undefined;
}

const quit = () => {
    changeButton("start!")
    const turnBox = document.getElementById("turn-box")
    turnBox.innerText = "아직 게임 시작을 하지 않았습니다."
    initBoard();

    fetch(`/exit`, {
        method: "POST",
        headers: {"Content-Type": "application/json"},
    }).catch(error => alert("게임 정보를 삭제하는데 문제가 발생했습니다."));
}
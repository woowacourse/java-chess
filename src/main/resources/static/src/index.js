const movePosition = {
    source: undefined,
    target: undefined
};

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

function drawTurnBox() {
    const turnBox = document.getElementById("turn-box")

    const response = fetch(`/turn`, {
        method: "GET",
        headers: {"Content-Type": "application/json"}
    });
    response
        .then(data => data.json())
        .then(body => {
           turnBox.innerText = body +"팀 차례!";
        });
}

const startGame = () => {
    const response = fetch(`/start`, {
        method: "GET",
        headers: {"Content-Type": "application/json"}
    });
    response.then(data => data.json())
        .then(body => {
            drawBoard(body);
            const button = document.getElementById("start-button")
            button.innerText = "end!";
            drawTurnBox();
        });

    movePiece();
}


const movePiece = () => {
    const blocks = document.querySelectorAll('#chess-board tr td');

    blocks.forEach(block => {
        block.addEventListener('click', (e) => clickBLock(e, block));
    })
}

function clickBLock(e, block) {
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
    }
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
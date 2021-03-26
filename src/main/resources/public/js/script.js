function createChessBoard() {
    const pieces = [
        ["BR", "BN", "BB", "BQ", "BK", "BB", "BN", "BR"],
        new Array(8).fill("BP"),
        new Array(8),
        new Array(8),
        new Array(8),
        new Array(8),
        new Array(8).fill("WP"),
        ["WR", "WN", "WB", "WQ", "WK", "WB", "WN", "WR"]
    ]

    const table = document.getElementById("chess-board");
    for (let i = 0; i < 8; i++) {
        const newTr = document.createElement("tr");
        for (let j = 0; j < 8; j++) {
            const newTd = document.createElement("td");

            const row = String(8 - i); // 열(12345678)
            const asciiNum = 97; // h의 아스키코드
            const column = String.fromCharCode(asciiNum + j);
            newTd.id = column + row;
            if (pieces[i][j]) {
                const piece = document.createElement("img");
                piece.src = "images/" + pieces[i][j] + ".png";
                newTd.appendChild(piece);
            }
            if (i % 2 == j % 2) {
                newTd.className = "block1";
            } else {
                newTd.className = "block2";
            }
            newTr.appendChild(newTd);
        }
        table.appendChild(newTr);
    }
    addEvent();
}

function addEvent() {
    const table = document.getElementById("chess-board");
    table.addEventListener("click", selectPiece);
}

createChessBoard();

function selectPiece(event) {
    const clickPiece = event.target.closest("td");
    const clickedPiece = getClickedPiece();

    // 클릭이 되있을 경우
    if (clickedPiece) {
        clickedPiece.classList.toggle("clicked");
        const sourcePosition = clickedPiece.id;
        const targetPosition = clickPiece.id;
        move(sourcePosition, targetPosition);

        // 아무것도 클릭이 안 되있는 상태 -> 클릭한 것 clicked로 바꾸기
    } else {
        clickPiece.classList.toggle("clicked");
    }
}

function getClickedPiece() {
    const tds = document.getElementsByTagName("td");
    for (let i = 0; i < tds.length; i++) {
        if (tds[i].classList.contains("clicked")) {
            return tds[i];
        }
    }
    return null;
}

async function move(sourcePosition, targetPosition) {
    try {
        const res = await axios({
            method: 'post',
            url: '/move',
            data: {
                source: sourcePosition,
                target: targetPosition
            }
        });
        const data = res.data;
        if (data.code === 401) {
            alert(data.message);
            return;
        }
        if (data.code !== 204) {
            alert(data.message);
            return;
        }
        const source = document.getElementById(sourcePosition);
        const target = document.getElementById(targetPosition);
        const piece = source.getElementsByTagName("img")[0];
        if (target.getElementsByTagName("img")[0]) {
            target.getElementsByTagName("img")[0].remove();
        }
        target.appendChild(piece);

        const isFinished = await checkFinished();
        console.log("isFinished 체크 : ");
        console.log(isFinished);
        if (isFinished === true) {
            const winner = await getWinner();
            if (winner === "WHITE") {
                alert("player1이 이겼습니다!");
            } else if (winner === "BLACK") {
                alert("player2가 이겼습니다!");
            }
        }
        changeTurn();
    } catch (e) {
        console.log(e);
    }
}

async function start() {
    try {
        const res = await axios({
            method: 'post',
            url: '/start',
        });
        const data = res.data;
        if (data.code === 401) {
            alert(data.message);
            return;
        }
        if (data.code !== 204) {
            alert(data.message);
            return;
        }
        alert("게임을 시작합니다.");
        setFirstTurn();
    } catch (e) {
        console.log(e);
    }
}

function setFirstTurn() {
    const $player1 = document.getElementById("player1");
    $player1.className += " turn"
}

function changeTurn() {
    const $players = document.getElementsByClassName("player");
    for (let i = 0; i < $players.length; i++) {
        $players[i].classList.toggle("turn");
    }
}

async function checkFinished() {
    try {
        const res = await axios({
            method: 'get',
            url: '/check/finished',
        });
        console.log("checkFinished 응답값")
        console.log(res);
        const data = res.data;
        if (data.code !== 200) {
            alert(data.message);
            return;
        }
        if (data.code === 200) {
            return data.data.isFinished;
        }
    } catch (e) {
        console.log(e);
    }
}

async function getWinner() {
    try {
        const res = await axios({
            method: 'get',
            url: '/winner',
        });
        console.log("getWinner 응답값")
        console.log(res);
        const data = res.data;
        if (data.code !== 200) {
            alert(data.message);
            return;
        }
        if (data.code === 200) {
            return data.data.winner;
        }
    } catch (e) {
        console.log(e);
    }
}
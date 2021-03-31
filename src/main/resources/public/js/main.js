const startButton = document.getElementById("start");
startButton.addEventListener("click", createChessBoard);

async function createChessBoard() {
    startButton.setAttribute("style", "display: none");
    const response = await fetch("/start")
    .then(res => res.json())

    const responseDto = response.responseDto;
    const boardDto = responseDto.boardDto;
    let pieces = [];
    for (let i = 0; i < 64; i += 8) {
        pieces.push(responseDto.pieceDtos.slice(i, i + 8));
        pieces[i / 8] = pieces[i / 8].map(x => x.name);
    }
    makeTable(pieces, boardDto);
    addClickEventListener();
}

function makeTable(pieces, boardDto) {
    const table = document.createElement('table');
    table.id = 'chess-board';
    for (let i = 0; i < 8; i++) {
        table.appendChild(makeTr(pieces, i));
    }

    const hyperText = document.createElement('h2');
    hyperText.innerHTML = "현재 <span id='user-turn'> White</span>의 턴입니다!";

    const container = document.querySelector(".main-container");
    container.appendChild(hyperText);
    container.appendChild(table);
    document.getElementById('user-turn').innerText = boardDto.team;
}

function makeTr(pieces, row) {
    const newTr = document.createElement("tr");
    for (let j = 0; j < 8; j++) {
        newTr.appendChild(makeTd(pieces, row, j));
    }
    return newTr;
}

function makeTd(pieces, row, col) {
    const newTd = document.createElement("td");
    newTd.id = String.fromCharCode('a'.charCodeAt(0) + col) + String(8 - row);
    if (pieces[row][col] && pieces[row][col] !== ".") {
        const piece = document.createElement("img");
        piece.src = "img/" + pieces[row][col] + ".png";
        newTd.appendChild(piece);
    }
    decideCellStyle(newTd, row, col)
    return newTd;
}

function decideCellStyle(td, row, col) {
    if ((row % 2 === 0 && col % 2 === 0) || (row % 2 === 1 && col % 2 === 1)) {
        td.setAttribute("style",
            "background-color: rgb(204, 204, 204); width: 80px; height: 82px;");
        return;
    }
    td.setAttribute("style",
        "background-color: rgb(000, 102, 051); width: 80px; height: 82px;");
}

function addClickEventListener() {
    const table = document.getElementById("chess-board");
    table.addEventListener("click", onSelectPiece);
}

async function onSelectPiece(event) {
    const nowClickedPiece = event.target.closest("td");
    const pastClickedPiece = decideClickedPiece();

    if (pastClickedPiece === "") {
        if (nowClickedPiece.childElementCount === 0) {
            alert("빈 공간은 선택할 수 없습니다!");
            return;
        }
        nowClickedPiece.classList.toggle("clicked");
        return;
    }
    clearClicked();
    await move(pastClickedPiece.id, nowClickedPiece.id);
    await checkEndGame();
}

function decideClickedPiece() {
    const tds = document.getElementsByTagName("td");
    for (let i = 0; i < tds.length; i++) {
        if (tds[i].classList.contains("clicked")) {
            return tds[i];
        }
    }
    return "";
}

function clearClicked() {
    const tds = document.getElementsByTagName("td");
    for (let i = 0; i < tds.length; i++) {
        if (tds[i].classList.contains("clicked")) {
            tds[i].classList.remove("clicked");
        }
    }
}

async function move(sourcePosition, targetPosition) {
    const data = {
        source: sourcePosition,
        target: targetPosition
    };

    const option = {
        method: 'POST',
        header: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    };

    const response = await fetch("/move", option)
    .then(res => res.json());
    if (response.code === "401") {
        alert(response.message);
        return;
    }
    settingImg(sourcePosition, targetPosition);
    changeTurnText();
}

function settingImg(sourcePosition, targetPosition) {
    const source = document.getElementById(sourcePosition);
    const target = document.getElementById(targetPosition);
    const piece = source.getElementsByTagName("img")[0];
    if (target.getElementsByTagName("img")[0]) {
        target.getElementsByTagName("img")[0].remove();
    }
    target.appendChild(piece);
}

function changeTurnText() {
    if (document.getElementById("user-turn").innerText === 'White') {
        document.getElementById("user-turn").innerText = 'Black';
        return;
    }
    document.getElementById("user-turn").innerText = 'White';
}

async function checkEndGame() {
    const response = await fetch("/end")
    .then(res => res.json());
    if (response.code === "212") {
        alert(response.message);
        location.replace('/result');
    }
}

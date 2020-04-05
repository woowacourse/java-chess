const chessBoard = document.querySelector("#chess-board tbody");

for (let i = 8; i > 0; i--) {
    const tableRow = document.createElement("TR");

    for (let j = 1; j <= 8; j++) {
        const tableColumn = document.createElement("TD");
        tableColumn.setAttribute("id", String.fromCharCode(j + 'a'.charCodeAt(0) - 1) + i);
        tableColumn.classList.add("cell", colorCell(i, j));
        tableRow.appendChild(tableColumn);
    }
    chessBoard.appendChild(tableRow);
}

function colorCell(i, j) {
    return (i + j) % 2 === 0 ? "white-cell" : "black-cell";
}

//새 게임 클릭 시 초기화 이벤트 관련 =>  모든 초기화 말 json 데이터 가져오도록 한다.
//document.querySelectorAll(".cell").forEach(node => node.addEventListener('click', e => console.log(e.target)));
const cells = document.querySelectorAll("td.cell");
cells.forEach(node => node.addEventListener('click', e => {
    toggleCell(e.target);
    const count = findCountOfToggled(cells);
    if (count === 0) {
        localStorage.clear();
    } else if (count === 1) {
        localStorage.setItem('from', e.target.id);
    } else if (count === 2) {
        const obj = {'from': localStorage.getItem('from'), 'to': e.target.id}
        localStorage.clear();
        removeAllClickedToggle(cells);
        httpPostRequest(JSON.stringify(obj));
    }
}));

function toggleCell(node) {
    if (node.classList.contains("clicked")) {
        node.classList.remove("clicked");
    } else {
        node.classList.add("clicked");
    }
}

function findCountOfToggled(nodes) {
    return Array.from(nodes).filter(node => node.classList.contains("clicked"))
        .length
}

function removeAllClickedToggle(nodes) {
    nodes.forEach(node => node.classList.remove("clicked"));
}

function httpPostRequest(data) {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            updatePieceOnBoard(xhttp);
            requestRecord();
        }
    };
    xhttp.open("POST", "/chess/move", true);
    xhttp.send(data);
}


const newGameButton = document.getElementById("new-game");
newGameButton.addEventListener('click', requestNewGame);

function requestNewGame() {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            updatePieceOnBoard(xhttp);
            requestRecord();
        }
    };
    xhttp.open("GET", "/chess/start", true);
    xhttp.send();
}

function updatePieceOnBoard(xhttp) {
    //모든 포지션, 블럭 정보 가져온다음에, 각 포지션마다 말 위치 시켜준다.
    //position : {row: 3, col:3}, piece: {symbol:p, team: black}
    const piecesToUpdate = JSON.parse(xhttp.responseText);
    console.log(piecesToUpdate);
    moveAllPieces(piecesToUpdate);
}

function moveAllPieces(piecesToUpdate) {
    for (let i = 0; i < piecesToUpdate.length; i++) {
        movePiece(piecesToUpdate[i]);
    }
}

function movePiece(piecesToUpdate) {
    //position : {row: 3, col:3}, piece: {symbol:p, team: black}
    const position = piecesToUpdate["position"];
    const symbol = piecesToUpdate["symbol"];
    const team = piecesToUpdate["team"];
    const element = document.querySelector("#" + position);
    element.innerText = position + symbol + team;
}

function requestRecord() {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let data = xhttp.responseText;
            const parse = JSON.parse(data);
            updateRecord(parse);
        }
    };
    xhttp.open("GET", "/chess/record", true);
    xhttp.send();
}

function updateRecord(parse) {
    for (let i = 0; i < parse.length; i++) {
        let select = "";
        if (parse[i]["team"] === "black") {
            select = document.querySelector(".black-team");
        } else {
            select = document.querySelector(".white-team");
        }
        select.innerHTML = parse[i]["score"];
    }
}
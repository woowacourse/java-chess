const white_bishop = img_create("../chess-img/bishop_white.png");
const white_rook = img_create("../chess-img/rook_white.png");
const white_knight = img_create("../chess-img/knight_white.png");
const white_queen = img_create("../chess-img/queen_white.png");
const white_king = img_create("../chess-img/king_white.png");
const white_pawn = img_create("../chess-img/pawn_white.png");
const black_bishop = img_create("../chess-img/bishop_black.png");
const black_rook = img_create("../chess-img/rook_black.png");
const black_knight = img_create("../chess-img/knight_black.png");
const black_queen = img_create("../chess-img/queen_black.png");
const black_king = img_create("../chess-img/king_black.png");
const black_pawn = img_create("../chess-img/pawn_black.png");

function img_create(src) {
    const img = document.createElement('IMG');
    img.src = src;
    img.classList.add("piece");
    return img;
}
const chessBoard = document.querySelector("#chess-board tbody");

for (let i = 8; i > 0; i--) {
    const tableRow = document.createElement("TR");

    for (let j = 1; j <= 8; j++) {
        const tableColumn = document.createElement("TD");
        tableColumn.setAttribute("id", String.fromCharCode(j + 'a'.charCodeAt(0) - 1) + i);
        tableColumn.classList.add("cell-size", "cell", colorCell(i, j));
        tableRow.appendChild(tableColumn);
    }
    chessBoard.appendChild(tableRow);
}

function colorCell(i, j) {
    return (i + j) % 2 === 0 ? "white-cell" : "black-cell";
}


const cells = document.querySelectorAll("td.cell");
cells.forEach(node => node.addEventListener('click', e => {
    const clickedCell = e.currentTarget;
    console.log(clickedCell);
    toggleCell(clickedCell);
    const count = findCountOfToggled(cells);
    if (count === 0) {
        localStorage.clear();
    } else if (count === 1) {
        localStorage.setItem('from', clickedCell.id);
    } else if (count === 2) {
        const obj = {'from': localStorage.getItem('from'), 'to': clickedCell.id}
        localStorage.clear();
        removeAllClickedToggle(cells);
        requestMovePieces(JSON.stringify(obj));
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

function renderCurrentGameState(currentState) {
    const turn = currentState["turn"];
    const state = currentState["gameState"];
    document.getElementById("current-turn").innerText = turn;
    document.getElementById("exception-message").innerText = state;
}

function updateGameState() {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            renderCurrentGameState(JSON.parse(xhttp.responseText));
        }
    };
    xhttp.open("GET", "/chess/state", true);
    xhttp.send();
}

function requestMovePieces(data) {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            updatePieceOnBoard(xhttp);
            requestRecord();
            checkWhetherGameIsFinished();
            updateGameState();
        } else if(this.status === 500) {
            console.log(this.responseText);
        }
    };
    xhttp.open("POST", "/chess/move", true);
    xhttp.send(data);
}

function checkWhetherGameIsFinished() {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            if (!JSON.parse(xhttp.responseText)) {
                requestWinner();
            }
        }
    };
    xhttp.open("GET", "/chess/game/isnotfinish", true);
    xhttp.send();
}

document.getElementById("end-game").addEventListener('click', requestEndGame);
document.getElementById("new-game").addEventListener('click', requestNewGame);

function requestEndGame() {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            updateGameState();
            requestWinner();
        }
    };
    xhttp.open("GET", "/chess/end", true);
    xhttp.send();
}

function requestWinner() {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            alert(xhttp.responseText)
        }
    };
    xhttp.open("GET", "/chess/result/winner", true);
    xhttp.send();
}

function requestNewGame() {
    cleanBoard();
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            requestAllPieces();
            updateGameState();
        }
    };
    xhttp.open("GET", "/chess/start", true);
    xhttp.send();
}

function cleanBoard() {
    cells.forEach(cell => cell.innerHTML = '');
}

function updatePieceOnBoard(xhttp) {
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
    const position = piecesToUpdate["position"];
    const symbol = piecesToUpdate["symbol"];
    const team = piecesToUpdate["team"];
    const element = document.querySelector("#" + position);
    renderPiece(element, symbol, team);
}

function renderPiece(element, symbol, team) {
    let img;
    element.innerHTML = '';
    if (team === 'none') {
        return;
    }
    if (team === 'black') {
        switch (symbol) {
            case 'p': img = black_pawn.cloneNode(true); break;
            case 'b': img = black_bishop.cloneNode(true); break;
            case 'r': img = black_rook.cloneNode(true); break;
            case 'n': img = black_knight.cloneNode(true); break;
            case 'q': img = black_queen.cloneNode(true); break;
            case 'k': img = black_king.cloneNode(true);
        }
    } else {
        switch (symbol) {
            case 'p': img = white_pawn.cloneNode(true); break;
            case 'b': img = white_bishop.cloneNode(true); break;
            case 'r': img = white_rook.cloneNode(true); break;
            case 'n': img = white_knight.cloneNode(true); break;
            case 'q': img = white_queen.cloneNode(true); break;
            case 'k': img = white_king.cloneNode(true);
        }
    }
    element.appendChild(img);
}

function requestRecord() {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            console.log(this);
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

function requestAllPieces() {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            updatePieceOnBoard(xhttp);
            requestRecord();
        }
    };
    xhttp.open("GET", "/chess/pieces", true);
    xhttp.send();
}

window.addEventListener('load', initialLoad);
function initialLoad() {
    updateGameState();
    requestAllPieces();
}
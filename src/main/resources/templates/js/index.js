const createImage = function img_create(src) {
    const img = document.createElement('IMG');
    img.src = src;
    img.classList.add("piece");
    return img;
};
const white_bishop = createImage("../chess-img/bishop_white.png");
const white_rook = createImage("../chess-img/rook_white.png");
const white_knight = createImage("../chess-img/knight_white.png");
const white_queen = createImage("../chess-img/queen_white.png");
const white_king = createImage("../chess-img/king_white.png");
const white_pawn = createImage("../chess-img/pawn_white.png");
const black_bishop = createImage("../chess-img/bishop_black.png");
const black_rook = createImage("../chess-img/rook_black.png");
const black_knight = createImage("../chess-img/knight_black.png");
const black_queen = createImage("../chess-img/queen_black.png");
const black_king = createImage("../chess-img/king_black.png");
const black_pawn = createImage("../chess-img/pawn_black.png");

const endGameButton = document.getElementById("end-game");
const newGameButton = document.getElementById("new-game");

const chessBoard = document.querySelector("#chess-board tbody");

const currentTurn = document.getElementById("current-turn");
const currentState = document.getElementById("current-state");

const colorCell = function colorCell(i, j) {
    return (i + j) % 2 === 0 ? "white-cell" : "black-cell";
};

(function() {
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
}());

const cells = document.querySelectorAll("td.cell");

const toggleCell = function toggleCell(node) {
    if (node.classList.contains("clicked")) {
        node.classList.remove("clicked");
    } else {
        node.classList.add("clicked");
    }
};

const findCountOfToggled = function findCountOfToggled(nodes) {
    return Array.from(nodes).filter(node => node.classList.contains("clicked"))
        .length
};

const removeAllClickedToggle = function removeAllClickedToggle(nodes) {
    nodes.forEach(node => node.classList.remove("clicked"));
};

const renderCurrentGameState = function renderCurrentGameState(currentTurnAndState) {
    const turn = currentTurnAndState["turn"];
    const state = currentTurnAndState["gameState"];
    currentTurn.innerText = turn;
    currentState.innerText = state;
};

const updateGameState = function updateGameState() {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            const data = JSON.parse(xhttp.responseText);
            if (data['status'] === 'ERROR') {
                alert(data['message']);
                return;
            }
            renderCurrentGameState(data['data']);
        }
    };
    xhttp.open("GET", "/chess/state", true);
    xhttp.send();
};

const requestMovePieces = function requestMovePieces(data) {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            const data = JSON.parse(xhttp.responseText);
            if (data['status'] === 'ERROR') {
                alert(data['message']);
                return;
            }
            updatePieceOnBoard(data['data']);
            requestRecord();
            checkWhetherGameIsFinished();
            updateGameState();
        } else if (this.status === 500) {
            console.log(this.responseText);
        }
    };
    xhttp.open("POST", "/chess/move", true);
    xhttp.send(data);
};

const checkWhetherGameIsFinished = function checkWhetherGameIsFinished() {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            const data = JSON.parse(xhttp.responseText);
            if (data['status'] === 'ERROR') {
                alert(data['message']);
                return;
            }
            if (!data['data']) {
                requestWinner();
            }
        }
    };
    xhttp.open("GET", "/chess/isnotfinish", true);
    xhttp.send();
};

const requestEndGame = function requestEndGame() {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            const data = JSON.parse(xhttp.responseText);
            if (data['status'] === 'ERROR') {
                alert(data['message']);
                return;
            }
            updateGameState();
            requestWinner();
        }
    };
    xhttp.open("POST", "/chess/state", true);
    xhttp.send("end");
};

const requestWinner = function requestWinner() {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            const data = JSON.parse(xhttp.responseText);
            if (data['status'] === 'ERROR') {
                alert(data['message']);
                return;
            }
            alert("승리 : " + data['data']);
        }
    };
    xhttp.open("GET", "/chess/result", true);
    xhttp.send();
};

const requestNewGame = function requestNewGame() {
    cleanBoard();
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            const data = JSON.parse(xhttp.responseText);
            if (data['status'] === 'ERROR') {
                alert(data['message']);
                return;
            }
            requestAllPieces();
            updateGameState();
        }
    };
    xhttp.open("POST", "/chess/state", true);
    xhttp.send("start");
};

const cellClickListener = function cellClickListener() {
    return e => {
        const clickedCell = e.currentTarget;
        console.log(clickedCell);
        toggleCell(clickedCell);
        const count = findCountOfToggled(cells);
        if (count === 0) {
            localStorage.clear();
        } else if (count === 1) {
            localStorage.setItem('from', clickedCell.id);
        } else if (count === 2) {
            const obj = {'from': localStorage.getItem('from'), 'to': clickedCell.id};
            localStorage.clear();
            removeAllClickedToggle(cells);
            requestMovePieces(JSON.stringify(obj));
        }
    };
};

cells.forEach(node => node.addEventListener('click', cellClickListener()));

const cleanBoard = function cleanBoard() {
    cells.forEach(cell => cell.innerHTML = '');
};

const updatePieceOnBoard = function updatePieceOnBoard(datas) {
    console.log(datas);
    moveAllPieces(datas);
};

const moveAllPieces = function moveAllPieces(piecesToUpdate) {
    for (let i = 0; i < piecesToUpdate.length; i++) {
        movePiece(piecesToUpdate[i]);
    }
};

const movePiece = function movePiece(piecesToUpdate) {
    const position = piecesToUpdate["position"];
    const symbol = piecesToUpdate["symbol"];
    const element = document.querySelector("#" + position);
    renderPiece(element, symbol);
};

const renderPiece = function renderPiece(element, symbol) {
    let img;
    element.innerHTML = '';
    if (symbol === '.') {
        return;
    }

    switch (symbol) {
        case 'P':
            img = black_pawn.cloneNode(true);
            break;
        case 'B':
            img = black_bishop.cloneNode(true);
            break;
        case 'R':
            img = black_rook.cloneNode(true);
            break;
        case 'N':
            img = black_knight.cloneNode(true);
            break;
        case 'Q':
            img = black_queen.cloneNode(true);
            break;
        case 'K':
            img = black_king.cloneNode(true);
            break;
        case 'p':
            img = white_pawn.cloneNode(true);
            break;
        case 'b':
            img = white_bishop.cloneNode(true);
            break;
        case 'r':
            img = white_rook.cloneNode(true);
            break;
        case 'n':
            img = white_knight.cloneNode(true);
            break;
        case 'q':
            img = white_queen.cloneNode(true);
            break;
        case 'k':
            img = white_king.cloneNode(true);
    }
    element.appendChild(img);
};

const requestRecord = function requestRecord() {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            const data = JSON.parse(xhttp.responseText);
            if (data['status'] === 'ERROR') {
                alert(data['message']);
                return;
            }
            updateRecord(data['data']);
        }
    };
    xhttp.open("GET", "/chess/record", true);
    xhttp.send();
};

const updateRecord = function updateRecord(datas) {
    for (let i = 0; i < datas.length; i++) {
        let select = "";
        if (datas[i]["team"] === "black") {
            select = document.querySelector(".black-team");
        } else {
            select = document.querySelector(".white-team");
        }
        select.innerHTML = datas[i]["score"];
    }
};

const requestAllPieces = function requestAllPieces() {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            const data = JSON.parse(xhttp.responseText);
            if (data['status'] === 'ERROR') {
                alert(data['message']);
                return;
            }
            updatePieceOnBoard(data['data']);
            requestRecord();
        }
    };
    xhttp.open("GET", "/chess/pieces", true);
    xhttp.send();
};

const initialLoad = function initialLoad() {
    updateGameState();
    requestAllPieces();
};

endGameButton.onclick = requestEndGame;
newGameButton.onclick = requestNewGame;
window.onload = initialLoad;

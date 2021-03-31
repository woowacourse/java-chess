function getBaseUrl() {
    return 'http://localhost:8080/chessgame/' + roomNumber;
}

function initiate() {
    const xmlHttp = new XMLHttpRequest();
    const url = getBaseUrl() + '/update';
    xmlHttp.onreadystatechange = function () {
        if (isValidHttpResponse(xmlHttp)) {
            const boardDTO = JSON.parse(this.responseText);
            if (boardDTO.isCheckmate === true) {
                window.location.href = 'chessboard/result';
            }
            printChessBoard(boardDTO.rows, boardDTO.currentTeamType);
        }
    }
    xmlHttp.open('GET', url, true);
    xmlHttp.send();
}

function isValidHttpResponse(xmlHttp) {
    return xmlHttp.readyState === 4 && xmlHttp.status === 200;
}

function printChessBoard(rows, currentTeamType) {
    printEachRow(rows);
    changeCurrentTeamType(currentTeamType);
    Array.from(document.getElementsByClassName('piece'))
        .forEach(piece => addMovableEvent(piece, currentTeamType));
}

function printEachRow(rows) {
    const board = document.getElementById('board');
    const pieceTemplate = document.querySelector('#template-list-piece').innerHTML;
    for (let i = 0; i < rows.length; i++) {
        const columns = rows[i].pieces;
        for (let j = 0; j < columns.length; j++) {
            const piece = columns[j];
            const coordinate = calculateCoordinate(i, j);
            const pieceHtmlNode = pieceTemplate.replace('{url}', generatePieceUrl(piece))
                .replace('{coordinate}', coordinate);
            board.insertAdjacentHTML('beforeend', pieceHtmlNode);
        }
    }
}

function calculateCoordinate(i, j) {
    const x = j + 97;
    const y = 8 - i;
    return String.fromCharCode(x) + y;
}

function generatePieceUrl(piece) {
    if (piece === null) {
        return "Blank";
    }
    const teamPrefix = (piece.teamType === "BLACK") ? "B" : "W";
    return teamPrefix + piece.name.toUpperCase();
}

function changeCurrentTeamType(currentTeamType) {
    const teamTypeElement = document.querySelector('.current-team-type');
    teamTypeElement.textContent = currentTeamType;
}

function addMovableEvent(piece, currentTeamType) {
    piece.addEventListener('click', function (event) {
        event.stopPropagation();
        const current = document.querySelector('.current');
        if (isPieceChosenForTheFirst(current)) {
            current.value = piece.id;
            piece.style.border = '1px solid red';
            return;
        }
        if (isPieceChosenAlready(piece)) {
            current.value = '';
            piece.style.border = '1px solid black';
            return;
        }
        sendMoveRequest(current.value, piece.id, currentTeamType);
        current.value = '';
    });
}

function isPieceChosenForTheFirst(current) {
    return current.value.length === 0;
}

function isPieceChosenAlready(piece) {
    return piece.style.border === '1px solid red';
}

function sendMoveRequest(current, destination, currentTeamType) {
    const requestData = JSON.stringify({
        "current": current,
        "destination": destination,
        "teamType": currentTeamType
    });
    const xmlHttp = new XMLHttpRequest();
    const url = getBaseUrl() + '/move';
    xmlHttp.onreadystatechange = function () {
        if (isValidHttpResponse(xmlHttp)) {
            removeOutdatedChessBoard();
            const boardDTO = JSON.parse(this.responseText);
            if (boardDTO.isCheckmate === true) {
                window.location.href = '/result';
            }
            printChessBoard(boardDTO.rows, boardDTO.currentTeamType);
            return;
        }
        if (isExceptionThrown(xmlHttp)) {
            alert(this.responseText);
            const currentPiece = document.getElementById(current);
            currentPiece.style.border = '1px solid black';
        }
    }
    xmlHttp.open('POST', url, true);
    xmlHttp.send(requestData);
}

function removeOutdatedChessBoard() {
    Array.from(document.getElementsByClassName('piece'))
        .forEach(t => t.remove());
}

function isExceptionThrown(xmlHttp) {
    return (xmlHttp.readyState === 4 && xmlHttp.status === 500);
}

initiate();




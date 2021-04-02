function initiate() {
    const xmlHttp = new XMLHttpRequest();
    const url = getBaseUrl() + '/show';
    xmlHttp.onreadystatechange = function () {
        if (isValidHttpResponse(this)) {
            const boardDTO = JSON.parse(this.responseText);
            if (boardDTO.isCheckmate === true) {
                window.location.href = 'http://localhost:8080/result/' + roomId;
                return;
            }
            printChessBoard(boardDTO.rows, boardDTO.currentTeamType);
        }
    }
    xmlHttp.open('GET', url, true);
    xmlHttp.send();
}

function getBaseUrl() {
    return 'http://localhost:8080/chessgame/' + roomId;
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
        return 'Blank';
    }
    const teamPrefix = (piece.teamType === 'BLACK') ? 'B' : 'W';
    return teamPrefix + piece.name.toUpperCase();
}

function changeCurrentTeamType(currentTeamType) {
    const currentTeamTypeNode = document.querySelector('.current-team-type');
    currentTeamTypeNode.textContent = currentTeamType;
}

function addMovableEvent(piece, currentTeamType) {
    piece.addEventListener('click', function (event) {
        event.stopPropagation();
        const currentPosition = document.querySelector('.current-position');
        if (isPieceChosenForTheFirst(currentPosition)) {
            currentPosition.value = piece.id;
            piece.style.border = '1px solid red';
            return;
        }
        if (isPieceChosenAlready(piece)) {
            currentPosition.value = '';
            piece.style.border = '1px solid black';
            return;
        }
        sendMoveRequest(currentPosition.value, piece.id, currentTeamType);
        currentPosition.value = '';
    });
}

function isPieceChosenForTheFirst(current) {
    return current.value.length === 0;
}

function isPieceChosenAlready(piece) {
    return piece.style.border === '1px solid red';
}

function sendMoveRequest(current, destination, teamType) {
    const xmlHttp = new XMLHttpRequest();
    const url = getBaseUrl() + '/move';
    const requestData = JSON.stringify({
        "current": current,
        "destination": destination,
        "teamType": teamType
    });
    xmlHttp.onreadystatechange = function () {
        if (isValidHttpResponse(this)) {
            removeOutdatedChessBoard();
            const boardDTO = JSON.parse(this.responseText);
            if (boardDTO.isCheckmate === true) {
                window.location.href = '/result/' + roomId;
                return;
            }
            printChessBoard(boardDTO.rows, boardDTO.currentTeamType);
            return;
        }
        if (isExceptionThrown(this)) {
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
        .forEach(piece => piece.remove());
}

function isExceptionThrown(xmlHttp) {
    return (xmlHttp.readyState === 4 && xmlHttp.status === 500);
}

initiate();




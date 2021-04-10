const url = new URL(window.location.href);
const serchParam = url.searchParams;
const roomId = serchParam.get('id');

const piecesMap = {
    "P": "♟", "R": "&#9820;", "N": "&#9822;", "B": "&#9821;", "Q": "&#9819;", "K": "&#9818;",
    "p": "&#9817;", "r": "&#9814;", "n": "&#9816;", "b": "&#9815;", "q": "&#9813;", "k": "&#9812;"
}

loadChessGame();

function loadChessGame() {
    axios.get('/loadChessGame?id=' + roomId)
        .then(function (response) {
            refreshChessBoard(response.data)
        }).catch(function (error) {
        alert('게임을 로드 할 수 없습니다.')
    });
}

const btnStart = document.getElementsByClassName('btn-start')[0];
btnStart.addEventListener('click', function (e) {
    axios.get('/refreshChessGame?id=' + roomId)
        .then(function (response) {
            refreshChessBoard(response.data);
        })
        .catch(function (error) {
            alert('게임을 갱신 할 수 없습니다.')
        });
})

const tiles = document.getElementsByClassName('tile');
for (let i = 0; i < tiles.length; i++) {
    tiles.item(i).addEventListener('click', function (e) {
        let haveSelected = document.getElementsByClassName('selected-piece').length > 0;
        if (haveSelected) {
            movePiece(e.target);
        } else {
            selectPiece(e.target);
        }
    })
}

function selectPiece(target) {
    let position = target.getAttribute('id');
    axios.get('/selectPiece?id=' + roomId + '&position=' + position)
        .then(function (response) {
            target.classList.add('selected-piece');
        })
        .catch(function (error) {
            alert('선택할 수 없습니다.');
        })
}

function movePiece(target) {
    console.log('movePiece() called');
    let selectedPiece = document.getElementsByClassName('selected-piece')[0];
    axios.get('/movePiece?id=' + roomId + '&selected=' + selectedPiece.id + '&target=' + target.id)
        .then(function (response) {
            refreshChessBoard(response.data);
        })
        .catch(function (error) {
            alert('움직일 수 없습니다.');
        })
        .then(function () {
            clearSelect();
        })
}

function clearSelect() {
    let selectedPiece = document.getElementsByClassName('selected-piece');
    for (let i = 0; i < selectedPiece.length; i++) {
        selectedPiece[i].classList.remove('selected-piece');
    }
}

function refreshChessBoard(chessGame) {
    console.log(chessGame);
    let isRunning = chessGame.isRunning;
    clearChessBoard();
    if (isRunning) {
        let blackPieces = chessGame.blackTeamDTO.piecesDto.pieceDtoList;
        console.log(blackPieces);
        for (let i = 0; i < blackPieces.length; i++) {
            let piece = blackPieces[i]
            let tile = document.getElementById(piece.position);
            console.log(piece.position);
            tile.classList.add('piece');
            tile.classList.add(piece.piece);
            tile.innerHTML = piecesMap[piece.piece];
        }

        let whitePieces = chessGame.whiteTeamDTO.piecesDto.pieceDtoList;
        console.log(whitePieces);
        for (let i = 0; i < whitePieces.length; i++) {
            let piece = whitePieces[i];
            console.log(piece.position);
            let tile = document.getElementById(piece.position);
            tile.classList.add('piece');
            tile.classList.add(piece.piece);
            tile.innerHTML = piecesMap[piece.piece];
        }

        let blackScore = chessGame.blackTeamDTO.score;
        let whiteScore = chessGame.whiteTeamDTO.score;
        document.getElementById('score-white').innerHTML = whiteScore;
        document.getElementById('score-black').innerHTML = blackScore;

        if (chessGame.blackTeamDTO.isTurn) {
            document.getElementById('name-black').innerHTML = chessGame.blackTeamDTO.name + "♟";
            document.getElementById('name-white').innerHTML = chessGame.whiteTeamDTO.name;
        } else if (chessGame.whiteTeamDTO.isTurn) {
            document.getElementById('name-black').innerHTML = chessGame.blackTeamDTO.name;
            document.getElementById('name-white').innerHTML = chessGame.whiteTeamDTO.name + "&#9817;";
        }
    } else {
        alert('게임이 종료 되었습니다.')
    }
}

function clearChessBoard() {
    let pieces = document.getElementsByClassName('piece');
    for (let i = 0; i < pieces.length; i++) {
        pieces[i].innerHTML = "";
    }
}





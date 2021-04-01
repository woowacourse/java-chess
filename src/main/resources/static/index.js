const piecesMap = {
    "P": "♟", "R": "&#9820;", "N": "&#9822;", "B": "&#9821;", "Q": "&#9819;", "K": "&#9818;",
    "p": "&#9817;", "r": "&#9814;", "n": "&#9816;", "b": "&#9815;", "q": "&#9813;", "k": "&#9812;"
}


const btnStart = document.getElementsByClassName('btn-start')[0];
btnStart.addEventListener('click', function (e) {
    axios.get('/startChessGame')
        .then(function (response) {
            let data = response.data;
            if (data.success) {
                refreshChessBoard(data.data)
            } else {
                alert(data.message)
            }
        })
})


const btnEnd = document.getElementsByClassName('btn-end')[0]
btnEnd.addEventListener('click', function (e) {
    axios.get('/endChessGame')
        .then(function (response) {
            let data = response.data;
            if (data.success) {
                clearChessBoard();
                alert(data.message);
            } else {
                alert(data.message);
            }
        })
})

const tiles = document.getElementsByClassName('tile');
for (let i = 0; i < tiles.length; i++) {
    tiles.item(i).addEventListener('click', function (e) {
        let haveSelected = document.getElementsByClassName('selected-piece').length > 0;
        console.log(haveSelected);
        if (haveSelected) {
            movePiece(e.target);
        } else {
            selectPiece(e.target);
        }
    })
}

function selectPiece(target) {
    let position = target.getAttribute('id');
    axios.get('/selectPiece?position=' + position)
        .then(function (response) {
            console.log(response);
            let data = response.data;
            if (data.success) {
                target.classList.add('selected-piece');
            } else {
                alert(data.message)
            }
        })
        .catch(function (error) {
        })
}

function movePiece(target) {
    console.log('movePiece() called');
    let selectedPiece = document.getElementsByClassName('selected-piece')[0];
    axios.get('/movePiece?selected=' + selectedPiece.id + '&target=' + target.id)
        .then(function (response) {
            let data = response.data;
            if (data.success) {
                refreshChessBoard(data.data);
            } else {
                alert(data.message);
            }
        })
        .catch(function (error) {
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

function refreshChessBoard(data) {
    let chessGame = JSON.parse(data);
    let isRunning = chessGame.isRunning;
    clearChessBoard();
    if (isRunning) {
        let pieces = chessGame.piecesDto.pieceDtoList;
        console.log(pieces);
        for (let i = 0; i < pieces.length; i++) {
            let piece = pieces[i]
            let tile = document.getElementById(piece.position);
            tile.classList.add('piece');
            tile.classList.add(piece.piece);
            tile.innerHTML = piecesMap[piece.piece];
        }
        let blackScore = chessGame.blackTeam.score;
        let whiteScore = chessGame.whiteTeam.score;
        document.getElementById('score-white').innerHTML = whiteScore;
        document.getElementById('score-black').innerHTML = blackScore;

        if (chessGame.blackTeam.isTurn) {
            document.getElementById('name-black').innerHTML = chessGame.blackTeam.name + "♟";
            document.getElementById('name-white').innerHTML = chessGame.whiteTeam.name;
        } else if (chessGame.whiteTeam.isTurn) {
            document.getElementById('name-black').innerHTML = chessGame.blackTeam.name;
            document.getElementById('name-white').innerHTML = chessGame.whiteTeam.name + "&#9817;";
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





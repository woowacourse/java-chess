const piecesMap = {"P" : "♟", "R" : "&#9820;", "N" : "&#9822;", "B" : "&#9821;", "Q" : "&#9819;", "K" : "&#9818;",
"p" : "&#9817;", "r" : "&#9814;", "n" : "&#9816;", "b" : "&#9815;", "q" : "&#9813;", "k" : "&#9812;"}


const btnStart = document.getElementsByClassName('btn-start')[0];
btnStart.addEventListener('click', function (e) {
    axios.get('/startChessGame')
        .then(function (response) {
            let data = response.data;
            if (data.success) {
                getChessBoardData(refreshChessBoard);
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
            } else {
                alert(data.message);
            }
        })
})

const tiles = document.getElementsByClassName('tile');
for (let i = 0; i < tiles.length; i++) {
    tiles.item(i).addEventListener('click', function(e) {
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
    axios.get('/selectPiece?position='+position)
        .then(function (response) {
            console.log(response);
            let data = response.data;
            if(data.success) {
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
    axios.get('/movePiece?selected='+selectedPiece.id+'&target='+target.id)
        .then(function (response) {
            let data = response.data;
            if(data.success) {
               getChessBoardData();
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

function getChessBoardData() {
    axios.get('/refreshChessBoard')
        .then(function (response) {
            refreshChessBoard(response);
        })
}

function refreshChessBoard(response) {
    console.log(response);
    let isRunning = response.data.isRunning;
    clearChessBoard();
    if (isRunning) {
        let pieces = response.data.piecesDto.pieceDtoList;
        console.log(pieces);
        for (let i = 0; i < pieces.length; i++) {
            let piece = pieces[i]
            let tile = document.getElementById(piece.position);
            tile.classList.add('piece');
            tile.classList.add(piece.piece);
            tile.innerHTML = piecesMap[piece.piece];
        }
        let blackScore = response.data.blackTeam.score;
        console.log('blackScore : ' + blackScore);
        let whiteScore = response.data.whiteTeam.score;
        console.log('whiteScore : ' + whiteScore);
        document.getElementById('score-white').innerHTML = whiteScore;
        document.getElementById('score-black').innerHTML = blackScore;

        if (response.data.blackTeam.isTurn) {
            document.getElementById('name-black').innerHTML = response.data.blackTeam.name + "♟";
            document.getElementById('name-white').innerHTML = response.data.whiteTeam.name;
        } else if (response.data.whiteTeam.isTurn) {
            document.getElementById('name-black').innerHTML = response.data.blackTeam.name;
            document.getElementById('name-white').innerHTML = response.data.whiteTeam.name + "&#9817;";
        }

    }
}

function clearChessBoard() {
    let pieces = document.getElementsByClassName('piece');
    for (let i = 0; i < pieces.length; i++) {
        pieces[i].innerHTML = "";
    }
}





const piecesMap = {"P" : "&#9821;", "R" : "&#9820;", "N" : "&#9822;", "B" : "&#9821;", "Q" : "&#9819;", "K" : "&#9818;",
"p" : "&#9817;", "r" : "&#9814;", "n" : "&#9816;", "b" : "&#9815;", "q" : "&#9813;", "k" : "&#9812;"}


const btnStart = document.getElementsByClassName('btn-start')[0];
btnStart.addEventListener('click', function (e) {
    axios.get('/startChessGame')
        .then(function (response) {
            let data = response.data;
            console.log(data);
            if (data.success) {
                getChessBoardData(refreshChessBoard);
            } else {
                alert(data.message)
            }
        })
        .catch(function (error) {

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
            console.log(response);
            let data = response.data;
            if(data.success) {
               getChessBoardData();
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
    clearChessBoard();
    let pieces = response.data.pieceDtoList;
    for (let i = 0; i < pieces.length; i++) {
        let piece = pieces[i]
        let tile = document.getElementById(piece.position);
        tile.classList.add('piece');
        tile.classList.add(piece.piece);
        tile.innerHTML = piecesMap[piece.piece]
    }
}

function clearChessBoard() {
    let pieces = document.getElementsByClassName('piece');
    for (let i = 0; i < pieces.length; i++) {
        pieces[i].innerHTML = "";
    }
}





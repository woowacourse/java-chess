const piecesMap = {"P" : "&#9821;", "R" : "&#9820;", "N" : "&#9822;", "B" : "&#9821;", "Q" : "&#9819;", "K" : "&#9818;",
"p" : "&#9817;", "r" : "&#9814;", "n" : "&#9816;", "b" : "&#9815;", "q" : "&#9813;", "k" : "&#9812;"}

const tiles = document.getElementsByClassName('tile');
for (let i = 0; i < tiles.length; i++) {
    tiles.item(i).addEventListener('click', function(e) {
        let havePiece = e.target.classList.contains("piece");
        let piece = "none"
        if (havePiece) {
            piece = e.target.classList["3"];
        }

        let tileID = e.target.getAttribute('id');
        let position = JSON.stringify({piece : piece, position : tileID})
        alert(position)
        $.ajax({
            type:'GET',
            url:"./move",
            data: {position},
            dataType: 'application/json',
        })
    })
}

getChessBoardData(refreshChessBoard);
function getChessBoardData() {
    $.get('/start', function(response) {
        refreshChessBoard(response);
    });
}

function refreshChessBoard(response) {
    let jsonData = JSON.parse(response)
    let pieces = jsonData.pieceDtoList;
    for (let i = 0; i < pieces.length; i++) {
        let piece = pieces[i]
        let tile = document.getElementById(piece.position);
        tile.classList.add('piece');
        tile.classList.add(piece.piece);
        tile.innerHTML = piecesMap[piece.piece]
    }
}





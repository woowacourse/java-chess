window.onload = initializeChessBoard;


function initializeChessBoard() {
    $.ajax({
        url: '/data',
        type: 'get',
        success: function (piecesDto) {
            const piecesJson = JSON.parse(piecesDto);
            addPieces(piecesJson.pieces);
        },
        fail: function (error) {
            alert("실패했습니다.");
        }
    });
}

function addPieces(pieces) {
    console.log(pieces)
    for (let i = 0; i < pieces.length; i++) {
        let piece = document.createElement("img");
        piece.src = `../img/${pieces[i].color}-${pieces[i].name}.png`
        piece.className = "piece";
        piece.id = pieces[i].name;
        piece.style.width = "100%";
        piece.style.height = "100%";
        document.getElementById(pieces[i].position).appendChild(piece);
    }
}


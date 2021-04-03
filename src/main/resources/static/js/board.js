const pieceMap = {'R':'♜', 'N':'♞', 'B':'♝', 'Q':'♛', 'K':'♚', 'P':'♟',
    'r':'♖', 'n':'♘', 'b':'♗', 'q':'♕', 'k':'♔', 'p':'♙'}

document.getElementById("start-btn").addEventListener("click", gameStart);
function gameStart() {
    axios({
        method: 'post',
        url: '/start'
    })
        .then((res) => {
            const data = res.data;
            if(data.isOk === "false"){
                alert(data.message);
                return;
            }
            getPieces(data.piecesDto);
    });
}

function getPieces(piecesDto) {
    const pieces = piecesDto.pieces;
    for (let i = 0; i < pieces.length; i++){
        const piece = document.getElementById(pieces[i].position);
        piece.innerHTML = pieceMap[pieces[i].notation];
    }
}


const table = document.getElementsByClassName("square")
for(let i=0;i<table.length;i++){
    table[i].addEventListener("click", select);
}

let isClick = false;
let source

function select(event) {
    if (!isClick) {
        isClick = true;
        source = event.target.id;
        return;
    }
    let target = event.target.id;
    isClick = false;
    axios({
        method: 'post',
        url: '/move',
        data: {
            source: source,
            target: target
        }
    })
        .then((res) => {
            const data = res.data;
            if(data.isOk === "false"){
                alert(data.message);
                return;
            }
            document.getElementById(source).innerHTML = "";
            getPieces(data.piecesDto);
        });
}

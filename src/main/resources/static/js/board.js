const pieceMap = {'R':'♜', 'N':'♞', 'B':'♝', 'Q':'♛', 'K':'♚', 'P':'♟',
    'r':'♖', 'n':'♘', 'b':'♗', 'q':'♕', 'k':'♔', 'p':'♙'}

document.getElementById("start-btn").addEventListener("click", gameStart);
function gameStart() {
    axios({
        method: 'post',
        url: '/start'
    })
        .then((res) => {
            const data = res.data
            if(data.isOk === "false"){
                alert(data.message);
                return;
            }
            showTurn(data.turn);
            getPieces(data.piecesDto);
            saveButton();
    });
}

function saveButton(){
    const save = document.getElementById("connect");

    const loading_btn = document.createElement("button");
    loading_btn.setAttribute("class", "loading button");
    loading_btn.innerHTML = "LOADING";

    const save_btn = document.createElement("button");
    save_btn.setAttribute("class", "save button");
    save_btn.innerHTML = "SAVE";

    save.appendChild(loading_btn);
    save.appendChild(save_btn);
}

function showTurn(turn) {
    const turnName = document.getElementById("turn");
    turnName.innerHTML = turn;

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
            showTurn(data.turn);
            if(data.isOk === "end"){
                alert(data.message);
                location.reload();
            }
        });
}

document.getElementById("end-btn").addEventListener("click", endGame);

function endGame() {
    axios({
        method: 'post',
        url: '/end'
    }).then((res) => {
        const data = res.data;
        if(data.isOk === "false"){
            alert(data.message);
            return;
        }
        alert(data.message);
        location.reload();
    })
}

document.getElementById("status-btn").addEventListener("click", status);

function status() {
    axios({
        method: 'post',
        url: '/status'
    }).then((res) => {
        const data = res.data;
        if(data.isOk === "false"){
            alert(data.message);
            return;
        }
        const h1 = document.getElementById("status");
        h1.innerHTML = data.message;
    })
}

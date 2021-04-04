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

function saveButton(){
    const save = document.getElementById("connect");

    const loading_btn = document.createElement("button");
    loading_btn.addEventListener("click", loadPieces);
    loading_btn.setAttribute("class", "loading button");
    loading_btn.innerHTML = "LOAD";

    const save_btn = document.createElement("button");
    save_btn.addEventListener("click", savePieces);
    save_btn.setAttribute("class", "save button");
    save_btn.setAttribute("id", "save-btn")

    save_btn.innerHTML = "SAVE";

    save.appendChild(loading_btn);
    save.appendChild(save_btn);
}

function savePieces() {
    axios({
        method: 'post',
        url: '/save'
    }).then((res) => {
        const data = res.data;
        if(data.isOk === 'false'){
            alert(data.message);
            return;
        }
        alert(data.message);
    })
}

function loadPieces() {
    axios({
        method: 'post',
        url: '/load'
    }).then((res) => {
        const data = res.data;
        if(data.isOk === 'false'){
            alert(data.message);
        }
        alert(data.message);
        deletePieces()
        getPieces(data.piecesDto);
        showTurn(data.turn);
    });
}

function deletePieces() {
    const row = ["a","b","c","d","e","f","g","h"];

    for( let i = 0 ;i < row.length; i++) {
        for(let j=1;j <= 8;j++){
            const cell = document.getElementById(row[i]+j);
            cell.innerHTML = "";
        }
    }
}

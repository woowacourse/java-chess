const pieceMap = {
    'R': '♜', 'N': '♞', 'B': '♝', 'Q': '♛', 'K': '♚', 'P': '♟',
    'r': '♖', 'n': '♘', 'b': '♗', 'q': '♕', 'k': '♔', 'p': '♙'
}

document.getElementById("start-btn").addEventListener("click", gameStart);

function gameStart() {
    const roomID = prompt("방의 이름을 입력하세요");
    axios({
        method: 'post',
        url: '/start',
        data:{
            roomID:roomID
        }
    })
        .then((res) => {
            const data = res.data
            if (data.isOk === "false") {
                alert(data.message);
                return;
            }
            showRoomNumber(data.roomID);
            showTurn(data.turn);
            getPieces(data.piecesDto);
            saveButton();
        });
}

function showRoomNumber(roomID) {
    const roomNum = document.getElementById("room-number");
    roomNum.innerHTML = roomID;
}

function showTurn(turn) {
    const turnName = document.getElementById("turn");
    turnName.innerHTML = turn;

}

function getPieces(piecesDto) {
    const pieces = piecesDto.pieces;
    for (let i = 0; i < pieces.length; i++) {
        const piece = document.getElementById(pieces[i].position);
        piece.innerHTML = pieceMap[pieces[i].notation];
    }
}


const table = document.getElementsByClassName("square")
for (let i = 0; i < table.length; i++) {
    table[i].addEventListener("click", select);
}

let isClick = false;
let source

function select(event) {
    const roomID = document.getElementById("room-number").innerHTML;
    if (!isClick) {
        isClick = true;
        source = event.target.id;
        document.getElementById(source).style.background = "#AE905E";
        return;
    }
    let target = event.target.id;
    isClick = false;
    document.getElementById(source).style.background = "";
    axios({
        method: 'post',
        url: '/move',
        data: {
            source: source,
            target: target,
            roomID: roomID
        }
    })
        .then((res) => {
            const data = res.data;
            if (data.isOk === "false") {
                alert(data.message);
                return;
            }
            if (data.isOk === "end") {
                alert(data.message);
                location.reload();
                return;
            }
            document.getElementById(source).innerHTML = "";
            getPieces(data.piecesDto);
            showTurn(data.turn);

        });
}

document.getElementById("end-btn").addEventListener("click", endGame);

function endGame() {
    const roomID = document.getElementById("room-number").innerHTML;
    axios({
        method: 'post',
        url: '/end',
        data: {
            roomID:roomID
        }
    }).then((res) => {
        const data = res.data;
        if (data.isOk === "false") {
            alert(data.message);
            return;
        }
        alert(data.message);
        location.reload();
    })
}

document.getElementById("status-btn").addEventListener("click", status);

function status() {
    const roomID = document.getElementById("room-number").innerHTML;
    axios({
        method: 'post',
        url: '/status',
        data:{
            roomID:roomID
        }
    }).then((res) => {
        const data = res.data;
        if (data.isOk === "false") {
            alert(data.message);
            return;
        }
        const h1 = document.getElementById("status");
        h1.innerHTML = data.score;
    })
}



document.getElementById("load-btn").addEventListener("click", loadPieces);
function loadPieces() {
    axios({
        method: 'post',
        url: '/load'
    }).then((res)=> {
        const data = res.data;
        const form = document.getElementById("room-name");
        const br = document.createElement('br');
        for(let i=0;i<data.roomIDs.length;i++) {
            const label = document.createElement("label");
            label.innerHTML = data.roomIDs[i];
            form.appendChild(label);

            const input = document.createElement("input");
            input.setAttribute("type","radio");
            input.setAttribute("name","roomName");
            input.setAttribute("onclick",'getRoomName');
            input.value = data.roomIDs[i];
            form.appendChild(input);
        }
        form.appendChild(br);
        const button = document.createElement("input");
        button.setAttribute("type", "button");
        button.setAttribute("value", "LOAD");
        button.addEventListener("click",getRoomName);
        form.appendChild(button);
    });


}

function getRoomName() {
    console.log("hello");
    const roomNameList = document.getElementsByName('roomName');
    let roomID;
    roomNameList.forEach((node) => {
        console.log(node);
        if(node.checked) {
            roomID = node.value;
        }
    })
    axios({
        method: 'post',
        url: '/loadGame',
        data:{
            roomID:roomID
        }
    }).then((res) => {
        const data = res.data;
        if (data.isOk === 'false') {
            alert(data.message);
        }
        alert("게임을 불러왔습니다.");
        deletePieces()
        showRoomNumber(roomID);
        getPieces(data.piecesDto);
        showTurn(data.turn);
    });

}


function deletePieces() {
    const row = ["a", "b", "c", "d", "e", "f", "g", "h"];

    for (let i = 0; i < row.length; i++) {
        for (let j = 1; j <= 8; j++) {
            const cell = document.getElementById(row[i] + j);
            cell.innerHTML = "";
        }
    }
}

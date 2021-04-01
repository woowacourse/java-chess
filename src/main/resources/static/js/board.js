window.onload = initializeChessBoard;
let is_source_clicked = false;
let source = "";
let target = "";

function initializeChessBoard() {
    axios({
        method: 'get',
        url: '/data',
    }).then(function (response) {
        const jsonData = JSON.parse(response.data.jsonData);
        const pieces = jsonData.pieces;
        addPieces(pieces);
        setTurn(jsonData.turn);
        setScoreByTeam(jsonData.totalScoreByColor);
    }).catch(function (error) {
        console.log(error);
    });
    document.getElementById("chess-board")
        .addEventListener("click", getSourceTarget)
}

function getSourceTarget(e) {
    if (is_source_clicked) {
        target = e.target.parentNode.id;
        is_source_clicked = false;
        moveFromSourceToTarget();
    } else {
        source = e.target.parentNode.id;
        is_source_clicked = true;
    }
}

function moveFromSourceToTarget() {
    axios({
        method: 'post',
        url: '/move',
        data: {
            "source": source,
            "target": target
        }
    }).then(function (response) {
        if (response.data.isSuccess) {
            const jsonData = JSON.parse(response.data.jsonData);
            console.log(jsonData.isFinish)
            updateBoard(jsonData);
        } else {
            alert(response.data.jsonData);
        }
    }).catch(err => {
        console.log(err)
    })
}

async function addPieces(pieces) {
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

async function clearPieces(pieces) {
    for (let i = 0; i < pieces.length; i++) {
        let temp = document.getElementById(pieces[i].position);
        document.getElementById(pieces[i].position).removeChild(temp.childNodes[0]);
    }
}

function setTurn(turn) {
    document.getElementById("turn").innerText = turn + " TURN";
}

function updateBoard(jsonData) {
    clearPieces(jsonData.pieces);
    addPieces(jsonData.pieces);
    setTurn(jsonData.turn);
    setScoreByTeam(jsonData.totalScoreByColor);
}

function setScoreByTeam(totalScoreByColor) {
    document.getElementById("WHITE").innerText = "WHITE " + totalScoreByColor.WHITE;
    document.getElementById("BLACK").innerText = "BLACK " + totalScoreByColor.BLACK;
}
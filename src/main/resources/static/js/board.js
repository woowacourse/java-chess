let is_source_clicked = false;
let source = "";
let target = "";
const chessGameId = getParameterByName("id");
const startButton = document.getElementById("start");
const resetButton = document.getElementById("reset");


startButton.addEventListener("click", start);
resetButton.addEventListener("click", reset);


function start() {
    initializeChessBoard();
    document.getElementById("start").disabled = 'disabled';
}

function reset() {
    axios({
        method: 'get',
        url: '/reset',
        params: {
            id: chessGameId
        }
    }).then(function (response) {
        const data = JSON.parse(response.data);
        updateBoard(data);
    }).catch(err => {
        console.log(err)
    })

}

function initializeChessBoard() {
    axios({
        method: 'get',
        url: '/data',
        params: {
            id: chessGameId
        }
    }).then(function (response) {
        const data = JSON.parse(response.data);
        const pieces = data.pieces;
        addPieces(pieces);
        setTurn(data.turn);
        setScoreByTeam(data.totalScoreByColor);
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
        params: {
            id: chessGameId
        },
        data: {
            "source": source,
            "target": target
        }
    }).then(function (response) {
        const data = JSON.parse(response.data);
        updateBoard(data);
    }).catch(err => {
        alert(err.response.data)
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
    if (turn === "WHITE") {
        document.getElementById("turn").innerText = `🏳️ ${turn} TURN 🏳️`;
    }
    if (turn === "BLACK") {
        document.getElementById("turn").innerText = `🏴 ${turn} TURN 🏴`;
    }
}

function updateBoard(jsonData) {
    clearPieces(jsonData.pieces);
    addPieces(jsonData.pieces);
    setTurn(jsonData.turn);
    setScoreByTeam(jsonData.totalScoreByColor);
}

function setScoreByTeam(totalScoreByColor) {
    document.getElementById("WHITE").innerHTML = `<h2>WHITE SCORE</h2><br><h1>${totalScoreByColor.WHITE}</h1>`;
    document.getElementById("BLACK").innerHTML = `<h2>BLACK SCORE</h2><br><h1>${totalScoreByColor.BLACK}</h1>`;
}

function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results == null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}
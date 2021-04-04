const end = document.getElementById("end");
const exit = document.getElementById("exit");
const chessBoard = document.querySelector(".chess-board");
const sourceKey = document.getElementById("sourceKey");
const targetKey = document.getElementById("targetKey");
const tiles = document.getElementsByClassName("tile");
const whiteCount = document.querySelector(`#whiteScore strong`);
const blackCount = document.querySelector(`#blackScore strong`);
const winner = document.querySelector(`#winner`);
const basePath = 'http://localhost:4567';
let isEnd = true;

end.addEventListener("click", () => {
    if (isEnd === true) {
        alert("이미 게임끝냤슈!");
        return
    }
    if (window.confirm("정말 끝내려구?")) {
        isEnd = true;
        axios({
            method: 'put',
            url: basePath + '/games',
            data: {
                chessName: localStorage.getItem("name"),
                isGameOver: isEnd
            }
        }).then(() => {
            loadGame()
        }).catch(error => console.log(error));
        alert("이 게임 끝났습니다.");
    }
});

exit.addEventListener("click", () => {
    if (window.confirm("정말 나갈래요?")) {
        window.location = basePath;
    }
})

const loadGame = () => {
    axios.get(basePath + '/games/' + localStorage.getItem("name"))
        .then(responsePieces => {
            reRangeBoard(responsePieces)
            if (responsePieces.data.isGameOver) {
                let winnerNode = winner.querySelector("strong");
                if (responsePieces.data.winner === "BLANK") {
                    winnerNode.innerText = "무승부";
                    alert("무승부!");
                } else {
                    winnerNode.innerText = responsePieces.data.winner;
                    alert("승리자는" + responsePieces.data.winner);
                }
                winner.style.visibility = "visible";
            }
        })
        .catch(error => alert(error));
};

loadGame();

function reRangeBoard(responsePieces) {
    for (let idx = 0; idx < tiles.length; idx++) {
        let childNodes = tiles[idx].childNodes;
        for (let childIdx = 0; childIdx < childNodes.length; childIdx++) {
            let img = document.createElement("img");
            img.src = "css/image/blank.png";
            tiles[idx].replaceChild(img, childNodes[childIdx]);
        }
    }

    const pieces = responsePieces.data.pieces;

    for (let pieceIdx = 0; pieceIdx < pieces.length; pieceIdx++) {
        for (let idx = 0; idx < tiles.length; idx++) {
            if (tiles[idx].id === pieces[pieceIdx].position) {
                let img = document.createElement("img");
                img.src = "css/image/" + pieces[pieceIdx].pieceName + ".png";
                tiles[idx].removeChild(tiles[idx].childNodes[0]);
                tiles[idx].appendChild(img);
                break;
            }
        }
    }
    isEnd = responsePieces.data.isGameOver;
    whiteCount.innerText = responsePieces.data.scoreDto.whiteScore;
    blackCount.innerText = responsePieces.data.scoreDto.blackScore;
}

chessBoard.addEventListener("click", (source) => {
    if (isEnd === true) {
        alert("게임 끝났슈!");
        return;
    }

    if (isEmpty(sourceKey.value)) {
        sourceKey.value = source.target.parentElement.id;
        return;
    }

    targetKey.value = source.target.parentElement.id;

    if (isSamePosition()) {
        alert("같은 위치의 돌을 선택할 수 없습니다.");
        clearMoveSource();
        return;
    }

    movePiece();
    clearMoveSource();
})

function isSamePosition() {
    return sourceKey.value === targetKey.value;
}

function isEmpty(value) {
    return !value || value === "";
}

function movePiece() {
    axios({
        method: 'put',
        url: basePath + '/pieces',
        data: {
            chessName: localStorage.getItem("name"),
            source: sourceKey.value,
            target: targetKey.value
        }
    }).then(() => {
        loadGame()
    }).catch(error => alert(error));
}

function clearMoveSource() {
    sourceKey.value = "";
    targetKey.value = "";
}

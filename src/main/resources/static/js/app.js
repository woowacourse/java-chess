const start = document.getElementById("start");
const end = document.getElementById("end");
const chessBoard = document.querySelector(".chess-board");
const sourceKey = document.getElementById("sourceKey");
const targetKey = document.getElementById("targetKey");
const tiles = document.getElementsByClassName("tile");
const whiteCount = document.querySelector(`#whiteScore strong`);
const blackCount = document.querySelector(`#blackScore strong`);
let isEnd = true;

start.addEventListener("click", () => {
    initializePieces();
});

end.addEventListener("click", () => {
    if (isEnd === true) {
        alert("이미 게임끝냤슈!");
        return
    }
    if (window.confirm("정말 끝내려구?")) {
        isEnd = true;
        axios({
            method: 'put',
            url: 'http://localhost:4567/games',
            data: {
                isGameOver: isEnd
            }
        }).then(
        ).catch(error => console.log(error));
        alert("이 게임 끝났습니다.");
    }
});

const initializePieces = () => {
    axios.get('http://localhost:4567/games')
        .then(responsePieces => {
            reRangeBoard(responsePieces);
        })
        .catch(error => console.log(error));
};

const getPieces = () => {
    axios.get('http://localhost:4567/pieces')
        .then(responsePieces => {
            reRangeBoard(responsePieces);
        })
        .catch(error => console.log(error));
};

getPieces();

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

    movePiece();
    clearMoveSource();
})

function isEmpty(value) {
    return !value || value === "";
}

function movePiece() {
    axios({
        method: 'put',
        url: 'http://localhost:4567/pieces',
        data: {
            source: sourceKey.value,
            target: targetKey.value
        }
    }).then((response) => {
        movePieces(response);
        getPieces();
    }).catch(error => alert(error));
}

const movePieces = (response) => {
    let sourcePiece = document.getElementById(response.data.source);
    let targetPiece = document.getElementById(response.data.target);

    let sourceChildPiece = sourcePiece.querySelector('img');
    let targetChildPiece = targetPiece.querySelector('img');

    targetChildPiece.src = sourceChildPiece.src;
    sourceChildPiece.src = "";
};

function clearMoveSource() {
    sourceKey.value = "";
    targetKey.value = "";
}
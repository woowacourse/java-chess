const start = document.getElementById("start");
const chessBoard = document.querySelector(".chess-board");
const sourceKey = document.getElementById("sourceKey");
const targetKey = document.getElementById("targetKey");
const tiles = document.getElementsByClassName("tile");
const whiteCount = document.querySelector(`#whiteScore strong`);
const blackCount = document.querySelector(`#blackScore strong`);


start.addEventListener("click", () => {
    getInitialBoard();
});

const getInitialBoard = () =>{
    axios({
        method: 'get',
        url: 'http://localhost:4567/',
    }).then(() => {
        getPieces();
    }).catch(error => alert(error));
}

const getPieces = () => {
    axios.get('http://localhost:4567/start')
        .then(responsePieces => {
            reRangeBoard(responsePieces);
        })
        .catch(error => console.log(error));
};

const movePieces = (response) => {
    let sourcePiece = document.getElementById(response.data.source);
    let targetPiece = document.getElementById(response.data.target);

    let sourceChildPiece = sourcePiece.querySelector('img');
    let targetChildPiece = targetPiece.querySelector('img');

    targetChildPiece.src = sourceChildPiece.src;
    sourceChildPiece.src = "";
};

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
            }
        }
    }

    whiteCount.innerText = responsePieces.data.scoreDto.whiteScore;
    blackCount.innerText = responsePieces.data.scoreDto.blackScore;
}

chessBoard.addEventListener("click", (source) => {
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
        method: 'post',
        url: 'http://localhost:4567/move',
        data: {
            source: sourceKey.value,
            target: targetKey.value
        }
    }).then((response) => {
        movePieces(response);
        getPieces();
    }).catch(error => alert(error));
}

function clearMoveSource() {
    sourceKey.value = "";
    targetKey.value = "";
}
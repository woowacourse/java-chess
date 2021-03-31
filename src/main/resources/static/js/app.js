const start = document.getElementById("start");
const chessBoard = document.querySelector(".chess-board");
const sourceKey = document.getElementById("sourceKey");
const targetKey = document.getElementById("targetKey");
const tiles = document.getElementsByClassName("tile");

start.addEventListener("click", () => {
    axios.get('http://localhost:4567/start')
        .then(responsePieces => {
            reRangeBoard(responsePieces);
        })
        .catch(error => console.log(error));
});

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
}


chessBoard.addEventListener("click", (source) => {
    if (isEmpty(sourceKey.value)) {
        sourceKey.value = source.target.parentElement.id;
        return;
    }

    targetKey.value = source.target.parentElement.id;
    console.log(sourceKey.value + " " + targetKey.value);

    movePiece();

    clearMoveSource();
})

function isEmpty(value) {
    return !value || value === "";
}

function movePiece() {

}

function clearMoveSource() {
    sourceKey.value = "";
    targetKey.value = "";
}
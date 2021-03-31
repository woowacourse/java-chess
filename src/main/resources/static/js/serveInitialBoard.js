export const serveInitialBoard = () => {
    const $startButton = document.getElementById("start-button");
    $startButton.addEventListener("click", function(e) {
        requestInitialBoard();
    })
}

const requestInitialBoard = () => {
    const pieces = document.getElementsByClassName("PackedPiece");
    // Array.prototype.forEach(pieces, function(element) {
    //     element.remove();
    // })
    Array.from(pieces).forEach(function(element) {
        element.remove();
    });

    axios.post("/initial")
    .then(response => allocateInitialPiece(response))
    .catch(error => console.log(error))
}

const allocateInitialPiece = (response) => {
    const data = response.data;
    for(let key in data) {
        let pieceArea = document.getElementById(key);
        let image = packedImage(key, data[key]);
        pieceArea.appendChild(image);
    }
}

const packedImage = (key, pieceKind) => {
    let packedImageDiv = document.createElement("div");
    packedImageDiv.setAttribute("class", "PackedPiece")
    let image =document.createElement("img");
    image.setAttribute("alt", "chess piece");
    image.setAttribute("class", "ChessPieceImage");
    pieceKind = voidTranslate(pieceKind);
    image.src = "ChessPiece/"+pieceKind +".png";
    packedImageDiv.appendChild(image);
    return packedImageDiv;
}

const voidTranslate = (pieceKind) => {
    if (pieceKind == "."){
        return "void";
    }
    return pieceKind;
}
export const packedImage = (key, pieceKind) => {
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

export const switchImage = (pieceKind) => {
    let packedImageDiv = document.createElement("div");
    packedImageDiv.setAttribute("class", "PackedPiece")
    let image =document.createElement("img");
    image.setAttribute("alt", "chess piece");
    image.setAttribute("class", "ChessPieceImage");
    image.src = pieceKind;
    packedImageDiv.appendChild(image);
    return packedImageDiv;
}

export const voidImage = () => {
    let packedImageDiv = document.createElement("div");
    packedImageDiv.setAttribute("class", "PackedPiece")
    let image =document.createElement("img");
    image.setAttribute("alt", "chess piece");
    image.setAttribute("class", "ChessPieceImage");
    image.src = "ChessPiece/void.png";
    packedImageDiv.appendChild(image);
    return packedImageDiv;
}

const voidTranslate = (pieceKind) => {
    if (pieceKind == "."){
        return "void";
    }
    return pieceKind;
}
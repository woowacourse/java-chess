function fillSquare(squareName, pieceType, pieceColor) {
    pieceColor = pieceColor.toLowerCase()
    pieceType = pieceType.toLowerCase()
    console.log(squareName + pieceType + pieceColor)
    if (pieceColor !== "nothing") {
        console.log(document.getElementById(squareName))
        document.getElementById(squareName).innerHTML = "<img class=piece-image src='/image/pieces/" + pieceColor + "/"
            + pieceColor + "-" + pieceType + ".svg" + "'/>"
    }
}
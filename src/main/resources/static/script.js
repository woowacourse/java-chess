async function start() {
    let pieces;
    await fetch("/start")
        .then(res => res.json())
        .then(data => pieces = data)
    printPieces(pieces);
}

function printPieces(pieces) {
    for (const key in pieces) {
        const piece = pieces[key];
        const square = document.getElementById(key.toLowerCase());
        const img = document.createElement("img");
        removeChildren(square);
        attachPieceInSquare(piece, img, square);
    }

    function removeChildren(node) {
        while (node.hasChildNodes()) {
            node.removeChild(node.firstChild);
        }
    }

    function attachPieceInSquare(piece, img, square) {
        let pieceColor = piece.color.toLowerCase();
        let pieceType = piece.type.toLowerCase();
        if (pieceType !== "empty") {
            img.setAttribute("src", "/images/" + pieceColor + "_" + pieceType + ".png");
            img.setAttribute("class", "piece");
            square.appendChild(img);
        }
    }
}

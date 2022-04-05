async function start() {
    let pieces;
    await fetch("/start")
        .then(res => res.json())
        .then(data => pieces = data)
    console.log(pieces);
    printPieces(pieces.board);
}

function end() {
    let whiteSquares = document.getElementsByClassName("white-square");
    let blackSquares = document.getElementsByClassName("black-square");
    for (let square of whiteSquares) {
        removeChildren(square);
    }

    for (let square of blackSquares) {
        removeChildren(square);
    }
}

function printPieces(pieces) {
    for (const key in pieces) {
        const piece = pieces[key];
        const square = document.getElementById(key);
        const img = document.createElement("img");
        removeChildren(square);
        attachPieceInSquare(piece, img, square);
    }

    function attachPieceInSquare(piece, img, square) {
        if (piece !== "empty") {
            img.setAttribute("src", "/images/" + piece + ".png");
            img.setAttribute("class", "piece");
            square.appendChild(img);
        }
    }
}

function removeChildren(node) {
    while (node.hasChildNodes()) {
        node.removeChild(node.firstChild);
    }
}

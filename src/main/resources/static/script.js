let from = "";
let turn = "";

async function start() {
    let pieces;
    await fetch("/start")
        .then(res => res.json())
        .then(data => pieces = data)
    console.log(pieces);
    turn = pieces.turn;
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

async function selectPiece(pieceDiv) {
    let pieceClasses = pieceDiv.classList;

    if (from === "") {
        from = pieceDiv.id;
        pieceClasses.add('selected');
        console.log("select!" + from);
        return;
    }

    if (from !== "") {
        let sourceClassList = document.getElementById(from).classList;
        sourceClassList.remove('selected');
        move(from, pieceDiv.id)
    }
}

async function move(fromPosition, toPosition) {
    let pieces;
    from = "";
    await fetch("/move", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify({
            from: fromPosition,
            to: toPosition
        })
    }).then(res => res.json())
        .then(data => pieces = data)
        .catch((error) => {
            alert("이동 불가!");
        })
    printPieces(pieces.board);
    turn = pieces.turn;
}

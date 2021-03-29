createChessBoard();

function createChessBoard() {
    const pieces = [
        ["BR", "BN", "BB", "BQ", "BK", "BB", "BN", "BR"],
        new Array(8).fill("BP"),
        new Array(8).fill("."),
        new Array(8).fill("."),
        new Array(8).fill("."),
        new Array(8).fill("."),
        new Array(8).fill("WP"),
        ["WR", "WN", "WB", "WQ", "WK", "WB", "WN", "WR"]
    ]

    makeTable(pieces);
}

function makeTable(pieces) {
    const table = document.getElementById("chess-board");
    for (let i = 0; i < 8; i++) {
        const newTr = document.createElement("tr");
        for (let j = 0; j < 8; j++) {
            const newTd = document.createElement("td");

            const row = String(8 - i);
            const column = String.fromCharCode('a'.charCodeAt(0) + j);
            newTd.id = column + row;
            let pieceName = pieces[i][j];
            if (pieceName !== ".") {
                const piece = document.createElement("img");
                piece.src = "images/" + pieceName + ".png";
                newTd.appendChild(piece);
            }
            if ((i % 2 === 0 && j % 2 === 0) || (i % 2 !== 0 && j % 2 !== 0)) {
                newTd.className = "whiteTile";
            }
            else {
                newTd.className = "blackTile";
            }
                table.appendChild(newTd);
        }
        table.appendChild(newTr);
    }
}
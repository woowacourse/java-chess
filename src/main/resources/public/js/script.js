function createChessBoard() {
    const pieces = [
        ["BR", "BN", "BB", "BQ", "BK", "BB", "BN", "BR"],
        new Array(8).fill("BP"),
        new Array(8),
        new Array(8),
        new Array(8),
        new Array(8),
        new Array(8).fill("WP"),
        ["WR", "WN", "WB", "WQ", "WK", "WB", "WN", "WR"]
    ]

    const table = document.getElementById("chess-board");
    for (let i = 0; i < 8; i++) {
        const newTr = document.createElement("tr");
        for (let j = 0; j < 8; j++) {
            const newTd = document.createElement("td");

            const row = String(8 - i); // 열(12345678)
            const asciiNum = 97; // h의 아스키코드
            const column = String.fromCharCode(asciiNum + j);
            newTd.id = row + column;
            if (pieces[i][j]) {
                const piece = document.createElement("img");
                piece.src = "images/" + pieces[i][j] + ".png";
                newTd.appendChild(piece);
            }
            if (i % 2 == j % 2) {
                newTd.className = "block1";
            } else {
                newTd.className = "block2";
            }
            newTr.appendChild(newTd);
        }
        table.appendChild(newTr);
    }
    document.body.appendChild(table);
    addEvent();
}

function addEvent() {
    const table = document.getElementById("chess-board");
    table.addEventListener("click", selectPiece);
}

createChessBoard();

function selectPiece(event) {
    const target = event.target;
    target.closest("td").classList.toggle("clicked");
}

// function getClickedPiece() {
//     const tds = document.querySelectorAll("td");
//     for (let i = 0; i < tds.length; i++) {
//         if (tds[i].classList.contains("clicked")) {
//             return tds[i];
//         }
//     }
//     return null;
// }

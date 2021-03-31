const BLANK = ".";

async function start() {
    /**
     * batch Piece on Board
     */
    let data;
    try {
        const res = await axios({
            method: 'post',
            url: '/start',
        });
        data = res.data;
        console.log(data);
    } catch (e) {
        console.log(e);
    }

    const table = document.getElementById("chessBoard");
    for (let i = 0; i < 8; i++) {
        const newTr = document.createElement("tr");
        for (let j = 0; j < 8; j++) {
            const newTd = document.createElement("td");

            const row = String(8 - i); // 열(12345678)
            const asciiNum = 'a'.charCodeAt(); // h의 아스키코드
            const column = String.fromCharCode(asciiNum + j);
            let position = column + row;
            newTd.id = position;
            const pieceName = data[position].teamColor
                + data[position].pieceType;

            if (data[position].pieceType !== BLANK) {
                const piece = document.createElement("img");
                piece.src = "images/" + pieceName + ".png";
                newTd.appendChild(piece);
            }

            newTr.appendChild(newTd);
        }
        table.appendChild(newTr);
    }
    eventList()
}

function eventList() {
    const table = document.getElementById("chessBoard");
    table.addEventListener("click", selectPiece);
}

function selectPiece(event) {
    const clickPiece = event.target.closest("td");
    const clickedPiece = getClickedPiece();

    if (clickedPiece) {
        clickedPiece.classList.toggle("clicked");
        const sourcePosition = clickedPiece.id;
        const targetPosition = clickPiece.id;
        move(sourcePosition, targetPosition);
    } else {
        clickPiece.classList.toggle("clicked");
    }
}

function getClickedPiece() {
    const tds = document.getElementsByTagName("td");
    for (let i = 0; i < tds.length; i++) {
        if (tds[i].classList.contains("clicked")) {
            return tds[i];
        }
    }
    return null;
}

async function move(source, target) {
    /**
     * run move
     * source : String
     * target : String
     */
    try {
        await axios({
            method: 'post',
            url: '/move',
            data: {
                source: source,
                target: target
            }
        });
    } catch (e) {
        console.log(e);
    }
}
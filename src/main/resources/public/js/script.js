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
            newTd.id = column + row;
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
    const clickPiece = event.target.closest("td");
    const clickedPiece = getClickedPiece();

    // 클릭이 되있을 경우
    if (clickedPiece) {
        clickedPiece.classList.toggle("clicked");
        const sourcePosition = clickedPiece.id;
        const targetPosition = clickPiece.id;
        move(sourcePosition, targetPosition);

        // 아무것도 클릭이 안 되있는 상태 -> 클릭한 것 clicked로 바꾸기
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

async function move(sourcePosition, targetPosition) {
    console.log("move 함수 실행")
    try {
        axios({
            method: 'post',
            url: '/move',
            data: {
                source: sourcePosition,
                target: targetPosition
            }
        })
            .then((res) => {
                console.log("응답값");
                console.log(res);
            })
    } catch (e) {
        console.log(e);
    }
}

function start() {
    try {
        axios({
            method: 'post',
            url: '/start',
        })
            .then((res) => {
                console.log("응답값");
                console.log(res);
            })
    } catch (e) {
        console.log(e);
    }
}
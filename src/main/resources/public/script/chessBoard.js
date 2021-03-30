// fetch("http://localhost:4567").then((response) =>
//     console.log(response)
// )
//
// fetch("http://localhost:4567")
// .then((response) => response.json())
// .then((data) => console.log(data))

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
            newTd.id = column + row;
            newTd.innerText = data[column + row].teamColor + data[column
            + row].pieceType
            newTr.appendChild(newTd);
        }
        table.appendChild(newTr);
    }
}

async function move(source, target) {
    /**
     * run move
     * source : String
     * target : String
     */
    try {
        const res = await axios({
            method: 'post',
            url: '/move',
            data: {
                source,
                target
            }
        });
    } catch (e) {
        console.log(e);
    }
}


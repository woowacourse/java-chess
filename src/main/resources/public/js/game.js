let board;
createBoard();

function getFetch(url) {
    return fetch(url).then(response => response.json());
}

function postFetch(url) {
    return fetch(url, {method: "post"
        , headers: {'Content-type': 'application/json',
            'Accept': 'application/json'
            },
        body: JSON.stringify(board)} // jsonObject
        ).then(response => response.json());
}

async function changeBoard(){
    await postFetch("move").then(data => {
        board = data;
    });
    refreshBoard();
}

async function createBoard() {
    await getFetch("create").then(data => {
        board = data;
    })
    refreshBoard();
}

function refreshBoard() {
    let boardKeys = Object.keys(board.board);
    let boardSize = boardKeys.length;
    for (let i = 0; i < boardSize; i++) {
        let tileData = document.getElementById(boardKeys[i]);
        if (board.board[boardKeys[i]].name != null) {
            tileData.innerHTML = `
            <img src="../images/${board.board[boardKeys[i]].team.toLowerCase()}-${board.board[boardKeys[i]].name.toLowerCase()}.png" width="80px" height="80px">
            `;
        }
    }
}

function move() {

}
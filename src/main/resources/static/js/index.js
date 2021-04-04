const generatorButton = document.getElementById("generator");
const chessGameList = document.getElementById("chess-game-list");
updateChessGameList();

function updateChessGameList() {
    axios({
        method: 'get',
        url: '/chess-game-list',
    }).then(function (response) {
        const jsonData = JSON.parse(response.data.jsonData);
        addChessGameList(jsonData);
    }).catch(function (error) {
        console.log(error);
    });
}
function addChessGameList(chessGameList) {
    for(let i = 0; i < chessGameList.length; i++) {
        let chessGameLI = document.createElement('li');
        chessGameLI.className = "list-group-item";
        chessGameLI.innerHTML = `
            <div class="view" onclick = "enterGame(this)">
            NO.${chessGameList[i]} Room
            </div>`;
        document.getElementById("chess-game-list").appendChild(chessGameLI);
    }

}


generatorButton.addEventListener("click", askGenerateChessGame);

function askGenerateChessGame() {
    function generateChessGame() {
        axios({
            method: 'get',
            url: '/new-game',
        }).then(function (response) {
            updateChessGameList();
        }).catch(function (error) {
            console.log(error);
        });
    }

    if (confirm("새로운 체스 게임방을 만드시겠습니까?")) {
        generateChessGame();
    } else {

    }
}


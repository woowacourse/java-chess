const generatorButton = document.getElementById("generator");
updateChessGameList();

function updateChessGameList() {
    axios({
        method: 'get',
        url: '/chess-game-list',
    }).then(function (response) {
        const data = JSON.parse(response.data);
        addChessGameList(data);
    }).catch(function (error) {
        console.log(error);
    });
}

function addChessGameList(chessGameList) {
    for (let i = 0; i < chessGameList.length; i++) {
        let chessGameLI = document.createElement('li');
        chessGameLI.className = "list-group-item";
        chessGameLI.innerHTML = `
            <div class="view" onclick = "enterGame(this)" id = "${chessGameList[i]}">
            NO.${chessGameList[i]} Room
            </div>`;
        document.getElementById("chess-game-list").appendChild(chessGameLI);
    }
}

function enterGame(chessGameList) {
    console.log(chessGameList);
    location.href = "/start?id=" + chessGameList.id;
}


generatorButton.addEventListener("click", askGenerateChessGame);

function askGenerateChessGame() {
    if (confirm("새로운 체스 게임방을 만드시겠습니까?")) {
        generateChessGame();
    } else {

    }

    function generateChessGame() {
        axios({
            method: 'post',
            url: '/generate',
        }).then(function (response) {
            location.reload();
        }).catch(function (error) {
            console.log(error);
        });
    }
}


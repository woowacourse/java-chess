const createButton = document.querySelector(".create")
const enterButton = document.querySelector(".enter")

createButton.addEventListener("click", createNewGame);
enterButton.addEventListener("click", enterPreviousGame);

function createNewGame() {
    const gameId = document.querySelector(".newId_Input").value
    axios({
        method: 'post',
        url: '/create',
        data: {
            gameId: gameId
        }
    })
        .then(function (res) {
            if (res.data) {
                enterGame(gameId);
                return;
            }
            alert("이미 방이 존재합니다.");
        })
}

function enterGame(gameId) {
    axios({
        method: 'post',
        url: '/enter',
        params: {
            gameId: gameId
        }
    })
        .then(function (res) {
            if (res.data) {
                location.replace("/game?id=" + gameId);
                alert(gameId + "번 방에 입장했습니다.");
                return;
            }
            alert("존재하는 방이 없습니다.");
        })
}

function enterPreviousGame() {
    const gameId = document.querySelector(".id_Input").value
    enterGame(gameId);
}


const App = function app() {
    const sourceKey = document.getElementById("sourceKey");
    const targetKey = document.getElementById("targetKey");

    const startBtn = document.querySelector("#new-game");
    const loadBtn = document.querySelector("#load-game");
    const surrenderBtn = document.querySelector("#surrender-game");
    const endBtn = document.querySelector("#end-game");

    const tiles = document.getElementsByClassName("tile");

    const gameId = document.getElementById("game-id");
    const turn = document.getElementById("now-turn");
    const blackScore = document.getElementById("black-score");
    const whiteScore = document.getElementById("white-score");

    const blackDeadPieces = document.getElementById("black-dead");
    const whiteDeadPieces = document.getElementById("white-dead");

    addTileClickEvent();

    startBtn.addEventListener('click', (evnet) => {
        clearDeadPieces();
        fetch("http://localhost:8080/chessboard", {method: "POST"})
            .then(resolver)
            .then(loadChessBoard)
            .catch(alert)
        ;
    });

    loadBtn.addEventListener('click', (event) => {
        fetch("http://localhost:8080/chessboard", {method: "GET"})
            .then(resolver)
            .then(loadSavedGame)
            .catch(errorHandler);
    });

    surrenderBtn.addEventListener('click', (event) => {
        const id = gameId.innerText;
        const loseTeam = turn.innerText;
        if (!id) {
            return;
        }
        var surrenderRequest = {};
        surrenderRequest.gameId = id;
        surrenderRequest.loseTeam = loseTeam;

        fetch("http://localhost:8080/surrender", {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(surrenderRequest)
        })
            .then(res => alert(loseTeam + "의 항복으로 게임 종료"))
            .catch(errorHandler)
            .finally(() => window.location.href = "http://localhost:8080/home")
    });

    endBtn.addEventListener('click', (event => {
        window.location.href = "http://localhost:8080/home";
    }));

    function loadSavedGame(response) {
        const data = response.body.data;
        const savedGameResponses = data.savedGameResponses;
        if (savedGameResponses.length === 0) {
            alert("저장된 게임이 존재하지 않습니다.");
            return;
        }
        var message = "저장된 게임 목록\n";
        for (let i = 0; i < savedGameResponses.length; i++) {
            const saved = "게임 ID : " + savedGameResponses[i].id + " - 생성 일자 : " + savedGameResponses[i].createdTime + "\n";
            message += saved;
        }
        const select = prompt(message, '');
        if (!select || select.trim() === '') {
            return;
        }
        fetch("http://localhost:8080/chessboard/" + select, {method: "GET"})
            .then(resolver)
            .then(loadChessBoard)
            .catch(errorHandler);
    }

    function addTileClickEvent() {
        for (let i = 0; i < tiles.length; i++) {
            tiles[i].addEventListener("click", (event) => clickMoveEvent(i));
        }
    }

    function clickMoveEvent(i) {
        fillTileKey(i);

        if (canNotMove()) {
            return false;
        }

        const moveRequest = {};
        moveRequest.sourceKey = sourceKey.value;
        moveRequest.targetKey = targetKey.value;
        moveRequest.id = parseInt(gameId.textContent);

        fetch("http://localhost:8080/chessboard/move", {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(moveRequest)
        })
            .then(resolver)
            .then(res => moveSuccessHandler(moveRequest.sourceKey, moveRequest.targetKey, res))
            .catch(errorHandler)
            .finally(clearMoveSource);
    }

    function fillTileKey(i) {
        if (isNotGameStarted()) {
            return false;
        }
        if (isEmpty(sourceKey.value)) {
            sourceKey.value = tiles[i].id;
            saveSelectedTile();
            return;
        }
        targetKey.value = tiles[i].id;

        removeSelectedTile();
        if (sourceKey.value === targetKey.value) {
            clearMoveSource();
        }
    }

    function saveSelectedTile() {
        const sourceTile = document.getElementById(sourceKey.value);
        originalColor = sourceTile.style.backgroundColor;
        sourceTile.style.backgroundColor = "gray";
    }

    var originalColor;

    function removeSelectedTile() {
        const sourceTile = document.getElementById(sourceKey.value);
        if (sourceTile) {
            sourceTile.style.backgroundColor = originalColor;
        }
    }

    function resolver(response) {
        return new Promise((resolve, reject) => {
            let func;
            response.status < 400 ? func = resolve : func = reject;
            response.json().then(data => func({'status': response.status, 'body': data}));
        });
    }

    function errorHandler(res) {
        const data = res.body;
        alert(data.message);
    }

    function canNotMove() {
        if (isNotGameStarted() || isEmpty(sourceKey.value) || isEmpty(targetKey.value)) {
            return true;
        }
    }

    function moveSuccessHandler(sourceKey, targetKey, res) {
        const data = res.body.data;
        const kingAlive = data.kingAlive;
        const nowTeam = data.nowTurn;
        const targetPiece = data.targetPiece;
        const sourcePiece = data.sourcePiece;
        const deadPiece = data.deadPiece;

        changeTurn(nowTeam);
        replacePiece(sourceKey, sourcePiece);
        replacePiece(targetKey, targetPiece);
        addDeadPiece(turn.innerText.toLowerCase() + "-dead", deadPiece);
        updateScore(data.teamScoreDto);
        if (!kingAlive) {
            alert(nowTeam + "팀 승리");
        }
    }

    function addDeadPiece(targetId, pieceName) {
        if (pieceName === 'blank') {
            return false;
        }
        const target = document.getElementById(targetId);
        const img = document.createElement('img');
        img.src = "/img/" + pieceName + ".png";
        img.className = 'dead-piece d-flex';
        target.appendChild(img);
    }

    function replacePiece(key, pieceName) {
        const tile = document.getElementById(key);
        const img = document.createElement('img');
        img.src = '/img/' + pieceName + '.png';

        if (tile.hasChildNodes()) {
            tile.removeChild(tile.firstChild);
        }
        tile.appendChild(img);
    }

    function changeTurn(team) {
        turn.innerHTML = team;
    }

    function updateScore(teamScoreDto) {
        blackScore.innerHTML = teamScoreDto.blackScore;
        whiteScore.innerHTML = teamScoreDto.whiteScore;
    }

    function loadChessBoard(res) {
        let data = res.body.data;

        gameId.innerHTML = data.id;
        turn.innerHTML = data.turn;
        const teamScoreDto = data.teamScoreDto;
        const tiles = data.tilesDto.tiles;

        updateScore(teamScoreDto);
        replaceChessBoard(tiles);
        clearDeadPieces();
        removeSelectedTile();
        clearMoveSource();
    }

    function replaceChessBoard(responseTiles) {
        for (let idx = 0; idx < tiles.length; idx++) {
            let childNodes = tiles[idx].childNodes;
            for (let childIdx = 0; childIdx < childNodes.length; childIdx++) {
                tiles[idx].removeChild(childNodes[childIdx]);
            }
        }

        responseTiles.forEach(newTile => {
            for (let idx = 0; idx < tiles.length; idx++) {
                const tile = document.querySelector("#" + newTile.coordinate);
                if (tiles[idx].id === newTile.coordinate) {
                    const img = document.createElement("img");
                    img.src = "/img/" + newTile.piece + ".png";
                    tiles[idx].appendChild(img);
                }
            }
        })
    }

    function isNotGameStarted() {
        return (!gameId.textContent || gameId.textContent === "");
    }

    function isEmpty(value) {
        return !value || value === "";
    }

    function clearMoveSource() {
        sourceKey.value = "";
        targetKey.value = "";
    }

    function clearDeadPieces() {
        let blackDeadChilds = blackDeadPieces.childNodes;
        blackDeadChilds.forEach(function (child) {
            if (blackDeadPieces.hasChildNodes()) {
                blackDeadPieces.removeChild(child);
            }
        });

        let whiteDeadChilds = whiteDeadPieces.childNodes;
        whiteDeadChilds.forEach(function (child) {
            if (whiteDeadPieces.hasChildNodes()) {
                whiteDeadPieces.removeChild(child);
            }
        });
    }

};


new App();
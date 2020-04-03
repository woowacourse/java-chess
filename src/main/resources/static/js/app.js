const App = function app() {
    const sourceKey = document.getElementById("sourceKey");
    const targetKey = document.getElementById("targetKey");

    const startBtn = document.querySelector("#new-game");
    const tiles = document.getElementsByClassName("tile");

    const gameId = document.getElementById("game-id");
    const turn = document.getElementById("now-turn");
    const blackScore = document.getElementById("black-score");
    const whiteScore = document.getElementById("white-score");

    startBtn.addEventListener('click', (evnet) => {
        fetch("http://localhost:8080/chessboard", {method: "POST"})
            .then(res => res.json())
            .then(loadChessBoard)
            .catch(alert);
    });

    for (let i = 0; i < tiles.length; i++) {
        tiles[i].addEventListener("click", (event) => {
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
                .then(res => moveSuccessRender(moveRequest.sourceKey, moveRequest.targetKey, res))
                .catch(errorHandler)
                .finally(clearMoveSource);
        })
    }

    function fillTileKey(i) {
        if (isNotGameStarted()) {
            return false;
        }
        if (isEmpty(sourceKey.value)) {
            sourceKey.value = tiles[i].id;
        } else {
            targetKey.value = tiles[i].id;
        }

        if (sourceKey.value === targetKey.value) {
            clearMoveSource();
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

    function moveSuccessRender(sourceKey, targetKey, res) {
        const data = res.body.data;
        const kingAlive = data.kingAlive;
        const targetPiece = data.targetPiece;
        const sourcePiece = data.sourcePiece;

        replacePiece(sourceKey, sourcePiece);
        replacePiece(targetKey, targetPiece);
        changeTurn(data.nowTurn);
        addDeadPiece(turn.innerText.toLowerCase() + "-dead", data.deadPiece);
        updateScore(data.teamScoreDto);
    }

    function addDeadPiece(targetId, pieceName) {
        if (pieceName === 'blank') {
            return false;
        }
        const target = document.getElementById(targetId);
        const img = document.createElement('img');
        img.src = "/img/" + pieceName + ".png";
        img.className = 'dead-piece';
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
        let data = res.data;

        gameId.innerHTML = data.id;
        turn.innerHTML = data.turn;
        const teamScoreDto = data.teamScoreDto;
        updateScore(teamScoreDto);

        replaceChessBoard(data.tilesDto.tiles);
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

};


new App();
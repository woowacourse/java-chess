const App = function app() {
    const newGameButton = document.querySelector("#new-game");

    const gameId = document.getElementById("game-id");
    const tiles = document.getElementsByClassName("tile");
    const turn = document.getElementById("now-turn");
    const blackScore = document.getElementById("black-score");
    const whiteScore = document.getElementById("white-score");

    newGameButton.addEventListener('click', (evnet) => {
        fetch("http://localhost:8080/chessboard", {method: "POST"})
            .then(res => res.json())
            .then(res => {
                loadChessBoard(res);
            })
            .catch(err => {
                alert(err);
            });
    });

    function loadChessBoard(res) {
        let data = res.data;

        gameId.innerHTML = data.id;
        turn.innerHTML = data.turn;
        const teamScoreDto = data.teamScoreDto;
        blackScore.innerHTML = teamScoreDto.blackScore;
        whiteScore.innerHTML = teamScoreDto.whiteScore;

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
};


new App();
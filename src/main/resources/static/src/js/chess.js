document.addEventListener("DOMContentLoaded", function() {
    startButtonInitializer.initializeStartButton();
    endButtonInitializer.initializeEndButton();
    scoreButtonInitializer.initializeScoreButton();
});

const startButtonInitializer = {
    initializeStartButton: function() {
        let startButton = document.getElementById('start-button');

        startButton.addEventListener("click", () => {
            let httpRequest = new XMLHttpRequest();

            httpRequest.onreadystatechange = function() {
                if (httpRequest.readyState === XMLHttpRequest.DONE && httpRequest.status === 200) {
                    let chessBoard = JSON.parse(httpRequest.responseText);
                    boardDrawer.drawBoard(chessBoard);
                    moveButtonInitializer.initializeMoveButton(chessBoard);
                } else {
                    if (httpRequest.readyState === XMLHttpRequest.DONE) {
                        alert('요청을 처리할 수 없습니다');
                        return;
                    }
                }
            }

            httpRequest.open("GET", "/start/1");
            httpRequest.send();
        })
    }
}

const endButtonInitializer = {
    initializeEndButton: function() {
        let endButton = document.getElementById('end-button');

        endButton.addEventListener("click", async () => {
            let httpRequest = new XMLHttpRequest();

            httpRequest.onreadystatechange = function() {
                if (httpRequest.readyState === XMLHttpRequest.DONE) {
                    alert('게임이 종료되었습니다');
                }
            }

            httpRequest.open("post", "/end/1");
            httpRequest.setRequestHeader("Content-Type", "application/json");
            httpRequest.send({});
        })
    }
}

const scoreButtonInitializer = {
    initializeScoreButton: function() {
        let endButton = document.getElementById('score-button');

        endButton.addEventListener("click", async () => {
            let httpRequest = new XMLHttpRequest();

            httpRequest.onreadystatechange = function() {
                if (httpRequest.readyState === XMLHttpRequest.DONE) {
                    let score = JSON.parse(httpRequest.responseText);
                    alert('백팀점수 : ' + score.whiteScore + " 흑팀점수 : " + score.blackScore);
                }
            }

            httpRequest.open("get", "/status/1");
            httpRequest.setRequestHeader("Content-Type", "application/json");
            httpRequest.send({});
        })
    }
}

const moveButtonInitializer = {
    initializeMoveButton: function(chessBoard) {
        let moveButton = document.getElementById('move-button');
        let turn = chessBoard.turn;

        moveButton.addEventListener("click", async () => {
            let httpRequest = new XMLHttpRequest();
            let commands = document.getElementById("move-input").value;

            if (this.validateTurn(commands, turn)) {
                httpRequest.onreadystatechange = function() {
                    if (httpRequest.readyState === XMLHttpRequest.DONE) {
                        let chessBoard = JSON.parse(httpRequest.responseText);
                        boardDrawer.drawBoard(chessBoard);
                    }
                }

                httpRequest.open("post", "/move/1");
                httpRequest.setRequestHeader("Content-Type", "application/json");
                httpRequest.send(commands);
            }
        })
    },

    validateTurn: function(commands, turn) {
        let from = commands.split(" ")[0];
        let piece = document.getElementById(from);
        if (piece.classList[0].toLowerCase() == turn.toLowerCase()) {
            return true;
        }
        return false;
    }
}

const boardDrawer = {
    drawBoard: function(chessBoard) {
        boardDrawer.initBoard();
        let board = chessBoard.board;
        let positions = Object.keys(board);
        positions.sort();
        for (let i = 0; i < positions.length; i++) {
            let piece = board[positions[i]][0].toLowerCase();
            let color = board[positions[i]][1].toLowerCase();

            let img = document.createElement("img");
            img.src = "images/" + color + "_" + piece + ".png";
            let cell = document.getElementById(positions[i]);
            cell.appendChild(img);
        }
    },

    initBoard() {
        let rows = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'];
        let columns = ['1', '2', '3', '4', '5', '6', '7', '8'];
        for (let i = 0; i < rows.length; i++) {
            for (let j = 0; j < columns.length; j++) {
                let parent = document.getElementById(rows[i]+columns[j]);
                let child = parent.firstChild;
                if (child !== null) {
                    parent.removeChild(child);
                }
            }
        }
    }
}

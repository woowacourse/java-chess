const index = {
    init: function () {
        const _this = this;
        document.querySelector(".chess-start-btn").addEventListener("click", event => {
            _this.start();
        });

        document.querySelector(".chess-end-btn").addEventListener("click", event => {
            _this.end();
        });

        document.querySelector(".chess-status-btn").addEventListener("click", event => {
            _this.scores();
        });

        document.querySelector(".chess-board").addEventListener("click", event => {
            const fromInput = document.querySelector(".from");
            const toInput = document.querySelector(".to");

            const clickedPosition = decideClickedPosition(event.target);
            if (fromInput.value === "") {
                if (event.target.tagName !== "IMG" && event.target.firstChild.tagName !== "IMG") {
                    alert("선택한 칸에 말이 없습니다!");
                    return;
                }

                fromInput.value = clickedPosition;
                return;
            }

            if (fromInput.value === clickedPosition) {
                fromInput.value = "";
                return;
            }

            toInput.value = clickedPosition;
            _this.move(`move ${fromInput.value} ${toInput.value}`);

            fromInput.value = "";
            toInput.value = "";
        })

    },

    move: function (command) {
        fetch(`/pieces?command=${command}`)
            .then(data => {
                return data.json()
            })
            .then(boardDto => {
                clearBoard();
                placePieces(boardDto.pieceDtos);
                winToggleButtons(boardDto.isFinished);
                changeTurn(boardDto.isFinished);
            })
            .catch(error => {
                alert("잘못된 명령입니다!")
            });
    },

    start: function () {
        const option = {
            method: "POST",
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify("start")
        };

        fetch("/chessgames", option)
            .then(data => {
                return data.json()
            })
            .then(boardDto => {
                placePieces(boardDto.pieceDtos);
                startAndEndToggleButtons(boardDto.isFinished);
            })
            .catch(error => {
                alert("잘못된 명령입니다!");
            });
    },

    end: function () {
        const option = {
            method: "DELETE",
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify("end")
        };
        fetch("/chessgames", option)
            .then(data => {
                return data.json()
            })
            .then(boardDto => {
                clearBoard();
                startAndEndToggleButtons(boardDto.isFinished);
            })
            .catch(error => {
                alert("잘못된 명령입니다!")
            });
    },
    scores: function () {
        fetch("/scores")
            .then(data => {
                return data.json()
            })
            .then(scoreDtos => {
                printScores(scoreDtos);
            })
            .catch(error => {
                alert("잘못된 명령입니다!")
            });
    }
}

decideClickedPosition = (target) => {
    if (target.tagName === "IMG") {
        return target.parentElement.id;
    }

    if (target.tagName === "TD") {
        return target.id;
    }
}

changeTurn = (isFinished) => {
    console.log("changeTurn 실행")
    if (isFinished) {
        return;
    }

    const turnInfoClassList = document.querySelector(".turn-info.color").classList;
    if (turnInfoClassList.contains("is-black")) {
        turnInfoClassList.remove("is-black");
        turnInfoClassList.add("is-white");
        return;
    }

    turnInfoClassList.remove("is-white");
    turnInfoClassList.add("is-black");
}

printScores = (scoreDtos) => {
    document.querySelectorAll(".score")
        .forEach(aa => aa.classList.remove("hidden"));
    document.querySelector(".score-black").value = scoreDtos.blackScore;
    document.querySelector(".score-white").value = scoreDtos.whiteScore;
    document.querySelector(".score-black-value-tag").innerText = scoreDtos.blackScore;
    document.querySelector(".score-white-value-tag").innerText = scoreDtos.whiteScore;
}

winToggleButtons = (isFinished) => {
    if (isFinished) {
        document.querySelector(".chess-start-btn").classList.remove("hidden");
        document.querySelector(".chess-status-btn").classList.add("hidden");
        document.querySelector(".chess-end-btn").classList.add("hidden");
        document.querySelector(".turn-info.text").innerText = "승리!";
        return;
    }

    document.querySelector(".chess-start-btn").classList.add("hidden");
    document.querySelector(".chess-status-btn").classList.remove("hidden");
    document.querySelector(".chess-end-btn").classList.remove("hidden");
}

startAndEndToggleButtons = (isFinished) => {
    if (isFinished) {
        document.querySelector(".chess-start-btn").classList.remove("hidden");
        document.querySelector(".chess-status-btn").classList.add("hidden");
        document.querySelector(".chess-end-btn").classList.add("hidden");
        return;
    }

    document.querySelector(".turn-info.text").innerText = "누구 차례?";
    document.querySelector(".chess-start-btn").classList.add("hidden");
    document.querySelector(".chess-status-btn").classList.remove("hidden");
    document.querySelector(".chess-end-btn").classList.remove("hidden");
}

clearBoard = () => {
    document.querySelectorAll(".piece")
        .forEach(piece => piece.parentNode.removeChild(piece));
}

placePieces = pieceDtos => {
    pieceDtos.forEach(pieceDto => this.changeChessBoardUnitTemplate(pieceDto));
}

changeChessBoardUnitTemplate = (pieceDto) => {
    const position = pieceDto.position;
    const chessBoardUnit = document.querySelector(`#${position}`);
    const inputValue = `<img class="piece" src="images/${decidePieceColor(pieceDto.notation)}.png" alt=${pieceDto.notation}>`
    chessBoardUnit.innerHTML = inputValue;
}

decidePieceColor = (notation) => {
    return notation.charCodeAt(0) === notation.toUpperCase().charCodeAt(0)
        ? `black-${notation}` : `white-${notation}`;
}

index.init();

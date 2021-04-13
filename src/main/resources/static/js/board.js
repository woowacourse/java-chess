const $url = "http://localhost:4567/game/";
const $board = document.querySelector("#main");
const $sidebar = document.querySelector("#menu-container");
const $gameId = document.querySelector("#game-id").innerHTML;
let $isPlaying = true;
let $turnOwner = "WHITE";

window.addEventListener("load", loadBoard)
$board.addEventListener("mouseover", onSquare);
$board.addEventListener("mouseout", outSquare);
$board.addEventListener("click", onclickSquare);

async function loadBoard() {
    await fetch($url + $gameId + "/load")
        .then(data => {
            if (data.status === 400) {
                exceptionHandling(data.json());
            } else if (!data.ok) {
                throw new Error(data.status);
            }
            return data.json();
        })
        .then(pieces => {
            state();
            score();
            history();

            for (const pieceInfo of pieces) {
                const $symbol = pieceInfo.symbol;
                const $position = pieceInfo.position;

                if ($symbol === ".") {
                    continue;
                }

                if ($symbol === $symbol.toLowerCase()) {
                    const $imgTag = document.createElement("img");
                    $imgTag.classList.add("piece");
                    $imgTag.classList.add("white");
                    $imgTag.src = "../images/w" + $symbol + ".png";
                    $board.querySelector("#" + $position).appendChild($imgTag);
                }

                if ($symbol === $symbol.toUpperCase()) {
                    const $imgTag = document.createElement("img");
                    $imgTag.classList.add("piece");
                    $imgTag.classList.add("black");
                    $imgTag.src = "../images/b" + $symbol + ".png";
                    $board.querySelector("#" + $position).appendChild($imgTag);
                }
            }
        })
        .catch(error => {
            alert(error);
        })
}

function onSquare(event) {
    event.target.classList.add("onboard");
}

function outSquare(event) {
    event.target.classList.remove("onboard");
}

function onclickSquare(event) {
    let $source;
    if (event.target.tagName === 'IMG') {
        $source = event.target.closest('div').id;
    } else {
        $source = event.target.id;
    }
    const $selectSquare = $board.querySelector("#" + $source);

    if ($selectSquare.classList.contains("selected")) {
        removeMovablePath();
    } else if ($selectSquare.classList.contains("movable")) {

        const $source = $board.querySelector(".selected").id;
        const $target = event.target.closest("div").id;

        const $movePosition = {
            source: $source,
            target: $target
        }

        const $option = {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify($movePosition)
        }

        fetch($url + $gameId + '/move', $option)
            .then(data => {
                if (data.status === 400) {
                    exceptionHandling(data.json());
                } else if (!data.ok) {
                    throw new Error(data.status);
                }
                return data.json();
            })
            .then(history => {
                movePiece($source, $target);
                score();
                if ($isPlaying) {
                    state();
                }
                addHistory(history);
                afterFinishedChangeState()
            })
            .catch(error => {
                alert(error);
            })
    } else if (!$selectSquare.querySelector(".piece")) {
        if ($selectSquare.classList.contains("movable")) {
            return;
        }
        $selectSquare.querySelector('.highlight').setAttribute("src", "../images/red.png");
        setTimeout(invalidSquare, 1000, $source);
    } else if (!$selectSquare.querySelector(".piece").classList.contains($turnOwner.toLowerCase())) {
        $selectSquare.querySelector('.highlight').setAttribute("src", "../images/red-take.png");
        setTimeout(invalidSquare, 1000, $source);
    } else {
        fetch($url + $gameId + "/path?source=" + $source)
            .then(data => {
                if (data.status === 400) {
                    exceptionHandling(data.json());
                } else if (!data.ok) {
                    throw new Error(data.status);
                }
                return data.json()
            })
            .then(path => {
                removeMovablePath();
                $selectSquare.classList.add("selected");
                $selectSquare.querySelector('.highlight')
                    .setAttribute("src", "../images/green-select.png");

                let $isCatchableEnemy;
                for (const dto of path) {
                    $board.querySelector("#" + dto).classList.add("movable")
                    if ($board.querySelector("#" + dto).querySelector(".piece")) {
                        $isCatchableEnemy = true;
                    }
                }

                let $movableSquare = "../images/green.png"
                if ($isCatchableEnemy) {
                    $movableSquare = "../images/green-take.png"
                }
                for (const dto of path) {
                    $board.querySelector("#" + dto)
                        .querySelector('.highlight').setAttribute("src", $movableSquare);
                }
            })
            .catch(error => {
                alert(error);
            })
    }
}

function removeMovablePath() {
    const $selectedPiece = $board.querySelector(".selected");
    if ($selectedPiece) {
        $selectedPiece.querySelector(".highlight").setAttribute("src", "../images/null.png");
        $selectedPiece.classList.remove("selected");
    }

    const $movablePath = $board.querySelectorAll(".movable");

    $movablePath.forEach(path => {
        path.classList.remove("movable");
        path.querySelector(".highlight").setAttribute("src", "../images/null.png")
    })
}

function invalidSquare(id) {
    $board.querySelector("#" + id).querySelector('.highlight')
        .setAttribute("src", "../images/null.png");
}

function score() {
    fetch($url + $gameId + "/score")
        .then(data => {
            if (data.status === 400) {
                exceptionHandling(data.json());
            } else if (!data.ok) {
                throw new Error(data.status);
            }
            return data.json();
        })
        .then(status => {
            const $whiteScoreText = status.whiteScore;
            const $blackScoreText = status.blackScore;
            $sidebar.querySelector("#white-score").innerHTML = $whiteScoreText;
            $sidebar.querySelector("#black-score").innerHTML = $blackScoreText;
        })
        .catch(error => {
            alert(error);
        })
}

function state() {
    fetch($url + $gameId + "/state")
        .then(data => {
            if (data.status === 400) {
                exceptionHandling(data.json());
            } else if (!data.ok) {
                throw new Error(data.status);
            }
            return data.json();
        })
        .then(state => {
            if (!state.isPlaying) {
                $isPlaying = state.isPlaying;
                alert("게임이 종료됐습니다!");
                document.querySelector("#turn-color").classList.add("hidden");
                document.querySelector("#turn-number").classList.add("hidden");
                document.querySelector("#winner").innerHTML = $turnOwner + " 승리!!";
            }
            $turnOwner = state.turnOwner;
            document.querySelector("#turn-color").innerHTML = "현재 턴 : " + state.turnOwner;
            document.querySelector("#turn-number").innerHTML = "턴 횟수 : " + state.turnNumber;
        })
}

function afterFinishedChangeState() {
    if (!$isPlaying) {
        if ($turnOwner === "WHITE") {
            $turnOwner = "BLACK";
            return;
        }
        if ($turnOwner === "BLACK") {
            $turnOwner = "WHITE";
        }
    }
}


function replacePieceColor(targetImg) {
    if ($turnOwner === "WHITE") {
        targetImg.classList.replace("black", "white");
    }
    if ($turnOwner === "BLACK") {
        targetImg.classList.replace("white", "black");
    }
}

function history() {
    fetch($url + $gameId + "/history")
        .then(data => {
            if (data.status === 400) {
                exceptionHandling(data.json());
            } else if (!data.ok) {
                throw new Error(data.status);
            }
            return data.json();
        })
        .then(histories => {
            for (const history of histories) {
                addHistory(history);
            }
        })
        .catch(error => {
            alert(error);
        })
}

function addHistory(history) {
    document.querySelector("#historyList ol").insertAdjacentHTML("beforeend", renderHistoryItem(history));
    const $historyList = document.querySelector("#historyList");
    $historyList.scrollTop = $historyList.scrollHeight;
}

function renderHistoryItem(history) {
    return `<li>
                <dl>
                    <dt>
                        <span>${history.turnNumber}</span>
                    </dt>
                    <dd>
                        <span>${history.turnOwner} ${history.moveCommand}</span>
                    </dd>
                </dl>
            </li>`;
}

function movePiece(source, target) {
    const $sourceImg = $board.querySelector("#" + source).querySelector(".piece");
    const $targetImg = $board.querySelector("#" + target).querySelector(".piece");
    if ($targetImg) {
        replacePieceColor($targetImg);
        $targetImg.setAttribute("src", $sourceImg.getAttribute("src"));
        $board.querySelector("#" + source).removeChild($sourceImg);
    } else {
        $board.querySelector("#" + target).appendChild($sourceImg);
    }
    removeMovablePath();
}

function exceptionHandling(error) {
    error.then(data => {
        console.log(data);
        alert(data.exceptionMessage);
    })
}
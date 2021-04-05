const $board = document.querySelector("#main");

$board.addEventListener("mouseover", onSquare);
$board.addEventListener("mouseout", outSquare);
$board.addEventListener("click", clickSquareShowMovablePath);

function onSquare(event) {
    event.target.classList.add("onboard");
}

function outSquare(event) {
    event.target.classList.remove("onboard");
}

function clickSquareShowMovablePath(event) {
    let $source;
    if (event.target.tagName === 'IMG') {
        $source = event.target.closest('div').id;
    } else {
        $source = event.target.id;
    }
    if ($board.querySelector("#" + $source).classList.contains("selected")) {
        removeMovablePath();
    } else {
        fetch('http://localhost:4567/game/path?source=' + $source)
            .then(data => {
                if (!data.ok) {
                    throw new Error(data.status)
                }
                return data.json()
            }).then(path => {
            removeMovablePath();
            $board.querySelector("#" + $source).classList.add("selected");
            $board.querySelector("#" + $source).querySelector('.highlight').setAttribute("src", "./images/green-select.png");

            let $isCatchableEnemy;
            for (const dto of path) {
                $board.querySelector("#" + dto).classList.add("movable")
                if ($board.querySelector("#" + dto).querySelector(".piece")) {
                    $isCatchableEnemy = true;
                }
            }

            let $movableSquare = "./images/green.png"
            if ($isCatchableEnemy) {
                $movableSquare = "./images/green-take.png"
            }
            for (const dto of path) {
                $board.querySelector("#" + dto).querySelector('.highlight').setAttribute("src", $movableSquare);
            }
        }).catch(error => {
            $board.querySelector("#" + $source).querySelector('.highlight').setAttribute("src", "./images/red.png");
            setTimeout(invalidSquare, 1000, $source);
        })
    }
}

function removeMovablePath() {
    const $selectedPiece = $board.querySelector(".selected");
    if ($selectedPiece) {
        $selectedPiece.querySelector(".highlight").setAttribute("src", "./images/null.png");
        $selectedPiece.classList.remove("selected");
    }

    const $movablePath = $board.querySelectorAll(".movable");

    $movablePath.forEach(path => {
        path.classList.remove("movable");
        path.querySelector(".highlight").setAttribute("src", "./images/null.png")
    })
}

function invalidSquare(id) {
    $board.querySelector("#" + id).querySelector('.highlight').setAttribute("src", "./images/null.png");
}
const $board = document.querySelector(".board");
$board.addEventListener("click", onMovePiece);

async function onMovePiece(event) {
    if (event.target && event.target.parentElement.className === "position") {
        offSelectedPosition();
        offMovableAllPositions();
        const position = event.target.parentElement;
        position.classList.add("selected");

        const response = await fetch("./movable", {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({"id": position.id})
        });

        const result = await response.json();

        for (const dto of result.positionDtos) {
            $board.querySelector("#" + dto.id).classList.add("movable");
        }
        return;
    }

    if (event.target && event.target.classList.contains("movable")) {
        const sourcePosition = $board.querySelector(".selected");
        const targetPosition = event.target;

        const response = await fetch("./move", {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({
                "source": sourcePosition.id,
                "target": targetPosition.id
            })
        });

        const board = await response.json();

        for (let i = 0; i < 64; i++) {
            console.log("##########" + i);
            console.log(board.squareDtos[i].position);
            console.log(board.squareDtos[i].piece);
        }

        offSelectedPosition();
        offMovableAllPositions();
        return;
    }

    if (event.target && event.target.className === "position") {
        offSelectedPosition();
        offMovableAllPositions();
    }
}

function offSelectedPosition() {
    const position = $board.querySelector(".selected");
    if (position && position.classList.contains("selected")) {
        position.classList.remove("selected");
    }
}

function offMovableAllPositions() {
    const positions = $board.querySelectorAll(".movable");
    positions.forEach(function (position) {
        position.classList.remove("movable");
    })
}
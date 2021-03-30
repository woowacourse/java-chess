const $board = document.querySelector(".board");
$board.addEventListener("click", onClickPosition);

async function onClickPosition(event) {
    offSelectedPosition();
    offMovableAllPositions();

    if (event.target && event.target.parentElement.className === "position") {
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

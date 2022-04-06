let sourcePosition = "";


function move(position) {
    if (sourcePosition === "") {
        sourcePosition = position;
        return;
    }

    const object = {
        "source": sourcePosition,
        "destination": position,
    }

    movePiece(object, position);
}

function movePiece(object, position) {
    $.ajax({
        url: "/move",
        type: "POST",
        data: JSON.stringify(object),
        success(data) {
            const result = JSON.parse(data);
            printGameState(result);
            changePiece(position);
        },
        error(error) {
            resetSourPosition();
            alert(error.responseText);
        }
    })
}

function changePiece(position) {
    const source = sourcePosition;
    document.getElementById(position).innerHTML = document.getElementById(source).innerHTML;
    document.getElementById(source).innerHTML = "";
    resetSourPosition();
}

function resetSourPosition() {
    sourcePosition = ""
}

function printGameState(result) {
    if (result.isRunning === false) {
        alert(result.gameState + "가 승리했습니다.");
        document.getElementById("turn").innerText = "게임이 종료되었습니다.";
        getScore();
        return;
    }
    document.getElementById("turn").innerText = result.gameState + " Turn";
}
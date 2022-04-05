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

    $.ajax({
        url: "/move",
        type: "POST",
        data: JSON.stringify(object),
        success(data) {
            const result = JSON.parse(data);
            printGameState(result);
            movePiece(position);
        },
        error(error) {
            resetSourPosition();
            alert(error.responseText);
        }
    })
}

function movePiece(position) {
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
    document.getElementById("turn").innerText = result.gameState + "차례입니다.";
}
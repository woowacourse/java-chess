function move(moveInfo) {
    $.ajax({
        type: 'POST',
        url: '/api/move',
        data: JSON.stringify(moveInfo),
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        success: renderPiece,
        error: alertMessage
    });
}

function alertMessage(response) {
    alert(response.responseText);
}

function renderPiece(response) {
    const fromTo = response.split(" ");
    const sourceNode = document.getElementById(fromTo[0]);
    const targetNode = document.getElementById(fromTo[1]);

    targetNode.innerText = sourceNode.innerText;
    sourceNode.innerText = " ";
}

function boxClickHandler() {
    let isFrom = true;
    const moveInfo = {};
    return (event) => {
        if (isFrom && event.target.innerText !== " ") {
            moveInfo.from = event.target.id;
            isFrom = false;
        } else if (!isFrom) {
            moveInfo.to = event.target.id;
            isFrom = true;
            move(moveInfo);
        }
    };
}

function getMoveInfo() {
    $(`.box`).on(`click`, boxClickHandler())
}

function init() {
    getMoveInfo();
}

init();
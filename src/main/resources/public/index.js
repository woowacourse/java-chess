function move(moveInfo) {
    $.ajax({
        type: 'POST',
        url: '/api/move',
        data: JSON.stringify(moveInfo),
        dataType: 'json',
        contentType:'application/json; charset=utf-8',
        success: onSuccess,
    });
}

function onSuccess(response) {
    const fromTo = response.split(" ");
    const sourceNode = document.getElementById(fromTo[0]);
    const targetNode = document.getElementById(fromTo[1]);

    targetNode.innerText = sourceNode.innerText;
    sourceNode.innerText = " ";
}

function init() {
    let isFrom = true;
    const moveInfo = {};
    $(`.box`).on(`click`, (event) => {
        console.log("click");
        if (isFrom) {
            moveInfo.from = event.target.id;
            console.log(moveInfo.from);
            isFrom = false;
        }
        else if (!isFrom) {
            moveInfo.to = event.target.id;
            console.log(moveInfo.to);
            isFrom = true;
            move(moveInfo);
        }
    })
}

init();
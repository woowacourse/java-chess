let sourcePosition = "";

function move(position) {
    if (sourcePosition === "") {
        sourcePosition = position;
        return;
    }

    const object = {
        "source": sourcePosition,
        "destination": position,
        "roomId": "1"
    }

    $.ajax({
        url: "/move",
        type: "POST",
        data: JSON.stringify(object),
        success(data) {
            if (data) {
                alert(data);
                getScore();
            }
            movePiece(position)
        },
        error(error) {
            sourcePosition = "";
            alert(error.responseText);
        }
    })
}

function movePiece(position) {
    const source = sourcePosition;
    document.getElementById(position).innerHTML = document.getElementById(source).innerHTML;
    document.getElementById(source).innerHTML = "";
    sourcePosition = "";
}
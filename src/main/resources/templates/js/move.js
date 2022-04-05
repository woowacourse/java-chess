async function move(position) {
    if (document.getElementsByClassName('sourcePosition').length === 0) {
        document.getElementById(position).classList.add('sourcePosition');
        return;
    }
    const source = document.getElementsByClassName('sourcePosition')[0].id;
    const object = {
        "source" : source,
        "destination": position
    }
    $.ajax({
        url: "/move",
        type: "POST",
        data: JSON.stringify(object),
        success: function (data) {
                movePiece(position)
        },
        error: function (request, status, error){
            alert(error);
        }
    })
}

function movePiece(position) {
    document.getElementById(position).innerHTML = document.getElementsByClassName('sourcePosition')[0].innerHTML;
    document.getElementsByClassName('sourcePosition')[0].innerHTML = "";
    document.getElementsByClassName('sourcePosition')[0].classList.remove('sourcePosition');
}
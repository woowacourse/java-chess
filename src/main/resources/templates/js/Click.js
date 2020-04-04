function onClick(value) {
    if (document.getElementsByClassName('sourcePosition').length) {
        selectDestinationPiece(value);
    } else {
        sourcePosition(value);
    }
}

function sourcePosition(value) {
    document.getElementById(value).classList.add('sourcePosition');
}

function selectDestinationPiece(value) {
    document.getElementById(value).innerHTML = document.getElementsByClassName('sourcePosition')[0].innerHTML;
    console.log(document.getElementsByClassName('sourcePosition')[0].id);

    document.getElementsByClassName('sourcePosition')[0].innerHTML = "";
    document.getElementsByClassName('sourcePosition')[0].classList.remove('sourcePosition');
}
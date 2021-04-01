const pieces = document.querySelector(".chess-map");
const board = document.querySelector(".chess-map");

pieces.addEventListener('click', onClickEvent)
board.addEventListener("mouseover", onMouseOverEvent)
board.addEventListener("mouseout", onMouseOutEvent)

function onMouseOverEvent(event) {
    event.target.style.backgroundColor = "#b4dce7";
}

function onMouseOutEvent(event) {
    event.target.style.backgroundColor = "";
}

let source = null;
function onClickEvent(event) {
    if (source === null) {
        source = event.target.id;
    } else {
        let target = event.target.id;
        location.href = "/chess/move?source=" + source + "&target=" + target;
    }
}


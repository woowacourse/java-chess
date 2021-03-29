let source = null;
let target = null;

const squares = document.getElementsByClassName("square");
for (let i = 0; i < squares.length; i++) {
    squares.item(i).addEventListener("click", function () {
        mark(this);
        canMove(this);
    });
}

function mark(clickedLocation) {
    clickedLocation.style.boxShadow= "inset 3px 3px #ffd54f";
}

function canMove(clickedLocation) {
    if (source == null) {
        source = clickedLocation;
        return;
    }

    if (target == null) {
        target = clickedLocation;
        return;
    }
}
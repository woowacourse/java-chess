let source = null;
let target = null;

const tiles = document.getElementsByClassName("tile");
for (i = 0; i < tiles.length; i++) {
    tiles.item(i).addEventListener("click", function () {
        changeOpacity(this);
        checkSourceOrTarget(this.id);
    })
}

function checkSourceOrTarget(clickedPosition) {
    if (source == null) {
        source = clickedPosition;
        return;
    }
    if (source == clickedPosition) {
        source = null;
    }
    if (target == null) {
        target = clickedPosition;
        move(source, target);
    }
}

function move(source, target) {
    const moveInformation = {
        method: 'POST',
        headers: {'Content-Type': 'application/json',
            'source' : source,
            'target' : target}
    };
    fetch("/move", moveInformation).then(function (response) {
        console.log(response.body.getReader());
        // window.location = "http://localhost:4567/move"
    }).catch(function (error) {
        console.log("이동할 수 없습니다."+error);
    });
    initialize();
}

function changeOpacity(clickedPosition) {
    clickedPosition.style.opacity = "0.7";
}

function initialize() {
    source = null;
    target = null;
}

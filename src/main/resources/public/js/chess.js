function choose(value) {
    if (document.querySelector('.source')) {
        chooseDestination(value);
    } else {
        chooseSource(value);
    }
}

async function setPieces() {
    var pieceFileName = new Map();
    pieceFileName.set("K", "king");
    pieceFileName.set("Q", "queen");
    pieceFileName.set("B", "bishop");
    pieceFileName.set("N", "knight");
    pieceFileName.set("R", "rook");
    pieceFileName.set("P", "pawn");
    pieceFileName.set("BLACK", "black");
    pieceFileName.set("WHITE", "white");

    let status = await (fetch("/status").then((data) => data.json()));

    Object.values(document.querySelectorAll(".tile")).forEach(e => {
        e.innerHTML = "";
    });

    Object.keys(status.pieces).forEach(e => {
        document.querySelector("#" + e).innerHTML = "<img src='/img/" + pieceFileName.get(status.pieces[e].representation) + "_"
            + pieceFileName.get(status.pieces[e].team) + ".png' width='60px'>";
    });
    document.querySelector("#blackScore").innerText = status.score.blackScore;
    document.querySelector("#whiteScore").innerText = status.score.whiteScore;
}

function chooseSource(sourceValue) {
    document.getElementById(sourceValue).classList.add('source');
}

async function chooseDestination(destinationValue) {
    await document.getElementById(destinationValue).classList.add('destination');
    await move();
}

function clearSelection() {
    document.querySelector(".source").classList.remove('source');
    document.querySelector(".destination").classList.remove('destination');
}

async function move() {
    await $.ajax({
        url: '/move',
        async: true,
        type: 'POST',
        data: {
            source: document.querySelector(".source").id,
            destination: document.querySelector(".destination").id
        },
        dataType: 'text',
        success: function (jqXHR) {
            if (jqXHR) {
                alert(jqXHR);
            }
            setPieces();
        },
        error: function (jqXHR) {
            alert(jqXHR.responseText);
        },
        complete: clearSelection()
    });
}
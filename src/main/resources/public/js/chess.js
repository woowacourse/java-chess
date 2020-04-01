function choose(value) {
    if (document.getElementById('source').value !== '') {
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

    Object.keys(status.pieces).forEach(e => {
        document.querySelector("#" + e).innerHTML = "<img src='/img/" + pieceFileName.get(status.pieces[e].representation) + "_"
            + pieceFileName.get(status.pieces[e].team) + ".png' width='60px'>";
    });
    document.querySelector("#blackScore").innerText = status.score.blackScore;
    document.querySelector("#whiteScore").innerText = status.score.whiteScore;
}

function chooseSource(sourceValue) {
    document.getElementById('source').value = sourceValue;
    document.getElementById(sourceValue).classList.add('source');
}

function chooseDestination(destinationValue) {
    var redpoint = document.getElementsByClassName('destination').item(0);
    if (redpoint) {
        redpoint.classList.remove('destination');
    }
    document.getElementById('destination').value = destinationValue;
    document.getElementById(destinationValue).classList.add('destination');
}

function clearSelection() {
    if (document.querySelector(".source")) {
        document.querySelector(".source").classList.remove('source');
    }
    if (document.querySelector(".destination")) {
        document.querySelector(".destination").classList.remove('destination');
    }
    document.querySelector("#source").value = "";
    document.querySelector("#destination").value = "";
}

function move() {
    $.ajax({
        url: '/move', // 요청 할 주소
        async: false, // false 일 경우 동기 요청으로 변경
        type: 'POST', // GET, PUT
        data: {
            source: document.querySelector(".source").id,
            destination: document.querySelector(".destination").id
        }, // 전송할 데이터
        dataType: 'text', // xml, json, script, html
        success: function (jqXHR) {
            if (jqXHR) {
                alert(jqXHR);

            }
        },
        error: function (jqXHR) {
            alert(jqXHR.responseText);
        }
    });
    document.querySelector(".source").innerHTML = "";
    clearSelection();
    setPieces();
}
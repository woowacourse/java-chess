var dummy = null;


function loadBoardBackground() {
    let isBlack = false;
    const board = document.getElementById("chess-board");
    for (var i = 0; i < 8; i++) {
        const tr = document.createElement("tr");
        for (let j = 0; j < 8; j++) {
            isBlack = toggle(isBlack);
            const td = document.createElement("td");
            if (isBlack) {
                td.classList.add("black");
            } else {
                td.classList.add("white");
            }
            tr.appendChild(td);
        }
        isBlack = toggle(isBlack);
        board.appendChild(tr);
    }
}

function toggle(isBlack) {
    return !isBlack;
}

function drawBoardByResponse(responseJson) {
    drawBoard(responseJson['board']);
}

function drawBoard(board) {
    const responses = board['pieceResponses']
    for (let index in responses) {
        const piece = responses[index]
        drawPiece(piece['horizontalIndex'], piece['verticalIndex'], piece['type'], piece['color']);
    }
}

function drawPiece(horizontal, vertical, type, color) {
    // console.log(horizontal, vertical, type, color);
    const board = document.getElementById("chess-board");
    const image = document.createElement("img")
    image.setAttribute("src", `/images/${color}_${type}.png`);
    const point = board.rows[8 - vertical].cells[horizontal - 1]
    point.innerHTML = '';
    board.rows[8 - vertical].cells[horizontal - 1].appendChild(image);
}


function toJSON(form) {
    const json = {};
    const formData = new FormData(form);
    Array.from(formData.entries()).forEach(([key, value]) => {
        json[key] = value;
    })
    return JSON.stringify(json);
}

function getCurrentParam(key) {
    let params = (new URL(document.location)).searchParams;
    return params.get(key);
}

function showStatus(responseJson) {
    let string = 'WHITE SCORE = ' + responseJson['score']['WHITE'] +
        '\n' +
        'BLACK SCORE = ' + responseJson['score']['BLACK'];
    alert(string);
}

function setUpMain() {
    const moveForm = document.getElementById("move_form")
    moveForm.addEventListener("submit", e => {
        e.preventDefault();
        let roomName = getCurrentParam("room_name");

        send("/move?room_name=" + roomName, {
                method: 'post',
                body: toJSON(e.target),
                headers: new Headers({'Content-Type': 'application/json'})
            },
            drawBoardByResponse)
    });

    const statusForm = document.getElementById("status_form");
    statusForm.addEventListener("submit", e => {
        e.preventDefault();
        let roomName = getCurrentParam("room_name");
        send("/status?room_name=" + roomName, {
            method: 'get'
        }, showStatus);
    });

    const startForm = document.getElementById("start_form");
    startForm.addEventListener("submit", e => {
        e.preventDefault();
        let roomName = getCurrentParam("room_name");
        send("/start?room_name=" + roomName, {
            method: 'get'
        }, relocate);
    });

    const endForm = document.getElementById("end_form");
    endForm.addEventListener("submit", e => {
        e.preventDefault();
        let roomName = getCurrentParam("room_name");
        send("/end?room_name=" + roomName, {
            method: 'get'
        }, relocate);
    });

    console.log("setup done")
}

function relocate(responseJson) {
    console.log("responseJson in relocate =", responseJson);
    window.location.href = responseJson['url'];
}

function setUpIndex() {
    const createForm = document.getElementById("create_form");
    createForm.addEventListener("submit", e => {
        e.preventDefault();
        let roomName = new FormData(createForm).get("room_name");
        send("/create?room_name=" + roomName, {
            method: 'get'
        }, relocate);
    })

    console.log("setupIndex done")
}

async function send(path, fetchBody, handler) {
    async function alertIfException(responseJson) {
        console.log("toJSON = ", responseJson);
        if (responseJson['exception']) {
            alert(responseJson['exception']);
            return null;
        }
        return responseJson;
    }

    let response = await fetch(path, fetchBody);
    let responseJson = await response.json();
    if (await alertIfException(responseJson)) {
        handler(responseJson);
    }
    // let alertIfException1 = alertIfException(response);


    // then(response => alertIfException(response))
    //     .then(response => handler(response));
}
function onloadBody(id) {
    const board = document.getElementById("chessBoard");
    for (let row = 0; row < board.rows.length; row++) {
        addEventListenerEachCol(id, board.rows[row]);
    }
}

function addEventListenerEachCol(id, rows) {
    for (let col = 0; col < rows.cells.length; col++) {
        rows.cells[col].addEventListener("click", e => clickEvent(e, id));
    }
}

function toIntFile(pos) {
    const map = new Map([
        ["A", 0], ["B", 1], ["C", 2], ["D", 3],
        ["E", 4], ["F", 5], ["G", 6], ["H", 7]
    ]);
    return map.get(pos.substr(0, 1));
}

function toIntRank(pos) {
    return 8 - pos.substr(1, 2);
}

function toFile(value) {
    const map = new Map([
        [0, "A"], [1, "B"], [2, "C"], [3, "D"],
        [4, "E"], [5, "F"], [6, "G"], [7, "H"]
    ]);
    return map.get(value);
}

function toRank(value) {
    return String(8 - value);
}

function addPiece(pos, type, color) {
    const board = document.getElementById("chessBoard");
    board.rows[toIntRank(pos)].cells[toIntFile(pos)].innerHTML = "<img src='/image/" + type + "_" + color + ".png' width='80' height='80'/>";
}

let pickedPiece = null;

function clickEvent(event, id) {
    if (pickedPiece != null) {
        const form = createMoveForm(event, id);
        document.body.appendChild(form);
        form.submit();
    }

    if (pickedPiece == null) {
        pickedPiece = findPosition(event.currentTarget);
        console.log(pickedPiece);
    }
}

function createMoveForm(event, id) {
    const form = document.createElement("form");
    form.setAttribute("method", "post");
    form.setAttribute("action", "/chess-game/move");
    form.appendChild(createInputElement("from", pickedPiece));
    form.appendChild(createInputElement("to", findPosition(event.currentTarget)));
    form.appendChild(createInputElement("chess-game-id", id));
    return form;
}

function createInputElement(name, value) {
    const input = document.createElement("input");
    input.setAttribute("type", "hidden");
    input.setAttribute("name", name);
    input.setAttribute("value", value);
    return input
}

function findPosition(square) {
    const board = document.getElementById("chessBoard");
    for (let row = 0; row < board.rows.length; row++) {
        for (let col = 0; col < board.rows[row].cells.length; col++) {
            if (board.rows[row].cells[col] === square) {
                return toFile(col) + toRank(row);
            }
        }
    }
}
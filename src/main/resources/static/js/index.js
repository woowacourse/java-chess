const button = document.querySelector(".button");
const chessBoard = document.querySelector("table");

button.addEventListener("click", onClickButton);
chessBoard.addEventListener("click", onClickBoard);

function onClickButton ({target: {id}}) {
    if (id === "start-button") {
        onClickStartButton();
        return;
    }
    if (id === "end-button") {
        onClickEndButton();
        return;
    }
    if (id === "status-button") {
       onClickStatusButton();
        return;
    }
}

async function onClickStartButton () {
    const isRestart = confirm("이전 게임을 불러오시겠습니까?");
    const response = await getStartFetch(isRestart);
    const data = await response.json();

    if (response.ok) {
        initBoard(data);
        return;
    }

    alert(JSON.stringify(data));
}

async function getStartFetch (isRestart) {
    if (isRestart) {
        return await fetch("/restart");
    }
    return await fetch("/start");
}

function initBoard (data) {
    removeAllPiece();
    Object.entries(data.positionsAndPieces).forEach(([key, value]) => {
        const block = document.getElementById(key);
        block.appendChild(createPieceImage(value));
    });
}

function removeAllPiece () {
    chessBoard.querySelectorAll(".piece").forEach(e => e.remove());
}

function createPieceImage ({color, name}) {
    const image = document.createElement("img");
    image.src = `/images/pieces/${color}/${color}-${name}.svg`;
    image.width = 90;
    image.height = 90;
    image.classList.add("piece");
    return image;
}

async function onClickEndButton () {
    const response = await fetch("/end");
    if (response.ok) {
        alert("게임이 종료됐습니다!");
        return;
    }
    alert(JSON.stringify(await response.json()));
}

async function onClickStatusButton () {
    const response = await fetch("/status");
    const data = await response.json();
    alert(JSON.stringify(data, null, 2));
}

function onClickBoard ({target: {classList, id, parentNode}}) {
    if (!hasFirstSelected()) {
        classList.toggle("first-selected");
        return;
    }

    if (hasFirstSelected() && !hasSecondSelected()) {
        classList.add("second-selected");
        onClickPiece(id);
        return;
    }
}

function hasFirstSelected () {
    return chessBoard.querySelector(".first-selected") !== null;
}

function hasSecondSelected () {
    return chessBoard.querySelector(".second-selected") !== null;
}

async function onClickPiece (id) {
    const from = chessBoard.querySelector(".first-selected").parentNode.id;
    const to = getSecondSelectedId(id);

    removeSelected();

    const response = await fetch("/move", {
                       method: "put",
                       headers: {"Content-Type": "application/json"},
                       body: JSON.stringify({from: from, to: to})
                     });
    if (response.ok) {
        movePiece(from, to);
        const isRemovedKing = await response.json();
        if (isRemovedKing) {
            onClickStatusButton();
        }
        return;
    }

    const errorMessage = await response.json();
    alert(JSON.stringify(errorMessage));
}

function getSecondSelectedId (id) {
    if (id === "") {
        return chessBoard.querySelector(".second-selected").parentNode.id;
    }
    return id;
}

function removeSelected () {
    chessBoard.querySelector(".first-selected").classList.remove("first-selected");
    chessBoard.querySelector(".second-selected").classList.remove("second-selected");
}

function movePiece (from, to) {
    const fromPiece = document.getElementById(from).firstElementChild;
    const toPiece = document.getElementById(to).firstElementChild;
    if (toPiece !== null) {
        toPiece.remove();
    }
    fromPiece.remove();
    document.getElementById(to).appendChild(fromPiece);
}

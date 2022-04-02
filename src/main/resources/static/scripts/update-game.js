const FILE = ["a", "b", "c", "d", "e", "f", "g", "h"];
const RANK = [8, 7, 6, 5, 4, 3, 2, 1];

const IS_OCCUPIED = "occupied";
const SELECTED = "selected";

let sourcePieceKey = null;

const updateGame = async (source, target) => {
    const config = {
        headers: {'Content-Type': 'application/json'},
        method: "post",
        body: JSON.stringify({source, target})
    };
    const response = await fetch(window.location.pathname, config)
    if (response.ok) {
        window.location.reload();
    }
}

const toggleSelection = ({target: {id: positionKey, classList}}) => {
    console.log(positionKey);
    if (sourcePieceKey === null) {
        if (classList.contains(IS_OCCUPIED)) {
            sourcePieceKey = positionKey;
            classList.add(SELECTED);
        }
        return;
    }
    if (sourcePieceKey === positionKey) {
        sourcePieceKey = null;
        classList.remove(SELECTED);
        return;
    }
    updateGame(sourcePieceKey, positionKey);
}

const formatSquare = (square, positionKey) => {
    square.id = positionKey;
    square.classList.add(IS_OCCUPIED);
    square.addEventListener("click", toggleSelection);
}

const formatRow = (row, rowIdx) => {
    const squares = row.querySelectorAll("div.square");
    squares.forEach((square, fileIdx) => {
        const positionKey = FILE[fileIdx] + RANK[rowIdx];
        formatSquare(square, positionKey);
    })
};

const init = () => {
    const rows = document.querySelectorAll("div.row");
    rows.forEach((row, rowIdx) => formatRow(row, rowIdx));
}

init();
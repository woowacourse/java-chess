const IS_OCCUPIED = "isOccupied";

const FILE = ["a", "b", "c", "e", "f", "g", "h", "i"];
const RANK = [8, 7, 6, 5, 4, 3, 2, 1];

const rows = document.querySelectorAll("div.row");

const formatSquare = (square, positionKey) => {
    if (square.innerText.length > 0) {
        square.classList.add(IS_OCCUPIED);
    }
    square.id = positionKey;
}

const formatRow = (row, rowIdx) => {
    const squares = row.querySelectorAll("div.square");
    squares.forEach((square, fileIdx) => {
        const positionKey = FILE[fileIdx] + RANK[rowIdx];
        formatSquare(square, positionKey);
    })
};

const init = () => {
    rows.forEach((row, rowIdx) => formatRow(row, rowIdx));
}

init();
import data from "../json/sample.js";


const $chessboardSection = document.querySelector(".chessboard_section");

function drawBoard(data) {
    let innerValue = ''

    for (let i = 9; i > 0; i--) {
        innerValue += `<div class="row">`;
        for (let j = 0; j < 9; j++) {
            innerValue += makeCell(i, j, data);
        }
        innerValue += "</div>";
    }

    $chessboardSection.innerHTML = innerValue;
}

function makeCell(i, j, data) {
    function makePosition() {
        return `<div class="position">${data[i][j]}</div>`;
    }

    function makeBoardCell() {
        return `<div class="cell ${(i + j) % 2 === 0 ? "light" : "dark"}">${data[i][j]}</div>`;
    }

    if (i === 9 || j === 0) {
        return makePosition();
    }

    return makeBoardCell()
}

drawBoard(data);

console.log(data[1][1]);
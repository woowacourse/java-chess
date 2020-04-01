export {initDefaultBoard};

let rankNumber = 8;
const board = () => {
    let rank = "<div class='rank-board'><div class='rank-set'>";
    for (let x = 0; x < 4; x++) {
        rank += "<div class='rank'>";
        let file = 65;
        for (let i = 0; i < 4; i++) {
            rank += "<div class='cell white-cell' id='" + String.fromCharCode(file++) + rankNumber + "'></div><div class='cell black-cell' id='" + String.fromCharCode(file++) + rankNumber + "'></div>"
        }
        rankNumber--;
        rank += "</div>";
        rank += "<div class='rank'>";
        file = 65;
        for (let i = 0; i < 4; i++) {
            rank += "<div class='cell black-cell' id='" + String.fromCharCode(file++) + rankNumber + "'></div><div class='cell white-cell' id='" + String.fromCharCode(file++) + rankNumber + "'></div>"
        }
        rank += "</div>";
        rankNumber--;
    }
    rank += "</div>";
    rank += "<div class='rank-text-set'>";

    for (let x = 8; x > 0; x--) {
        rank += "<div class='rank-text'>" + x + "</div>";
    }
    rank += "</div></div>";
    rank += "<div class=\"file-container\">\n" +
        "                        <div class=\"file-text\">A</div>\n" +
        "                        <div class=\"file-text\">B</div>\n" +
        "                        <div class=\"file-text\">C</div>\n" +
        "                        <div class=\"file-text\">D</div>\n" +
        "                        <div class=\"file-text\">E</div>\n" +
        "                        <div class=\"file-text\">F</div>\n" +
        "                        <div class=\"file-text\">G</div>\n" +
        "                        <div class=\"file-text\">H</div>\n" +
        "                    </div>";
    return rank;
}
const initBoard = function () {
    document.getElementById("board").innerHTML = board();
}

const initDefaultBoard = function () {
    document.getElementById("board").innerHTML = board();
}

window.onload = () => {
    initBoard();
}

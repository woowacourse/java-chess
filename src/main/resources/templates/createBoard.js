let i = 8;
const board = () => {
    let rank = "<div class='rank-board'><div class='rank-set'>";
    for (let x = 0; x < 4; x++) {
        rank += "<div class='rank'>";
        for (let i = 0; i < 4; i++) {
            rank += "<div class='cell white-cell'></div><div class='cell black-cell'></div>"
        }
        rank += "</div>";
        rank += "<div class='rank'>";
        for (let i = 0; i < 4; i++) {
            rank += "<div class='cell black-cell'></div><div class='cell white-cell'></div>"
        }
        rank += "</div>";
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
    document.write(board());
}

window.onload(initBoard());
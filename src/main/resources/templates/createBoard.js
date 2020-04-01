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
    for (let file = 65; file < 73; file++) {
        document.getElementById(String.fromCharCode(file) + 2).innerHTML = "<img src='../img/piece/pawn_white.png' alt='' width='70' height='70'>";
    }
    document.getElementById("A1").innerHTML = "<img src='../img/piece/rook_white.png' alt='' width='70' height='70'>";
    document.getElementById("H1").innerHTML = "<img src='../img/piece/rook_white.png' alt='' width='70' height='70'>";
    document.getElementById("B1").innerHTML = "<img src='../img/piece/bishop_white.png' alt='' width='70' height='70'>";
    document.getElementById("G1").innerHTML = "<img src='../img/piece/bishop_white.png' alt='' width='70' height='70'>";
    document.getElementById("C1").innerHTML = "<img src='../img/piece/knight_white.png' alt='' width='70' height='70'>";
    document.getElementById("F1").innerHTML = "<img src='../img/piece/knight_white.png' alt='' width='70' height='70'>";
    document.getElementById("D1").innerHTML = "<img src='../img/piece/queen_white.png' alt='' width='70' height='70'>";
    document.getElementById("E1").innerHTML = "<img src='../img/piece/king_white.png' alt='' width='70' height='70'>";

    for (let file = 65; file < 73; file++) {
        document.getElementById(String.fromCharCode(file) + 2).innerHTML = "<img src='../img/piece/pawn_white.png' alt='' width='70' height='70'>";
    }
    document.getElementById("A1").innerHTML = "<img src='../img/piece/rook_white.png' alt='' width='70' height='70'>";
    document.getElementById("H1").innerHTML = "<img src='../img/piece/rook_white.png' alt='' width='70' height='70'>";
    document.getElementById("B1").innerHTML = "<img src='../img/piece/bishop_white.png' alt='' width='70' height='70'>";
    document.getElementById("G1").innerHTML = "<img src='../img/piece/bishop_white.png' alt='' width='70' height='70'>";
    document.getElementById("C1").innerHTML = "<img src='../img/piece/knight_white.png' alt='' width='70' height='70'>";
    document.getElementById("F1").innerHTML = "<img src='../img/piece/knight_white.png' alt='' width='70' height='70'>";
    document.getElementById("D1").innerHTML = "<img src='../img/piece/queen_white.png' alt='' width='70' height='70'>";
    document.getElementById("E1").innerHTML = "<img src='../img/piece/king_white.png' alt='' width='70' height='70'>";
}

window.onload = () => {
    initBoard();
}

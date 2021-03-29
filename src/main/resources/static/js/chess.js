var xmlhttp = new XMLHttpRequest();
var url = "http://localhost:8080/main";

function calculateCoordinate(i, j) {
    const y = 8 - i;
    const x = j + 97;
    return String.fromCharCode(x) + "" + y;
}

function printArr(myArr, currentTeamType) {
    const ui = document.getElementById("board");
    const html = document.querySelector("#template-list-piece").innerHTML;
    for (let i = 0; i < 8; i++) {
        let target = myArr[i];
        for (let j = 0; j < 8; j++) {
            let piece = target.pieces[j];
            const coordinate = calculateCoordinate(i, j);
            const pieceImg = html.replace("{url}", generateHTML(piece))
                .replace("{coordinate}", coordinate);
            ui.insertAdjacentHTML('beforeend', pieceImg);
        }
    }

    let element = document.querySelector('.current-team-type');
    element.textContent = "현재 턴 : " + currentTeamType;
    document.getElementsByClassName('piece').forEach(piece => {
        piece.addEventListener('click', function (event) {

        });
    });
}

function getName(piece) {
    let prefix;
    if (piece.teamType === "BLACK") {
        prefix = "B";
    } else {
        prefix = "W";
    }
    return prefix + piece.name.toUpperCase();
}

function generateHTML(piece) {
    let name;
    if (piece === null) {
        name = "Blank";
    } else {
        name = getName(piece);
    }
    return name;
}

xmlhttp.onreadystatechange = function () {
    if (this.readyState === 4 && this.status === 200) {
        var myArr = JSON.parse(this.responseText).data;
        printArr(myArr.rows, myArr.currentTeamType);
    }
};

xmlhttp.open("GET", url, true);
xmlhttp.send();

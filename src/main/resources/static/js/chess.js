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
    element.textContent = currentTeamType;
    Array.from(document.getElementsByClassName('piece')).forEach(piece => {
        piece.addEventListener('click', function (event) {
                let current = document.querySelector(".current");
                if (current.value.length === 0) {
                    current.value = piece.id;
                    piece.style.border = "1px solid red";
                    return;
                }
                if (piece.style.border === "1px solid red") {
                    current.value = '';
                    piece.style.border = '1px solid black';
                    return;
                }
                var data = JSON.stringify({
                    "current": current.value,
                    "destination": piece.id,
                    "teamType": currentTeamType
                });
                var post = new XMLHttpRequest();
                post.open("POST", "http://localhost:8080/main/post", true);
                post.send(data);
                current.value = '';
                post.onreadystatechange = function () {
                    if (post.readyState === 4 && post.status === 200) {
                        var myArr = JSON.parse(post.responseText).data;
                        Array.from(document.getElementsByClassName('piece'))
                            .forEach(t => t.remove());
                        printArr(myArr.rows, myArr.currentTeamType);
                    }
                }

            }
        );
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


function loadBoardBackground() {
    let isBlack = false;
    const board = document.getElementById("chess-board");
    for (var i = 0; i < 8; i++) {
        const tr = document.createElement("tr");
        for (let j = 0; j < 8; j++) {
            isBlack = toggle(isBlack);
            const td = document.createElement("td");
            if (isBlack) {
                td.classList.add("black");
                // td.textContent = "black";
            } else {
                td.classList.add("white");
                // td.textContent = "white";
            }
            tr.appendChild(td);
        }
        isBlack = toggle(isBlack);
        board.appendChild(tr);
    }
}

function toggle(isBlack) {
    return !isBlack;
}

function drawPiece(horizontal, vertical, type, color) {
    console.log(horizontal, vertical, type, color);
    const board = document.getElementById("chess-board");
    const image = document.createElement("img")
    image.setAttribute("src", `/images/${color}_${type}.png`);
    board.rows[8 - vertical].cells[horizontal - 1].appendChild(image);
}
//
// function setUp() {
//     let form = document.getElementById("move_form")
//     form.addEventListener("submit", e => {
//         e.preventDefault();
//         send(form, "/move");
//
//
//     });
//     console.log("setup done")
// }
//
//
// function send(form, path) {
//     const formData = new FormData(form);
//     const xhr = new XMLHttpRequest();
//     xhr.open("POST", path);
//     xhr.setRequestHeader("Content-Type", "application/json");
//
//     xhr.send(JSON.stringify(Object.fromEntries(formData)));
//     xhr.onreadystatechange = function() {
//         if (xhr.readyState === XMLHttpRequest.DONE) {
//             console.log(xhr.responseURL);
//             window.location.href=xhr.responseURL;
//             form.reset(); //reset form after AJAX success or do something else
//         }
//     }
//     return false;
// }
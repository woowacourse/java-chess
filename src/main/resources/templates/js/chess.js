const gameInfo = {
    K: "&#9818",
    Q: "&#9819",
    R: "&#9820",
    B: "&#9821",
    N: "&#9822",
    P: "&#9823",
    k: "&#9812",
    q: "&#9813",
    r: "&#9814",
    b: "&#9815",
    n: "&#9816",
    p: "&#9817",
    ".": "",
    "white turn": "white turn",
    "black turn": "black turn",
    "white wins": "white wins",
    "black wins": "black wins"
}

const $chessboard = document.querySelector(".chessboard");
const $startButton = document.querySelector("#startButton");
const $saveButton = document.querySelector("#saveButton");
const $loadButton = document.querySelector("#loadButton");

$chessboard.addEventListener("click", onSelect);
$startButton.addEventListener("click", onStart);
$saveButton.addEventListener("click", onSave);
$loadButton.addEventListener("click", onLoad);

function onLoad() {
        fetch("load").then((response) => response.json())
            .then(data => {
                if (data.message == "no data") {
                    alert("저장된 체스판이 없습니다.");
                    return;
                }
                Object.keys(data).forEach(key => {
                    if (key == "blackScore" || key == "whiteScore") {
                        document.querySelector("#" + key).innerHTML = key + " : " + data[key];
                        return;
                    }
                    document.querySelector("#" + key).innerHTML = gameInfo[data[key]]
            })});
         cleanBoard();
         return;
}

function onSave() {
    var message = document.querySelector("#message");
    if (message.textContent === gameInfo["white turn"] || message.textContent === gameInfo["black turn"]) {
        fetch("save").then((response) => {
            if (!response.ok) {
                console.log("not right");
            }
            console.log(response)});
        alert("현재 체스판 저장 완료");
        return;
    }
    alert("white turn이나 black turn일 때 저장할 수 있습니다.");
}

function onSelect(event) {
    if (!event.target.classList.contains("selected") && !event.target.classList.contains("possible")) {
        cleanBoard();
    }
    if (event.target && !event.target.classList.contains("chessboard")) {
        if (event.target.classList.contains("possible")) {
            var selectedSquare = document.getElementsByClassName("selected")[0];
            fetch("move", {
                  method: "POST",
                  headers: {
                    "Content-Type": "application/json",
                  },
                  body: JSON.stringify({
                    source: selectedSquare.id,
                    target: event.target.id,
                  }),
                }).then((response) => response.json())
                .then(data => Object.keys(data).forEach(key => {
                    if (key == "blackScore" || key == "whiteScore") {
                        document.querySelector("#" + key).innerHTML = key + " : " + data[key];
                        return;
                    }
                    document.querySelector("#" + key).innerHTML = gameInfo[data[key]]
                }));
             cleanBoard();
             return;
        }
        event.target.classList.add("selected");
        fetch("select", {
              method: "POST",
              headers: {
                "Content-Type": "application/json",
              },
              body: JSON.stringify({
                position: event.target.id
              }),
            }).then((response) => {
                if (!response.ok) {
                    console.log("not right");
                }
                return response.json()})
            .then(data => Object.keys(data).forEach(key => document.querySelector("#" + key).classList.add(data[key])));
    }
}

function onStart() {
    document.querySelector("#blackScore").innerHTML = "";
    document.querySelector("#whiteScore").innerHTML = "";
    fetch("start").then((response) => response.json())
        .then(data => Object.keys(data).forEach(key => document.querySelector("#" + key).innerHTML = gameInfo[data[key]]));
}

function cleanBoard() {
    for (var i = 0; i < $chessboard.children.length; i++) {
        $chessboard.children[i].classList.remove("selected", "possible");
    }
}

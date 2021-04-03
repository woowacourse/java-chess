const API_URL = "http://localhost:4567/";

const board = document.querySelector("#board");
const squares = document.querySelectorAll("#board .square")
const scoreBoard = document.getElementById("score-board-container");

let startPointSelected = false;
let startPoint = null;
let isKingDie = false;

function pieceClick(event) {
    if (isKingDie) {
        return;
    }
    console.log("move event!!")
    const clickedSection = event.target;
    console.log("you click" + clickedSection.id);

    if (startPointSelected === false) {
        if (clickedSection.style.backgroundImage === "") {
            console.log("그곳에는 말이 없습니다.");
            return;
        }
        startPointSelected = true;
        startPoint = clickedSection;
        startPoint.style.backgroundColor = "yellow";
        return;
    }

    // 여기까지 온것은, 말을 클릭하고, 목적지를 클릭한것
    startPointSelected = false;
    // 서버에게 시작부터 목적지를 갈수 있는지 물어본다.
    pieceMoveTry(startPoint, clickedSection);
    startPoint.style.backgroundColor = "";
    startPoint = null;
}


function pieceMoveTry(startPoint, endPoint) {
    console.log(startPoint, endPoint);
    const newData = {
        "startPoint": startPoint.id,
        "endPoint": endPoint.id
    }
    const option = getOption("PUT", newData);
    console.log(option);

    fetch(API_URL + "move", option)
        .then((response) => {
            if (!response.ok) {
                throw new Error("움직일 수 없는 위치입니다.");
            }
            return response.json();
            //return null;
        }).then((responseData) => {
        pieceMove(responseData);
        getStatus();
    })
        .catch((error) => {
            console.log(error);
            alert(error);
        })
}

function pieceMove(pieces) {
    boardReset();
    for (const piece of pieces) {
        const position = piece["position"];
        const team = piece["team"];
        const initial = piece["initial"];
        document.getElementById(position).style.backgroundImage = `url("/image/${team}_${initial}.png")`
    }
}

function boardReset() {
    for (const square of squares) {
        square.style.backgroundImage = "";
    }
}


function getStatus() {
    console.log("getStatus 가 호출됨");
    fetch(API_URL + "status", {
        method: 'GET'
    }).then((response) => {
        if (!response.ok) {
            throw new Error("getStatus 실패");
        }
        return response.json();
    }).then(applyStatus)
        .catch((error) => {
            console.log(error);
        });
}

function applyStatus(response) {
    scoreBoard.querySelector("#black-score .score").textContent = response["blackScore"];
    scoreBoard.querySelector("#white-score .score").textContent = response["whiteScore"];
    if (response["isKingDie"] === true) {
        alert("왕이 죽었습니다!");
        alert("승자는! " + response["winner"]);
        isKingDie = true;
    }
}

function getOption(methodType, bodyData) {
    return {
        method: methodType,
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(bodyData)
    };
}

function init() {
    for (const square of squares) {
        square.addEventListener("click", pieceClick)
    }
}

console.log("run..")
init();
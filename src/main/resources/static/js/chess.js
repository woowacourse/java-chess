const API_URL = "http://localhost:4567/";

const board = document.querySelector("#board");
const squares = document.querySelectorAll("#board .square")

let startPointSelected = false;
let startPoint = null;

function pieceClick(event) {
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
            return null;
        }).then(() => {
        location.reload();
    })
        .catch((error) => {
            console.log(error);
            alert(error);
        })
}


function pieceMove(startPoint, endPoint) {
    endPoint.style.backgroundImage = startPoint.style.backgroundImage;
    startPoint.style.backgroundImage = "";
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
const $move = document.querySelector('input[class="move"]')
const $save = document.querySelector('input[value="save"]')

$move.addEventListener('keyup', movePieceRequest);
$save.addEventListener('click', saveGame);

function movePieceRequest(event) {
    const moveCommand = event.target.value;
    if (event.key === "Enter" && moveCommand !== "") {
        event.target.value = "";
        const trimmedMoveCommand = moveCommand
            .replace(/\s+/g, ' ')
            .trim()
        const http = new XMLHttpRequest();
        const url = '/game/move';

        http.open('POST', url);
        http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        http.onreadystatechange = function () {
            const sourcePosition = trimmedMoveCommand.split(" ")[1]
            const targetPosition = trimmedMoveCommand.split(" ")[2]

            if (http.readyState === XMLHttpRequest.DONE) {
                if (http.status === 200) {
                    replaceComponents(http.responseText, sourcePosition, targetPosition)
                } else {
                    alert(sourcePosition + "에서" + targetPosition + "으로 움직일 수 없습니다!")
                }
            }
        }
        http.send(trimmedMoveCommand);
    }
}

function replaceComponents(dom, sourcePosition, targetPosition) {
    let parser = new DOMParser();
    let xmlDoc = parser.parseFromString(dom, "text/html");

    let source = xmlDoc.querySelector("#" + sourcePosition);
    let target = xmlDoc.querySelector("#" + targetPosition);
    let turn = xmlDoc.querySelector("#turn");

    document.querySelector("#" + sourcePosition).innerHTML = source.innerHTML
    document.querySelector("#" + targetPosition).innerHTML = target.innerHTML
    document.querySelector("#turn").innerHTML = turn.innerHTML

    const result = xmlDoc.querySelector("#result").textContent
    if (result !== "") {
        alert("black: " + result[0] + " / white : " + result[1])
        window.location.replace("/menu")
    }
}

function saveGame() {
    const params = {
        room_id: document.querySelector('#room').className,
        state: createStateJson(),
    };
    const http = new XMLHttpRequest();
    const url = '/game/save';

    http.open('POST', url);
    http.setRequestHeader('Content-type', 'application/json;charset=UTF-8');
    http.onreadystatechange = function () {
        if (http.readyState === XMLHttpRequest.DONE) {
            if (http.status === 200) {
                alert("성공적으로 저장되었습니다.")
            } else {
                alert("저장할 수 없습니다.")
            }
        }
    };
    http.send(JSON.stringify(params));
}

function createStateJson() {
    const status  = document.querySelectorAll('td')
    let state = {
        turn: document.querySelector('#turn').className,
    };
    for (var i = 0; i < status.length; i++) {
        const position = status[i].id;
        const img = status[i].querySelector('img');
        const color = img.id.split("_")[0]
        const type = img.id.split("_")[1]
        state[position] = {
            color: color,
            type: type,
        }
    }
    return state
}
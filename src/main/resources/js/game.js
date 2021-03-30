const $move = document.querySelector('input[class="move"]')

$move.addEventListener('keyup', movePieceRequest);

function movePieceRequest(event) {
    const moveCommand = event.target.value;
    if (event.key === "Enter" && moveCommand !== "") {
        event.target.value = "";
        const trimmedMoveCommand = moveCommand
            .replace(/\s+/g, ' ')
            .trim()
        const http = new XMLHttpRequest();
        const url = '/game';

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
    let turn = xmlDoc.querySelector("h1");

    document.querySelector("#" + sourcePosition).innerHTML = source.innerHTML
    document.querySelector("#" + targetPosition).innerHTML = target.innerHTML
    document.querySelector("h1").innerHTML = turn.innerHTML

    const result = xmlDoc.querySelector("#result").textContent
    if (result !== "") {
        alert("black: " + result[0] + " / white : " + result[1])
        window.location.replace("/menu")
    }
}
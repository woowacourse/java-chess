const $move = document.querySelector('input[class="move"]')

$move.addEventListener('keyup', movePieceRequest);

function movePieceRequest(event) {
    const moveCommand = event.target.value;
    if (event.key === "Enter" && moveCommand !== "") {
        const http = new XMLHttpRequest();
        const url = '/game';

        http.open('POST', url);
        http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');

        http.onreadystatechange = function () {
            if (http.readyState === 4 && http.status === 200) {
                // let player know next turn
            }
        }
        http.send(moveCommand);
        event.target.value = "";
    }
}
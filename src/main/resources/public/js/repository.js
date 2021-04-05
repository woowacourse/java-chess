const $div = document.querySelector('div')

$div.addEventListener('click', loadGame)

function loadGame(event) {
    const params = {
        room_id: event.target.value,
    };
    const http = new XMLHttpRequest();
    const url = '/game/load';

    http.open('POST', url);
    http.setRequestHeader('Content-type', 'application/json;charset=UTF-8');
    http.onreadystatechange = function () {
        if (http.readyState === XMLHttpRequest.DONE) {
            if (http.status === 200) {
                alert("성공적으로 게임을 불러왔습니다.")
                // window.history.pushState({"html" : http.responseText},"체스", "/game/load");
                document.write(http.responseText);
            } else {
                alert("게임을 불러올 수 없습니다.")
            }
        }
    };
    http.send(JSON.stringify(params));
}
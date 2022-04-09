let before = "";
let after = "";

const initialize = () => {
    document.querySelectorAll('.piece-image')
        .forEach(cell => cell.addEventListener('click', cellClick));
}

function getCommand(url, value) {
    window.location.replace(url + value);
}

function createRoom() {
    const roomName = window.prompt("방 제목을 입력해주세요.", "포돌이체스방");

    let f = document.createElement("form");
    f.setAttribute("method", "post");
    f.setAttribute("action", "/room/create"); // url
    document.body.appendChild(f);

    let i = document.createElement("input");
    i.setAttribute("type", "hidden"); //
    i.setAttribute("name", "roomName"); // key
    i.setAttribute("value", roomName); // value
    f.appendChild(i);
    f.submit();
}

function updateRoomName(id) {

    const roomName = window.prompt("바꿀 방 제목을 입력해주세요");

    let f = document.createElement("form");
    f.setAttribute("method", "post");
    f.setAttribute("action", "/room/update/" + id); //url
    document.body.appendChild(f);

    let i = document.createElement("input");
    i.setAttribute("type", "hidden");
    i.setAttribute("name", "roomName"); // key
    i.setAttribute("value", roomName); // value
    f.appendChild(i);

    let i2 = document.createElement("input");
    i2.setAttribute("type", "hidden");
    i2.setAttribute("name", "roomId"); // key
    i2.setAttribute("value", id); // value
    f.appendChild(i2);

    f.submit();
}

function deleteRoom(id) {

    let f = document.createElement("form");
    f.setAttribute("method", "post");
    f.setAttribute("action", "/room/delete/" + id); //url
    document.body.appendChild(f);

    let i = document.createElement("input");
    i.setAttribute("type", "hidden");
    i.setAttribute("name", "roomId"); // key
    i.setAttribute("value", id); // value
    f.appendChild(i);

    f.submit();
}


function postRoomWithIdAndUrl(url, url) {
    let f = document.createElement("form"); // form 엘리멘트 생성
    f.setAttribute("method", "post"); // method 속성을 post로 설정
    f.setAttribute("action", url + value);
    document.body.appendChild(f);

    let i = document.createElement("input");
    i.setAttribute("type", "hidden");
    i.setAttribute("name", "command"); // name
    i.setAttribute("value", value); // value
    f.appendChild(i);
    f.submit();
}


function joinRoomAndStart(id) {
    //1. /start 로 post (/:command)
    let f = document.createElement("form"); // form 엘리멘트 생성
    f.setAttribute("method", "post"); // method 속성을 post로 설정
    f.setAttribute("action", "/start");
    document.body.appendChild(f);

    //2. "command" key - "start" value
    let i = document.createElement("input");
    i.setAttribute("type", "hidden");
    i.setAttribute("name", "command"); // name
    i.setAttribute("value", "start"); // value
    f.appendChild(i);

    //3. "roomId" key - (id) -> id value
    let i2 = document.createElement("input");
    i2.setAttribute("type", "hidden");
    i2.setAttribute("name", "roomId"); // name
    i2.setAttribute("value", id); // value
    f.appendChild(i2);

    f.submit();
}

function postToCommand(id, value) {
    //1. '/' + value(start) 로 post  to /:command
    let f = document.createElement("form"); // form 엘리멘트 생성
    f.setAttribute("method", "post"); // method 속성을 post로 설정
    f.setAttribute("action", "/" + value);
    document.body.appendChild(f);

    let i = document.createElement("input");
    i.setAttribute("type", "hidden");
    i.setAttribute("name", "command"); // name
    i.setAttribute("value", value); // value
    f.appendChild(i);

    //3. "roomId" key - (id) -> id value
    let i2 = document.createElement("input");
    i2.setAttribute("type", "hidden");
    i2.setAttribute("name", "roomId"); //roomId
    i2.setAttribute("value", id); // value
    f.appendChild(i2);

    f.submit();
}

const cellClick = (event) => {
    let currentPieceImg = event.target;

    if (before === "") {
        before = currentPieceImg;
        before.style.backgroundColor = '#ffb9b9';
        return;
    }

    if (before !== "" && after === "") {
        after = currentPieceImg;
        after.style.backgroundColor = '#fc8383';

        postMoveCommandWith(before, after)

        before = "";
        after = "";

        return;
    }
}

function postMoveCommandWith(before, after) {
    let f = document.createElement("form"); // form 엘리멘트 생성
    f.setAttribute("method", "post"); // method 속성을 post로 설정
    f.setAttribute("action", "/move"); // submit했을 때 무슨 동작을 할 것인지 설정
    document.body.appendChild(f);

    let i = document.createElement("input"); // input 엘리멘트 생성
    i.setAttribute("type", "hidden"); // type 속성을 hidden으로 설정
    i.setAttribute("name", "command"); // name 속성을 'm_nickname'으로 설정
    i.setAttribute("value", "move " + before.id + " " + after.id); // value 속성을 neilong에 담겨있는 값으로 설정
    f.appendChild(i); // form 엘리멘트에 input 엘리멘트 추가

    //3. "roomId" key - (id) -> id value
    // let i2 = document.createElement("input");
    // i2.setAttribute("type", "hidden");
    // i2.setAttribute("name", "roomId"); //roomId
    // i2.setAttribute("value", id); // value
    // f.appendChild(i2);

    f.submit();
}

initialize();

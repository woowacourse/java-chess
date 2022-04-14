let before = "";
let after = "";

// const initialize = () => {
//1. game.hmtl 기물 이미지태그에 onlclick 달아주기
// document.querySelectorAll('.piece-image')
//     .forEach(cell => cell.addEventListener('click', cellClick));
//2-1. game.hmtl 기물 이미지태그에 -> 현재 오는 {{roomId}}정보를 받으려면,
// -> 여기서 바로찾지말고, body에서 onlick을 달아서 function(id) -> function(id)에서  piece-image img태그 찾아서 id 배정해줘야할 듯??
// --> 즉, querySelectorAll()보다 바로 윗단계에서 id를 받아와야하는데,
// -> js 맨 마지막에 initialize()로 호출할게 아니라..
// -> html에서 onload = " function({{id}}) " 로 호출해서 해야한다.
// }

function onloadBody(id) {
    document.querySelectorAll('.piece-image')
        // .forEach(cell => cell.addEventListener('click', cellClick));
        //2-2. onclick 메서드에  func 대신 e -> func(e, id)로 람다식을 명시한다.
        .forEach(cell => cell.addEventListener('click', e => cellClick(e, id)));

}

//2-3.
// const cellClick = (event) => {
const cellClick = (event, id) => {
    console.log(id);
    console.log(before);
    console.log(after);
    let currentPieceImg = event.target;

    if (before === "") {
        before = currentPieceImg;
        before.style.backgroundColor = '#ffb9b9';
        return;
    }

    if (after === "") {
        after = currentPieceImg;
        after.style.backgroundColor = '#fc8383';

        // postMoveCommandWith(before, after)
        postMoveCommandWith(before, after, id)

        before = "";
        after = "";

        return;
    }
}

function getCommand(url, value) {
    window.location.replace(url + value);
}

function createRoom() {
    const roomName = window.prompt("방 제목을 중복되지 않도록 입력해주세요.");

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
    f.setAttribute("action", "/room/update/"); //url
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
    f.setAttribute("action", "/room/delete/"); //url
    document.body.appendChild(f);

    let i = document.createElement("input");
    i.setAttribute("type", "hidden");
    i.setAttribute("name", "roomId"); // key
    i.setAttribute("value", id); // value
    f.appendChild(i);

    console.log(f);
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

function postMoveCommandWith(before, after, id) {
    let f = document.createElement("form"); // form 엘리멘트 생성
    f.setAttribute("method", "post"); // method 속성을 post로 설정
    f.setAttribute("action", "/move"); // submit했을 때 무슨 동작을 할 것인지 설정
    document.body.appendChild(f);

    let i = document.createElement("input"); // input 엘리멘트 생성
    i.setAttribute("type", "hidden"); // type 속성을 hidden으로 설정
    i.setAttribute("name", "command"); // name 속성을 'm_nickname'으로 설정
    i.setAttribute("value", "move " + before.id + " " + after.id); // value 속성을 neilong에 담겨있는 값으로 설정
    f.appendChild(i); // form 엘리멘트에 input 엘리멘트 추가

    //2-4. "roomId" key - (id) -> id value
    let i2 = document.createElement("input");
    i2.setAttribute("type", "hidden");
    i2.setAttribute("name", "roomId"); //roomId
    i2.setAttribute("value", id); // value
    f.appendChild(i2);

    f.submit();
}

// initialize();

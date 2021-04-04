let roomListData = [];
const btnCreate = document.getElementById('btn-game-create')
btnCreate.addEventListener('click', function (e) {
    let name = prompt("사용자 이름을 입력해 주세요.");
    let pw = prompt("비밀번호를 입력해 주세요");
    axios.post('/createRoom', {
        "name": name,
        "pw": pw
    }).then(function (response) {

        let data = response.data;
        if (data.success) {
            refreshRoomList(data.data)
        } else {
            alert(data.message)
        }
    })
})

const roomList = document.getElementById('list-chess-game')
roomList.addEventListener('click', function (e) {
    enterGame(e);
})

function refreshRoomList(data) {
    console.log(data);
    let rooms = JSON.parse(data);
    roomListData = rooms.roomDTOList;
    console.log(roomListData);
    let list = document.getElementById("list-chess-game");
    list.innerHTML = "";
    for (let i = 0; i < roomListData.length; i++) {
        let room = roomListData[i];
        list.innerHTML += "<div class = box-chess-game data-idx = " + i + ">\n" +
            "<p class=box-chess-game-title> " + room.name + "</p>\n" +
            "</div>\n"
    }
}

function enterGame(e) {
    let idx = e.target.dataset.idx
    let room = roomListData[idx];
    location.href = '/enterRoom?id=' + room.id;
}


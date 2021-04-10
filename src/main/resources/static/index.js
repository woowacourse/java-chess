let roomListData = [];
const btnCreate = document.getElementById('btn-game-create')

getTotalRoom();

function getTotalRoom() {
    axios.get('/getTotalRoom')
        .then(function (response) {
            refreshRoomList(response.data)
        }).catch(function (error) {
        alert('방 정보를 갱신하지 못했습니다.');
        });
}

btnCreate.addEventListener('click', function (e) {
    let name = prompt("사용자 이름을 입력해 주세요.");
    let pw = prompt("비밀번호를 입력해 주세요");
    axios.post('/createRoom', {
        "name": name,
        "pw": pw
    }).then(function (response) {
        refreshRoomList(response.data)
    }).catch(function (error) {
        alert('방을 만들지 못했습니다.');
    });
})

const roomList = document.getElementById('list-chess-game')
roomList.addEventListener('click', function (e) {
    enterGame(e);
})

function refreshRoomList(data) {
    roomListData = data.roomDTOList;
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
    let pw = prompt("비밀번호를 입력해 주세요");
    if (pw === room.pw) {
        location.href = '/enterRoom?id=' + room.id;
    } else {
        alert('비밀번호가 틀렸습니다.')
    }
}


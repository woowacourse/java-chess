const btnCreate = document.getElementById('btn-game-create')
btnCreate.addEventListener('click', function (e) {
    let name = prompt("사용자 이름을 입력해 주세요.");
    let pw = prompt("비밀번호를 입력해 주세요");

    console.log(name);
    console.log(pw);
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

function refreshRoomList(data) {
    let roomListData = JSON.parse(data);
    let roomList = roomListData.roomDTOList;
    let list = document.getElementById("list-chess-game");
    list.innerHTML = "";
    for (let i = 0; i < roomList.length; i++) {
        let room = roomList[i];
        console.log(room);
        list.innerHTML += " <div class=\"box-chess-game\">\n" +
            "        <p class=\"box-chess-game-title\"> " + room.name + "</p>\n" +
            "    </div>\n"
    }
}


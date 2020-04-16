const rooms = document.getElementById("rooms");
const createRoomName = document.getElementById("room-name");
const creatRoom = document.getElementById("create-room");
const deleteRoom = document.getElementById("delete-room");
const intoRoom = document.getElementById("into-room");

fetch('/viewRooms').then(res => res.json()).then(data => {
    roomSetting(data);
});

intoRoom.onclick = () => {
    if (rooms.value === "") {
        alert("들어갈 방이 없습니다. 방을 생성하세요.");
        createRoomName.focus();
        return;
    }
    document.getElementById("start").submit();
};

deleteRoom.onclick = () => {
    if (rooms.value === "") {
        alert("삭제할 방이 없습니다. 방을 만들어보면 어떨까요?");
        createRoomName.focus();
        return;
    }
    let roomId = rooms.value;
    fetch("/deleteRoom", {
        method: 'Post',
        body: JSON.stringify({
            roomId
        })
    }).then(res => res.json()).then(data => {
        initialRooms();
        roomSetting(data);
        createRoomName.value = "";
    })
};

creatRoom.onclick = () => {
    let roomName = createRoomName.value;
    let roomPassword = '';
    fetch('/createRoom', {
        method: 'POST',
        body: JSON.stringify({
            roomName, roomPassword
        })
    }).then(res => res.json()).then(data => {
        initialRooms();
        roomSetting(data);
        createRoomName.value = "";
    })
};

function roomSetting(data) {
    for (key in data.rooms) {
        if (data.rooms.hasOwnProperty(key)) {
            let opt = document.createElement("option");
            opt.value = key;
            opt.textContent = "#" + key + " - " + data.rooms[key];
            rooms.appendChild(opt);
        }
    }
}

function initialRooms() {
    let roomsChild = rooms.children;
    for (let index = 0; index < rooms.childElementCount;) {
        rooms.removeChild(roomsChild.item(index));
    }
}
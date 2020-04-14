const rooms = document.getElementById("rooms");
const createRoomName = document.getElementById("room-name");
const creatRoom = document.getElementById("create-room");
const deleteRoom = document.getElementById("delete-room");
const intoRoom = document.getElementById("into-room");

let roomName = null;
let roomPassword = null;
let roomsChild = null;
let index = null;
let roomId = null;

fetch('/viewRooms').then(res => res.json()).then(data => {
    for (key in data.rooms) {
        let opt = document.createElement("option");
        opt.value = key;
        opt.textContent = data.rooms[key];
        rooms.appendChild(opt);
    }
});

intoRoom.onclick = () => {
    if (rooms.value === "") {
        alert("들어갈 방이 없습니다. 방을 생성하세요.");
        createRoomName.focus()
        return;
    }
    document.getElementById("start").submit();
};

deleteRoom.onclick = () => {
    if (rooms.value === "") {
        alert("삭제할 방이 없습니다.");
        return;
    }
    roomId = rooms.value;
    roomsChild = rooms.children;
    for (index = 0; index < rooms.childElementCount;) {
        rooms.removeChild(roomsChild.item(index));
    }
    fetch("/deleteRoom", {
        method: 'Post',
        body: JSON.stringify({
            roomId
        })
    }).then(res => res.json()).then(data => {
        for (key in data.rooms) {
            let opt = document.createElement("option");
            opt.value = key;
            opt.textContent = data.rooms[key];
            rooms.appendChild(opt);
            createRoomName.value = "";
        }
    })
};

creatRoom.onclick = () => {
    roomsChild = rooms.children;
    for (index = 0; index < rooms.childElementCount;) {
        rooms.removeChild(roomsChild.item(index));
    }
    roomName = createRoomName.value;
    roomPassword = '';
    fetch('/createRoom', {
        method: 'POST',
        body: JSON.stringify({
            roomName, roomPassword
        })
    }).then(res => res.json()).then(data => {
        for (key in data.rooms) {
            let opt = document.createElement("option");
            opt.value = key;
            opt.textContent = data.rooms[key];
            rooms.appendChild(opt);
            createRoomName.value = "";
        }
    })
};

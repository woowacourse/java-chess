const rooms = document.querySelector("#rooms");
const newRoom = document.querySelector("#newRoom");
const form = document.querySelector("#form");
const roomId = document.querySelector("#roomId");

window.addEventListener("load", onWindowLoad);
newRoom.addEventListener("click", onNewRoom);
rooms.addEventListener("click", onRoomSelect);

function onRoomSelect(event) {
    if (event.target && event.target.nodeName == "LI") {
        roomId.value = event.target.id;
        form.submit();
    }
}

function onNewRoom() {
    fetch("newRoomId")
        .then(response => {
            if (response.ok) {
                return response.json();
            }
            throw new Error("새 게임을 만들 수 없습니다.");
        })
        .then(data => {
            roomId.value = data["roomId"]
            form.submit();
        })
        .catch(error => {
            console.error('fetch error:', error);
        });
}

function onWindowLoad() {
    fetch("rooms")
        .then(response => {
            if (response.ok) {
                return response.json();
            }
            var element = document.createElement("li");
            element.textContent = "방이 없습니다! 새 게임을 눌러 방을 만들어주세요.";
            rooms.appendChild(element);
            throw new Error("방이 없습니다.");
        })
        .then(data => Object.values(data).forEach(value => {
            var element = document.createElement("li");
            element.id = value;
            element.textContent = value;
            element.classList.add("clickable");
            rooms.appendChild(element);
        }))
        .catch(error => {
            console.error('fetch error:', error);
        });
}

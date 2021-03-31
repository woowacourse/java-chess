function initiate() {
    const xmlHttp = new XMLHttpRequest();
    const url = 'http://localhost:8080/room/update'
    xmlHttp.onreadystatechange = function () {
        if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
            const response = JSON.parse(xmlHttp.responseText);
            const roomListNode = document.getElementById('room-list');
            for (let i = 0; i < response.length; i++) {
                const roomNode = createRoom(response[i].id, response[i].name);
                roomListNode.appendChild(roomNode);
            }
        }
    };
    xmlHttp.open('GET', url, true);
    xmlHttp.send();
}

function createRoom(roomNumber, name) {
    const roomNode = document.createElement('li');
    const link = document.createElement('a');
    link.href = '/chessgame/' + roomNumber;
    link.textContent = name;
    roomNode.appendChild(link);
    return roomNode;
}

function addRoomRegistrationEvent() {
    const createButton = document.getElementById('create-button');
    createButton.addEventListener('click', function (event) {
        event.preventDefault();
        const xmlHttp = new XMLHttpRequest();
        const url = 'http://localhost:8080/room/add';
        const roomName = document.getElementById('room-name').value;
        const requestData = JSON.stringify({'name': roomName});
        xmlHttp.onreadystatechange = function () {
            if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
                const roomListNode = document.getElementById('room-list');
                const response = JSON.parse(xmlHttp.responseText);
                const roomNode = createRoom(response.id, response.name);
                roomListNode.appendChild(roomNode);
            }
        };
        xmlHttp.open('POST', url, true);
        xmlHttp.send(requestData);
    });
}

initiate();
addRoomRegistrationEvent();

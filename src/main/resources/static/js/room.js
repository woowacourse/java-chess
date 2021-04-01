const ROOM_LIST_NODE = document.getElementById('room-list');

function initiateRoomRegistrationEvent() {
    const createButton = document.getElementById('create-button');
    createButton.addEventListener('click', function (event) {
        event.preventDefault();
        const xmlHttp = new XMLHttpRequest();
        const url = 'http://localhost:8080/room/add';
        const roomNameNode = document.getElementById('room-name');
        const requestData = JSON.stringify({'name': roomNameNode.value});
        xmlHttp.onreadystatechange = function () {
            if (isValidHttpResponse(this)) {
                const responseData = JSON.parse(xmlHttp.responseText);
                const roomNode = createRoomNode(responseData.id, responseData.name);
                ROOM_LIST_NODE.appendChild(roomNode);
                roomNameNode.value = '';
            }
        };
        xmlHttp.open('POST', url, true);
        xmlHttp.send(requestData);
    });
}

function isValidHttpResponse(xmlHttp) {
    return xmlHttp.readyState === 4 && xmlHttp.status === 200;
}

function createRoomNode(id, name) {
    const roomNode = document.createElement('li');
    const link = document.createElement('a');
    link.href = '/chessgame/' + id;
    link.textContent = name;
    roomNode.appendChild(link);
    return roomNode;
}

function showRoomList() {
    const xmlHttp = new XMLHttpRequest();
    const url = 'http://localhost:8080/room/show'
    xmlHttp.onreadystatechange = function () {
        if (isValidHttpResponse(this)) {
            const roomList = JSON.parse(this.responseText);
            for (let i = 0; i < roomList.length; i++) {
                const roomNode = createRoomNode(roomList[i].id, roomList[i].name);
                ROOM_LIST_NODE.appendChild(roomNode);
            }
        }
    };
    xmlHttp.open('GET', url, true);
    xmlHttp.send();
}

initiateRoomRegistrationEvent();
showRoomList();

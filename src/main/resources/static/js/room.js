function initiate() {
    const xmlHttp = new XMLHttpRequest();
    const url = 'http://localhost:8080/room/update'
    xmlHttp.onreadystatechange = function () {
        if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
            const response = JSON.parse(xmlHttp.responseText);
            const roomListNode = document.getElementById('room-list');
            const html = '<a href=\"chessgame/{roomNumber}\">{name}</a>';
            for (let i = 0; i < response.length; i++) {
                const li = document.createElement('li');
                const roomNodeHtml = html.replace('{roomNumber}', response[i].roomNumber)
                    .replace('{name}', response[i].name);
                li.insertAdjacentHTML('beforeend', roomNodeHtml);
                roomListNode.appendChild(li);
            }
        }
    };
    xmlHttp.open('GET', url, true);
    xmlHttp.send();
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
                const html = '<a href=\"chessgame/{roomNumber}\">{name}</a>';
                const li = document.createElement('li');
                const response = JSON.parse(xmlHttp.responseText);
                const roomNodeHtml = html.replace('{roomNumber}', response.roomNumber)
                    .replace('{name}', response.name);
                li.insertAdjacentHTML('beforeend', roomNodeHtml);
                roomListNode.appendChild(li);

            }
        };
        xmlHttp.open('POST', url, true);
        xmlHttp.send(requestData);
    });
}

initiate();
addRoomRegistrationEvent();

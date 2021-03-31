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
                response[i].roomNumber;
                response[i].name;
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

initiate();

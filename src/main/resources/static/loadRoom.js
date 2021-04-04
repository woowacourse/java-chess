window.onload = findRooms();

function findRooms() {
    axios({
        method: 'get',
        url: '/roomNumber'
    }).then(function (numberList) {
        let roomNumberList = numberList.data;
        let select = document.getElementById('room-number');
        for (let i = 0; i < roomNumberList.length; i++) {
            let opt = document.createElement('option');
            opt.setAttribute("value", roomNumberList[i]);
            opt.innerHTML = roomNumberList[i] + "번 방";
            select.appendChild(opt);
        }
    });
}
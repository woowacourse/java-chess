window.onload = startBtnClick;

function startBtnClick() {
    let currentUrl = location.href;
    let roomNumber = currentUrl.slice(currentUrl.indexOf('?') + 1, currentUrl.length).split('=')[1];
    if (roomNumber == 'new') {
        roomNumber = document.getElementById('roomNumber').innerText;
    }
    location.href = '/start?roomNumber=' + roomNumber;
}
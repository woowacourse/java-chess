let rooms = document.querySelectorAll('.room-no');
for (let i = 0; i < rooms.length; ++i) {
    rooms[i].innerHTML = i + 1;
}

let users = document.querySelectorAll('.user-no');
for (let i = 0; i < users.length; ++i) {
    users[i].innerHTML = i + 1;
}


function createRoom() {
    let name = prompt("방 이름을 입력해주세요");
    if (name !== "") {
        newGame(name);
    } else {
        alert("방 이름은 한 글자 이상이어야합니다.");
    }
}

function newGame(name) {
    $.ajax({
        url: "/createNewGame",
        data: {
            name: name
        },
        method: "POST",
        dataType: "json"
    }).done(function (success) {
        if (success) {
            location.reload();
        }
    }).error(function (response) {
        const errorMessage = response.responseText;
        location.href = "/error?error=" + errorMessage;
    });
}
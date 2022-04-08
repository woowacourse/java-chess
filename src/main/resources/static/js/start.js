const load = document.getElementById('load-button');
const send = document.getElementById('send');

function openLoadGameWindowPop(url, title) {
    var options = 'top=10, left=10, width=500, height=600, status=no, menubar=no, toolbar=no, resizable=no';
    var win = window.open(url, title, options);
    win.loadGame = function (id) {
        win.close();
        location.href = '/game/' + id;
    }

    win.deleteGame = function (id) {
        win.close();
        fetch('/game/' + id, {
            method: 'DELETE',
        }).then(handleErrors)
            .catch(function (error) {
                alert(error.message)
            })
    }
}

load.addEventListener('click', function () {
    openLoadGameWindowPop('/games', '게임 불러오기')
})

function loadChessPage(response) {
    let gameId = response["gameId"];
    location.href = '/game/' + gameId;
}

send.addEventListener("click", function () {
    var name = document.getElementById("game-name-input");
    if (name.value === '') {
        alert("게임 이름은 빈칸일 수 없습니다.")
        return;
    }
    const request = {
        name: name.value,
    }
    fetch('/start', {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(request)
        }
    ).then(handleErrors)
        .then(res => res.json())
        .then(res => loadChessPage(res))
        .catch(function (error) {
            alert(error.message)
        });
})

async function handleErrors(res) {
    if (!res.ok) {
        let error = await res.json();
        throw Error(error["errorMessage"])
    }
    return res;
}

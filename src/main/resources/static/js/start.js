const load = document.getElementById('load-button');

function openLoadGameWindowPop(url, title) {
    var options = 'top=10, left=10, width=500, height=600, status=no, menubar=no, toolbar=no, resizable=no';
    var win = window.open(url, title, options);
    win.loadGame = function (id) {
        win.close();
        location.href = '/game/' + id;
    }

    win.deleteGame = function (id) {
        win.close();
        fetch('/delete/' + id, {
            method: 'DELETE',
        }).then(handleErrors)
            .catch(function (error) {
                alert(error.message)
            })
    }
}

load.addEventListener('click', function () {
    openLoadGameWindowPop('/findGames', '게임 불러오기')
})

async function handleErrors(res) {
    if (!res.ok) {
        let error = await res.json();
        throw Error(error["errorMessage"])
    }
}

const load = document.getElementById('load-button');

function openLoadGameWindowPop(url, title) {
    var options = 'top=10, left=10, width=500, height=600, status=no, menubar=no, toolbar=no, resizable=no';
    var win = window.open(url, title, options);
    win.loadGame = function (id) {
        win.close();
        location.href = '/game/' + id;
    }
}

load.addEventListener('click', function () {
    openLoadGameWindowPop('/findGames', '게임 불러오기')
})

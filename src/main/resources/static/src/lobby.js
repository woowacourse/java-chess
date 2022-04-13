window.onload = function () {
    fetch("/load/games")
        .then(response => response.json())
        .then(data => {
            const ul = document.getElementsByClassName("game-list")[0];
            for (let i = 0; i < data.length; i++) {
                const li = document.createElement("li");
                li.textContent += "게임 이름 : " + data[i].id;
                li.textContent += " | 상태 : " + data[i].state;
                li.textContent += " | 턴 : " + data[i].turn;
                ul.appendChild(li);
            }
        });
}

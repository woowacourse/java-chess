const $searchPageButton = document.querySelector("#search-page");
$searchPageButton.addEventListener("click", onSearchPage);

async function onSearchPage(event) {
    location.replace("/search/search-page");
}

const $createGame = document.querySelector("#createGame");
$createGame.addEventListener("click", onCreateGame);

async function onCreateGame(event) {

    const gameName = window.prompt("방 제목을 입력해주세요.");

    try {
        const responseOfTryCreateGame = await fetch("./tryCreateGame", {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({"gameName": gameName})
        });

        if(window.confirm("[" + gameName + "] 방에 입장하시겠습니까?")) {
            location.replace(gameName);
        } else {
            location.reload();
        }

    } catch (err) {
        alert("동일한 제목의 방이 이미 존재합니다.");
    }
}

const $gameIds = document.querySelector(".game-ids");
$gameIds.addEventListener("click", onSelectGame);

async function onSelectGame(event) {
    if (event.target && event.target.classList.contains("game-list")) {
        const gameName = event.target.id;

        location.replace(gameName);
    }
}

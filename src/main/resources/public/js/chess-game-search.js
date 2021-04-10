const $searchButton = document.querySelector("#search");
$searchButton.addEventListener("click", onSearch);

async function onSearch(event) {
    const $userId = document.querySelector("#search-bar").value;

    const response = await fetch("./search", {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({"whiteUserId": $userId, "blackUserId": $userId})
    });

    const result = await response.json();
    console.log(result);

    const $searchResult = document.querySelector(".search-result");
    while ($searchResult.hasChildNodes()) {
        $searchResult.removeChild($searchResult.firstChild);
    }

    for (const child of result) {
        const winner = child.winner;
        const whiteScore = child.scoreDto.white;
        const blackScore = child.scoreDto.black;
        const whiteUserId = child.userIdsDto.whiteUserId;
        const blackUserId = child.userIdsDto.blackUserId;
        const pTag = document.createElement("p");
        pTag.innerText = "승자 : " + winner + " / 백색 유저 : " + whiteUserId
            + " / 흑색 유저 : " + blackUserId
            + " / 백색 점수 : " + whiteScore + " / 흑색 점수 : " + blackScore;
        $searchResult.appendChild(pTag);
        $searchResult.appendChild(document.createElement("hr"));
    }
}
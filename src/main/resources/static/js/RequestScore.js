export const requestScore = () => {
    axios.post("/grade")
        .then(response => gradeScore(response))
        .catch(error => console.log(error))
}

const gradeScore = (response) => {
    const data = response.data;
    const whiteScore = data["whiteScore"];
    const blackScore = data["blackScore"];
    const $whiteScoreSlot = document.getElementById("white-score");
    $whiteScoreSlot.innerHTML = "White Team : " + whiteScore;
    const $blackScoreSlot = document.getElementById("black-score");
    $blackScoreSlot.innerHTML = "Black Team : " + blackScore;
}

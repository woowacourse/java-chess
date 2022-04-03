async function scoreButton() {
    let response = await fetch("/score");
    const score = await response.json();
    alert("블랙 : " + score.model.BLACK + ", 화이트 : " + score.model.WHITE);
}

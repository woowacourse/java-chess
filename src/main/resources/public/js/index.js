
// 점수 현황 동적 렌더링
let scoreStatus = document.getElementById("score-status");
let score = document.querySelector("#score-status span");
if (score.innerText === "") {
    scoreStatus.style.visibility = "hidden"
} else {
    scoreStatus.style.visibility = "visible";
}

// 점수 현황 동적 렌더링
let scoreStatus = document.getElementById("score-status");
let score = document.querySelector("#score-status span");
if (score.innerText === "") {
    scoreStatus.style.visibility = "hidden"
} else {
    scoreStatus.style.visibility = "visible";
}

// turn
let turn = document.getElementById("turn-presenter");
let currentTurn = document.querySelector("#turn-presenter span");
if (currentTurn.innerText === "") {
    turn.style.visibility = "hidden"
} else {
    turn.style.visibility = "visible";
}

// 게임 끝났을 경우
let isFinished = document.getElementById("finish-checker").value;
if (isFinished === "true") {
    var target = document.getElementById('layer');
    $(target).fadeIn();
}

$('.btn-layerClose').on('click', function() {
    $('.layer-wrap').fadeOut();
});
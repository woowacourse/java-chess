const startGame = document.getElementById("start-game");
const followGame = document.getElementById("follow-game");
const resultGame = document.getElementById("result-game");
const startButton = document.getElementById("start-button");
const followButton = document.getElementById("follow-button");
const resultButton = document.getElementById("result-button");
const blackName = document.getElementById("black-name");
const whiteName = document.getElementById("white-name");
const blackNameFollow = document.getElementById("black-name-follow");
const whiteNameFollow = document.getElementById("white-name-follow");

startButton.onclick = () => {
    if (blackName.value === "WHITE") {
        alert("Black팀의 이름은 WHITE로 지정할 수 없습니다.")
        return;
    }
    if (whiteName.value === "BLACK") {
        alert("White팀의 이름은 BLACK으로 지정할 수 없습니다.")
        return;
    }
    if (blackName.value !== "" && blackName.value === whiteName.value) {
        alert("Black팀과 White 팀의 이름은 같을 수 없습니다.");
        return;
    }
    startGame.submit();
};

followButton.onclick = () => {
    if (blackName.value === "WHITE") {
        alert("Black팀의 이름은 WHITE로 지정할 수 없습니다.")
        return;
    }
    if (whiteName.value === "BLACK") {
        alert("White팀의 이름은 BLACK으로 지정할 수 없습니다.")
        return;
    }
    if (blackName.value !== "" && blackName.value === whiteName.value) {
        alert("Black팀과 White 팀의 이름은 같을 수 없습니다.");
        return;
    }
    whiteNameFollow.value = whiteName.value;
    blackNameFollow.value = blackName.value;
    followGame.submit();
};

const startGame = document.getElementById("start-game");
const followGame = document.getElementById("follow-game");
const startButton = document.getElementById("start-button");
const followButton = document.getElementById("follow-button");
const blackName = document.getElementById("black-name");
const whiteName = document.getElementById("white-name");
const blackNameFollow = document.getElementById("black-name-follow");
const whiteNameFollow = document.getElementById("white-name-follow");
const roomButton = document.getElementById("room-button");

startButton.onclick = () => {
    if (checkNames()) {
        startGame.submit();
    }
};

roomButton.onclick = () => {
    location.href = '/'
};

followButton.onclick = () => {
    if (checkNames()) {
        whiteNameFollow.value = whiteName.value;
        blackNameFollow.value = blackName.value;
        followGame.submit();
    }
};

function checkNames() {
    if (blackName.value.toUpperCase() === "WHITE") {
        alert("Black팀의 이름은 WHITE로 지정할 수 없습니다.");
        return false;
    }
    if (whiteName.value.toUpperCase() === "BLACK") {
        alert("White팀의 이름은 BLACK으로 지정할 수 없습니다.");
        return false;
    }
    if ((blackName.value !== "" || whiteName.value !== "")
        && blackName.value === whiteName.value) {
        alert("Black팀과 White 팀의 이름은 같을 수 없습니다.");
        return false;
    }
    return true;
}


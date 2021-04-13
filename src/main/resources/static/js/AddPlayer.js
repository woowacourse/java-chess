import { allocatePlayer } from "./AllocatePlayer.js";

export const addPlayer = () => {
    const $addPlayerButton = document.getElementById("addPlayerButton");
    $addPlayerButton.addEventListener("click", function(e) {
        const playerName = requestAddPlayerName();
        if (playerName == null){
            alert("빈 입력입니다.");
            return;
        }
        axios.post("/addPlayer", {
            name : playerName
            })
        .then(response => addPlayerResult(response))
        .catch(error => console.log(error));
    })
}

const requestAddPlayerName = () => {
    const playerNameCandidate = prompt("추가하실 플레이어의 이름을 알려주세요");
    return playerNameCandidate;
}

const addPlayerResult = (response) => {
    const data = response.data;
    const isSuccess = data["result"];
    if (isSuccess == true) {
        alert("추가가 완료되었습니다.");
        setTimeout(allocatePlayer, 1000);
        return
    }
    alert("이미 있는 플레이어입니다.");
}

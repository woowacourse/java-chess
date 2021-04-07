import {startGameByGridId} from "../service/chessService.js";
import {store} from "../store.js";

export function addEvent() {
    const $startBtn = document.getElementById("start-btn");
    $startBtn.addEventListener("click", start);
}

export async function start() {
    try {
        if (store.gridDto && store.gridDto.isStarted) {
            alert("이미 게임이 시작했습니다.")
            return;
        }
        const res = await startGameByGridId(store.gridDto.gridId);
        const data = res.data;
        if (data.code === 401) {
            alert(data.message);
            return;
        }
        if (data.code !== 204) {
            alert(data.message);
            return;
        }
        store.gridDto.isStarted = true;
        alert("게임을 시작합니다.");
        setFirstTurn();
    } catch (e) {
        console.log(e);
    }
}

function setFirstTurn() {
    const $players = document.getElementsByClassName("player");
    for (let i = 0; i < $players.length; i++) {
        $players[i].classList.remove("turn");
    }
    const $player1 = document.getElementById("player1");
    $player1.className += " turn"
}
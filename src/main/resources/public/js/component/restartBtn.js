import {restartGameByRoomId} from "../service/chessService.js";
import * as chessBoardFactory from "./chessBoardFactory.js"
import * as startBtn from "./startBtn.js"
import {store} from "../store.js";

export function addEvent() {
    const $restartBtn = document.getElementById("restart-btn");
    $restartBtn.addEventListener("click", restart);
}

async function restart() {
    try {
        const res = await restartGameByRoomId(store.gridDto.roomId);
        const data = res.data;
        if (data.code !== 200) {
            alert(data.message);
            return;
        }
        cleanChessBoard();
        chessBoardFactory.createChessBoardAndPieces(data.data.gridDto, data.data.piecesResponseDto);
        await startBtn.start();
    } catch (e) {
        console.log(e);
    }
}

function cleanChessBoard() {
    const chessBoard = document.getElementById("chess-board");
    chessBoard.innerHTML = '';
    store.gridDto = null;
}
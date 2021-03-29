import {getGridAndPiecesByRoomName} from "./service/chessService.js";
import * as restartBtn from "./component/restartBtn.js";
import * as startBtn from "./component/startBtn.js";
import * as chessBoardFactory from "./component/chessBoardFactory.js"
import {store} from "./store.js";

(function () {
    restartBtn.addEvent();
    startBtn.addEvent();
})();

async function createChessBoard() {
    try {
        let roomName;
        do {
            roomName = prompt('입장할 방의 이름을 입력해주세요.');
            store.roomName = roomName;
        } while (!roomName)

        const res = await getGridAndPiecesByRoomName(roomName);
        const data = res.data;
        if (data.code !== 200) {
            alert(data.message);
            return;
        }
        chessBoardFactory.createChessBoardAndPieces(data.data.gridDto, data.data.piecesResponseDto);
    } catch (e) {
        console.log(e);
    }
}

createChessBoard();


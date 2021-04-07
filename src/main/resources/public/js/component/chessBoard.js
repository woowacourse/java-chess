import {finishGameByGridId, movePiece} from "../service/chessService.js";
import {store} from "../store.js";

export function addEvent() {
    const $chessBoard = document.getElementById("chess-board");
    $chessBoard.addEventListener("click", selectPiece);
}

function selectPiece(event) {
    if (!store.gridDto.isStarted) {
        alert("게임을 시작하시려면 Start 버튼을 눌러주세요.")
        return;
    }
    if (store.gridDto.isFinished) {
        alert("이미 게임이 끝났습니다. 게임을 다시 시작하시고 싶으면 Restart 버튼을 눌러주세요.")
        return;
    }
    const clickPiece = event.target.closest("td");
    const clickedPiece = getClickedPiece();

    // 클릭이 되있을 경우
    if (clickedPiece) {
        clickedPiece.classList.toggle("clicked");
        // 서로 다른 Piece를 클릭한 경우에만 move를 실행하기
        const sourcePosition = clickedPiece.id;
        const targetPosition = clickPiece.id;
        if (sourcePosition !== targetPosition) {
            move(sourcePosition, targetPosition);
        }

        // 아무것도 클릭이 안 되있는 상태 -> 클릭한 것 clicked로 바꾸기
    } else {
        clickPiece.classList.toggle("clicked");
    }
}

function getClickedPiece() {
    const tds = document.getElementsByTagName("td");
    for (let i = 0; i < tds.length; i++) {
        if (tds[i].classList.contains("clicked")) {
            return tds[i];
        }
    }
    return null;
}

async function move(sourcePosition, targetPosition) {
    try {
        const res = await movePiece(store.pieces.flat(), store.gridDto, sourcePosition, targetPosition);
        const data = res.data;
        if (data.code === 401) {
            alert(data.message);
            return;
        }
        if (data.code !== 204) {
            alert(data.message);
            return;
        }
        const source = document.getElementById(sourcePosition);
        const target = document.getElementById(targetPosition);
        const piece = source.getElementsByTagName("img")[0];
        if (target.getElementsByTagName("img")[0]) {
            target.getElementsByTagName("img")[0].remove();
        }
        target.appendChild(piece);

        const sourcePiece = findPieceByPosition(store.pieces, sourcePosition);
        const sourcePieceIsBlack = sourcePiece.isBlack;
        const sourcePieceName = sourcePiece.name;
        const targetPiece = findPieceByPosition(store.pieces, targetPosition);

        if (targetPiece.name == "K" || targetPiece.name == "k") {
            await finish();
            if (store.gridDto.isBlackTurn) {
                alert("player2가 이겼습니다.")
            } else {
                alert("player1이 이겼습니다.")
            }
            return;
        }

        sourcePiece.name = ".";
        targetPiece.name = sourcePieceName;
        targetPiece.isBlack = sourcePieceIsBlack;
        store.gridDto.isBlackTurn = !store.gridDto.isBlackTurn;

        changeTurn();
    } catch (e) {
        console.log(e);
    }
}

function changeTurn() {
    const $players = document.getElementsByClassName("player");
    for (let i = 0; i < $players.length; i++) {
        $players[i].classList.toggle("turn");
    }
}

async function finish() {
    try {
        const res = await finishGameByGridId(store.gridDto.gridId);
        const data = res.data;
        if (data.code === 204) {
            store.gridDto.isFinished = true;
        }
    } catch (e) {
        console.log(e);
    }
}

function findPieceByPosition(pieces, position) {
    const rowIndex = "87654321".indexOf(position[1]);
    const columnIndex = "abcdefgh".indexOf(position[0]);
    return pieces[rowIndex][columnIndex];
}
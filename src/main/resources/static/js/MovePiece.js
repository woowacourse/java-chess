import {
    switchImage, voidImage
} from "./PackImage.js";

const moveCommand = () => {
    sendMoveCommand(localStorage.activateFirstPiece, localStorage.activateSecondPiece);
};

const sendMoveCommand = (firstSlot, secondSlot) => {
    axios.post("/move", {
            move: "move " + firstSlot + " " + secondSlot
            }
        )
        .then(response => isValidMove(response))
        .catch(error => console.log(error))
};

const isValidMove = (response) => {
    const data = response.data;
    if (data["isSuccess"] == "true") {
        movePiece();
        isEnd(data);
        return;
    }
    initializedMove();
    alert(data["isSuccess"]);
};

const initializedMove = () => {
    localStorage.activateFirstPiece = "false";
    localStorage.activateSecondPiece = "false";
}

const isEnd = (data) => {
    if (data["isEnd"] == "true") {
        alert("게임이 끝났습니다." + data["winner"] + "의 승리입니다.");
    }
}

const movePiece = () => {
    const $firstSlotDiv = document.getElementById(localStorage.activateFirstPiece);
    const $firstSlotPieceName = extractPiece($firstSlotDiv);
    const $secondSlotDiv = document.getElementById(localStorage.activateSecondPiece);
    const $switchPiece = switchImage($firstSlotPieceName);
    const $voidImage = voidImage();

    clearPiece($firstSlotDiv);
    clearPiece($secondSlotDiv);
    $firstSlotDiv.appendChild($voidImage);
    $secondSlotDiv.appendChild($switchPiece);
    initializedMove();
};

const extractPiece = ($slot) => {
    const $slotPieceDiv = $slot.querySelector(".ChessPieceImage");
    return $slotPieceDiv.src
}

const clearPiece = ($slot) => {
    const packedImage = $slot.querySelector(".PackedPiece");
    packedImage.remove();
}

export const activatePiece = () => {
    const $pieceSlot = document.querySelector(".GameBoard");
    $pieceSlot.addEventListener("click", function (e) {
        const targetPiece = e.target;
        if (targetPiece && targetPiece.className == "ChessPieceImage") {
            recognizePieceOrder(targetPiece);
        }
    })
};

const recognizePieceOrder = (targetPiece) => {
    if (localStorage.activateFirstPiece == "false") {
        localStorage.activateFirstPiece = targetPiece.parentNode.parentNode.id;
        return;
    }
    localStorage.activateSecondPiece = targetPiece.parentNode.parentNode.id;
    moveCommand();
}
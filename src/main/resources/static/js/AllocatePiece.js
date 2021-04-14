import { changeTurn } from "./ChangeTurn.js";
import { requestScore } from "./RequestScore.js";
import { packedImage } from "./PackImage.js";

export const allocatePiece = (response) => {
    const data = response.data;
    const chessBoard = data["chessBoard"];
    const turn = data["turn"];
    changeTurn(turn);
    for(let key in chessBoard) {
        let pieceArea = document.getElementById(key);
        let image = packedImage(key, chessBoard[key]);
        pieceArea.appendChild(image);
    }

    requestScore();
}
import {changeTurn} from "./ChangeTurn.js";
import {packedImage} from "./PackImage.js";

export const serveInitialBoard = () => {
    const $startButton = document.getElementById("start-button");
    $startButton.addEventListener("click", function(e) {
        requestInitialBoard();
    })
}

const requestInitialBoard = () => {
    const pieces = document.getElementsByClassName("PackedPiece");
    
    Array.from(pieces).forEach(function(element) {
        element.remove();
    });

    axios.post("/initial")
    .then(response => allocateInitialPiece(response))
    .catch(error => console.log(error))
}

const allocateInitialPiece = (response) => {
    const data = response.data;
    const chessBoard = data["chessBoard"];
    const turn = data["turn"];
    changeTurn(turn);
    for(let key in chessBoard) {
        let pieceArea = document.getElementById(key);
        let image = packedImage(key, chessBoard[key]);
        pieceArea.appendChild(image);
    }
}
import {allocatePiece} from "./AllocatePiece.js";

export const serveBoard = () => {
    const $startButton = document.getElementById("load-button");
    $startButton.addEventListener("click", function(e) {
        requestBoard();
    })
}

const requestBoard = () => {
    const pieces = document.getElementsByClassName("PackedPiece");
    
    Array.from(pieces).forEach(function(element) {
        element.remove();
    });

    axios.post("/load")
    .then(response => allocatePiece(response))
    .catch(error => console.log(error)); 
}


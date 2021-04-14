import {allocatePiece} from "./AllocatePiece.js";

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
    const index = window.location.href.split("/")[4];
    axios.post("initial", {
        index : index
    })
    .then(response => allocatePiece(response))
    .catch(error => console.log(error)); 
}


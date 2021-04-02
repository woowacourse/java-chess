import {restartGame as reset, syncBoard} from "./board.js";

const button = document.querySelector("input")

button.addEventListener("click", restart)

function restart() {
    location.replace("/");
    reset();
}



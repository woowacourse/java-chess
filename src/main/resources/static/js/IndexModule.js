import { addPlayer } from "./AddPlayer.js";
import { allocatePlayer } from "./AllocatePlayer.js";

window.onload = function() {
    allocatePlayer();
    addPlayer();
}
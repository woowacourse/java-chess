import { addPlayer } from "./AddPlayer.js";
import { allocatePlayer } from "./AllocatePlayer.js";
import { addGameLink } from "./AddGameLink.js";

window.onload = function() {
    allocatePlayer();
    addPlayer();
    addGameLink();
}
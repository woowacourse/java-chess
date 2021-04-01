import {serveInitialBoard} from "./ServeInitialBoard.js";
import {activatePiece} from "./MovePiece.js";

window.onload = function() {
  localStorage.activateFirstPiece = false;
  localStorage.activateSecondPiece = false;
  serveInitialBoard();
  activatePiece();
}
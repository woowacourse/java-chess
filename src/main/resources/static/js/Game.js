import {Board} from "./board/Board.js"

window.onload = function () {
  const board = new Board();
  const body = document.querySelector("body")
  body.addEventListener("dragover", allowDrop);
  body.addEventListener("drop", e => dropPiece(e, board));
}

function allowDrop(e) {
  e.preventDefault()
}

function dropPiece(e, board) {
  const pieceId = e.dataTransfer.getData("pieceId");
  const piece = board.findPieceById(pieceId);
  piece.unhighlight();
}

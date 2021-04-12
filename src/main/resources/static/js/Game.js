import {Board} from "./board/Board.js"
import {getData} from "./utils/FetchUtil.js"

const url = "http://localhost:4567";

window.onload = async function () {
  const response = await requestData();
  initBoard(response);
}

async function requestData() {
  const gameId = findGameIdInUri();
  return await getData(`${url}/api/game/${gameId}`)
}

function findGameIdInUri() {
  const path = window.location.pathname
  const gameId = path.substr(path.lastIndexOf("/") + 1);
  return gameId;
}

function initBoard(response) {
  const pieceDtos = response["pieceDtos"]
  const whiteUserDto = response["whiteUserDto"];
  const blackUserDto = response["blackUserDto"];
  const turn = response["turn"];
  const isFinished = response["isFinished"];
  const board = new Board(pieceDtos);
  addEvent(board);
}

function addEvent(board) {
  const body = document.querySelector("body")
  body.addEventListener("dragover", allowDrop);
  body.addEventListener("drop", e => dropPiece(e, board));
}

function allowDrop(e) {
  e.preventDefault()
}

function dropPiece(e, board) {
  const sourcePosition = e.dataTransfer.getData("sourcePosition");
  const piece = board.findPieceBySourcePosition(sourcePosition);
  piece.unhighlight();
}

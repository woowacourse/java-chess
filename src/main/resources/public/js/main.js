import {getBoard, getScores} from "./fetch.js"
import {PIECES, SCORE_TEMPLATE} from "./constant.js";

window.onload = function () {

  function setBoard(boardData) {
    clearBoard();
    fillBoard(boardData);
  }

  function clearBoard() {
    document.querySelectorAll(".tile").forEach(tile => {
      while (tile.lastElementChild) {
        tile.removeChild(tile.lastElementChild);
      }
    });
  }

  async function fillScore() {
    const score = await getScores();
    document.querySelector(".players")
    .innerHTML = SCORE_TEMPLATE(score["WHITE"], score["BLACK"]);
  }

  function fillBoard(board) {
    const realBoard = Object.values(board).pop();
    // console.log("realBoard", Object.keys(realBoard));
    Object.keys(realBoard)
    .filter(tile =>
      //console.log(realBoard[tile]["pieceType"]);
      realBoard[tile]["pieceType"] !== "EMPTY"
    ).forEach(tile => {
      //console.log(realBoard[tile]["pieceType"])
      document.getElementById(tile)
      .insertAdjacentHTML("beforeend", PIECES[`${realBoard[tile]["pieceColor"]}_${realBoard[tile]["pieceType"]}`]);
    });
    fillScore();
  }

  function setMessage(turn, isError = false) {
    let message = document.querySelector(".turn");
    message.innerText = turn;
  }

  async function init() {
    const $start_bt = document.querySelector(".start");
    $start_bt.addEventListener("click", async () => {
      $start_bt.classList.remove("start");
      $start_bt.classList.add("restart");
      $start_bt.innerHTML = "RESTART";
      await setMessage("WHITE", true);
      const data = await getBoard();
      return setBoard(data);
    });
  }

  init();
};


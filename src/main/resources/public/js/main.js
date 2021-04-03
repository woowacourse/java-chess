import {getBoard, getScores, getTurn, move, restart} from "./fetch.js"
import {PIECES, SCORE_TEMPLATE} from "./constant.js";

window.onload = function () {

  function attackMove(from, to) {
    const source = document.getElementById(from.id).firstChild;
    const target = document.getElementById(to.id).firstChild;
    document.getElementById(to.id).replaceChild(source, target);
    fillScore();
  }

  function basicMove(from, to) {
    const source = document.getElementById(from.id).firstChild;
    document.getElementById(to.id).appendChild(source);
  }

  async function isValidSource(event) {
    const turn = await getTurn();
    return event.target.closest("div").hasChildNodes() &&
        event.target.classList.contains(`${turn.toLowerCase()}`)
  }

  async function grabPiece(event) {
    let movePieces = document.querySelectorAll(".selected").length;
    const tile = event.target.closest("div").classList;
    if (movePieces === 0 && await isValidSource(event)) {
      tile.add("selected", "source");
      // await showPath(event.target.parentNode.id);
      return;
    }
    if (movePieces === 1 && tile.contains("source")) {
      tile.remove("selected", "source");
      return;
    }
    if (movePieces === 1 && !tile.contains("source")) {
      tile.add("selected", "target");

      const from = document.querySelector(".source");
      const to = document.querySelector(".target");
      const movable = await move(from.id, to.id);
      if (movable) {
        document.getElementById(to.id).firstChild ? attackMove(from, to)
            : basicMove(from, to);
        setMessage(await getTurn());
      }
      if (!movable) {
        alert(`${from.id}에서 ${to.id}(으)로 이동할 수 없습니다.`);
      }
      from.classList.remove("selected", "source");
      to.classList.remove("selected", "target");
    }
  }

  async function movePiece() {
    document.querySelectorAll(".tile")
    .forEach(piece => piece.addEventListener("click", grabPiece));
  }

  function setBoard(boardData) {
    clearBoard();
    fillBoard(boardData);
    movePiece();
    // 킹 죽음 여부로 게임 오버 체크
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
    //console.log("realBoard", Object.keys(realBoard));
    Object.keys(realBoard)
    .filter(tile =>
        //console.log(realBoard[tile]["pieceType"]);
        realBoard[tile]["pieceType"] !== "EMPTY"
    ).forEach(tile => {
      //console.log(realBoard[tile]["pieceType"])
      document.getElementById(tile)
      .insertAdjacentHTML("beforeend",
          PIECES[`${realBoard[tile]["pieceColor"]}_${realBoard[tile]["pieceType"]}`]);
    });
    fillScore();
  }

  function setMessage(turn) {
    let message = document.querySelector(".turn");
    message.innerText = `${turn}`;
  }

  async function init() {
    const $start_bt = document.querySelector(".start");
    $start_bt.addEventListener("click", async () => {
      $start_bt.classList.remove("start");
      $start_bt.innerHTML = "RESTART";
      setMessage(await getTurn());
      const data = await getBoard();
      return setBoard(data);
    });
  }

  async function restartGame() {
    console.log("game restarted");
    setBoard(await restart());
    fillScore();
    setMessage(await getTurn());
  }

  function addRestartGameEventHandler() {
    const $restart_bt = document.querySelector(".restart");
    $restart_bt.addEventListener("click", () => {
      if($restart_bt.classList.contains("start")){
        return;
      }
      if (confirm("게임을 다시 시작하시겠습니까?")) {
        restartGame();
      }
    });
  }

  addRestartGameEventHandler();
  init();
}


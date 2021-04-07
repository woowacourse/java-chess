import {
  getBoard,
  getPath,
  getScores,
  getStatus,
  getTurn,
  loadBoard,
  move,
  restart
} from "./fetch.js"
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

  async function endGame() {
    document.querySelectorAll(".tile").forEach(tile => {
      tile.classList.add("end");
    });
  }

  async function checkGameOver() {
    if (await getStatus()) {
      const message = await checkTurn() + ` WIN!`;
      setMessage(message);
      await endGame();
    }
  }

  async function movePiece() {
    const from = document.querySelector(".source");
    const to = document.querySelector(".target");
    const movable = await move(from.id, to.id);
    if (movable) {
      document.getElementById(to.id).firstChild ? attackMove(from, to)
          : basicMove(from, to);
      setMessage(await checkTurn());
      await checkGameOver();
    }
    if (!movable) {
      alert(`${from.id}에서 ${to.id}(으)로 이동할 수 없습니다.`);
    }
    from.classList.remove("selected", "source");
    to.classList.remove("selected", "target");
  }

  async function showPath() {
    const from = document.querySelector(".source");
    let paths = await getPath(from.id);
    paths.forEach(path => {
      document.getElementById(path).classList.add("path");
    });
  }

  function removePath() {
    document.querySelectorAll(".path").forEach(path => {
      path.classList.remove("path");
    })
  }

  async function grabPiece(event) {
    let movePieces = document.querySelectorAll(".selected").length;
    const tile = event.target.closest("div").classList;
    if (movePieces === 0 && await isValidSource(event)) {
      tile.add("selected", "source");
      await showPath();
      return;
    }
    if (movePieces === 1 && tile.contains("source")) {
      tile.remove("selected", "source");
      removePath();
      return;
    }
    if (movePieces === 1 && !tile.contains("source")) {
      tile.add("selected", "target");
      removePath();
      await movePiece();
    }
  }

  async function addMoveEventHandler() {
    document.querySelectorAll(".tile")
    .forEach(piece => piece.addEventListener("click", grabPiece));
  }

  async function setBoard(boardData) {
    clearBoard();
    await fillBoard(boardData);
    await addMoveEventHandler();
    await checkGameOver();
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

  async function fillBoard(board) {
    const realBoard = Object.values(board).pop();
    Object.keys(realBoard)
    .filter(tile =>
        realBoard[tile]["pieceType"] !== "EMPTY"
    ).forEach(tile => {
      document.getElementById(tile)
      .insertAdjacentHTML("beforeend",
          PIECES[`${realBoard[tile]["pieceColor"]}_${realBoard[tile]["pieceType"]}`]);
    });
    await fillScore();
  }

  function setMessage(text) {
    let message = document.querySelector(".turn");
    message.innerText = text;
  }

  async function restartGame() {
    await setBoard(await restart());
    await fillScore();
    setMessage(await checkTurn());
    document.querySelectorAll(".tile").forEach(tile => {
      tile.classList.remove("end");
    })
  }

  async function checkTurn() {
    return `${await getTurn()}`;
  }

  function addRestartGameEventHandler() {
    const $restart_bt = document.querySelector(".restart");
    $restart_bt.addEventListener("click", () => {
      if ($restart_bt.classList.contains("start")) {
        return;
      }
      if (confirm("게임을 다시 시작하시겠습니까?")) {
        return restartGame();
      }
    });
  }

  async function init() {
    const $start_bt = document.querySelector(".start");
    const $load_bt = document.querySelector(".load");

    $start_bt.addEventListener("click", async () => {
      $start_bt.classList.remove("start");
      $load_bt.classList.add("hidden");
      setMessage(await checkTurn());
      const data = await getBoard();
      return setBoard(data);
    });

    $load_bt.addEventListener("click", async () => {
      if (confirm("게임을 이어 갑니까?")) {
        $start_bt.classList.remove("start");
        $load_bt.innerHTML = "LOAD";
        $load_bt.classList.add("hidden");
        const data = await loadBoard();
        await fillScore();
        setMessage(await checkTurn());
        return setBoard(data);
      }
    });
  }

  addRestartGameEventHandler();
  init();
}


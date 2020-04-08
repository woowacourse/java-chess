import { AVAILABLE_PATH, PIECES, ROOM_TEMPLATE } from "./template.mjs";
import {
  addBoard,
  checkGameOver,
  finishGame,
  getAvailablePath,
  getBoard,
  getBoards,
  getScore,
  getScores,
  isWhiteTurn,
  move,
  restart
} from "./fetch.mjs";

window.onload = function () {

  let roomId;

  async function setBoard(boardData) {
    clearAllPositions();
    fillBoard(boardData);
    addDragEventToPiece();
    if (await checkGameOver(roomId)) {
      endGameIfKingIsDead();
    }
  }

  function clearAllPositions() {
    document.querySelectorAll(".position").forEach(positionNode => {
      while (positionNode.lastElementChild) {
        positionNode.removeChild(positionNode.lastElementChild);
      }
    });
  }

  function fillBoard(board) {

    Object.keys(board["board"])
        .filter(position => board["board"][position]["type"] !== "EMPTY")
        .forEach(position => {
          document.getElementById(position).insertAdjacentHTML("beforeend",
              PIECES[`${board["board"][position]["side"]}_${board["board"][position]["type"]}`]);
        });
  }

  function endGameIfKingIsDead() {
    document.querySelectorAll(".piece").forEach(pieceNode => {
      pieceNode.setAttribute("draggable", "false");
    });
    setMessage("왕이 잡혔습니다. 게임을 종료해주세요.");
  }

  function addDragEventToPiece() {
    document.querySelectorAll(".piece").forEach(pieceNode => {
      pieceNode.setAttribute("id", Math.random().toString());
      pieceNode.addEventListener("dragstart", event => drag(event, pieceNode), false);
      pieceNode.addEventListener("dragend", event => {
        document.querySelectorAll(".available-path")
            .forEach(pathNode => pathNode.parentNode.removeChild(pathNode));
        event.currentTarget.style.opacity = "1";
      }, false);
      pieceNode.addEventListener("dragover", event => {
        event.preventDefault();
      }, false);
      pieceNode.addEventListener("drop", event => dropPiece(event, false));
    });
  }

  function setMessage(message, isError = false) {
    let element = document.querySelector(".message");
    element.innerText = message;
    element.style.backgroundColor = isError ? "rgba(255, 0, 0, 0.6)" : "";
  }

  async function showPaths(from) {
    let paths = await getAvailablePath(roomId, from);
    paths.forEach(path => {
      document.getElementById(path).insertAdjacentHTML("beforeend", AVAILABLE_PATH);
    });
  }

  async function checkTurn(roomId) {
    const turnOfWhite = await isWhiteTurn(roomId);
    document.querySelectorAll(".white.piece").forEach(element => {
      element.setAttribute("draggable", `${turnOfWhite}`);
    });
    document.querySelectorAll(".black.piece").forEach(element => {
      element.setAttribute("draggable", `${!turnOfWhite}`);
    });
    return `${turnOfWhite ? "백" : "흑"}의 차례입니다.`;
  }

  function dropPiece(event, isPosition) {
    event.preventDefault();
    const data = event.dataTransfer.getData("piece");
    const position = isPosition ? event.currentTarget : event.currentTarget.parentNode;
    appendPieceOnPosition(event, position, data);
    event.stopPropagation();
  }

  async function attackMove(position, data) {
    const killSound = document.getElementById("kill-sound");
    killSound.volume = 0.2;
    position.replaceChild(document.getElementById(data), position.firstElementChild);
    const score = await getScore(roomId);
    document.querySelector(`.room-${roomId} .room-score`).innerText = `${score["백"]} :  ${score["흑"]}`;
    killSound.play();
  }

  function basicMove(position, data) {
    const dropSound = document.getElementById("drop-sound");
    position.appendChild(document.getElementById(data));
    dropSound.play();
  }

  async function appendPieceOnPosition(event, position, data) {
    position.style.opacity = "1";
    const from = event.dataTransfer.getData("from");
    const to = position.id;
    const movable = await move(roomId, from, to);
    if (movable && position.tagName === "DIV") {
      position.firstElementChild ? await attackMove(position, data) : basicMove(position, data);
      event.stopPropagation();
      if (await checkGameOver(roomId)) {
        endGameIfKingIsDead();
        return;
      }
      setMessage(await checkTurn(roomId));
    }
    if (!movable) {
      setMessage(`${from}에서 ${to}로는 움직일 수 없습니다.`, true);
    }
  }

  async function drag(event, pieceNode) {
    event.target.style.opacity = "0";
    const img = document.createElement("img");
    img.src = pieceNode.getAttribute("src");
    event.dataTransfer.setDragImage(img, 50, 50);
    event.dataTransfer.setData("from", event.target.parentNode.id);
    event.dataTransfer.setData("piece", event.target.id);
    await showPaths(event.target.parentNode.id);
  }

  async function restartGame() {
    const score = await getScore(roomId);
    document.querySelector(`.room-${roomId} .room-score`).innerText = `${score["백"]} : ${score["흑"]}`;
    setBoard(await restart(roomId));
    setMessage(`게임을 재시작합니다. ${await checkTurn(roomId)}`);
  }

  async function roomEventHandler(roomNode) {
    document.querySelectorAll(".room").forEach(element => element.classList.remove("room-selected"));
    roomNode.classList.add("room-selected");
    roomId = parseInt(roomNode.querySelector(".room-number").innerText);
    setBoard(await getBoard(roomId));
    if (await checkGameOver(roomId)) {
      endGameIfKingIsDead();
      return;
    }
    setMessage(`${roomId}번 체스 게임에 입장하셨습니다. ${await checkTurn(roomId)}`);
  }

  async function initiate() {
    const scores = await getScores();
    const boards = await getBoards();
    if (Object.keys(boards).length === 0) {
      setMessage("새 게임을 추가해주세요.");
      return;
    }
    Object.entries(boards).forEach(([index, boardData]) => {
      document.querySelector(".rooms")
          .insertAdjacentHTML("beforeend", ROOM_TEMPLATE(boardData, index, scores[index]["백"], scores[index]["흑"]));
    });
    roomId = Object.keys(boards)[0];
    const board = await getBoard(roomId);
    setBoard(board);
    document.querySelectorAll(".room").forEach(roomNode => {
      roomNode.addEventListener("click", () => roomEventHandler(roomNode));
    });
    document.querySelector(".room").classList.add("room-selected");
    if (await checkGameOver(roomId)) {
      endGameIfKingIsDead();
      return;
    }
    setMessage(`게임을 시작합니다. ${await checkTurn(roomId)}`);
  }

  function addPositionDragEventHandler() {
    document.querySelectorAll(".position").forEach(positionNode => {
      positionNode.addEventListener("dragover", event => {
        event.preventDefault();
        event.currentTarget.style.opacity = "0.5";
      }, false);
      positionNode.addEventListener("dragleave", event => {
        event.currentTarget.style.opacity = "1";
      });
      positionNode.addEventListener("drop", event => dropPiece(event, true));
    });
  }

  function addResetButtonEventHandler() {
    document.querySelector(".button.restart").addEventListener("click", () => {
      if (document.querySelector(".room-selected") !== null && confirm("다시 시작하시겠습니까?")) {
        restartGame();
      }
    });
  }

  function addRoomEventHandler() {
    document.querySelector(".room-add").addEventListener("click", async () => {
      const rooms = document.querySelector(".rooms");
      Object.entries(await addBoard()).forEach(([key, value]) => {
        rooms.insertAdjacentHTML("beforeend", ROOM_TEMPLATE(value, key));
      });
      rooms.lastElementChild.addEventListener("click", async function () {
        await roomEventHandler(this);
      });
    });
  }

  function addEndButtonEventHandler() {
    document.querySelector(".end").addEventListener("click", async function () {
      if (roomId && confirm(`정말 ${roomId}번 게임을 종료하시겠습니까?`)) {
        await finishGame(roomId);
        const roomToRemove = document.querySelector(`.room-${roomId}`);
        roomToRemove.parentNode.removeChild(roomToRemove);
      }
      const boards = await getBoards();
      if (Object.keys(boards).length === 0) {
        document.querySelectorAll(".position").forEach(element => element.innerHTML = "");
        setMessage("새 게임을 추가해주세요.");
        return;
      }
      const firstRoomLeft = Object.keys(boards)[0];
      setMessage(`${roomId}번 게임이 종료되었습니다. ${firstRoomLeft ? `${firstRoomLeft}번 게임에 입장합니다.` : ""}`);
      roomId = firstRoomLeft;
      const board = await getBoard(roomId);
      document.querySelector(`.room-${roomId}`).classList.add("room-selected");
      setBoard(board);
    });
  }

  addPositionDragEventHandler();
  addResetButtonEventHandler();
  addRoomEventHandler();
  addEndButtonEventHandler();
  initiate();
};

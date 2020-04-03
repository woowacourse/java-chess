window.onload = function () {
  const PIECES = {
    BLACK_KING: `<img src="../image/pieces/black_king.png" draggable="true" class="black king piece"/>`,
    BLACK_QUEEN: `<img src="../image/pieces/black_queen.png" draggable="true" class="black queen piece"/>`,
    BLACK_ROOK: `<img src="../image/pieces/black_rook.png" draggable="true" class="black rook piece"/>`,
    BLACK_BISHOP: `<img src="../image/pieces/black_bishop.png" draggable="true" class="black bishop piece"/>`,
    BLACK_KNIGHT: `<img src="../image/pieces/black_knight.png" draggable="true" class="black knight piece"/>`,
    BLACK_PAWN: `<img src="../image/pieces/black_pawn.png" draggable="true" class="black piece pawn"/>`,
    WHITE_KING: `<img src="../image/pieces/white_king.png" draggable="true" class="white king piece"/>`,
    WHITE_QUEEN: `<img src="../image/pieces/white_queen.png" draggable="true" class="white queen piece"/>`,
    WHITE_ROOK: `<img src="../image/pieces/white_rook.png" draggable="true" class="white rook piece"/>`,
    WHITE_BISHOP: `<img src="../image/pieces/white_bishop.png" draggable="true" class="white bishop piece"/>`,
    WHITE_KNIGHT: `<img src="../image/pieces/white_knight.png" draggable="true" class="white knight piece"/>`,
    WHITE_PAWN: `<img src="../image/pieces/white_pawn.png" draggable="true" class="white pawn piece"/>`,
  };

  const AVAILABLE_PATH = `<div class="available-path"/>`

  function ROOM_TEMPLATE(room, index, whiteScore, blackScore) {
    return `
        <div class="room room-${index}">
          <div class="room-number">${index}</div>
          <div class="players">
            <div class="player">${room["백"]["username"]}</div>
            <div class="player">${room["흑"]["username"]}</div>
          </div>
          <div class="room-status room-score">${whiteScore || 38} : ${blackScore || 38}</div>
        </div>
    `
  }

  let roomId = 1;

  async function getBoards() {
    const response = await fetch("http://localhost:8080/boards");
    return await response.json();
  }

  async function addBoard() {
    const response = await fetch("http://localhost:8080/boards", {
      method: "POST"
    });
    return await response.json();
  }

  async function getBoard() {
    const response = await fetch(`http://localhost:8080/boards/${roomId}`);
    return await response.json();
  }

  async function restart() {
    const response = await fetch(`http://localhost:8080/boards/${roomId}`, {
      method: "POST"
    });
    return await response.json();
  }

  async function isWhiteTurn() {
    const response = await fetch(`http://localhost:8080/boards/${roomId}/turn`);
    return await response.json();
  }

  async function move(from, to) {
    const data = await fetch(`http://localhost:8080/boards/${roomId}/move`, {
      method: 'POST',
      body: JSON.stringify({from, to}),
      headers: {'Content-Type': 'application/json'}
    });
    const movable = await data.json();
    if (!movable) {
      setMessage(`${from}에서 ${to}로는 움직일 수 없습니다.`, true);
    }
    return movable;
  }

  async function getAvailablePath(from) {
    const data = await fetch(`http://localhost:8080/boards/${roomId}/movable`, {
      method: "POST",
      body: JSON.stringify({from})
    });
    return await data.json();
  }

  async function getScores() {
    const response = await fetch(`http://localhost:8080/scores`);
    return await response.json();
  }

  async function getScore(id) {
    const response = await fetch(`http://localhost:8080/boards/${id}/score`);
    return await response.json();
  }

  function setBoard(board) {
    document.querySelectorAll(".position").forEach(element => {
      while (element.lastElementChild) {
        element.removeChild(element.lastElementChild);
      }
    });
    Object.keys(board)
        .filter(position => board[position]["type"] !== "EMPTY")
        .forEach(position => {
          const {side, type} = board[position];
          document.getElementById(position)
              .insertAdjacentHTML("beforeend", PIECES[`${side}_${type}`]);
        });
    document.querySelectorAll(".piece").forEach(element => {
      element.setAttribute("id", Math.random());
      element.addEventListener("dragstart", drag(element), false);
      element.addEventListener("dragend", function (e) {
        window.requestAnimationFrame(function () {
          e.target.style.visibility = "";
        });
        document.querySelectorAll(".available-path")
            .forEach(element => element.parentNode.removeChild(element));
        e.currentTarget.style.opacity = "1";

      }, false);
      element.addEventListener("dragover", function (e) {
        e.preventDefault();
      }, false);
      element.addEventListener("drop", dropPiece(false))
    });
  }

  function setMessage(message, isError = false) {
    let element = document.querySelector(".message");
    element.innerText = message;
    element.style.backgroundColor = isError ? "rgba(255, 0, 0, 0.6)" : "";
  }

  function appendMessage(message) {
    let element = document.querySelector(".message");
    element.innerText = element.innerText + " " + message;
  }

  (async function () {
    const scores = await getScores();
    const boards = await getBoards();
    if (Object.keys(boards).length === 0) {
      setMessage("새 게임을 추가해주세요.");
      return;
    }
    Object.entries(boards).forEach(([key, value]) => {
      document.querySelector(".rooms").insertAdjacentHTML("beforeend", ROOM_TEMPLATE(value, key, scores[key]["백"], scores[key]["흑"]))
    });
    roomId = 1;
    const board = await getBoard();
    setBoard(board);
    setMessage(`게임을 시작합니다. ${await checkTurn()}`);
    document.querySelectorAll(".room").forEach(element => {
      element.addEventListener("click", async function () {
        document.querySelectorAll(".room").forEach(element => element.classList.remove("room-selected"))
        element.classList.add("room-selected");
        let roomNumber = parseInt(element.querySelector(".room-number").innerText);
        roomId = roomNumber;
        const board = await getBoard();
        setBoard(board);
        setMessage(`${roomNumber}번 체스 게임에 입장하셨습니다. ${await checkTurn()}`)
      })
    });
    document.querySelector(".room").classList.add("room-selected");
  })();


  async function showPaths(from) {
    let paths = await getAvailablePath(from);
    paths.forEach(path => {
      document.getElementById(path).insertAdjacentHTML("beforeend", AVAILABLE_PATH);
    });
  }

  function dropPiece(isPosition) {
    return function (e) {
      e.preventDefault();
      const data = e.dataTransfer.getData("piece");
      const position = isPosition ? e.currentTarget : e.currentTarget.parentNode;
      appendPieceOnPosition(e, position, data);
      e.stopPropagation();
    }
  }

  async function checkTurn() {
    const turnOfWhite = await isWhiteTurn();
    document.querySelectorAll(".white.piece").forEach(element => {
      element.setAttribute("draggable", `${turnOfWhite}`);
    });
    document.querySelectorAll(".black.piece").forEach(element => {
      element.setAttribute("draggable", `${!turnOfWhite}`);
    });
    return `${turnOfWhite ? "백" : "흑"}의 차례입니다.`;
  }

  async function appendPieceOnPosition(e, position, data) {
    const dropSound = document.getElementById("drop-sound");
    const killSound = document.getElementById("kill-sound");
    killSound.volume = 0.2;
    position.style.opacity = "1";
    const from = e.dataTransfer.getData("from");
    const to = position.id;
    if (await move(from, to) && position.tagName === "DIV") {
      if (position.firstElementChild) {
        position.replaceChild(document.getElementById(data), position.firstElementChild);
        const score = await getScore(roomId);
        document.querySelector(`.room-${roomId} .room-score`).innerText = `${score["백"]} :  ${score["흑"]}`;
        killSound.play();
      } else {
        position.appendChild(document.getElementById(data));
        dropSound.play();
      }
      e.stopPropagation();
      setMessage(await checkTurn());
    }
  }

  function drag(element) {
    return async function (e) {
      e.target.style.opacity = "0";
      const img = document.createElement("img");
      img.src = element.getAttribute("src");
      e.dataTransfer.setDragImage(img, 50, 50);
      e.dataTransfer.setData("from", e.target.parentNode.id);
      e.dataTransfer.setData("piece", e.target.id);
      await showPaths(e.target.parentNode.id);
    };
  }

  document.querySelectorAll(".position").forEach(element => {
    element.addEventListener("dragover", function (e) {
      e.preventDefault();
      e.currentTarget.style.opacity = "0.5"
    }, false);
    element.addEventListener("dragleave", function (e) {
      e.currentTarget.style.opacity = "1";
    });
    element.addEventListener("drop", dropPiece(true))
  });

  document.querySelector(".button.restart").addEventListener("click", function () {
    if (document.querySelector(".room-selected") === null) {
      return;
    }
    const isRestarting = confirm("다시 시작하시겠습니까?");
    if (isRestarting) {
      (async function () {
        const newBoard = await restart();
        setBoard(newBoard);
        const score = await getScore(roomId);
        document.querySelector(`.room-${roomId} .room-score`).innerText = `${score["백"]}:${score["흑"]}`;
        setMessage("게임을 시작합니다. 백이 선공입니다.");
      })();
    }
  });

  document.querySelector(".room-add").addEventListener("click", async function () {
    let rooms = document.querySelector(".rooms");
    Object.entries(await addBoard()).forEach(([key, value]) => {
      rooms.insertAdjacentHTML("beforeend", ROOM_TEMPLATE(value, key))
    });
    rooms.lastElementChild.addEventListener("click", async function (e) {
      document.querySelectorAll(".room").forEach(element => element.classList.remove("room-selected"))
      this.classList.add("room-selected");
      let roomNumber = parseInt(this.querySelector(".room-number").innerText);
      roomId = roomNumber;
      const board = await getBoard();
      setBoard(board);
      setMessage(`${roomNumber}번 게임에 입장하셨습니다. ${await checkTurn()}`)
    })
  });
};

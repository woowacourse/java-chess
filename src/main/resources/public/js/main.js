window.onload = function () {
  const PIECES = {
    BLACK_KING: `<img src="../image/pieces/black_king.png" draggable="true" class="piece king"/>`,
    BLACK_QUEEN: `<img src="../image/pieces/black_queen.png" draggable="true" class="piece queen"/>`,
    BLACK_ROOK: `<img src="../image/pieces/black_rook.png" draggable="true" class="piece rook"/>`,
    BLACK_BISHOP: `<img src="../image/pieces/black_bishop.png" draggable="true" class="piece bishop"/>`,
    BLACK_KNIGHT: `<img src="../image/pieces/black_knight.png" draggable="true" class="piece knight"/>`,
    BLACK_PAWN: `<img src="../image/pieces/black_pawn.png" draggable="true" class="piece pawn"/>`,
    WHITE_KING: `<img src="../image/pieces/white_king.png" draggable="true" class="piece king"/>`,
    WHITE_QUEEN: `<img src="../image/pieces/white_queen.png" draggable="true" class="piece queen"/>`,
    WHITE_ROOK: `<img src="../image/pieces/white_rook.png" draggable="true" class="piece rook"/>`,
    WHITE_BISHOP: `<img src="../image/pieces/white_bishop.png" draggable="true" class="piece bishop"/>`,
    WHITE_KNIGHT: `<img src="../image/pieces/white_knight.png" draggable="true" class="piece knight"/>`,
    WHITE_PAWN: `<img src="../image/pieces/white_pawn.png" draggable="true" class="piece pawn"/>`,
  };


  async function getBoard() {
    const response = await fetch("http://localhost:4567/board");
    return await response.json();
  }

  async function restart() {
    const response = await fetch("http://localhost:4567/board", {
      method: "POST"
    });
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
        e.currentTarget.style.opacity = "1";
      }, false);
      element.addEventListener("dragover", function (e) {
        e.preventDefault();
      }, false);
      element.addEventListener("drop", dropPiece(false))
    });
  }

  (async function () {
    const board = await getBoard();
    setBoard(board);
  })();

  async function move(from, to) {
    const data = await fetch("http://localhost:4567/move", {
      method: 'POST',
      body: JSON.stringify({from, to}),
      headers: {'Content-Type': 'application/json'}
    });
    const movable = await data.json();
    if (!movable) {
      // TODO:
      console.log(`${from}에서 ${to}로 움직일 수 없습니다.`);
    }
    return movable;
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
        killSound.play();
      } else {
        position.appendChild(document.getElementById(data));
        dropSound.play();
      }
      e.stopPropagation();
    }
  }

  function drag(element) {
    return function (e) {
      e.target.style.opacity = "0";
      const img = document.createElement("img");
      img.src = element.getAttribute("src");
      e.dataTransfer.setDragImage(img, 50, 50);
      e.dataTransfer.setData("from", e.target.parentNode.id);
      e.dataTransfer.setData("piece", e.target.id);
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
    const isRestarting = confirm("다시 시작하시겠습니까?");
    if (isRestarting) {
      (async function () {
        const newBoard = await restart();
        setBoard(newBoard);
      })();
    }
  })
}

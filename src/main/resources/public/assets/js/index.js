const numbers = (n) => {
  return Array.from(Array(n).keys());
};

const squareStyleTemplate = (squareStyle) => {
  return `position: fixed; 
    left:${squareStyle.left}px;
    top:${squareStyle.top}px;
    width:${squareStyle.width}px;
    height:${squareStyle.height}px;
    cursor:pointer;`;
};

const columns = ["a", "b", "c", "d", "e", "f", "g", "h"];
const rows = ["1", "2", "3", "4", "5", "6", "7", "8"];
const teams = {
  w: "white",
  b: "black",
  n: "none"
};

const boardSize = 8;
const points = numbers(boardSize).flatMap(
    x => numbers(boardSize).map(y => {
      return {x: x, y: y}
    })
);
const boardWidthRateWithoutBorder = 1020 / 1077;

const removeAllMovables = () => {
  const movables = document.querySelectorAll(".movable");
  movables.forEach(movable => {
    movable.parentElement.removeChild(movable);
  });
}

const addMovables = async function (point) {
  const response = await fetch("./movablePoints/" + point);
  const result = await response.json();
  for (const i in result) {
    const square = document.querySelector("#" + result[i].x + result[i].y);
    const moveDiv = document.createElement("div");
    moveDiv.classList.add("movable");
    moveDiv.value = point;
    square.insertAdjacentElement("afterbegin", moveDiv);
  }
}

const updateTurnBadge = async function() {
  const response = await fetch("./currentTurn");
  const result = await response.json();

  const whiteBadge = document.querySelector(".whiteTurn");
  const blackBadge = document.querySelector(".blackTurn");

  if (result === "BLACK") {
    whiteBadge.style.visibility = "hidden";
    blackBadge.style.visibility = "visible";
  } else {
    whiteBadge.style.visibility = "visible";
    blackBadge.style.visibility = "hidden";
  }
  console.log(result);
};

const move = async function (source, destination) {
  const response = await fetch(
      "./move?source=" + source + "&destination=" + destination);
  const result = await response.json();

  console.log(result);
  const boardDto = result["board"];
  for (const i in boardDto["board"]) {
    const newSquare = boardDto["board"][i];
    const square = document.querySelector("#" + newSquare.x + newSquare.y);
    square.setAttribute("class",
        "square " + newSquare["piece"] + newSquare["team"]);
  }
  updateTurnBadge().then();
}

const checkGameState = async function() {
  const response = await fetch("./getGameStatus");
  const result = await response.json();

  console.log(result);
  if (result["isOngoing"] === "false") {
    const resultDiv = document.createElement("div");
    resultDiv.classList.add("result");
    resultDiv.innerHTML = `<p>Winner is ${teams[result["winner"]]}</p>`;
    document.querySelector("body").insertAdjacentElement("afterbegin", resultDiv);
    const style = document.createElement('style');
    style.innerHTML = `
      header, .square, .board {
        filter: blur(2px);
      }
    `;
    document.head.appendChild(style);
  }
}

const removeHighlight = () => {
  document.querySelectorAll(".selected").forEach(selected => {
    selected.classList.remove("selected");
  });
}

const clickSquare = function (event) {
  removeHighlight();
  if (event.target.classList.contains("movable")) {
    move(event.target.value, event.target.closest(".square").id).then(() => {
      checkGameState().then();
      removeAllMovables();
    });
    return;
  }
  removeAllMovables();
  document.querySelector("#" + event.target.id).classList.add("selected");
  addMovables(event.target.id).then(r => {
  });
};

const addEventToSquares = () => {
  const squares = document.querySelectorAll(".square");
  squares.forEach(square =>
      square.addEventListener("click", clickSquare)
  );
}

const reloadBoard = () => {
  const boardRect = document.querySelector(".board").getBoundingClientRect();
  const squareWidth = boardRect.width * boardWidthRateWithoutBorder / boardSize;
  const squareHeight = boardRect.height * boardWidthRateWithoutBorder
      / boardSize;
  const boardLeft = boardRect.left + boardRect.width
      * (1 - boardWidthRateWithoutBorder) / 2;
  const boardTop = boardRect.top + boardRect.height
      * (1 - boardWidthRateWithoutBorder) / 2;

  points.forEach(point => {
    const square = document.querySelector(
        "#" + columns[point.x] + rows[point.y]);
    const squareStyle = {
      width: squareWidth,
      height: squareHeight,
      left: boardLeft + squareWidth * point.x,
      top: boardTop + squareHeight * (boardSize - 1 - point.y)
    };
    square.style.cssText = squareStyleTemplate(squareStyle);
  });

  const whiteTurn = document.querySelector(".whiteTurn");
  whiteTurn.style.position = "fixed";
  whiteTurn.style.left = boardRect.left.toString() + "px";
  whiteTurn.style.top = (boardRect.bottom + 4).toString() + "px";
  whiteTurn.style.width = (boardRect.width / 24).toString() + "px";
  whiteTurn.style.height = (boardRect.width / 24).toString() + "px";

  const blackTurn = document.querySelector(".blackTurn");
  blackTurn.style.position = "fixed";
  blackTurn.style.left = (boardRect.right - boardRect.width / 24).toString() + "px";
  blackTurn.style.top = (boardRect.top - boardRect.width / 24 - 4).toString() + "px";
  blackTurn.style.width = (boardRect.width / 24).toString() + "px";
  blackTurn.style.height = (boardRect.width / 24).toString() + "px";
}

window.onload = () => {
  reloadBoard();
  updateTurnBadge().then();
  addEventToSquares();
};

window.onresize = () => {
  reloadBoard();
  updateTurnBadge().then();
}

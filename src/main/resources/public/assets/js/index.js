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

const move = async function (source, destination) {
  const response = await fetch(
      "./move?source=" + source + "&destination=" + destination);
  const result = await response.json();

  for (const i in result["board"]) {
    const newSquare = result["board"][i];
    const square = document.querySelector("#" + newSquare.x + newSquare.y);
    square.setAttribute("class",
        "square " + newSquare["piece"] + newSquare["team"]);
  }
}

const clickSquare = function (event) {
  if (event.target.classList.contains("movable")) {
    move(event.target.value, event.target.closest(".square").id).then(r => {
    });
    removeAllMovables();
    return;
  }
  removeAllMovables();
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
}

window.onload = () => {
  reloadBoard();
  addEventToSquares();
};

window.onresize = () => {
  reloadBoard();
}

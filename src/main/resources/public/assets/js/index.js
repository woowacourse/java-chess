const numbers = (n) => {
  return Array.from(Array(n).keys());
};

const squareStyleTemplate = (squareStyle) => {
  return `position: fixed; 
    left:${squareStyle.left}px;
    top:${squareStyle.top}px;
    width:${squareStyle.width}px;
    height:${squareStyle.height}px;`;
};

const squareImageTemplate = (piece) => {
  return `background: url('./assets/images/${piece}.png') 50% 50% no-repeat;
      background-size: contain;
      filter: drop-shadow(2px 2px 5px #000)`;
}

const columns = ["a", "b", "c", "d", "e", "f", "g", "h"];
const rows = ["1", "2", "3", "4", "5", "6", "7", "8"];

const boardSize = 8;
const points = numbers(boardSize).flatMap(
    x => numbers(boardSize).map(y => {
      return {x: x, y: y}
    })
);
const boardWidthRateWithoutBorder = 1020 / 1077;

const reloadBoard = () => {
  const boardRect = document.querySelector(".board").getBoundingClientRect();
  console.log(boardRect);
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
    square.style.cssText += squareImageTemplate(square.getAttribute("value"));
  });
}

window.onload = () => {
  reloadBoard();
};

window.onresize = () => {
  reloadBoard();
}

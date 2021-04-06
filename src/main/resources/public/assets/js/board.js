const squareStyleTemplate = (squareStyle) => {
  return `position: fixed;
    left:${squareStyle.left}px;
    top:${squareStyle.top}px;
    width:${squareStyle.width}px;
    height:${squareStyle.height}px;`;
};

const columns = ["a", "b", "c", "d", "e", "f", "g", "h"];
const rows = ["1", "2", "3", "4", "5", "6", "7", "8"];

const numbers = (n) => {
  return Array.from(Array(n).keys());
};

const boardSize = 8;
const boardWidthRateWithoutBorder = 1020 / 1077;
const points = numbers(boardSize).flatMap(
    x => numbers(boardSize).map(y => {
      return {x: x, y: y}
    })
);

export async function getBoardAfterMove(source, destination, roomId) {
  const response = await fetch(
      "./" + roomId + "/move", {
        headers: {
          "Content-Type": "application/json"
        },
        method: "PUT",
        body: JSON.stringify({
          "source": source,
          "destination": destination
        })
      });
  const result = await response.json();
  if (response.ok) {
    return result;
  } else {
    alert("HTTP-Error: " + result["message"]);
  }
}

export async function getInitializedBoard(roomId) {
  const response = await fetch("./" + roomId + "/start", {
    method: "PUT"
  });
  const result = await response.json();
  if (response.ok) {
    return result;
  } else {
    alert("HTTP-Error: " + result["message"]);
  }
}

export const reloadBoard = () => {
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
  blackTurn.style.left = (boardRect.right - boardRect.width / 24).toString()
      + "px";
  blackTurn.style.top = (boardRect.top - boardRect.width / 24 - 4).toString()
      + "px";
  blackTurn.style.width = (boardRect.width / 24).toString() + "px";
  blackTurn.style.height = (boardRect.width / 24).toString() + "px";
}
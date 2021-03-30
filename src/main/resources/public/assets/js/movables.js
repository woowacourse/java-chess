export const removeAllMovables = () => {
  const movables = document.querySelectorAll(".movable");
  movables.forEach(movable => {
    movable.parentElement.removeChild(movable);
  });
}

async function getMovables(point) {
  const response = await fetch("./movablePoints/" + point);
  return await response.json();
}

export const addMovables = (point) => {
  getMovables(point).then(movables => {
    for (const i in movables) {
      const square = document.querySelector(
          "#" + movables[i].x + movables[i].y);
      const moveDiv = document.createElement("div");
      moveDiv.classList.add("movable");
      moveDiv.value = point;
      square.insertAdjacentElement("afterbegin", moveDiv);
    }
  });
}
export async function getBoard() {
  const response = await fetch("http://localhost:8080/board");
  return await response.json();
}

export async function getScores() {
  const response = await fetch("http://localhost:8080/board/score");
  return await response.json();
}

export async function showPath(from) {
  const data = await fetch("http://localhost:8080/board/movable", {
    method: "POST",
    body: JSON.stringify({from})
  });
  return await data.json();
}

export async function move(from, to){
  const data = await fetch("http://localhost:8080/board/move", {
    method: "POST",
    body:JSON.stringify({from, to}),
    headers: {'Content-Type': 'application/json'}
  });
  return await data.json();
}

export async function getTurn(){
  const response = await fetch("http://localhost:8080/board/turn");
  return await response.json();
}

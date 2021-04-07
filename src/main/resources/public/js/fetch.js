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

export async function move(from, to) {
  const data = await fetch("http://localhost:8080/board/move", {
    method: "POST",
    body: JSON.stringify({from, to}),
    headers: {'Content-Type': 'application/json'}
  });
  return await data.json();
}

export async function getTurn() {
  const response = await fetch("http://localhost:8080/board/turn");
  return await response.json();
}

export async function getStatus() {
  const response = await fetch("http://localhost:8080/board/status");
  return await response.json();
}

export async function getPath(from) {
  const data = await fetch("http://localhost:8080/board/path", {
    method: "POST",
    body: JSON.stringify({from}),
    headers: {'Content-Type': 'application/json'}
  });
  return await data.json();
}

export async function restart() {
  const response = await fetch("http://localhost:8080/board/restart");
  return await response.json();
}

export async function loadBoard() {
  const response = await fetch("http://localhost:8080/load");
  return await response.json();
}

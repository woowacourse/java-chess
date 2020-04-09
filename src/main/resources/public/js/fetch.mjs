export async function getBoards() {
  const response = await fetch("http://localhost:8080/boards");
  return await response.json();
}

export async function getBoard(roomId) {
  const response = await fetch(`http://localhost:8080/boards/${roomId}`);
  return await response.json();
}

export async function addBoard() {
  const response = await fetch("http://localhost:8080/boards", {
    method: "POST"
  });
  return await response.json();
}

export async function restart(roomId) {
  const response = await fetch(`http://localhost:8080/boards/${roomId}`, {
    method: "POST"
  });
  return await response.json();
}

export async function isWhiteTurn(roomId) {
  const response = await fetch(`http://localhost:8080/boards/${roomId}/turn`);
  return await response.json();
}

export async function move(roomId, from, to) {
  const data = await fetch(`http://localhost:8080/boards/${roomId}/move`, {
    method: 'POST',
    body: JSON.stringify({from, to}),
    headers: {'Content-Type': 'application/json'}
  });
  const movable = await data.json();
  return movable;
}

export async function getAvailablePath(roomId, from) {
  const data = await fetch(`http://localhost:8080/boards/${roomId}/movable`, {
    method: "POST",
    body: JSON.stringify({from})
  });
  return await data.json();
}

export async function getScores() {
  const response = await fetch(`http://localhost:8080/scores`);
  return await response.json();
}

export async function getScore(roomId) {
  const response = await fetch(`http://localhost:8080/boards/${roomId}/score`);
  return await response.json();
}

export async function finishGame(roomId) {
  const response = await fetch(`http://localhost:8080/boards/${roomId}`, {
    method: "DELETE"
  });
  return await response.json();
}

// TODO: 왕이 죽으면 게임 종료
export async function checkGameOver(roomId) {
  const response = await fetch(`http://localhost:8080/boards/${roomId}/status`);
  return await response.json();
}

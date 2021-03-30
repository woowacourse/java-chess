
const HOME = 'http://localhost:4567';

const piece_cells = document.getElementsByClassName("piece-cell");

const files = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'];
const files_size_in_one_rank = files.length;
let current_rank = 8;
let current_file_index = 0;
for (let i = 0; i < piece_cells.length; i++) {
  piece_cells[i].id = files[current_file_index] + String(current_rank);
  current_file_index += 1;
  if ((i + 1) % files_size_in_one_rank === 0) {
    current_rank -= 1;
    current_file_index = 0;
  }
}

for (let i = 0; i < piece_cells.length; i++) {
  const img = document.createElement('IMG');
  img.style.width = '100%';
  img.style.height = '100%';
  if (piece_cells[i].innerText === 'P') {
    piece_cells[i].innerText = '';
    img.src = '/images/black-pawn.png';
    img.id = piece_cells[i].id;
  }
  if (piece_cells[i].innerText === 'R') {
    piece_cells[i].innerText = '';
    img.src = '/images/black-rook.png';
    img.id = piece_cells[i].id;
  }
  if (piece_cells[i].innerText === 'N') {
    piece_cells[i].innerText = '';
    img.src = '/images/black-knight.png';
    img.id = piece_cells[i].id;
  }
  if (piece_cells[i].innerText === 'B') {
    piece_cells[i].innerText = '';
    img.src = '/images/black-bishop.png';
    img.id = piece_cells[i].id;
  }
  if (piece_cells[i].innerText === 'Q') {
    piece_cells[i].innerText = '';
    img.src = '/images/black-queen.png';
    img.id = piece_cells[i].id;
  }
  if (piece_cells[i].innerText === 'K') {
    piece_cells[i].innerText = '';
    img.src = '/images/black-king.png';
    img.id = piece_cells[i].id;
  }
  if (piece_cells[i].innerText === 'p') {
    piece_cells[i].innerText = '';
    img.src = '/images/white-pawn.png';
    img.id = piece_cells[i].id;
  }
  if (piece_cells[i].innerText === 'r') {
    piece_cells[i].innerText = '';
    img.src = '/images/white-rook.png';
    img.id = piece_cells[i].id;
  }
  if (piece_cells[i].innerText === 'n') {
    piece_cells[i].innerText = '';
    img.src = '/images/white-knight.png';
    img.id = piece_cells[i].id;
  }
  if (piece_cells[i].innerText === 'b') {
    piece_cells[i].innerText = '';
    img.src = '/images/white-bishop.png';
    img.id = piece_cells[i].id;
  }
  if (piece_cells[i].innerText === 'q') {
    piece_cells[i].innerText = '';
    img.src = '/images/white-queen.png';
    img.id = piece_cells[i].id;
  }
  if (piece_cells[i].innerText === 'k') {
    piece_cells[i].innerText = '';
    img.src = '/images/white-king.png';
    img.id = piece_cells[i].id;
  }
  if (piece_cells[i].innerText === '.') {
    piece_cells[i].innerText = '';
    img.id = piece_cells[i].id;
    continue;
  }
  piece_cells[i].appendChild(img);
}

let is_start_position_clicked = false;
let start_position = null;
let destination = null;

for (let i = 0; i < piece_cells.length; i++) {
  piece_cells[i].addEventListener('click', (event) => {
    if (!is_start_position_clicked) {
      start_position = event.target.id;
      is_start_position_clicked = true;
      return;
    }
    destination = event.target.id;
    request_move_post();
  });
}

const game_id = document.getElementById('game-id').innerText;

function request_move_post() {
  const xhr = new XMLHttpRequest();
  xhr.open('POST', '/move', true);
  xhr.setRequestHeader('Content-Type', 'application/json');
  xhr.responseType = 'json';
  xhr.send(JSON.stringify({
    gameId : game_id,
    startPosition: start_position,
    destination: destination
  }));
  start_position = null;
  destination = null;
  is_start_position_clicked = false;
  xhr.onload = function () {
    const move_response = xhr.response;
    if (move_response['isMoveError']) {
      alert(move_response['errorMessage']);
      return;
    }
    window.location.href = HOME + '/chess-board?id=' + game_id;
  };
}

const is_king_dead = document.getElementById('is-king-dead');
if (is_king_dead.innerText === "true") {
  const before_turn_team_name = document.getElementById('before-turn-team-name');
  alert(before_turn_team_name.innerText + ' 팀이 이겼습니다.');
  window.location.href = HOME + '/delete?id=' + game_id;
}


function endConfirm() {
  return confirm('정말 나가시겠습니까?');
}
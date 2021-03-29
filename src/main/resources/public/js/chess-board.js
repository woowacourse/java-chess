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
  xhr.open('POST', 'http://localhost:4567/move', true);
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
    window.location.href='http://localhost:4567/chess-board?id=' + game_id;
  };
}

const is_king_dead = document.getElementById('is-king-dead');
if (is_king_dead.innerText === "true") {
  const before_turn_team_name = document.getElementById('before-turn-team-name');
  alert(before_turn_team_name.innerText + ' 팀이 이겼습니다.');
  window.location.href='http://localhost:4567/delete?id=' + game_id;
}


function endConfirm() {
  return confirm('정말 나가시겠습니까?');
}
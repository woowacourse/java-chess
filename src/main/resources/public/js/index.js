
const HOME = 'http://localhost:4567';

const enter_room_buttons =  document.getElementsByClassName("enter-room-button");

for (let i = 0; i < enter_room_buttons.length; i++) {
  enter_room_buttons[i].addEventListener('click', (event) => {
    window.location.href = HOME + '/chess-board?id=' + event.target.parentElement.id;
  });
}

const remove_room_buttons =  document.getElementsByClassName("delete-room-button");
for (let i = 0; i < remove_room_buttons.length; i++) {
  remove_room_buttons[i].addEventListener('click', (event) => {
    const is_delete = confirm('정말 삭제하시겠습니까?');
    if (is_delete === true) {
      window.location.href = HOME + '/delete?id=' + event.target.parentElement.id;
    }
  });
}

const room_title_min_size = 5;
function create_room_check() {
  const new_room_title = document.getElementById('new_room_title');

  if(new_room_title.value.length < room_title_min_size) {
    alert('방 제목은 ' + room_title_min_size + '글자 이상이어야 합니다.');
    new_room_title.focus();
    return false;
  }
  return true;
}
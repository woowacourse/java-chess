
const HOME = 'http://localhost:4567';

const create_room_buttons =  document.getElementsByClassName("enter-room-button");

for (let i = 0; i < create_room_buttons.length; i++) {
  create_room_buttons[i].addEventListener('click', (event) => {
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
const $start_bt = document.querySelector(".start");
const $quit_bt = document.querySelector(".end");

$start_bt.addEventListener("click", onStart);
$quit_bt.addEventListener("click", onQuit);

function onStart() {
  location.href = "/start"
}

function onQuit() {
  const end = confirm("정말 게임을 종료하시겠습니까?");
  if (end) {
    alert("게임을 종료합니다.");
    window.close();
  }
}
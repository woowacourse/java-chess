const $board = document.querySelector("#main");

$board.addEventListener("mouseover", onSquare);
$board.addEventListener("mouseout", outSquare);

function onSquare(event) {
    event.target.classList.add("onboard");
}

function outSquare(event) {
    event.target.classList.remove("onboard");
}
const $board = document.querySelector("#main");

$board.addEventListener("mouseover", onSquare);
$board.addEventListener("mouseout", outSquare);
$board.addEventListener("onclick", onClickBoard);

function onSquare(event) {
    event.target.classList.add("onboard");
}

function outSquare(event) {
    event.target.classList.remove("onboard");
}

function onClickSquare(event) {
    fetch()
}
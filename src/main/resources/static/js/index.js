console.log("js 로드 성공")
console.log(window.document.querySelector(".board"))
window.document.querySelector(".board").addEventListener('click', clicked)

function clicked(event) {
    console.log(event.target.closest("div"))
}


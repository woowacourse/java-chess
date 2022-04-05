const score = document.querySelector(".score");
const button = document.querySelector("#status")

const clickHandler = () => {
    deleteHidden();
}

const deleteHidden = () => {
    score.classList.remove("hidden");
}

button.addEventListener("click", clickHandler);

const onClickRoom = (event) => {
  if (event.target.classList.contains("room")) {
    window.location.href = "./room/" + event.target.id;
  }
}

document.querySelector(".title").addEventListener("click", () => {
  window.location.href = "./";
});

document.querySelector(".rooms").addEventListener("click", onClickRoom);
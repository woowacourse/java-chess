document.querySelector(".title").addEventListener("click", () => {
  window.location.reload();
});

document.querySelector(".create").addEventListener("click", async () => {
  document.querySelector(".modal").style.display = "block";
})

document.querySelector(".rooms").addEventListener("click", (event) => {
  if (event.target.classList.contains("room")) {
    window.location.href = "./room/" + event.target.id;
  }
});

document.querySelector(".rooms").addEventListener("click", async (event) => {
  if (event.target.classList.contains("remove-room")) {
    const idToBeRemoved = event.target.closest(".room").getAttribute(
        "id").toString();
    console.log(idToBeRemoved)
    const response = await fetch("./room", {
      method: "PUT",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({id: idToBeRemoved})
    });
    const result = await response.json();
    if (response.ok) {
      window.location.reload();
    } else {
      alert("HTTP-Error: " + result["message"]);
    }
  }
});

document.querySelector(".modal").addEventListener("click", (event) => {
  if (event.target.classList.contains("modal")) {
    event.target.style.display = "none";
  }
});

document.querySelector(".close").addEventListener("click", (event) => {
  document.querySelector(".modal").style.display = "none";
});

document.querySelector(".create-room").addEventListener("submit",
    async (event) => {
      const roomName = document.querySelector("#room_name");
      const white = document.querySelector("#white");
      const black = document.querySelector("#black");

      if (isValueLengthIsLongerThan(roomName.value, 2)
          && isValueLengthIsLongerThan(white.value, 2)
          && isValueLengthIsLongerThan(black.value, 2)) {
        const response = await fetch("./room", {
          method: "POST",
          headers: {
            "Content-Type": "application/json"
          },
          body: JSON.stringify({
            name: roomName.value,
            white: white.value,
            black: black.value
          })
        });
        const result = await response.json();
        if (response.ok) {
          window.location.href = "./room/" + result["roomId"];
        } else {
          alert("HTTP-Error: " + result["message"]);
        }
      } else {
        alert("2자 이상의 이름을 입력해주세요.");
      }

    })

function isValueLengthIsLongerThan(value, length) {
  return value && (value.length >= length);
}
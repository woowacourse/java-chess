const roomNameCountMin = 2;

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
    const idToBeRemoved = event.target.closest(".room").getAttribute("id").toString();
    console.log(idToBeRemoved)
    await fetch("./room", {
      method: "PUT",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({id: idToBeRemoved})
    });
    window.location.reload();
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

document.querySelector(".create-room").addEventListener("submit", async (event) => {
  const roomName =  document.querySelector("#room_name");
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
    if (result["result"] === "success") {
      window.location.href = "./room/" + result["roomId"];
      return;
    }
    alert("서버에 요청 중에 에러가 발생했습니다. (" + result["message"] + ")");
    return;
  } else {
    alert("2자 이상의 이름을 입력해주세요.");
  }

})

function isValueLengthIsLongerThan(value, length) {
  return value && (value.length >= length);
}
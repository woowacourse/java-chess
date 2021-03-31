const roomNameCountMin = 2;

document.querySelector(".title").addEventListener("click", () => {
  window.location.reload();
});

document.querySelector(".create").addEventListener("click", async () => {
  const name = prompt("방 이름을 입력해주세요.");
  if (name === null) {
    return;
  }
  if (name.length < roomNameCountMin) {
    alert(roomNameCountMin + "자 이상의 이름을 입력해주세요.");
    return;
  }
  const response = await fetch("./room", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify({name: name})
  });
  const result = await response.json();
  if (result["result"] === "success") {
    window.location.href = "./room/" + result["roomId"];
    return;
  }
  alert(result["message"]);
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
})
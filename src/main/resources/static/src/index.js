let source = "";
let target = "";

function clicked(id) {
    console.log(id);
    setVariable(id);
    changeClickedBackground(id);
    checkSendToServer();
}

function setVariable(id) {
    if (source !== "") {
        target = id;
    } else {
        source = id;
    }
}

function changeClickedBackground(id) {
    let elementById = document.getElementById(id);
    elementById.style.backgroundColor = "#ff0";
}

function checkSendToServer() {
    if (source !== "" && target !== "") {
        sendToServer(source, target);
        source = "";
        target = "";
    }
}

function sendToServer() {
  fetch('/move', {
          method: "POST",
          headers: {
              "Content-Type": "text/plain",
          },
          body: source + " " + target
      }).then((response) => {
              response.json().then(data => {
                  console.log(data);
                  if (data.status === 400) {
                      alert(data.errorMessage);
                  }

                  if (data.isGameOver == true) {
                      alert("게임이 종료되었습니다.");
                      document.location.href = '/result'
                      return;
                  }

                  location.reload();
              });
          }
      );
}
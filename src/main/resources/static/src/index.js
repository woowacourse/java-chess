let source = "";
let target = "";

function clicked(id) {
    console.log(id);
    setVariable(id);
    checkSendToServer();
}

function setVariable(id) {
    if (source !== "") {
        target = id;
    } else {
        source = id;
    }
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
                  if (data.status === 400) {
                      alert(data.errorMessage);
                  }
                  location.reload();
              });
          }
      );
}
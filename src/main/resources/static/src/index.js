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
    let xhr = new XMLHttpRequest();
    let url = "/move";
    xhr.open("POST", url, true);

    xhr.setRequestHeader("Content-Type", "application/json");


    var data = source + " " + target;
    xhr.send(data);

    location.reload();
}
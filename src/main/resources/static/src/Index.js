let first = '';
let second = '';

function clicked(id) {
    if (first !== '') {
        second = id;
    } else {
        first = id;
    }

    if (first !== '' && second !== '') {
        sendToServer(first, second);
        first = '';
        second = '';
    }
}

function checkStatus() {
    fetch('/status', {
        method: "GET",
        headers: {
            "Content-Type": "text/plain",
        }
    }).then((response) => {
        response.json().then(data => {
            document.getElementById("statusResult")
                .innerHTML = "검은말 : " + data.BLACK + "<br >"
                + "흰말 : " + data.WHITE + "<br >"
                + data.winner;
        });
    });
}

function sendToServer(first, second) {
    fetch('/move', {
        method: "POST",
        headers: {
            "Content-Type": "text/plain",
        },
        body: "command=move " + first + " " + second
    }).then((response) => {
            response.json().then(data => {
                console.log(data.finished);
                if (data.status === 400) {
                    alert(data.errorMessage);
                }
                if (data.finished === true) {
                    alert("게임이 종료되었습니다.");
                    document.location.href = '/start'
                    return;
                }
                location.reload();
            });
        }
    );
}

var command = "";
var clickCount = 0;

document.getElementById("terminate").addEventListener('click', (e) => {
    if (e.target.id) {
        if (confirm("재시작 하시겠습니까?")) {
            fetch("/terminate", {
            }).then(() => {
                location.href = "/";
            });
        } else {
            location.href = "/";
        }
    }
});

let chessUI = document.getElementById('chessUi');
for (let node of chessUI.childNodes) {
    node.addEventListener('click', (e) => {
        clickCount++;
        let target = e.target;
        if (command === "") {
            command += target.id;
            return;
        }
        command += "," + target.id;

        if (clickCount === 2) {
            fetch("/move", {
                method: "POST",
                body: `command=${command}`
            }).then(res => {
                if (!res.ok) {
                    throw res;
                }
                location.href = "/";
            }).catch(err => {
                err.text().then(msg => {
                    alert(msg);
                    location.href = "/";
                })
            })
        }
    });
}

var command = "";
var clickCount = 0;
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
            console.log(command);
            fetch("/move", {
                method: "POST",
                body: `command=${command}`
            }).then(() => {location.href = "/"})
        }
    });
}

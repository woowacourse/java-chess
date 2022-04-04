async function displayBoard() {
    Array.from(await getBoard()).forEach(
        function(element) {
            const imgTeg = document.createElement("img");
            const imgPath = "images/" + element.color + "_" + element.name + ".png";

            imgTeg.setAttribute("src", imgPath);
            document.getElementById(element.position).appendChild(imgTeg);
        }
    )
}

async function getBoard() {
    return await fetch("/board")
        .then((response) => response.json());
}

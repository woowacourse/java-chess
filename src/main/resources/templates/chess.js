let $squares = document.querySelector("table");
$squares.addEventListener('click', getPoint);

function hi() {
    for (let j = 1; j<= 8; j++) {
        for(let i = 0; i<8;i++) {
            let row = String.fromCharCode('a'.charCodeAt(0) + i);
            let point = row + j;
            fetch('/board', {
                method: 'POST',
                body: point,
            }).then(res => res.json()).then(data => {
                document.getElementById(point).innerHTML = renderImage(data);
            });
        }
    }
}

function renderImage(input) {
    if(input === "") {
        return `<img className="piece">`;
    } else{
        return `<img src=${input} class="piece">`;
    }
}

function toggleClicked(sourcePoint, targetPoint) {
    document.getElementById(sourcePoint).classList.toggle("clicked");
    document.getElementById(targetPoint).classList.toggle("clicked");
}

function movePiece(sourcePoint, targetPoint) {
    let sourcePiece = document.getElementById(sourcePoint).innerHTML;
    document.getElementById(sourcePoint).innerHTML = renderImage("");
    document.getElementById(targetPoint).innerHTML = sourcePiece;
    toggleClicked(sourcePoint, targetPoint);
}

async function move(sourcePoint, targetPoint) {
    let data = {
        source: sourcePoint,
        target: targetPoint
    }

    const response = await fetch('/move', {
        method: 'POST',
        body: JSON.stringify(data),
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(res => res.json());
    console.log(response);

    if(response === 200) {
        alert('성공');
        movePiece(sourcePoint, targetPoint);
    }
    if (response === 401) {
        alert('실패');
        toggleClicked(sourcePoint, targetPoint);
    }
}

function getPoint(event) {
    let clickedId = event.target.closest("td").id;
    let sourcePoint;
    let targetPoint;

    const list = document.querySelectorAll("td");

    for (let i = 0; i < list.length; i++) {
        if (list[i].classList.contains("clicked")) {
            sourcePoint = list[i].id;
            targetPoint = clickedId;
                document.getElementById(targetPoint).classList.toggle("clicked");

            console.log(sourcePoint, targetPoint);

            move(sourcePoint, targetPoint);
            return;
        }
    }
    document.getElementById(clickedId).classList.toggle("clicked");
}



hi();
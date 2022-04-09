let source = '';
let target = '';

function move(id) {
    if (source === '') {
        let elementById = document.getElementById(id);
        elementById.style.backgroundColor = "#FF0000";
        source = id;
        return;
    }

    if (target === '') {
        let elementById = document.getElementById(id);
        elementById.style.backgroundColor = "#FF0000";
        target = id;
        movePiece();
    }
}

function movePiece() {
    fetch("/move", {
        method: "POST",
        headers: {
            "Content-Type": "text/plain"
        },
        body: source + " " + target
    }).then((res) => {
        res.json().then(data => {
            document.getElementById(source).style.backgroundColor = '';
            document.getElementById(target).style.backgroundColor = '';
            source = '';
            target = '';
            if (data.statusCode === 301) {
                alert("킹이 잡혀 게임이 종료 되었습니다!")
                end();
            }
            if (data.statusCode === 302) {
                location.replace("/chess");
            }
            if (data.statusCode === 501) {
                alert(data.errorMessage);
            }
        })
    })
}

function start() {
    fetch("/start").then(res => {
        res.json().then(data => {
            if (data.statusCode === 501) {
                alert(data.errorMessage);
                return;
            }
            location.replace("/chess");
        })
    })
}

function end() {
    fetch("/end").then(res => {
        res.json().then(data => {
            if (data.statusCode === 501) {
                alert(data.errorMessage);
                return;
            }
            location.replace("/result");
        })
    })
}


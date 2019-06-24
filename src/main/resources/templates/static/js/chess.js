const pieces = {
    "w-king": "♔",
    "w-queen": "♕",
    "w-rook": "♖",
    "w-bishop": "♗",
    "w-knight": "♘",
    "w-pawn": "♙",
    "b-king": "♚",
    "b-queen": "♛",
    "b-rook": "♜",
    "b-bishop": "♝",
    "b-knight": "♞",
    "b-pawn": "♟",
}

var selects = [];

Array.prototype.remove = function () {
    var _target, _arr = arguments, _len = a.length, ax;
    while (_len && this.length) {
        _target = arr[--_len];
        while ((ax = this.indexOf(_target)) !== -1) {
            this.splice(ax, 1);
        }
    }
    return this;
};

function initBoard() {
    const board = document.querySelector('.board');
    var flag = true;
    for (let i = 8; i >= 1; i--) {
        const tr = document.createElement('tr');
        for (let j = 97; j <= 104; j++) {
            const td = document.createElement('td');
            td.id = `${String.fromCharCode(j)}${i}`;
            if (flag) {
                td.classList.add('odd');
            } else {
                td.classList.add('even');
            }
            tr.appendChild(td);
            flag = !flag;
        }
        board.appendChild(tr);
        flag = !flag;
    }
}

function bodyData(from, to) {
    this.method = "POST";
    this.headers = {
        'Content-Type': 'application/x-www-form-urlencoded'
    };
    this.body = "from=" + from + "&to=" + to;
}

function clearSelected(flag) {
    console.log("클리어 전");
    console.log(selects);
    selects.forEach(v => document.getElementById(v).innerHTML = "");
    if (flag) {
        selects = [];
    }
}

function pieceHandler(event) {
    function selectTarget(event) {
        let target;
        if (event.target.tagName === "P") {
            target = event.target.parentNode;
        } else {
            target = event.target;
        }
        if (selects.includes(target.id)) {
            target.classList.remove('selected');
            selects.remove(target.id);
        }
        return target;
    }

    var target = selectTarget(event);
    console.log(target);
    selects.push(target.id);
    if (selects.length >= 2) {
        clearSelected(true);
        fetch('/game/1/move', new bodyData(selects[0], selects[1]))
            .then(res => res.json())
            .then(data => {
                refresh(data);
            })
            .catch(err => {
                console.log(err)
                alert("실패");
            });

    } else {
        event.target.firstChild.classList.add('selected');
    }
}


function clickEvent() {
    Array.from(document.getElementsByTagName("td"))
        .forEach(v => v.addEventListener('click', pieceHandler));
}

function refresh(data) {
    clearBoard();
    console.log(data);
    Object.keys(data)
        .filter(key => data[key] !== "0,Empty")
        .forEach(key => {
            const target = document.getElementById(key);
            const pieceData = data[key].split(",");
            console.log(teamToken(pieceData[0]) + "-" + pieceData[1].toLowerCase());
            const element = document.createElement("p");
            element.classList.add("piece");
            element.innerText = pieces[teamToken(pieceData[0]) + "-" + pieceData[1].toLowerCase()];
            target.append(element);
        });
}

function clearBoard() {
    Array.from(document.getElementsByTagName("td"))
        .forEach(element => element.innerHTML = "");
}

function teamToken(text) {
    if (text === "-1") {
        return "b";
    } else if (text === "1") {
        return "w";
    } else {
        console.error("해당 팀 토큰이 없음");
    }
}

initBoard()
fetch('game/1', {method: "POST"}).then(res => res.json()).then(data => refresh(data));
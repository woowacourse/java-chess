let source = null;
let target = null;

const squares = document.getElementsByClassName("square");
for (let i = 0; i < squares.length; i++) {
    squares.item(i).addEventListener("click", function () {
        mark(this);
        canMove(this);
    });
}

function makeMoveRequest(source, target) {
    var request = new XMLHttpRequest();
    request.open("POST", `./move?source=${source.id}&target=${target.id}`);
    request.send();
}

function update(board, turn) {
    console.log("board");
    console.log(board);
    for (i = 0; i < board.length; i++) {
        let pieceId = board[i].position.file + board[i].position.rank;
        let piece = document.getElementById(pieceId);

        if (board[i].piece) {
            let pieceImage = board[i].piece.name + "_" + board[i].piece.team.toLowerCase();
            piece.firstElementChild.src = "../images/" + pieceImage + ".png";
        } else {
            piece.firstElementChild.src = "../images/blank.png";
        }
    }

    let currentTeam = document.getElementById("turn");
    currentTeam.innerText = turn + "팀 차례입니다.";
}

function move(source, target) {
    const moveContents = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'source': source.id,
            'target': target.id
        }
    };
    console.log(moveContents);
    fetch("/play/move", moveContents)
        .then(response => {
            if (!response.ok) {
                alert("이동할 수 없는 곳입니다.");
            }
            const parsedResponse = response.text().then(text => {
                return text ? JSON.parse(text) : {}
            });
            return new Promise((resolve) => {
                parsedResponse.then(data => resolve({'status': response.status, 'body': data}));
            });
        })
        .then(response => {
            let board = response.body.squares;
            let turn = response.body.turn;
            console.log(board);
            console.log(turn);
            update(board, turn);
        });
    initialize();
}

function initialize() {
    initializeBoxShadow(source);
    initializeBoxShadow(target);
    source = null;
    target = null;
}

function initializeBoxShadow(location) {
    location.style.boxShadow = "";
}

function canMove(clickedLocation) {
    if (source === null) {
        source = clickedLocation;
        return;
    }
    if (target === null) {
        target = clickedLocation;
        move(source, target);
    }
}

function mark(clickedLocation) {
    if (clickedLocation.style.boxShadow) {
        clickedLocation.style.boxShadow = "";
    } else {
        clickedLocation.style.boxShadow = "inset 3px 3px #ffff60";
    }
}
let source = null;
let target = null;

const tiles = document.getElementsByClassName("tile");
for (i = 0; i < tiles.length; i++) {
    tiles.item(i).addEventListener("click", function () {
        changeOpacity(this);
        checkSourceOrTarget(this);
    })
}

function checkSourceOrTarget(clickedPosition) {
    if (source == null) {
        source = clickedPosition;
        return;
    }
    if (source === clickedPosition) {
        initializeOpacity(source);
        source = null;
    }
    if (target == null) {
        target = clickedPosition;
        move(source, target);
    }
}

function move(source, target) {
    const moveInformation = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'source': source.id,
            'target': target.id
        }
    };
    fetch("/playing/move", moveInformation)
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
            console.log(response.body);
            let board = response.body.chessPieces;
            let team = response.body.currentTeam;
            let winner = response.body.winner;
            if (winner != null) {
                alert(winner + "팀이 이겼습니다.");
                return;
            }
            update(board, team);
        });
    initialize(source, target);
}

function update(board, team) {
    for (i = 0; i < board.length; i++) {
        let pieceId = board[i].position.file + board[i].position.rank;
        let piece = document.getElementById(pieceId);

        if (board[i].piece) {
            let pieceImage = board[i].piece.pieceType + "_" + board[i].piece.team;
            piece.firstElementChild.src = "../images/" + pieceImage + ".png";
        } else {
            piece.firstElementChild.src = "../images/blank.png";
        }
    }

    let currentTeam = document.getElementById("current_team");
    currentTeam.innerText = team + "팀 차례입니다.";
}

function changeOpacity(clickedPosition) {
    clickedPosition.style.opacity = "0.7";
}

function initialize(sourceElement, targetElement) {
    initializeOpacity(sourceElement);
    initializeOpacity(targetElement);
    source = null;
    target = null;
}

function initializeOpacity(clickedPosition) {
    clickedPosition.style.opacity = "1.0";
}
window.onload = getPieces;
const pieceMap = {
    'R': '♜', 'N': '♞', 'B': '♝', 'Q': '♛', 'K': '♚', 'P': '♟',
    'r': '♖', 'n': '♘', 'b': '♗', 'q': '♕', 'k': '♔', 'p': '♙'
}
let isClick = false;
let source;

function getPieces() {
    axios({
        method: 'get',
        url: '/showData',
        params: {
            roomNumber: getRoomNumber()
        }
    }).then(function (result) {
        let pieces = result.data.piecesDto;
        alert("현재 게임 상태 : " + pieces.isEnd);
        if (pieces.isEnd) {
            alert("게임이 끝났습니다!!!\n" + "검은팀 : " + pieces.blackScore + "\n흰색팀 : " + pieces.whiteScore);
            location.href = '/';
            return;
        }
        changePieces(result.data.piecesDto.pieces);
        getStatus(pieces.blackScore, pieces.whiteScore, pieces.turn);
    });
}

function changePieces(pieces) {
    for (let i = 0; i < pieces.length; i++) {
        let piece = document.getElementById(pieces[i].position);
        piece.innerHTML = pieceMap[pieces[i].pieceName];
    }
}

function select(event) {
    if (!isClick) {
        isClick = true;
        source = event.target.id;
        return;
    }

    let target = event.target.id;
    isClick = false;
    axios({
        method: 'post',
        url: '/movedata',
        data: {
            source: source,
            target: target
        },
        params: {
            roomNumber: getRoomNumber()
        }
    }).then((result) => {
        let resultDto = result.data;
        if (resultDto.success) {
            location.reload();
            return;
        }
        alert(resultDto.errorMessage);
    });
}

function getStatus(blackScore, whiteScore, turn) {
    let result = document.getElementById("result");
    let Turn;
    if (turn == true) {
        Turn = "BLACK";
    } else {
        Turn = "WHITE";
    }
    result.innerHTML = "TURN : " + Turn + "<br />Black팀: " + blackScore + "<br />" + "White팀 : " + whiteScore;
}

function getRoomNumber() {
    let currentUrl = location.href;
    let roomNumber = currentUrl.slice(currentUrl.indexOf('?') + 1, currentUrl.length).split('=')[1];
    return roomNumber;
}
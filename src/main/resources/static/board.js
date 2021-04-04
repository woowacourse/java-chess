window.onload = getPieces;
const pieceMap = {
    'R': '♜', 'N': '♞', 'B': '♝', 'Q': '♛', 'K': '♚', 'P': '♟',
    'r': '♖', 'n': '♘', 'b': '♗', 'q': '♕', 'k': '♔', 'p': '♙'
}
let isClick = false;
let fromTarget;

function getPieces() {
    axios({
        method: 'get',
        url: '/showData',
    }).then(function (result) {
        let datas = result.data.response;
        let json = JSON.parse(datas);
        if (json["end"]) {
            changePieces(json["pieces"]);
            getStatus(json["status"], json["turn"]);
            return;
        }
        alert("게임이 끝났습니다!!!\n" + "검은팀 : " + json["status"]['BLACK'] + "\n흰색팀 : " + json["status"]['WHITE']);
        location.href = '/';
    });
}

function changePieces(datas) {
    for (let i = 0; i < datas.length; i++) {
        let piece = document.getElementById(datas[i].position);
        piece.innerHTML = pieceMap[datas[i].pieceName];
    }
}

function select(event) {
    if (!isClick) {
        isClick = true;
        fromTarget = event.target.id;
        return;
    }

    let toTarget = event.target.id;
    isClick = false;
    axios({
        method: 'post',
        url: '/movedata',
        data: {
            source: fromTarget,
            target: toTarget
        }
    })
        .then((result) => {
            let resultDto = result.data;
            if (resultDto.success) {
                location.reload();
                return;
            }
            alert(resultDto.errorMessage);
        });
}

function getStatus(scores, turn) {
    let resulthtml = document.getElementById("result");
    let Turn;
    if (turn == true) {
        Turn = "BLACK";
    } else {
        Turn = "WHITE";
    }
    resulthtml.innerHTML = "TURN : " + Turn + "<br />Black팀: " + scores['BLACK'] + "<br />" + "White팀 : " + scores['WHITE'];
}
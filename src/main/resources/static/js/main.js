const newGamemUrl = '/';
let squareClickselected = '';
let before = "";
let after = "";

const urls = location.href.split('/');
const gameId = urls[urls.length - 1];

const moveUrl = `/move`;
const scoreUrl = `/score/${gameId}`;
const isFinishedUrl = `/isFinished/${gameId}`;
// const boardUrl = `/board/${gameId}`;
/**
 * 페이지 첫 진입 시 => 기물 그리기 | 이벤트 적용 |점수 출력
 */
const initialize = () => {
    // fetch('/start');
    // alert("자동 시작~!")
    // .then(res => res.json())
    //     .then(board => board.forEach(boardDto =>
    //         setupPieceToSquare(document.getElementById(boardDto.position), boardDto.piece)));

    document.querySelectorAll('.piece-image')
        .forEach(square => square.addEventListener('click', squareClick));
    // document.querySelectorAll('.piece-image')
    //     .forEach(square => square.addEventListener('click', childrenImgClick));

    // setupScores();

    // document.getElementById("newGame").addEventListener('click', (event) => {
    //     location.href = newGamemUrl;
    // });

    // fetch(isFinishedUrl)
    //     .then(res => res.json())
    //     .then(result => {
    //         if (result) {
    //             document.querySelector('h2').innerHTML = 'GAME OVER';
    //             document.querySelectorAll('.square').forEach(e => e.removeEventListener('click', squareClick));
    //             document.getElementById('finish').play();
    //             return;
    //         }
    //         document.getElementById('start').play();
    //     })
}

// const setupPieceToSquare = (square, pieceName) => {
//     square.dataset.piece = pieceName;
//     square.style.backgroundImage = `url(/img/${pieceName}.png)`;
//     square.classList.add('hasPiece');
// }

/**
 * 점수 표시
 */
// const setupScores = () => {
//     fetch(scoreUrl)
//         .then(res => res.json())
//         .then(scores => document.querySelector('h3').innerHTML
//             = `WHITE : ${scores.WHITE}  |  BLACK : ${scores.BLACK}`);
// }

/**
 * 클릭 이벤트. 클릭 => 기물 없으면 리턴 | 기물 첫 클릭시 세팅 | 기물 두번째 클릭 시 move 요청 전송 및 후처리
 * @param event ClickEvent
 */
const squareClick = (event) => {
    // let currentPieceId = event.target.id;
    let currentPieceImg = event.target;

    // document.getElementById(currentPieceId).style.backgroundColor = 'red';
    //첫번째 선택before가 비어있으면   before변수에 element에 채우고 색을 준다.
    if (before === "") {
        before = currentPieceImg;
        before.style.backgroundColor = '#ffb9b9';
        return;
    }

    // 만약 before는 이미 선택한 상황이고 after가 비어있다면 after변수에 element를 채우고 색을 준다.
    if (before !== "" && after === "") {
        after = currentPieceImg;
        after.style.backgroundColor = '#fc8383';

        // post move
        movePiece(before, after);
        //둘다 채워진 상황이므로 -> post를 던지고, 기다린다. -> 다음을 위해 두 변수는 비워둔다.
        before = "";
        after = "";
        return;
    }
    if (currentPieceImg === '' && !event.target.classList.contains('piece-image')) {
        alert("잘못클릭햇어요")
        return;
    }
    if (selected === '') {
        setupSelected(currentPieceImg);
        return;
    }
    processMove(currentPieceId);
}

//후니
async function movePiece(before, after) {
    const board = await postMoveCommand(before, after);
    // alert("갔다왔따 board " + board);
    // alert("갔다왔따 board.board " + board.board);
    await updateBoard(board.board);
    // await checkGameOver(board.gameOver);
}


async function postMoveCommand(before, after) {
    // const bodyValue = {
    //     command: "move " + before.id + " " + after.id
    // }
    // console.log("move던지기 bodyValue 찍어보기 : " + bodyValue);

    var f = document.createElement("form"); // form 엘리멘트 생성
    f.setAttribute("method", "post"); // method 속성을 post로 설정
    f.setAttribute("action", "/move"); // submit했을 때 무슨 동작을 할 것인지 설정
    document.body.appendChild(f);

    var i = document.createElement("input"); // input 엘리멘트 생성
    i.setAttribute("type", "hidden"); // type 속성을 hidden으로 설정
    i.setAttribute("name", "command"); // name 속성을 'm_nickname'으로 설정
    i.setAttribute("value", "move " + before.id + " " + after.id); // value 속성을 neilong에 담겨있는 값으로 설정
    f.appendChild(i); // form 엘리멘트에 input 엘리멘트 추가
    f.submit();

    // let movedBoard = await fetch("/move", {
    //     method: 'POST',
    //     headers: {
    //         'Content-Type': 'application/json;charset=utf-8',
    //         'Accept': 'application/json'
    //     },
    //     body: JSON.stringify(bodyValue)
    // }).then(handleErrors)
    //     .catch(function (error) {
    //         alert(error.message);
    //     });
    //
    // alert("move 갔다왔따 프론트")
    // movedBoard = await movedBoard.json();
    // return movedBoard;
}

async function handleErrors(response) {
    if (!response.ok) {
        let message = await response.json();
        throw Error(message.errorMessage);
    }
    return response;
}


/**
 * 선택한 기물에 대한 박스 CSS 효과, 선택값 저장을 위한 데이터 처리
 * @param selectedSquare
 */
const setupSelected = (selectedSquare) => {
    selectedSquare.classList.add('selected');
    selected = selectedSquare.dataset.piece;
    from = selectedSquare.id;
}

/**
 * 두번째 클릭에 대한 ajax 및 기물 이동 처리
 * @param selectedSquare
 */
const processMove = (selectedSquare) => {
    fetch(moveUrl, {
        method: 'POST',
        body: JSON.stringify({
            gameId: gameId,
            piece: selected,
            from: from,
            to: selectedSquare.id
        })
    })
        .then(res => res.json())
        .then(moveResult => {
            if (!moveResult.result) {
                showFailMessage();
                return;
            }
            removePieceFromSquare(document.getElementById(moveResult.from));
            setupPieceToSquare(document.getElementById(moveResult.to), moveResult.piece);
            setupScores();
            document.getElementById('move').play();
            gameOverProcess();
        })

    document.querySelector('.selected').classList.remove('selected');
    selected = '';
    from = '';
}

/**
 * 기물 이동 실패 메시지 처리
 */
const showFailMessage = () => {
    document.querySelector('h2').innerText = '이동 실패.. 😅';
    setTimeout(() => document.querySelector('h2').innerText = '', 2000);
}

/**
 * 기물 이동 처리 이후 출발 지점에 대한 초기화
 * @param square
 */
const removePieceFromSquare = (square) => {
    square.classList.remove('hasPiece');
    square.dataset.piece = '';
    square.style.backgroundImage = '';
}

/**
 * 기물 이동 성공 시 마다, 게임 종료 여부 확인 및 이벤트 제거 처리
 */
const gameOverProcess = () => {
    fetch(isFinishedUrl)
        .then(res => res.json())
        .then(result => {
            if (result) {
                document.querySelector('h2').innerHTML = 'GAME OVER';
                document.querySelectorAll('.square').forEach(e => e.removeEventListener('click', squareClick));
                document.getElementById('finish').play();
            }
        })
}

initialize();

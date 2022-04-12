const newGamemUrl = '/';
let selected = '';
let from;

const urls = location.href.split('/');
const gameId = urls[urls.length - 1];

const moveUrl = `/move`;
const scoreUrl = `/score/${gameId}`;
const boardUrl = `/board/${gameId}`;
/**
 * 페이지 첫 진입 시 => 기물 그리기 | 이벤트 적용 |점수 출력
 */
const initialize = () => {
    fetch(boardUrl)
        .then(res => res.json())
        .then(board => board.forEach(boardDto =>
            setupPieceToSquare(document.getElementById(boardDto.position), boardDto.piece)))
        .then(() => setupScores())
        .then(() => {
            if (isGameOver()) {
                gameOverProcess();
                return;
            }
            document.getElementById('start').play();
        });

    document.querySelectorAll('.square')
        .forEach(square => square.addEventListener('click', squareClick));

    document.getElementById("newGame").addEventListener('click', (event) => {
        location.href = newGamemUrl;
    });
}

const setupPieceToSquare = (square, pieceName) => {
    square.dataset.piece = pieceName;
    square.style.backgroundImage = `url(/img/${pieceName}.png)`;
    square.classList.add('hasPiece');
}

/**
 * 점수 표시
 */
const setupScores = () => {
    fetch(scoreUrl)
        .then(res => res.json())
        .then(scores => document.querySelector('h3').innerHTML
            = `WHITE : ${scores.WHITE}  |  BLACK : ${scores.BLACK}`);
}

/**
 * 클릭 이벤트. 클릭 => 기물 없으면 리턴 | 기물 첫 클릭시 세팅 | 기물 두번째 클릭 시 move 요청 전송 및 후처리
 * @param event ClickEvent
 */
const squareClick = (event) => {
    let selectedSquare = event.target;
    if (selected === '' && !event.target.classList.contains('hasPiece')) {
        return;
    }
    if (selected === '') {
        setupSelected(selectedSquare);
        return;
    }
    processMove(selectedSquare);
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
        .then(json => {
            if (json.ok !== undefined && json.ok === false) {
                showFailMessage(json.message);
                return;
            }
            if (json.moveResult === 'FAIL') {
                showFailMessage();
                return;
            }
            if (json.moveResult === 'END') {
                gameOverProcess();
            }
            removePieceFromSquare(document.getElementById(json.from));
            setupPieceToSquare(document.getElementById(json.to), json.piece);
            setupScores();
            document.getElementById('move').play();
        })

    document.querySelector('.selected').classList.remove('selected');
    selected = '';
    from = '';
}

/**
 * 기물 이동 실패 메시지 처리
 */
const showFailMessage = (message) => {
    if (message === undefined) {
        message = '이동 실패.. 😅';
    }
    document.querySelector('h2').innerText = message;
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
    document.querySelector('h2').innerHTML = 'GAME OVER';
    document.querySelectorAll('.square').forEach(e => e.removeEventListener('click', squareClick));
    document.getElementById('finish').play();
}

const isGameOver = () => {
    return 2 > [...document.querySelectorAll('.hasPiece')].filter(square => square.style.backgroundImage.includes('k')).length;
}

initialize();

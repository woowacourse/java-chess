const FILES = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'];
const WHITE_SQUARE = "white-square";
const BLACK_SQUARE = "black-square";
const PIECES = {
    BLACK_KING: `<img src="/img/king_black.png" class="piece" alt="king_black"/>`,
    BLACK_QUEEN: `<img src="/img/queen_black.png" class="piece" alt="queen_black"/>`,
    BLACK_ROOK: `<img src="/img/rook_black.png" class="piece" alt="rook black"/>`,
    BLACK_BISHOP: `<img src="/img/bishop_black.png" class="piece" alt="bishop black"/>`,
    BLACK_KNIGHT: `<img src="/img/knight_black.png" class="piece" alt="knight black"/>`,
    BLACK_PAWN: `<img src="/img/pawn_black.png" class="piece" alt="pawn black"/>`,
    WHITE_KING: `<img src="/img/king_white.png" class="piece" alt="king white"/>`,
    WHITE_QUEEN: `<img src="/img/queen_white.png" class="piece" alt="queen white"/>`,
    WHITE_ROOK: `<img src="/img/rook_white.png" class="piece" alt="rook_white"/>`,
    WHITE_BISHOP: `<img src="/img/bishop_white.png" class="piece" alt="bishop white"/>`,
    WHITE_KNIGHT: `<img src="/img/knight_white.png" class="piece" alt="knight white"/>`,
    WHITE_PAWN: `<img src="/img/pawn_white.png" class="piece" alt="pawn white"/>`,
}

document.addEventListener("DOMContentLoaded", buildBoard);

const PATCH = {
    method: 'PATCH',
    headers: {
        'Content-Type': 'application/json'
    },
}

async function buildBoard() {
    let $board = document.getElementById("board");
    if ($board == null) {
        document.querySelector("body").insertAdjacentHTML("afterbegin", build());
        $board = document.getElementById("board");
    }
    $board.addEventListener("click", onMove);
    $board.addEventListener("mouseover", onMouseOverSquare);
    $board.addEventListener("mouseout", onRevertSquareColor);

    const data = await showChessInfo();
    const boardDTO = data.boardDTO;
    inputImageAtBoard(boardDTO.pieceDTOS);
    $blackScore.textContent = boardDTO.blackScore;
    $whiteScore.textContent = boardDTO.whiteScore;
    borderCurrentTurn(data.turn);
}

function borderCurrentTurn(turn) {
    if (turn === 'BLACK') {
        document.getElementById("black-versus").classList.add("currentTurn");
        document.getElementById("white-versus").classList.remove("currentTurn");
        return;
    }

    document.getElementById("white-versus").classList.add("currentTurn");
    document.getElementById("black-versus").classList.remove("currentTurn");
}

const $whiteScore = document.getElementById("white-score");
const $blackScore = document.getElementById("black-score");

async function showChessInfo() {
    const chessId = getCookie("chessId");
    const response = await fetch('/chess/' + chessId);
    return await response.json();
}

function getCookie(name) {
    return document.cookie.split("; ").find(row => row.startsWith(name)).split("=")[1];
}

function inputImageAtBoard(pieces) {
    Array.from(pieces)
        .filter(piece => piece.name !== "BLANK")
        .forEach(piece => {
            const position = piece.position;
            const pieceName = piece.color + "_" + piece.name;
            document.getElementById(position).innerHTML = PIECES[pieceName];
        });
}

async function onMove(event) {
    const $position = document.getElementsByClassName("selected");
    if (!$position.length) {
        onChangeColorOfSquare(event);
        return;
    }

    if (event.target.closest("div").classList.contains("selected")) {
        event.target.closest("div").classList.remove("selected");
        return;
    }

    const source = $position[0].id;
    const target = event.target.closest("div").id;

    const chessId = getCookie('chessId');
    const moved = await patchMovePiece(chessId, source, target);
    revertSquareColor($position);
    if (!moved) {
        alert("해당 위치로 이동할 수 없습니다.");
        return;
    }

    movePiece(source, target);

    const moveResult = await showChessInfo();
    borderCurrentTurn(moveResult.turn);
    if (moveResult.status.includes("KING_DEAD")) {
        alert("왕이 죽었습니다. 게임을 종료합니다.");
        window.location.href = "/";
    }
}

async function patchMovePiece(chessId, source, target) {
    const response = await fetch('/chess/' + chessId + '?source=' + source + '&target=' + target, PATCH);
    return response.ok;
}

function movePiece(source, target) {
    const $sourcePosition = document.getElementById(source);
    const $targetPosition = document.getElementById(target);

    $targetPosition.innerHTML = $sourcePosition.innerHTML;
    $sourcePosition.innerHTML = "";
}

function onChangeColorOfSquare(event) {
    let squareClassList = event.target.closest("div").classList;
    if (squareClassList.contains("selected")) {
        squareClassList.remove("selected");
    } else {
        squareClassList.add("selected");
    }
}

function revertSquareColor($position) {
    for (const $positionElement of $position) {
        $positionElement.classList.remove("selected");
    }
}

function onMouseOverSquare(event) {
    event.target.closest("div").classList.add("over-square");
}

function onRevertSquareColor(event) {
    event.target.closest("div").classList.remove("over-square");
}

function build() {
    let html = '<div id="board">';
    for (let rank = 8; rank >= 1; rank--) {
        html += addSquaresAtRank(rank);
    }
    html += "</div>";
    return html;
}

function addSquaresAtRank(rank) {
    let cellHtmlOfRank = '';
    for (let fileIndex = 0; fileIndex < 8; fileIndex++) {
        cellHtmlOfRank += addSquare(rank, fileIndex);
    }
    return cellHtmlOfRank;
}

function addSquare(rank, fileIndex) {
    let color = getSquareColor(rank, fileIndex);
    return `<div class=${color} id=${FILES[fileIndex] + rank}></div>`;
}

function getSquareColor(rank, fileIndex) {
    return isWhite(rank, fileIndex) ? WHITE_SQUARE : BLACK_SQUARE;
}

function isWhite(rank, fileIndex) {
    if ((rank % 2 === 0) && (fileIndex % 2 === 0)) {
        return true;
    }

    return (rank % 2 === 1) && (fileIndex % 2 === 1);
}

import {getFetch, postFetch} from "./promise/fetches.js"
import pieceFonts from "./enum/chessPieceFont.js"

const $chessboard = document.querySelector('#chessboard');
const $startBtn = document.querySelector('#startBtn');
const $resetBtn = document.querySelector('#resetBtn');

$chessboard.addEventListener("click", onClickPiece);
$startBtn.addEventListener("click", onClickStartBtn);
$resetBtn.addEventListener("click", onClickStartBtn);

async function start() {
    const piecesData = await getFetch("/game/start");
    setBoard(piecesData.piecesAndPositions);
}

async function movePiece(from, to) {
    const moveResult = await postFetch("/game/move", {id: 1, from: from, to: to});
    if (moveResult.isEnd || moveResult.isEnd === false) {
        console.log(`${from} + ${to}`)
        console.log($chessboard.querySelector(to))
        $chessboard.querySelector("#"+to).innerText = $chessboard.querySelector("#"+from).innerText;
        $chessboard.querySelector("#"+from).innerText = "";
    }
}

async function onClickPiece(e) {
    if (e.target && e.target.classList.contains("cell")) {
        if (checkAnySelected()) {
            await movePiece(getFirstSelected().id, e.target.id);
            removeSelectedClass();
            return;
        }
        if (e.target.innerText) {
            e.target.classList.toggle("selected");
        }
    }
}

async function onClickStartBtn(e) {
    if (e.target && e.target.id === "startBtn") {
        await start();
    }
}

function checkAnySelected() {
    return $chessboard.querySelectorAll(".selected").length === 1;
}

function getFirstSelected() {
    return $chessboard.querySelector('.selected');
}

function removeSelectedClass() {
    console.log("뇽뇽")
    $chessboard.querySelectorAll(".selected")
        .forEach(e => e.classList.remove("selected"));
}

function setBoard(positionsAndPieces) {
    Object.keys(positionsAndPieces).forEach(e => {
        const coordinate = $chessboard.querySelector('#' + e)
        coordinate.textContent = pieceFonts[positionsAndPieces[e].notation]
    });
}

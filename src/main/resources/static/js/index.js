import {getFetch, postFetch} from "./promise/fetches.js"
import pieceFonts from "./enum/chessPieceFont.js"

const $chessboard = document.querySelector('#chessboard');
const $startBtn = document.querySelector('#startBtn');
const $saveBtn = document.querySelector('#saveBtn');
const $blackScore = document.querySelector('#blackScore')
const $whiteScore = document.querySelector('#whiteScore')

let nextColor;

$chessboard.addEventListener("click", onClickPiece);
$startBtn.addEventListener("click", onClickStartBtn);
$saveBtn.addEventListener("click", onClickSaveBtn);

async function start() {
    const piecesData = await getFetch("/game/start");
    await calculateScore();
    setBoard(piecesData.piecesAndPositions);
    nextColor = piecesData.color;
}

async function movePiece(from, to) {
    const moveResult = await postFetch("/game/move", {id: 1, from: from, to: to});
    if (moveResult.hasOwnProperty("isEnd") && moveResult.isEnd === true) {
        alert("게임이 종료되었습니다~!");
    }
    if (moveResult.hasOwnProperty("isEnd") && moveResult.isEnd === false) {
        const $to = $chessboard.querySelector("#" + to);
        const $from = $chessboard.querySelector("#" + from);
        const color = $from.classList.contains("BLACK") ? "BLACK" : "WHITE";
        $to.innerText = $from.innerText;
        $from.innerText = "";

        $from.classList.remove("BLACK", "WHITE");
        $to.classList.remove("BLACK", "WHITE");
        $to.classList.add(color);
        nextColor = moveResult.nextColor;
    }
}

async function savePiece() {
    const rank = [8,7,6,5,4,3,2,1];
    const file = ["a","b","c","d","e","g","h"];

    const boardPieces = rank.flatMap(rank => file.map(file => file + rank))
        .map(position => getKeyByValue(pieceFonts, $chessboard.querySelector("#" + position).innerText))
        .join("");
    await postFetch("/game/save", {pieces: boardPieces});
}

function getKeyByValue(object, value) {
    return Object.keys(object).find(key => object[key] === value);
}

async function calculateScore() {
    const scoreResponseData = await getFetch("/game/score");

    $blackScore.innerText = scoreResponseData.colorsScore.BLACK;
    $whiteScore.innerText = scoreResponseData.colorsScore.WHITE;
}

async function onClickPiece(e) {
    if (e.target && e.target.classList.contains("cell")) {
        if (!checkAnySelected() && !e.target.classList.contains(nextColor)) {
            alert("움직일 수 있는 진영의 기물이 아닙니다.");
            return;
        }

        if (checkAnySelected() && sameColorCell(e.target)) {
            removeSelectedClass();
            if (!equalCell(e.target)) {
                return;
            }
        }
        if (checkAnySelected() && notEqualCell(e.target)) {
            await movePiece(getFirstSelected().id, e.target.id);
            await calculateScore();
            removeSelectedClass();
            return;
        }

        if (e.target.innerText !== "") {
            e.target.classList.toggle("selected");
        }
    }
}

async function onClickStartBtn(e) {
    if (e.target && e.target.id === "startBtn") {
        await start();
    }
}

function onClickSaveBtn(e) {
    if (e.target && e.target.id === "saveBtn") {
        savePiece();
    }
}

function equalCell(target) {
    return target === getFirstSelected();
}

function notEqualCell(target) {
    return target !== getFirstSelected();
}

function sameColorCell(target) {
    if (target.classList.contains("BLACK")) {
        return getFirstSelected().classList.contains("BLACK");
    }
    if (target.classList.contains("WHITE")) {
        return getFirstSelected().classList.contains("WHITE");
    }
    return false;

}

function checkAnySelected() {
    return $chessboard.querySelectorAll(".selected").length === 1;
}

function getFirstSelected() {
    return $chessboard.querySelector(".selected");
}

function removeSelectedClass() {
    $chessboard.querySelectorAll(".selected")
        .forEach(e => e.classList.remove("selected"));
}

function setBoard(positionsAndPieces) {
    $chessboard.querySelectorAll(".cell").forEach(e => {
        e.innerText = ""
        e.classList.remove("WHITE", "BLACK");
    });
    Object.keys(positionsAndPieces).forEach(e => {
        const coordinate = $chessboard.querySelector('#' + e)
        coordinate.textContent = pieceFonts[positionsAndPieces[e].notation]
        coordinate.classList.add(positionsAndPieces[e].color);
    });
}

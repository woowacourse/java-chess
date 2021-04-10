import ChessService from "./ChessService.js";

const apiService = new ChessService();

const $chessTable = document.querySelector(".chessboard");
const $results = document.querySelector(".results");
const $resultBtn = document.querySelector(".result-btn");
const $resultDisplay = document.querySelector(".result-display");
const $start = document.querySelector(".start-btn");
const $startSaved = document.querySelector(".start-saved-btn");
const $saving = document.querySelector(".save-btn");

const alpha = ['', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h']

function drawTable(data) {
    let innerValue = `<div class="row">
            <div class="position"></div>
            <div class="position">a</div>
            <div class="position">b</div>
            <div class="position">c</div>
            <div class="position">d</div>
            <div class="position">e</div>
            <div class="position">f</div>
            <div class="position">g</div>
            <div class="position">h</div>
        </div>`;

    for (let row = 8; row > 0; row--) {
        innerValue +=
            `<div class="row"> 
          <div class="position">${row}</div>`;

        for (let j = 1; j < 9; j++) {
            const column = alpha[j]
            const id = column + row;
            const pieceObj = data[id]
            const piece = pieceObj['pieceType'].toLowerCase();
            const color = pieceObj['teamColor'].toLowerCase();

            const icon = `<i class="fas fa-chess-${piece} ${color}"></i>`
            if (piece === "blank") {
                innerValue += `<div class="cell ${(row + j) % 2 === 0 ? "light"
                    : "dark"}" id="${id}"></div>`;
            } else {
                innerValue += `<div class="cell ${(row + j) % 2 === 0 ? "light"
                    : "dark"}" id="${id}">${icon}</div>`;
            }
        }

        innerValue += "</div>";
    }

    $chessTable.innerHTML = innerValue;
}

function modifyBtns() {
    if ($start.innerText === "START") {
        $start.innerText = 'END';
        $results.classList.remove('hidden');
        return;
    }
    apiService.terminateWithoutSaving().then(showResult)
    $chessTable.classList.add('hidden')
}

$start.addEventListener("click", () => apiService.getChessBoard().then(drawTable).then(() => modifyBtns()));

function modifySavingBtns() {
    $chessTable.classList.add('hidden')
}


function modifyStartSavedBtn() {
    $chessTable.classList.remove("hidden");
    $results.classList.remove('hidden');
}

$startSaved.addEventListener("click", () => apiService.getChessBoardSaved().then(drawTable).then(modifyStartSavedBtn));
$saving.addEventListener("click", () => apiService.saveBoard().then(showResult).then(modifySavingBtns))

let sourcePosition;
let targetPosition;
let count = 0;

function checkIfBoard(event) {
    const {target} = event;
    if (target.classList.contains("cell")) {
        count++;
        target.classList.toggle("clicked")
        if (count === 1) {
            sourcePosition = target.getAttribute("id");
        }
        if (count === 2) {
            count = 0;
            targetPosition = target.getAttribute("id");
            apiService.moveSourceToTarget(sourcePosition, targetPosition)
                .then(() => apiService.getChessBoard())
                .then(drawTable);
        }
        if (count > 2) {
            alert("NO~")
        }
    }
}


function showResult(data) {
    const result = data['result'];
    const blackScore = result['BLACK']['score'];
    const whiteScore = result['WHITE']['score'];
    const winner = data['winner'];

    $resultDisplay.innerHTML = `<span>블랙: ${blackScore} <br/><br/> 화이트: ${whiteScore} <br/><br/> 우승자 : ${winner} </span>`;

}

$chessTable.addEventListener("click", checkIfBoard)
$resultBtn.addEventListener("click", () => apiService.getResult().then(showResult))
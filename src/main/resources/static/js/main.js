import ChessService from "./ChessService.js";

const apiService = new ChessService();

const $chessTable = document.querySelector(".chessboard");
const $results = document.querySelector(".results");
const $resultBtn = document.querySelector(".result-btn");
const $resultDisplay = document.querySelector(".result-display");
const $isPlaying = document.querySelector(".isPlaying");
const $saving = document.querySelector(".saving");

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
      if(piece === "blank"){
        innerValue += `<div class="cell ${(row + j) % 2 === 0 ? "light"
            : "dark"}" id="${id}"></div>`;
      }else{
        innerValue += `<div class="cell ${(row + j) % 2 === 0 ? "light"
            : "dark"}" id="${id}">${icon}</div>`;
      }
    }

    innerValue += "</div>";
  }

  $chessTable.innerHTML = innerValue;
}

function modifyBtns() {
  if($isPlaying.innerText === "START"){
    $isPlaying.innerText= 'END';
    $results.classList.remove('hidden');
    return;
  }
  apiService.terminateWithoutSaving().then(showResult)
  $chessTable.classList.add('hidden')
}

$isPlaying.addEventListener("click", ()=>apiService.getChessBoard().then(drawTable).then(modifyBtns));

function modifySaving() {
  if($isPlaying.innerText === "START SAVED"){
    $isPlaying.innerText= 'SAVE';
    return;
  }
  apiService.saveBoard().then(showResult)
  $chessTable.classList.add('hidden')
}



$saving.addEventListener("click", modifySaving)

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
import {PIECES} from "/js/constant.js";

const $start_bt = document.querySelector(".restart");
const $quit_bt = document.querySelector(".end");
const $tile = document.querySelector(".board");
let $selectTiles = document.querySelectorAll(".selected");

$start_bt.addEventListener("click", onRestart);
$quit_bt.addEventListener("click", onQuit);
$tile.addEventListener("click", onMove);

window.onload = e => {
  console.log(PIECES)
  renderBoard();
}

function onRestart() {
  const restart = confirm("게임을 다시 시작하시겠습니까?");
  if (restart) {
    alert("게임을 다시 시작합니다.");
    location.href = "/start";
  }
}

function onQuit() {
  const end = confirm("정말 게임을 종료하시겠습니까?");
  if (end) {
    alert("게임을 종료합니다.");
    window.close();
  }
}

function showPath() {

}

function getOption(methodType, bodyData) {
  return {
    methodType: methodType,
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(bodyData)
  };
}

function replaceBoard(response) {

}

function movePiece(source, target) {
  const moveData = {
    "source": source,
    "target": target
  }

  const option = getOption("PUT", moveData);

  fetch("/move", option)
  .then((response) => {
    if (!response.ok) {
      throw new Error(" ");
    }
    return response.json();
  })
  .then(replaceBoard)
  .catch(error => console.log(error));
}

function onMove({target}) {

  // source를 클릭하면 showPath
  // 이후 target을 클릭하면
  // 1) 본인이면 source class 제거. count--
  // 2) 갈 수 없는 경로면

  const target_c = target.closest("div").classList;
  if (target_c.contains("location") || target.innerText === ".") {
    return;
  }

  if ($selectTiles.length === 0) {
    target_c.add("selected");
    target_c.add("source");
    console.log("source selected!!");

    //showPath(target_c);
    return;
  } else if ($selectTiles.length === 1 && !target_c.contains("source")) {
    target_c.add("selected");
    target_c.add("target");
    console.log("target selected!!");
  }

  const $source = document.querySelector(".source").id;
  const $target = document.querySelector(".target").id;

  movePiece($source, $target)

  // $selectTiles = document.querySelectorAll(".selected");
  for (const select of $selectTiles) {
    select.classList.remove("selected");
  }
}

function renderBoard() {
  const tiles = document.querySelectorAll(".tile");
  for (const tile of tiles) {
    const piece = document.getElementById(tile.id).innerText;
    console.log(piece, PIECES[piece])
    document.getElementById(tile.id).innerHTML = PIECES[piece];
  }
}

//
// function showSourcePath(source) {
//   const pathContents = {
//     method: 'POST',
//     headers: {
//       'Content-Type': 'application/json',
//       'source': source.id
//     }
//   };
//   console.log(pathContents);
// }
//
// function move(source, target) {
//   const moveContents = {
//     method: 'POST',
//     headers: {
//       'Content-Type': 'application/json',
//       'source': source.id,
//       'target': target.id
//     }
//   };
//   console.log(moveContents);
//   fetch("/play/move", moveContents)
//   .then(response => {
//     if (!response.ok) {
//       alert("이동할 수 없는 곳입니다.");
//     }
//     const parsedResponse = response.text().then(text => {
//       return text ? JSON.parse(text) : {}
//     });
//     return new Promise((resolve) => {
//       parsedResponse.then(
//           data => resolve({'status': response.status, 'body': data}));
//     });
//   })
//   .then(response => {
//     let board = response.body.squares;
//     let turn = response.body.turn;
//     console.log(board);
//     console.log(turn);
//     update(board, turn);
//   });
// }

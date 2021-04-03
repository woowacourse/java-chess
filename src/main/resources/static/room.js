const API_URL = "http://localhost:4567/";
const BOARD_LIST = document.querySelector("#board-list");

window.onload = () => {
  document.querySelector("#create-button").addEventListener("click", createButtonEvent);
  document.querySelector("#search-button").addEventListener("click", searchButtonEvent);
}

function createButtonEvent(event) {
  const whitePlayer = prompt("흰색을 움직일 플레이어를 입력해주세요(2글자 이상 12글자 이하)");
  if (whitePlayer.length < 2 || whitePlayer > 12) {
    alert("플레이어 이름은 2글자이상 12글자 이하입니다.");
    return;
  }
  const blackPlayer = prompt("검정색을 움직일 플레이어를 입력해주세요(2글자 이상 12글자 이하)");
  if (blackPlayer.length < 2 || blackPlayer > 12) {
    alert("플레이어 이름은 2글자이상 12글자 이하입니다.");
    return;
  }
  const newData = {
    "whitePlayer": whitePlayer,
    "blackPlayer": blackPlayer
  }
  console.log(newData);
  const option = getOption("POST", newData);
  fetch(API_URL + "create", option)
  .then((response) => {
    return response.json();
  })
  .then(renderChess)
  .catch((error) => {
    console.log(error);
  })
}

function searchButtonEvent(event) {
  const playerName = prompt("검색할 player를 입력해주세요.");
  if (playerName.length < 2 || playerName > 12) {
    alert("플레이어 이름은 2글자이상 12글자 이하입니다.");
    return;
  }
  fetch(API_URL + "search?playerName=" + playerName)
  .then((response) => {
    return response.json();
  })
  .then(updateBoardInfo)
  .catch((error) => {
    console.log(error);
  })
}

function renderChess(responseData) {
  location.href = API_URL + "chess?boardId=" + responseData["boardId"]
}

function getOption(methodType, bodyData) {
  return {
      method: methodType,
      headers: {
          'Content-Type': 'application/json'
      },
      body: JSON.stringify(bodyData)
  };
}

function updateBoardInfo(responseData) {

  while (BOARD_LIST.hasChildNodes()) {
    BOARD_LIST.removeChild(BOARD_LIST.firstChild);
  }

  for (data of responseData) {
    console.log(data);
    const newTableRow = document.createElement("tr");
    const tableDataBoardId = document.createElement("th");
    tableDataBoardId.innerText = data["boardId"];
    tableDataBoardId.setAttribute("scope", "row");

    const tableDataWhitePlayer = document.createElement("td");
    tableDataWhitePlayer.innerText = data["whitePlayer"];

    const tableDataBlackPlayer = document.createElement("td");
    tableDataBlackPlayer.innerText = data["blackPlayer"];

    const tableDataTrun = document.createElement("td");
    if (data["turnIsWhite"] === true) {
      tableDataTrun.innerText = "white";
    } else {
      tableDataTrun.innerText = "black";
    }

    const tableDataFinish = document.createElement("td");
    if (data["isFinish"] === true) {
      newTableRow.className += "table-secondary";
      tableDataFinish.innerText = "finish";
    } else {
      newTableRow.className += "table-light";
      tableDataFinish.innerText = "play";
    }
    newTableRow.appendChild(tableDataBoardId);
    newTableRow.appendChild(tableDataWhitePlayer);
    newTableRow.appendChild(tableDataBlackPlayer);
    newTableRow.appendChild(tableDataTrun);
    newTableRow.appendChild(tableDataFinish);

    newTableRow.addEventListener("click", playChessGame);
    BOARD_LIST.appendChild(newTableRow);
  }
}

function playChessGame(event) {
  const data = {"boardId": event.target.parentNode.firstChild.innerText};
  renderChess(data);
}
import {getData, postData} from "./utils/FetchUtil.js"

const url = "http://localhost:4567";

window.onload = function () {
  const newGameButton = document.querySelector(".new-game");
  const loadGameButton = document.querySelector(".load-game")
  const searchRecordButton = document.querySelector(".search-record")

  newGameButton.addEventListener("click", startNewGame);
  loadGameButton.addEventListener("click", loadGame);
}

async function startNewGame(e) {
  const whiteUserName = prompt("흰색 유저 이름을 입력하세요.");
  const blackUserName = prompt("검정색 유저 이름을 입력하세요.");

  try {
    validateName(whiteUserName, blackUserName);
  } catch (e) {
    alert("이름이 비어있거나, 같은 이름을 입력했습니다.");
    return;
  }

  try {
    await createUserIfNotExistent(whiteUserName);
    await createUserIfNotExistent(blackUserName);
    await createGame(whiteUserName, blackUserName);
  } catch (e) {
    alert(e);
  }
}

function validateName(whiteName, blackName) {
  if (whiteName.length === 0 || blackName.length === 0) {
    throw Error("이름을 입력하지 않았습니다.");
  }
  if (whiteName === blackName) {
    throw Error("유저의 이름은 같을 수 없습니다.");
  }
}

async function createUserIfNotExistent(userName) {
  const plainObject = await getData(`${url}/api/user/${userName}`);
  if (isEmptyObject(plainObject)) {
    await createUser(userName);
  }
  return plainObject;
}

function isEmptyObject(object) {
  return Object.keys(object).length === 0;
}

async function createUser(userName) {
  const body = {
    name: userName
  };

  await postData(`${url}/api/user`, body);
}

async function createGame(whiteUserName, blackUserName) {
  const body = {
    whiteName: whiteUserName,
    blackName: blackUserName
  };
  await postData(`${url}/game`, body);
}

function loadGame() {
  const gameId = prompt("이동할 방번호를 입력하세요.");
  window.location.href = `${url}/game/${gameId}`
}

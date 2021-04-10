import {getData, postData} from "./utils/FetchUtil.js"
import {User} from "./user/User.js"

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
    alert(e);
    return;
  }

  const whiteUser = await getUser(whiteUserName);
  const blackUser = await getUser(blackUserName);
  // TODO: /game get
}

function validateName(whiteName, blackName) {
  if (whiteName.length === 0 || blackName.length === 0) {
    throw Error("이름을 입력하지 않았습니다.");
  }
  if (whiteName === blackName) {
    throw Error("유저의 이름은 같을 수 없습니다.");
  }
}

async function getUser(userName) {
  const plainObject = await getData(`${url}/api/user/${userName}`);
  if (isEmptyObject(plainObject)) {
    return await createUser(userName);
  }
  return new User(plainObject);
}

function isEmptyObject(object) {
  return Object.keys(object).length === 0;
}

async function createUser(userName) {
  const body = {
    name: userName
  };

  try {
    await postData(`${url}/api/user`, body);
  } catch (e) {
    throw new Error(e);
  }
  return new User(await getData(`${url}/api/user/${userName}`));
}

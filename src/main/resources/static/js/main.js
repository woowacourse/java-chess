const mainStart = document.querySelector("#main-start");
const mainLoad = document.querySelector("#main-load");
const basePath = 'http://localhost:4567';

mainStart.addEventListener("click", () => {
    let result = window.prompt("게임 이름을 입력해주세요");
    if (result === '' || result === null) {
        return;
    }
    axios({
        method: 'post',
        url: basePath + '/games',
        data: {
            name: result
        }
    }).then(() => {
            localStorage.setItem("name", result)
            window.location = basePath + "/games"
        }
    ).catch(error => alert(error));
});

mainLoad.addEventListener("click", () => {
    let result = window.prompt("찾으려는 게임 이름을 입력해주세요");
    if (result === '' || result === null) {
        return;
    }
    axios({
        method: 'get',
        url: basePath + '/games/' + result
    }).then(() => {
            localStorage.setItem("name", result)
            window.location = basePath + "/games"
        }
    ).catch(error => alert(error));
});

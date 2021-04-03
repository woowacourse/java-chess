const start = document.querySelector("#start");
const basePath = 'http://localhost:4567';

start.addEventListener("click", () => {
    let result = window.prompt("게임 이름을 입력해주세요");

    axios({
        method: 'post',
        url: basePath + '/games',
        data: {
            name: result
        }
    }).then(
    ).catch(error => console.log(error));

    window.location = basePath + "/games";
})
export const endGame = () => {
    const $endButton = document.getElementById("end-button");
    $endButton.addEventListener("click", function(e) {
        axios.post("/end")
        .then()
        .catch(error => console.log(error));

        alert("게임이 종료되었습니다.");
    })
}
const $chessboard = document.querySelector(".chessboard");
$chessboard.addEventListener("click", select);

function movePieces(source, target) {
    document.querySelector("#" + target).innerText = document.querySelector("#" + source).textContent;
    document.querySelector("#" + source).innerText = "";
}

function move(source, target) {
    axios({
        method: 'post',
        url: '/move',
        data: {
            source: source,
            target: target
        }
    })
        .then(function (response) {
            console.log(response.data)
            if (response.data === 200) {
                movePieces(source, target);
                return;
            }
            alert("잘못된 이동입니다.");
        })
        // .catch(function (error) {
        //     console.log("에러")
            // handle error
        //     console.log(error);
        // })
}

function select(event) {
    const targets = $chessboard.querySelectorAll("div");
    for (let i = 0; i < targets.length; i++) {
        if (targets[i].classList.contains("select")) {
            if (targets[i].id !== event.target.id) {
                move(targets[i].id, event.target.id);
            }
            targets[i].classList.remove("select");
            return;
        }
    }
    if (event.target.textContent === "") {
        return;
    }
    event.target.classList.add("select");
}



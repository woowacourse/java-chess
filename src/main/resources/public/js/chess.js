addClickListener();

function addClickListener(){
    document.getElementById("chessboard").addEventListener("click", selectPieces)
}

function start(){
    postData("/start")
}

function move(source, target) {
    let response = postData("/move", {source:source.id, target:target.id})
    response.then(function (result){
        if(result == 400){
            alert("잘못된 움직임 입니다.")
        }
        if(result == 200){
            let sourceText = document.getElementById(source.id).textContent;
            document.getElementById(target.id).innerText = sourceText;
            document.getElementById(source.id).innerText = "";
        }
    })
}

function selectPieces(event){
    let source = clickedPieces();
    let target = event.target;
    if(source){
        if(source != target){
            move(source, target)
            source.classList.toggle("clicked")
            target.classList.toggle("clicked")
        }
    }
    event.target.classList.toggle("clicked")
}

function clickedPieces(){
    let pieces = document.querySelector("#chessboard").querySelectorAll("div")
    for(let i = 0; i < pieces.length; i++){
        if(pieces[i].classList.contains("clicked")){
            return pieces[i];
        }
    }
    return null;
}

async function postData(url = '', data = {}) {
    const response = await fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
    return response.json();
}

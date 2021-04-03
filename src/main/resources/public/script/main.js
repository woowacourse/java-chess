
window.onload = function(){
    const pieces = document.getElementsByClassName('piece');
    Array.from(pieces).forEach((el)=>{
        el.addEventListener('click', click());
    })

    const scores = document.getElementsByClassName("score");
    Array.from(scores).forEach((el)=>{
        el.addEventListener('change', checkIsEnd());
    })
}

let state = "stay"; // stay, show
let source = "";
let target = "";

function click(){
    return function (event){
        if(state === "stay"){
            show(event.target);
            state = "show";
            source = event.target.id;
            return;
        }

        if(state === "show"){
            clickWhereToMove(event.target);
            state = "stay";
            source = "";
            target = "";
            return;
        }
    }
}

function clickWhereToMove(eventTarget){
    if(checkIsValidTarget(eventTarget)){
        target = eventTarget.id;
        console.log("move");
        console.log("clicked source : "+ source);
        console.log("clicked target : "+ target);

        submitMove(source, target);
    }

    const piece = document.getElementsByClassName("piece");
    for(let i=0; i<piece.length; i++){
        piece[i].classList.remove("moveAble");
        piece[i].style.backgroundColor = "";
    }
}

function submitMove(src, tar){
    const form = document.createElement("form");

    form.setAttribute("charset", "UTF-8");
    form.setAttribute("method", "Post");
    form.setAttribute("action", "/move");

    const sourceField = document.createElement("input");
    sourceField.setAttribute("type", "hidden");
    sourceField.setAttribute("name", "source");
    sourceField.setAttribute("value", src);
    form.appendChild(sourceField);

    const targetField = document.createElement("input");
    targetField.setAttribute("type", "hidden");
    targetField.setAttribute("name", "target");
    targetField.setAttribute("value", tar);
    form.appendChild(targetField);

    document.body.appendChild(form);
    form.submit();
}

function checkIsValidTarget(target){
    return target.classList.contains("moveAble");
}

function checkIsEnd(){
    return function (event) {
        console.log(event.target.text);
    }
}

function show(target) {
    const requestQuery = "source="+target.id;

    $.ajax({
        url: "/show",
        type: "POST",
        data: requestQuery,
        success: function (result) {
            if (result !== null && result !== "[]") {
                const positions = result.slice(1, -1).split(", ");
                positions.forEach((el) => {
                    const piece = document.getElementById(el);
                    piece.classList.add("moveAble");
                    piece.style.backgroundColor = "skyblue";
                });
            }
        },
        error: function () {
            alert("에러 발생");
        }
    })
}


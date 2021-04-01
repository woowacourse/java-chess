export const changeTurn = (turn) => {
    resetTurn();
    changeColor(turn);
}

const resetTurn = () =>{
    const turns = document.getElementsByClassName("turn");
    Array.from(turns).forEach(function(element) {
        element.remove();
    });
}
    

const changeColor = (turn) => {
    const $turnSpace = document.getElementById("turn");
    let image = document.createElement("img");
    image.setAttribute("alt", "turn-color");
    image.setAttribute("class", "turn");
    image.src = "src/"+turn +".jpg";
    $turnSpace.appendChild(image);
}
function choose(value) {
    if (document.getElementById('source').value !== '') {
        chooseDestination(value);
    } else {
        chooseSource(value);
    }
}

// function setPieces() {
//     var pieces = new Map();
//     pieces.set("k", "king_white");
//     pieces.set("K", "king_black");
//     pieces.set("q", "queen_white");
//     pieces.set("Q", "queen_black");
//     pieces.set("b", "bishop_white");
//     pieces.set("B", "bishop_black");
//     pieces.set("n", "knight_white");
//     pieces.set("N", "knight_black");
//     pieces.set("r", "rook_white");
//     pieces.set("R", "rook_black");
//     pieces.set("p", "pawn_white");
//     pieces.set("P", "pawn_black");
//
//     Array.from(document.getElementsByClassName('tile')).forEach(e => {
//         if(e.innerText) {
//             e.innerHTML = "<img src='/img/"+pieces.get(e.innerText)+".png' width='60px'>";
//         }
//     })
// }

function chooseSource(sourceValue) {
    document.getElementById('source').value = sourceValue;
    document.getElementById(sourceValue).classList.add('source');
}

function chooseDestination(destinationValue) {
    var redpoint = document.getElementsByClassName('destination').item(0);
    if (redpoint) {
        redpoint.classList.remove('destination');
    }
    document.getElementById('destination').value = destinationValue;
    document.getElementById(destinationValue).classList.add('destination');
}
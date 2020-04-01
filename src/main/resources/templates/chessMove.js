var start;
var end;

function allowDrop(ev) {
    // 고유의 동작 제거
    ev.preventDefault();
}

function drag(ev) {
    ev.dataTransfer.setData("text", ev.target.id);
}

function drop(ev) {
    ev.preventDefault();
    var data = ev.dataTransfer.getData("text");
    var des = ev.target.id;
    console.log(data + " to " + des.toString());
}

// function dragstart_handler(event) {
//     // 데이터 전달 객체에 대상 요소의 id를 추가합니다.
//     event.dataTransfer.setData("text/plain", event.target.id);
//     scope = event.target.id;
// }
//
// function drop_handler(event) {
//     event.preventDefault();
//     console.log("되는거 ?")
//     // 대상의 id를 가져와 대상 DOM에 움직인 요소를 추가합니다.
//     var data = event.dataTransfer.getData("text/plain");
//     event.target.appendChild(document.getElementById(data));
// }
//
// function gamesetup() {
//     $('.gamecell').attr('chess', 'null');
//     $('#' + main.variables.pieces[gamepiece].position).html(main.variables.pieces[gamepiece].img);
//     for (gamepiece in main.variables.pieces) {
//         $('#' + main.variables.pieces[gamepiece].position).html(main.variables.pieces[gamepiece].img);
//         $('#' + main.variables.pieces[gamepiece].position).attr('chess', gamepiece);
//     }
// }

$(document).ready(function () {
    $('.gamecell').attr({
        "draggable": "true",
        "ondragstart": "drag(event)",
        "ondrop": "drop(event)",
        "ondragover": "allowDrop(event)"
    });
});


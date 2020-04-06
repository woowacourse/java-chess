
const cache = {}
cache["P"] = "pawn_black"
cache["p"] = "pawn_white"
cache["R"] = "rook_black"
cache["r"] = "rook_white"
cache["N"] = "knight_black"
cache["n"] = "knight_white"
cache["B"] = "bishop_black"
cache["b"] = "bishop_white"
cache["Q"] = "queen_black"
cache["q"] = "queen_white"
cache["K"] = "king_black"
cache["k"] = "king_white"
const rows = ["1", "2", "3", "4", "5", "6", "7", "8"]
const columns = ["a", "b", "c", "d", "e", "f", "g", "h"]
initialSet()

function initialSet() {
    $.ajax({
        url: "/info",
        type: "get",
        success : function(data) {
           caching(data)
        }
    })
}

function createImage(piece) {
    if (piece) {
        return `<img style="width:40px" src="./pieces/${cache[piece]}.png" alt="img">`
    } else {
        return ""
    }
}

function commend() {
    let input = document.getElementById("commends").value;
    if (input == "move") {
        move()
    } else if (input == "status") {
        status()
    } else {
        alert("commend를 입력하세요.")
    }
}

function move() {
    let input = document.getElementById("input-commend").value;
    $.ajax( {
        url: "/move",
        type: "get",
        data: {"move": input},
        success : function(data){
            caching(data)
            $("#input-commend").val("");
        },
    })

}

function status() {
    $.ajax( {
        url: "/status",
        type: "get",
        success : function(data){
            data = JSON.parse(data)
            $(`#${data["team"]}`).html(`<h2>${data["status"]}</h2>`)
        },
    })
}

function caching(data) {
    data = JSON.parse(data)
    for (key in data) {
        value = $(`#${key}`).text()
        $(`#${key}`).html(createImage(data[key]))
    }
}

function isFinished() {
    $.ajax( {
        url: "/isFinished",
        type: "get",
        success : function(data){
            console.log(data)
        }
    })
}

$("#commends").change(function() {
    let input = document.getElementById("commends").value;
    if (input == "status") {
        $("#input-commend").attr("readonly", true)
    } else {
        $("#input-commend").attr("readonly", false)
    }
})
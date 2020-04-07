
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
create()

function create() {
    for (column in columns) {
        for (row in rows) {
            value = $(`#${columns[column]}${rows[row]}`).text()
            if (value != "." && value != "") {
                $(`#${columns[column]}${rows[row]}`).html(createImage(value))
            }
            if (value == ".") {
                $(`#${columns[column]}${rows[row]}`).text("")
            }

        }
    }
}

function createImage(piece) {
    if (piece) {
        return `<img style="width:40px" src="./pieces/${cache[piece]}.png" alt="img">`
    } else {
        return ""
    }
}

function caching(data) {
    data = JSON.parse(data)
    for (key in data) {
        value = $(`#${key}`).text()
        $(`#${key}`).html(createImage(data[key]))
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
    let from = document.getElementById("from").value;
    let to = document.getElementById("to").value;
    $.ajax({
        url: "/move",
        type: "post",
        data: {"from": from, "to": to},
        success : function(data){
            window.location.href = "/chess"
        },
    })

}

function status() {
    $.ajax( {
        url: "/status",
        type: "post",
        success : function(data){
            data = JSON.parse(data)
            $(`#${data["team"]}`).html(`<h2>${data["status"]}</h2>`)
        },
    })
}

$("#commends").change(function() {
    let input = document.getElementById("commends").value;
    if (input == "status") {
        $("#from").attr("disabled", true)
        $("#to").attr("disabled", true)
    } else {
        $("#from").attr("disabled", false)
        $("#to").attr("disabled", false)
    }
})
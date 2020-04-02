$(document).ready(function(){
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
    let value = ""
    let position = ""
    for (row in rows) {
        for (column in columns) {
            position = columns[column] + rows[row]
            value = $(`#${position}`).text()
            &(`#${position}`).remove()
            $(`#${position}`).append(createImage(value))
        }
    }
    function createImage(piece) {
        if (piece) {
            return `<img style="width:40px" src="./pieces/${cache[piece]}.png" alt="img">`
        } else {
            return ""
        }
    }
});
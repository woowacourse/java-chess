let pieces = {
    black_p: "&#9823;",
    black_r: "&#9820;",
    black_n: "&#9822;",
    black_b: "&#9821;",
    black_k: "&#9818;",
    black_q: "&#9819;",
    white_p: "&#9817;",
    white_r: "&#9814;",
    white_n: "&#9816;",
    white_b: "&#9815;",
    white_k: "&#9812;",
    white_q: "&#9813;",
    _blank: ""
}

export function pieceImage(piece) {
    for (name in pieces) {
        if (name === piece) {
            return pieces[name].replace(/\s/, '')
        }
    }
    return "x";
}
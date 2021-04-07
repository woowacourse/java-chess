import {Piece} from "./Piece.js";

export class PieceFactory {
    static #pieces = {
        'r': '♜',
        'n': '♞',
        'b': '♝',
        'k': '♚',
        'q': '♛',
        'p': '♟'
    }

    static getPieceByNotation(notation, location) {
        if (PieceFactory.#isUpperCase(notation)) {
            return new Piece('black', PieceFactory.#pieces[notation.toLowerCase()], location)
        }

        return new Piece('white', PieceFactory.#pieces[notation], location)
    }

    static #isUpperCase(value) {
        return value.toUpperCase() === value
    }

    static getPiecesByPieceDtos({pieceDtos}) {
        let pieces = []

        for (let pieceDto of pieceDtos) {
            pieces.push(this.getPieceByNotation(pieceDto.notation, pieceDto.position))
        }

        return pieces
    }

}
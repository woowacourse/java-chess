import {Piece} from "./Piece";

export class PieceFactory {
    static #pieces = {
        'r' : '♜',
        'n' : '♞',
        'b' : '♝',
        'k' : '♛',
        'q' : '♚',
        'p' : '♟'
    }

    getPieceByNotation(notation, location) {
        if (PieceFactory.#isUpperCase(notation)) {
            return new Piece('black', PieceFactory.#pieces[notation.toLowerCase()], location)
        }

        return new Piece('white', PieceFactory.#pieces[notation], location)
    }

    static #isUpperCase(value) {
        return value.toUpperCase() === value
    }

    getInitializedPieces() {
        let pieces = [];

        pieces.push(this.getPieceByNotation('R', 'a8'))
        pieces.push(this.getPieceByNotation('N', 'b8'))
        pieces.push(this.getPieceByNotation('B', 'c8'))
        pieces.push(this.getPieceByNotation('Q', 'd8'))
        pieces.push(this.getPieceByNotation('K', 'e8'))
        pieces.push(this.getPieceByNotation('b', 'f8'))
        pieces.push(this.getPieceByNotation('N', 'g8'))
        pieces.push(this.getPieceByNotation('R', 'h8'))

        pieces.push(this.getPieceByNotation('P', 'a7'))
        pieces.push(this.getPieceByNotation('P', 'b7'))
        pieces.push(this.getPieceByNotation('P', 'c7'))
        pieces.push(this.getPieceByNotation('P', 'd7'))
        pieces.push(this.getPieceByNotation('P', 'e7'))
        pieces.push(this.getPieceByNotation('P', 'f7'))
        pieces.push(this.getPieceByNotation('P', 'g7'))
        pieces.push(this.getPieceByNotation('P', 'h7'))
    }

}
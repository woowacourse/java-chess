export class Piece {

    #color
    #notation
    #location

    constructor(color, notation, location) {
        this.#color = color
        this.#notation = notation
        this.#location = location
    }

    get color() {
        return this.#color;
    }

    get notation() {
        return this.#notation;
    }

    get location() {
        return this.#location
    }

}
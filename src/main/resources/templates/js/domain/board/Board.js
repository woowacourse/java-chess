import {Line} from "./Line.js";

export class Board {
    #dom
    #lines = []

    constructor() {
        for (let row = 8; row >= 1; row--) {
            this.#lines.push(new Line(row))
        }

        this.#dom = this.#createDom()
    }

    #createDom() {
        let wrapper = document
            .createRange()
            .createContextualFragment(
                `<div class="chess_board"></div>`
            ).firstElementChild

        for (let line of this.#lines) {
            wrapper.appendChild(line.dom)
        }

        return wrapper
    }

    setPieces(pieces) {
        for (let piece of pieces) {
            console.log(pieces)
            let dom = document.getElementById(piece.location)
            console.log(dom)
            dom.classList.add(piece.color)
            dom.textContent = piece.notation
        }
    }

    get dom() {
        return this.#dom
    }

}
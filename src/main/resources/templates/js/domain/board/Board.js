import {Line} from "./Line.js";

export class Board {
    #dom
    #lines = []

    constructor() {
        this.#dom = this.#createDom()
        this.#init();
    }

    #init() {
        for (let row = 8; row >= 1; row--) {
            this.#lines.push(new Line(row))
        }

        for (let line of this.#lines) {
            this.#dom.appendChild(line.dom)
        }
    }

    #createDom() {
        return document
            .createRange()
            .createContextualFragment(
                `<div id="chess_board"></div>`
            ).firstElementChild
    }

    setPieces(pieces) {
        this.#clear()
        this.#init()

        for (let piece of pieces) {
            let dom = document.getElementById(piece.location)

            dom.classList.add(piece.color)
            dom.textContent = piece.notation
        }
    }

    #clear() {
        while (this.#dom.hasChildNodes()) {
            this.#dom.removeChild(this.#dom.firstChild)
        }
        this.#lines = []
    }

    get dom() {
        return this.#dom
    }

}
import {Cell} from "./Cell.js";

export class Line {
    static #COL_NAMES = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h']

    #dom
    #cells = []

    constructor(row) {
        for (let column of Line.#COL_NAMES) {
            this.#cells.push(new Cell(column + row))
        }

        this.#dom = this.#createDom()
    }

    #createDom() {
        let wrapper = document
            .createRange()
            .createContextualFragment(
                `<div class="line"></div>`
            ).firstElementChild

        for (let cell of this.#cells) {
            wrapper.appendChild(cell.dom)
        }

        return wrapper
    }

    get dom() {
        return this.#dom
    }

}
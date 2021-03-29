export class Cell {
    #id
    #dom

    constructor(id) {
        this.#id = id
        this.#dom = Cell.#createDom(id)
    }

    static #createDom(id) {
        return document
            .createRange()
            .createContextualFragment(`
                <div id='${id}' class="cell"></div>
                `.trim()
            ).firstElementChild
    }

    get dom() {
        return this.#dom
    }

}
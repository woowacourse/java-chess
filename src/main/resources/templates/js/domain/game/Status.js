export class Status {

    #dom

    constructor() {
        this.#dom = this.#createDom()
    }

    setStatus(result) {
        this.#dom.innerHTML = `
            <p>black score : ${result.blackScore}</p><br>
            <p>white score : ${result.whiteScore}</p>
        `
    }


    #createDom() {
        return document.createRange()
            .createContextualFragment(
                `<div id='status'></div>`
            ).firstElementChild
    }

    get dom() {
        return this.#dom
    }

}
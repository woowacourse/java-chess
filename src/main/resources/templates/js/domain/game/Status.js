export class Status {

    #dom
    #blackScore
    #whiteScore

    constructor() {
        this.#dom = this.#createDom()
    }

    setStatus(result) {
        this.#dom.innerHTML = `
            <p>black score : ${result.blackScore}</p><br>
            <p>white score : ${result.whiteScore}</p>
        `
    }

    getWinnerByScore() {
        if (this.#blackScore > this.#whiteScore) return 'black'
        if (this.#blackScore < this.#whiteScore) return 'white'
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
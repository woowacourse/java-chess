export class InformationBar {

    #dom

    constructor() {
        this.#dom = this.#createDom()
    }

    #createDom() {
        return document.createRange()
            .createContextualFragment(
                `<div class='turn'></div>`
            ).firstElementChild
    }

    setTurn(turn) {
        this.#dom.innerHTML = `<strong>${turn}의 차례 입니다.</strong>`
    }

    setWinner(winner) {
        this.#dom.innerHTML = `<strong>${winner}가 승리했습니다.</strong>`
    }

    get dom() {
        return this.#dom
    }
}
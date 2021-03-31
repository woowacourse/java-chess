export class SelectedPiece {
    static #instance

    #selected

    constructor() {
        if (SelectedPiece.#instance) {
            return SelectedPiece.#instance
        }

        SelectedPiece.#instance = this
    }

    setSelected(selected) {
        this.#selected = selected
    }

    isContainsSelected() {
        return this.#selected !== undefined
    }

    setSelectedToUndefined() {
        this.#selected = undefined
    }

    get Id() {
        return this.#selected.id
    }

}
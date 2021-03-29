import {Board} from "../board/Board.js";
import {Turn} from "./Turn.js";

export class ChessGame {

    #dom
    #board = new Board()
    #turn = new Turn()

    constructor() {
        this.#dom = this.#createDom()
        this.#init()
    }

    #init() {
        document.body.appendChild(this.#dom)
        this.#dom.appendChild(this.#board.dom)
        this.#dom.appendChild(this.#turn.dom)
    }


    setTurn(turn) {
        this.#turn.setTurn(turn)
    }

    setPieces(pieces) {
        this.#board.setPieces(pieces)
    }

    #createDom() {
        return document.createRange()
            .createContextualFragment(
                `<div class="chess_game"></div>`
            ).firstElementChild
    }

}
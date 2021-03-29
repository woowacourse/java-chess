import {Board} from "../board/Board.js";
import {Turn} from "./Turn.js";
import {Status} from "./Status.js";

export class ChessGame {

    #dom
    #board = new Board()
    #turn = new Turn()
    #status = new Status()

    constructor() {
        this.#dom = this.#createDom()
        this.#init()
    }

    #init() {
        document.body.appendChild(this.#dom)
        this.#dom.appendChild(this.#board.dom)
        this.#dom.appendChild(this.#turn.dom)
        this.#dom.appendChild(this.#status.dom)
    }


    setTurn(turn) {
        this.#turn.setTurn(turn)
    }

    setPieces(pieces) {
        this.#board.setPieces(pieces)
    }

    setStatus(result) {
        this.#status.setStatus(result)
    }

    #createDom() {
        return document.createRange()
            .createContextualFragment(
                `<div class="chess_game"></div>`
            ).firstElementChild
    }

}
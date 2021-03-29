import {Board} from "../board/Board.js";
import {InformationBar} from "./InformationBar.js";
import {Status} from "./Status.js";

export class ChessGame {

    #dom
    #board = new Board()
    #informationBar = new InformationBar()
    #status = new Status()

    constructor() {
        this.#dom = this.#createDom()
        this.#init()
    }

    #init() {
        document.body.appendChild(this.#dom)
        this.#dom.appendChild(this.#board.dom)
        this.#dom.appendChild(this.#informationBar.dom)
        this.#dom.appendChild(this.#status.dom)
    }


    setTurn(turn) {
        this.#informationBar.setTurn(turn)
    }

    setPieces(pieces) {
        this.#board.setPieces(pieces)
    }

    setStatus(result) {
        this.#status.setStatus(result)
    }

    setWinner(winner) {
        this.#informationBar.setWinner(winner)
    }

    #createDom() {
        return document.createRange()
            .createContextualFragment(
                `<div class="chess_game"></div>`
            ).firstElementChild
    }

}
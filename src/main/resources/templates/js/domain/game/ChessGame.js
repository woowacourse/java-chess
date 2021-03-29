import {Board} from "../board/Board.js";
import {InformationBar} from "./InformationBar.js";
import {Status} from "./Status.js";

export class ChessGame {

    #dom
    #board = new Board()
    #informationBar = new InformationBar()
    #status = new Status()
    #gameId

    constructor() {
        this.#dom = this.#createDom()
        this.#init()
    }

    #init() {
        document.getElementById('wrapper').appendChild(this.#dom)
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

    set gameId(gameId) {
        this.#gameId = gameId
    }

    get gameId() {
        return this.#gameId
    }

    get winnerByScore() {
        return this.#status.getWinnerByScore()
    }

}
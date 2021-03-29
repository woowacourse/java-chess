import {Ajax} from "../util/Ajax.js";
import {PieceFactory} from "../domain/piece/PieceFactory.js";

export class ChessController {

    #chessGame
    #turn
    #selected

    constructor(chessGame) {
        this.#chessGame = chessGame
    }

    run() {
        this.startEventHandler();
        this.moveEventHandler();
    }

    async start() {
        let result
        try {
            result = await new Ajax().patch('start', '')
        } catch (e) {
            alert(e.message)
            return
        }

        let pieces = PieceFactory.getPiecesByPieceDtos(result)

        this.#chessGame.setPieces(pieces)
        this.#chessGame.setTurn(result.turn)
        this.#turn = result.turn
    }



    async #selectPiece(e) {
        if (e.target && [...e.target.classList].includes(this.#turn)) {
            if (this.#selected === e.target) {
                e.target.classList.remove('selected')
                this.#selected = undefined

                return
            }

            if (this.#selected !== undefined) {
                this.#selected.classList.remove('selected')
            }

            this.#selected = e.target
            e.target.classList.add('selected')
            return
        }
    }

    async #move(e) {
        if (this.#selected && e.target && ![...e.target.classList].includes(this.#turn)) {
            let source = this.#selected.id
            let target = e.target.id
            let result

            try {
                result = await new Ajax().patch('move', `
                {
                    "source" : "${source}",
                    "target" : "${target}"
                }`)
            } catch (e) {
                alert(e.message)
                return
            }

            let pieces = PieceFactory.getPiecesByPieceDtos(result)

            this.#chessGame.setPieces(pieces)
            this.#chessGame.setTurn(result.turn)
            this.#turn = result.turn

            this.#selected = undefined
        }
    }

    moveEventHandler() {
        document.getElementById('chess_board')
            .addEventListener('click', async e => {
                await this.#selectPiece(e)
                await this.#move(e)
            })
    }

    startEventHandler() {
        document.getElementById('start_button')
            .addEventListener('click', async e => {
                await this.start()
            })
    }

}
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

    async #start() {
        let result
        try {
            result = await new Ajax().patch('start', '')
        } catch (e) {
            alert(e.message)
            return
        }

        await this.#printGameStatus()
        this.#resultHandler(result)
    }

    async #move(e) {
        if (this.#selected && e.target && ![...e.target.classList].includes(this.#turn)) {
            let source = this.#selected.id
            let target = e.target.id

            let result
            try {
                result = await this.#sendMoveRequest(source, target)
            } catch (e) {
                alert(e.message)
                return
            }

            this.#resultHandler(result)
            await this.#printGameStatus()

            this.#selected = undefined
        }
    }

    #finished(winner) {
        this.#chessGame.setWinner(winner)
    }

    async #printGameStatus() {
        let result = await new Ajax().get('status')

        this.#chessGame.setStatus(result)
    }


    #resultHandler(result) {
        let pieces = PieceFactory.getPiecesByPieceDtos(result)
        this.#chessGame.setPieces(pieces)

        console.log(result)
        if(result.status === 'finished') {
            this.#finished(this.#turn)
            return
        }

        if(result.status === 'running') {
            this.#chessGame.setTurn(result.turn)
            this.#turn = result.turn
        }
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


    async #sendMoveRequest(source, target) {
        return await new Ajax().patch('move', `
                {
                    "source" : "${source}",
                    "target" : "${target}"
                }`)
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
                await this.#start()
            })
    }

}
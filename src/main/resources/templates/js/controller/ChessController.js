import {Ajax} from "../util/Ajax.js";
import {PieceFactory} from "../domain/piece/PieceFactory.js";

export class ChessController {

    #ajax
    #chessGame
    #turn
    #selected

    constructor(chessGame) {
        this.#chessGame = chessGame
        this.#ajax = new Ajax(chessGame)
    }

    run() {
        this.startEventHandler()
        this.moveEventHandler()
        this.loadEventHandler()
    }

    async #start() {
        let gameId = prompt('게임 아이디를 입력하세요')
        this.#chessGame.gameId = gameId

        let result
        try {
            result = await this.#ajax.get('start')
        } catch (e) {
            alert(e.message)
            return
        }

        await this.#printGameStatus()
        this.#resultHandler(result)
    }

    async #load() {
        let gameId = prompt('게임 아이디를 입력하세요')
        this.#chessGame.gameId = gameId

        let result
        try {
            result = await this.#ajax.get('load')
        } catch(e) {
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
        let result = await this.#ajax.get('status')

        this.#chessGame.setStatus(result)
    }


    #resultHandler(result) {
        let pieces = PieceFactory.getPiecesByPieceDtos(result)
        this.#chessGame.setPieces(pieces)

        if(result.status === 'running') {
            this.#chessGame.setTurn(result.turn)
            this.#turn = result.turn
        }

        if(result.status !== 'running') {
            this.#finished(this.#calculateWinner(result))
        }

    }

    #calculateWinner(result) {
        if(result.status === 'blackWin') return "black"
        if(result.status === 'whiteWin') return "white"
        return this.#chessGame.getWinnerByScore
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
        return await this.#ajax.patch('move', `
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

    loadEventHandler() {
        document.getElementById('load_button')
            .addEventListener('click', async e => {
                await this.#load()
            })
    }

}
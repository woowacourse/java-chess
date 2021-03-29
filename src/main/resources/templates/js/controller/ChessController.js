import {Ajax} from "../util/Ajax.js";
import {PieceFactory} from "../domain/piece/PieceFactory.js";

export class ChessController {

    #board


    constructor(board) {
        this.#board = board
    }

    run() {
        this.startEventHandler();
    }

    async start() {
        let result = await new Ajax().patch('start', '')
        let pieceDtos = await result.json()

        console.log(pieceDtos)

        let pieces = PieceFactory.getPiecesByPieceDtos(pieceDtos)
        this.#board.setPieces(pieces)
    }

    move() {

    }

    moveEventHandler() {
        document.getElementById('chess_board')
            .addEventListener('click', e => {

            })
    }

    startEventHandler() {
        document.getElementById('start_button')
            .addEventListener('click', async e => {
                await this.start()
            })
    }

}
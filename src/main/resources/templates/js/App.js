import {Board} from "./domain/board/Board.js";
import {ChessController} from "./controller/ChessController.js";

class App {
    #board = new Board()
    #controller = new ChessController(this.#board)

    run() {
        document.body.appendChild(this.#board.dom)
        this.#controller.run()
    }
}

let app = new App()
app.run()
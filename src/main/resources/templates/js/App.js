import {ChessController} from "./controller/ChessController.js";
import {ChessGame} from "./domain/game/ChessGame.js";

class App {
    #chessGame
    #controller

    run() {
        this.#chessGame = new ChessGame()
        this.#controller = new ChessController(this.#chessGame)

        this.#controller.run()
    }

}

let app = new App()
app.run()
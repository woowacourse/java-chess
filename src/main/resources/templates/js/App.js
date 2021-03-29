import {ChessController} from "./controller/ChessController.js";
import {ChessGame} from "./domain/game/ChessGame.js";

class App {
    #controller = new ChessController(new ChessGame())

    run() {
        this.#controller.run()
    }

}

let app = new App()
app.run()
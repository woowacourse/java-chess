import {Board} from "./board/Board.js";

class App {
    #board = new Board()

    run() {
        document.body.appendChild(this.#board.dom)
    }
}

let app = new App()
app.run()
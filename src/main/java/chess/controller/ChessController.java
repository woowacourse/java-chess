package chess.controller;

import chess.Command;
import chess.view.InputView;

public class ChessController {
    public void run() {
        InputView.startGame();
        Command command = new Command(InputView.inputCommand());
        while (!command.isEnd()) {
            //체스판을 출력한다.
            command = new Command(InputView.inputCommand());
        }
    }
}

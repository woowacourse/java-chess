package chess.controller;

import chess.controller.command.Command;
import chess.controller.command.StartCommand;
import chess.domain.ChessGame;
import chess.view.InputView;

public class ChessController {
    private Command command;

    public ChessController() {
        this.command = new StartCommand(this);
    }

    public void run() {
        ChessGame chessGame = new ChessGame();
        InputView.printStartChess();
        boolean keepPlaying = catchException(chessGame);
        while (keepPlaying) {
            keepPlaying = catchException(chessGame);
        }
    }

    private boolean catchException(ChessGame chessGame) {
        try {
            return command.operate(chessGame);
        } catch (IllegalArgumentException errorMassage) {
            System.out.println(errorMassage);
        }
        return true;
    }

    public void setCommend(Command command) {
        this.command = command;
    }
}

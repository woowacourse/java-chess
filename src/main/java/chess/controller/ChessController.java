package chess.controller;

import chess.controller.command.Command;
import chess.controller.command.StartCommand;
import chess.domain.ChessGame;
import chess.view.InputView;

public class ChessController {
    private final InputView inputView;
    private Command command;

    public ChessController() {
        this.inputView = new InputView();
        this.command = new StartCommand(this);
    }

    public void run() {
        ChessGame chessGame = new ChessGame();
        inputView.printStartChess();
        boolean keepPlaying = catchException(chessGame);
        while (keepPlaying) {
            keepPlaying = catchException(chessGame);
        }
    }

    private boolean catchException(ChessGame chessGame) {
        try {
            return command.operate(chessGame);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
        return true;
    }

    public void setCommend(Command command) {
        this.command = command;
    }
}

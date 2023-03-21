package chess.controller.command;

import chess.controller.ChessController;
import chess.domain.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;

public abstract class Command {
    protected final InputView inputView;
    protected final OutputView outputView;
    protected final ChessController chessController;

    public Command(ChessController chessController) {
        this.chessController = chessController;
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    abstract public boolean operate(ChessGame chessGame);
}

package chess.controller.commend;

import chess.controller.ChessController;
import chess.domain.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;

public abstract class Commend {
    protected final InputView inputView;
    protected final OutputView outputView;
    protected final ChessController chessController;

    public Commend(ChessController chessController) {
        this.chessController = chessController;
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    abstract public boolean operate(ChessGame chessGame);
}

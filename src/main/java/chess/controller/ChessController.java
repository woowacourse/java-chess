package chess.controller;

import chess.controller.commend.Commend;
import chess.controller.commend.StartCommend;
import chess.domain.ChessGame;
import chess.view.InputView;

public class ChessController {
    private final InputView inputView;
    private Commend commend;

    public ChessController() {
        this.inputView = new InputView();
        this.commend = new StartCommend(this);
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
            return commend.operate(chessGame);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
        return true;
    }

    public void setCommend(Commend commend) {
        this.commend = commend;
    }
}

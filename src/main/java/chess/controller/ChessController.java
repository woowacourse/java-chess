package chess.controller;

import chess.domain.ChessGame;
import chess.view.InputView;
import chess.view.commend.Commend;
import chess.view.commend.StartCommend;

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
        boolean keepPlaying = commend.operate(chessGame);
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

package chess.controller;

import chess.controller.commend.Commend;
import chess.controller.commend.StartCommend;
import chess.domain.ChessGame;
import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.board.Square;
import chess.renderer.FileInputRenderer;
import chess.renderer.RankInputRenderer;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {
    private final InputView inputView;
    private final OutputView outputView;
    private Commend commend;

    public ChessController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
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

    private Square makeSquare(String command) {
        File file = FileInputRenderer.renderString(String.valueOf(command.charAt(0)));
        Rank rank = RankInputRenderer.renderString(String.valueOf(command.charAt(1)));

        return new Square(file, rank);
    }
}

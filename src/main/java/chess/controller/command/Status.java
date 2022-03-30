package chess.controller.command;

import chess.domain.ChessGame;
import chess.view.OutputView;

public class Status implements Command {
    private final ChessGame chessGame;

    public Status(final ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    @Override
    public void execute(final String rawInputCommand, final OutputView outputView) {
        outputView.printStatus(chessGame.statusOfWhite(), chessGame.statusOfBlack());
    }
}

package chess.command;

import chess.domain.ChessGame;
import chess.view.OutputView;

public class Status implements Command {

    private static final Status INSTANCE = new Status();

    private Status() {
    }

    public static Status getInstance() {
        return INSTANCE;
    }

    @Override
    public void execute(final ChessGame chessGame) {
        try {
            OutputView.printStatus(chessGame.calculateScore());
        } catch (final IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
        }
    }
}

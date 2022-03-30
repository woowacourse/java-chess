package chess.command;

import chess.domain.ChessGame;
import chess.domain.GameStatus;
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
            checkBeforePlaying(chessGame);
            OutputView.printStatus(chessGame.calculateScore());
        } catch (final IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
        }
    }

    private void checkBeforePlaying(final ChessGame chessGame) {
        if (chessGame.isSameStatus(GameStatus.READY)) {
            throw new IllegalArgumentException("게임이 시작되지 않았습니다.");
        }
    }
}

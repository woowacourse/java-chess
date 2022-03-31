package chess.controller.command;

import chess.controller.result.Result;
import chess.controller.result.StatusResult;
import chess.domain.ChessGame;
import chess.domain.Score;

public class Status implements Command {

    private static final Status INSTANCE = new Status();

    private Status() {
    }

    public static Status getInstance() {
        return INSTANCE;
    }

    @Override
    public Result execute(final ChessGame chessGame) {
        final Score score = chessGame.calculateScore();
        return new StatusResult(score);
    }
}

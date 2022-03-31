package chess.controller.command;

import chess.controller.result.EndResult;
import chess.controller.result.Result;
import chess.domain.ChessGame;
import chess.domain.Score;

public class End implements Command {

    private static final End INSTANCE = new End();

    private End() {
    }

    public static End getInstance() {
        return INSTANCE;
    }

    @Override
    public Result execute(final ChessGame chessGame) {
        final Score score = chessGame.end();
        return new EndResult(score);
    }
}

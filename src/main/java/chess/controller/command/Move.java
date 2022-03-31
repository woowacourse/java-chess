package chess.controller.command;

import chess.controller.result.Result;
import chess.domain.ChessGame;
import chess.domain.position.Position;

public class Move implements Command {

    private final Position from;
    private final Position to;

    public Move(final String from, final String to) {
        this.from = Position.from(from);
        this.to = Position.from(to);
    }

    @Override
    public Result execute(final ChessGame chessGame) {
        final Result result = chessGame.move(from, to);
        return result;
    }
}

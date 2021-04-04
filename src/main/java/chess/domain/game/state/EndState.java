package chess.domain.game.state;

import chess.domain.CommandAsString;
import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.position.Position;
import chess.domain.result.EndResult;
import chess.domain.result.PathResult;
import chess.domain.result.Result;
import chess.domain.result.StatusResult;

public final class EndState extends PlayingState {

    public EndState(final Board board) {
        super(board);
    }

    @Override
    public GameState execute(final CommandAsString command) {
        return new EndState(currentBoard());
    }

    @Override
    public Result turnResult() {
        return new EndResult(currentBoard());
    }

    @Override
    public Result statusResult() {
        return new StatusResult(currentBoard());
    }

    @Override
    public Result pathResult(Position source) {
        return new PathResult(currentBoard(), source, Color.NOTHING);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public String currentState() {
        return "finished";
    }
}

package chess.domain.game.state;

import chess.domain.board.Board;
import chess.domain.board.position.Position;

public abstract class Finished extends Started {
    public Finished(Board board) {
        super(board);
    }

    @Override
    public State move(Position source, Position target) {
        throw new IllegalStateException();
    }

    @Override
    public State end() {
        throw new IllegalStateException();
    }

    @Override
    public boolean isNotFinished() {
        return false;
    }
}

package chess.domain.game.state;

import chess.domain.board.Board;
import chess.domain.board.position.Position;

public class End extends Started {
    public End(Board board) {
        super(board);
    }

    @Override
    public State start() {
        throw new IllegalStateException();
    }

    @Override
    public void moveIfValidColor(Position source, Position target) {
        throw new IllegalStateException();
    }

    @Override
    public State end() {
        throw new IllegalStateException();
    }

    @Override
    public String finishReason() {
        throw new IllegalStateException();
    }

    @Override
    public boolean isNotEnd() {
        return false;
    }
}

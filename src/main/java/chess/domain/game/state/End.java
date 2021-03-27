package chess.domain.game.state;

import chess.domain.board.Board;
import chess.domain.board.position.Position;

public class End extends Started {
    public End(Board board) {
        super(board);
    }

    @Override
    public State start() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void moveIfValidColor(Position source, Position target) {
        throw new UnsupportedOperationException();
    }

    @Override
    public State end() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String finishReason() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isNotEnd() {
        return false;
    }
}

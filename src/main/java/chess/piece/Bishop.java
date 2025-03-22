package chess.piece;

import chess.Position;

import java.util.List;

public class Bishop extends ChessPiece {

    public Bishop(final Position position) {
        super(position);
    }

    @Override
    protected List<Position> calculateCanMovePositions() {
        return List.of();
    }
}

package chess.piece;

import chess.Position;

import java.util.List;

public class Queen extends ChessPiece {

    public Queen(final Position position) {
        super(position);
    }

    @Override
    protected List<Position> calculateCanMovePositions() {
        return List.of();
    }
}

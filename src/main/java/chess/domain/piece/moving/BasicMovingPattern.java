package chess.domain.piece.moving;

import chess.domain.position.Position;

import java.util.List;

public class BasicMovingPattern implements MovingPattern {

    private final List<Movement> movements;

    public BasicMovingPattern(List<Movement> movements) {
        this.movements = movements;
    }

    @Override
    public boolean canMove(Position source, Position target, boolean hasTargetPiece) {
        int columnDifference = source.calculateColumnDifferenceTo(target);
        int rowDifference = source.calculateRowDifferenceTo(target);

        return movements.contains(Movement.find(columnDifference, rowDifference));
    }
}

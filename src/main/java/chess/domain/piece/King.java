package chess.domain.piece;

import static chess.domain.piece.PieceType.KING;

import chess.domain.position.Position;
import java.util.List;

public class King extends AbstractPiece {

    public King(PieceColor color) {
        super(color);
    }

    @Override
    protected List<Movement> chooseMovements() {
        return List.of(
                Movement.EAST_1STEP,
                Movement.WEST_1STEP,
                Movement.SOUTH_1STEP,
                Movement.NORTH_1STEP,
                Movement.NORTH_EAST_1STEP,
                Movement.NORTH_WEST_1STEP,
                Movement.SOUTH_EAST_1STEP,
                Movement.SOUTH_WEST_1STEP
        );
    }

    @Override
    protected String baseSignature() {
        return KING.getSignature();
    }

    @Override
    public boolean isCorrectMovement(Position source, Position target, boolean hasTargetPiece) {
        int columnDifference = source.calculateColumnDifferenceTo(target);
        int rowDifference = source.calculateRowDifferenceTo(target);

        return movements.contains(Movement.find(columnDifference, rowDifference));
    }

    @Override
    public boolean canJumpOverPieces() {
        return false;
    }

    @Override
    public double score() {
        return KING.getScore();
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isKing() {
        return true;
    }
}

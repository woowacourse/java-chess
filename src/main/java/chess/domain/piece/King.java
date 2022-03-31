package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Movement;
import chess.domain.position.Position;
import java.util.List;

public class King extends Piece {

    public King(Color color) {
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
        return "k";
    }

    @Override
    public boolean isCorrectMovement(Position source, Position target, Piece targetPiece) {
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
        return 0;
    }

    @Override
    public boolean isBlank() {
        return false;
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

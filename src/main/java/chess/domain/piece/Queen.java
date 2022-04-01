package chess.domain.piece;

import static chess.domain.piece.PieceType.QUEEN;

import chess.domain.Color;
import chess.domain.Movement;
import chess.domain.position.Position;
import java.util.List;

public class Queen extends AbstractPiece {

    public Queen(Color color) {
        super(color);
    }

    @Override
    protected List<Movement> chooseMovements() {
        return List.of(
                Movement.EAST_UNLIMITED,
                Movement.EAST_1STEP,
                Movement.WEST_UNLIMITED,
                Movement.WEST_1STEP,
                Movement.SOUTH_UNLIMITED,
                Movement.SOUTH_1STEP,
                Movement.SOUTH_2STEP,
                Movement.NORTH_UNLIMITED,
                Movement.NORTH_1STEP,
                Movement.NORTH_2STEP,
                Movement.SOUTH_EAST_UNLIMITED,
                Movement.SOUTH_EAST_1STEP,
                Movement.SOUTH_WEST_UNLIMITED,
                Movement.SOUTH_WEST_1STEP,
                Movement.NORTH_EAST_UNLIMITED,
                Movement.NORTH_EAST_1STEP,
                Movement.NORTH_WEST_UNLIMITED,
                Movement.NORTH_WEST_1STEP
        );
    }

    @Override
    protected String baseSignature() {
        return QUEEN.getSignature();
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
        return QUEEN.getScore();
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isKing() {
        return false;
    }
}

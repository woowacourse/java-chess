package chess.domain.piece;

import static chess.domain.piece.PieceType.ROOK;

import chess.domain.Movement;
import chess.domain.position.Position;
import java.util.List;

public class Rook extends AbstractPiece {

    public Rook(PieceColor color) {
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
                Movement.NORTH_2STEP
        );
    }

    @Override
    protected String baseSignature() {
        return ROOK.getSignature();
    }

    @Override
    public boolean isCorrectMovement(Position source, Position target, boolean hasTargetPiece) {
        int columnDifference = source.calculateColumnDifferenceTo(target);
        int rowDifference = Math.abs(source.calculateRowDifferenceTo(target));

        return movements.contains(Movement.find(columnDifference, rowDifference));
    }

    @Override
    public boolean canJumpOverPieces() {
        return false;
    }

    @Override
    public double score() {
        return ROOK.getScore();
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

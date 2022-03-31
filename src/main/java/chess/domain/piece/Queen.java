package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Movement;
import chess.domain.position.Position;
import java.util.List;

public class Queen extends Piece {

    public Queen(Color color) {
        super(color);
    }

    @Override
    protected List<Movement> chooseMovements() {
        return List.of(
                Movement.EAST_UNLIMITED,
                Movement.WEST_UNLIMITED,
                Movement.SOUTH_UNLIMITED,
                Movement.NORTH_UNLIMITED,
                Movement.SOUTH_EAST_UNLIMITED,
                Movement.SOUTH_WEST_UNLIMITED,
                Movement.NORTH_EAST_UNLIMITED,
                Movement.NORTH_WEST_UNLIMITED
        );
    }

    @Override
    protected String baseSignature() {
        return "q";
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
        return 9;
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
        return false;
    }
}

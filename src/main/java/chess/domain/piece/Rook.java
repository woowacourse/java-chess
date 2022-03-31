package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Movement;
import chess.domain.position.Position;
import java.util.List;

public class Rook extends Piece {

    public Rook(Color color) {
        super(color);
    }

    @Override
    protected List<Movement> chooseMovements() {
        return List.of(
                Movement.EAST_UNLIMITED,
                Movement.WEST_UNLIMITED,
                Movement.SOUTH_UNLIMITED,
                Movement.NORTH_UNLIMITED
        );
    }

    @Override
    protected String baseSignature() {
        return "r";
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
        return 5;
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

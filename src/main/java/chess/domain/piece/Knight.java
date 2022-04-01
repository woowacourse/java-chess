package chess.domain.piece;

import static chess.domain.piece.PieceType.KNIGHT;

import chess.domain.Color;
import chess.domain.Movement;
import chess.domain.position.Position;
import java.util.List;

public class Knight extends Piece {

    public Knight(Color color) {
        super(color);
    }

    @Override
    protected List<Movement> chooseMovements() {
        return List.of(
                Movement.KNIGHT_EAST_SOUTH,
                Movement.KNIGHT_EAST_NORTH,
                Movement.KNIGHT_WEST_SOUTH,
                Movement.KNIGHT_WEST_NORTH,
                Movement.KNIGHT_SOUTH_EAST,
                Movement.KNIGHT_SOUTH_WEST,
                Movement.KNIGHT_NORTH_EAST,
                Movement.KNIGHT_NORTH_WEST
        );
    }

    @Override
    protected String baseSignature() {
        return KNIGHT.getSignature();
    }

    @Override
    public boolean isCorrectMovement(Position source, Position target, boolean hasTargetPiece) {
        int columnDifference = source.calculateColumnDifferenceTo(target);
        int rowDifference = source.calculateRowDifferenceTo(target);

        return movements.contains(Movement.find(columnDifference, rowDifference));
    }

    @Override
    public boolean canJumpOverPieces() {
        return true;
    }

    @Override
    public double score() {
        return KNIGHT.getScore();
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

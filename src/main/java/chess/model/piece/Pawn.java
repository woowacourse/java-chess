package chess.model.piece;

import chess.model.square.Direction;
import java.util.List;

public class Pawn extends PawnMovingPiece {

    private static final double POINT = 1;

    public Pawn(Color color) {
        super(color);
    }

    public Pawn(Color color, int squareId) {
        super(0, color, squareId);
    }

    public Pawn(int id, Color color, int squareId) {
        super(id, color, squareId);
    }

    @Override
    public String name() {
        return PieceType.p.name();
    }

    @Override
    public boolean isNotEmpty() {
        return true;
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public List<Direction> getDirection() {
        if (color.isBlack()) {
            return List.of(Direction.SOUTH);
        }
        return List.of(Direction.NORTH);
    }

    @Override
    public double getPoint() {
        return POINT;
    }
}
